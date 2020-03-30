package ejb.session.stateless;

import entity.ComputerPart;
import entity.ComputerSet;
import entity.Staff;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.ComputerPartNotFoundException;
import util.exception.ComputerSetNotFoundException;
import util.exception.StaffNotFoundException;

@Stateless
public class ComputerSetSessionBean implements ComputerSetSessionBeanLocal {

    @EJB
    private ComputerPartSessionBeanLocal computerPartSessionBeanLocal;

    @EJB
    private StaffSessionBeanLocal staffSessionBeanLocal;

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;
    
    @Override
    // create set first, assign staff seperately later
    public Long createNewComputerSet(ComputerSet newComputerSet) {
        em.persist(newComputerSet);
        em.flush();
        
        return newComputerSet.getProductId();
    }
    

    @Override
    public ComputerSet retrieveComputerSetById(Long computerSetId, Boolean loadParts) throws ComputerSetNotFoundException {
        ComputerSet computerSet = em.find(ComputerSet.class, computerSetId);

        if (computerSet != null) {
            if (loadParts) {
                computerSet.getComputerParts().size();
            }
            return computerSet;
        } else {
            throw new ComputerSetNotFoundException("Computer Set ID " + computerSetId + " does not exist!");
        }
    }

    @Override
    // Put staffId as non-null if there is need for association/disassociation/replacement
    // Put computerPartId as non-null to add a computer part
    public void updateComputerSet(ComputerSet computerSet, Long staffId, Long computerPartId) throws ComputerPartNotFoundException, StaffNotFoundException {

        ComputerSet updatedComputerSet = em.merge(computerSet);
        
        // When need to change staff and computer assignments
        if (staffId != null) {
            Staff staff = staffSessionBeanLocal.retrieveStaffById(staffId, false, true);
            if(updatedComputerSet.getAssemblyAssignedTo() == null) {
                // do association
                updatedComputerSet.setAssemblyAssignedTo(staff);
                staff.getAssignedComputerSets().add(updatedComputerSet);
            } else if (updatedComputerSet.getAssemblyAssignedTo() == staff){
                // do disassociation
                updatedComputerSet.setAssemblyAssignedTo(null);
                staff.getAssignedComputerSets().remove(updatedComputerSet);
            } else {
                // do replacement
                updatedComputerSet.getAssemblyAssignedTo().getAssignedComputerSets().remove(updatedComputerSet);
                updatedComputerSet.setAssemblyAssignedTo(staff);
                staff.getAssignedComputerSets().add(updatedComputerSet);
            }
        }
        
//        if (computerPartId != null) {
//            ComputerPart computerPart = computerPartSessionBeanLocal.retrieveComputerPartById(computerPartId);
//            if (!updatedComputerSet.getComputerParts().contains(computerPart)) {
//                updatedComputerSet.getComputerParts().add(computerPart); 
//            } else {
//                updatedComputerSet.getComputerParts().remove(computerPart); 
//            }
//        }
    }
    
    @Override
    public void deleteComputerSet(Long computerSetId) throws ComputerSetNotFoundException { 
        ComputerSet computerSet = retrieveComputerSetById(computerSetId, false);     
        computerSet.setIsDisabled(true);
    }


}
