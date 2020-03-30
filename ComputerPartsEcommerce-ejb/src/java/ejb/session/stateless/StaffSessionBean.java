package ejb.session.stateless;

import entity.ComputerSet;
import entity.CustomerOrder;
import entity.Staff;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ComputerSetNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.StaffNotFoundException;
import util.security.CryptographicHelper;

@Stateless
public class StaffSessionBean implements StaffSessionBeanLocal {

    @EJB
    private ComputerSetSessionBeanLocal computerSetSessionBeanLocal;

    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;
    
    @Override
    public Long createNewStaff(Staff newStaff) {
        em.persist(newStaff);
        em.flush();

        return newStaff.getUserId();
    }
    
    @Override
    public Staff retrieveStaffById(Long staffId, Boolean loadDeliveries, Boolean loadAssignedComputerSets) throws StaffNotFoundException {
        Staff staff = em.find(Staff.class, staffId);

        if (staff != null) {
            if (loadDeliveries) {
                staff.getDeliveries().size();
            }
            if (loadAssignedComputerSets) {
                staff.getAssignedComputerSets().size();
            }
            return staff;
        } else {
            throw new StaffNotFoundException("Staff ID " + staffId + " does not exist!");
        }
    }
    
    @Override
    public Staff retrieveStaffByEmail(String email, Boolean loadDeliveries, Boolean loadAssignedComputerSets) throws StaffNotFoundException {
        Query query = em.createQuery("SELECT s FROM User s WHERE s.email = :inEmail");
        query.setParameter("inEmail", email);

        try {
            Staff staff = (Staff) query.getSingleResult();
            if (loadDeliveries) {
                staff.getDeliveries().size();
            }
            if (loadAssignedComputerSets) {
                staff.getAssignedComputerSets().size();
            }
            return staff;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new StaffNotFoundException("Staff Email " + email + " does not exist!");
        }
    }
    
    
    @Override
    public Staff staffLogin(String email, String password) throws InvalidLoginCredentialException {
        try {
            Staff staff = retrieveStaffByEmail(email, true, true);            
            String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + staff.getSalt()));
            
            if(staff.getPassword().equals(passwordHash)){
                // staffEntity.getSaleTransactionEntities().size();                
                return staff;
            } else {
                throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
            }
        } catch(StaffNotFoundException ex) {
            throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
        }
    }
    
    @Override
    public void updateStaff(Staff staff, Long customerOrderId, Long computerSetId) throws StaffNotFoundException, CustomerOrderNotFoundException, ComputerSetNotFoundException {
        Staff updatedStaff = em.merge(staff);
        
        if (customerOrderId != null) {
            CustomerOrder customerOrder = customerOrderSessionBeanLocal.retrieveCustomerOrderById(customerOrderId, false);
            if(!updatedStaff.getDeliveries().contains(customerOrder)) {
                // association
                updatedStaff.getDeliveries().add(customerOrder);
                customerOrder.getDeliveryAssignedTo().getDeliveries().remove(customerOrder);
                customerOrder.setDeliveryAssignedTo(updatedStaff);
            } else {
                // do disassociation
                updatedStaff.getDeliveries().remove(customerOrder);
                customerOrder.setDeliveryAssignedTo(null);
            }
        }
        
        if (computerSetId != null) {
            ComputerSet computerSet = computerSetSessionBeanLocal.retrieveComputerSetById(computerSetId, false);
            if(!updatedStaff.getAssignedComputerSets().contains(computerSet)) {
                // association
                updatedStaff.getAssignedComputerSets().add(computerSet);
                computerSet.getAssemblyAssignedTo().getAssignedComputerSets().remove(computerSet);
                computerSet.setAssemblyAssignedTo(updatedStaff);
            } else {
                // do disassociation
                updatedStaff.getAssignedComputerSets().remove(computerSet);
                computerSet.setAssemblyAssignedTo(null);
            }
        }
    }
    
    @Override
    public void deleteStaff(Long staffId) throws StaffNotFoundException, CustomerOrderNotFoundException, ComputerSetNotFoundException { 
        Staff staff = retrieveStaffById(staffId, true, true);
        /*
        // remove associations with staff
        for (CustomerOrder customerOrder : staff.getDeliveries()) {
            updateStaff(staff, customerOrder.getCustomerOrderId(), null);
        }
        for (ComputerSet computerSet : staff.getAssignedComputerSets()) {
            updateStaff(staff, null, computerSet.getProductId());
        }*/
        staff.setIsDisabled(true);
    }

    @Override
    public List<Staff> retrieveAllStaffs() {
        Query query = em.createQuery("SELECT s FROM Staff s WHERE s.isDisabled = false ");
        
        return query.getResultList();
    }
    
}
