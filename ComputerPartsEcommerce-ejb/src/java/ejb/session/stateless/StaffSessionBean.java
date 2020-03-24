package ejb.session.stateless;

import entity.Staff;
import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.InvalidLoginCredentialException;
import util.exception.StaffNotFoundException;

@Stateless
public class StaffSessionBean implements StaffSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewStaff(Staff newStaff) {
        em.persist(newStaff);
        em.flush();

        return newStaff.getUserId();
    }

    @Override
    public User retrieveStaffByEmail(String email) throws StaffNotFoundException {
        Query query = em.createQuery("SELECT s FROM User s WHERE s.email = :inEmail");
        query.setParameter("inEmail", email);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new StaffNotFoundException("Staff Email " + email + " does not exist!");
        }
    }
    
    @Override
    public User staffLogin(String email, String password) throws InvalidLoginCredentialException {
        try {
            User user = retrieveStaffByEmail(email);            
            // String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + staffEntity.getSalt()));
            
            if(user.getPassword().equals(password)){
                // staffEntity.getSaleTransactionEntities().size();                
                return user;
            } else {
                throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
            }
        } catch(StaffNotFoundException ex) {
            throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
        }
    }

}
