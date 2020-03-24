package ejb.session.stateless;

import entity.Staff;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}