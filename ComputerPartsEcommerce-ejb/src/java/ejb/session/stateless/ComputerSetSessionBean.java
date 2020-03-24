package ejb.session.stateless;

import entity.ComputerSet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ComputerSetSessionBean implements ComputerSetSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewComputerSet(ComputerSet newComputerSet) {
        em.persist(newComputerSet);
        em.flush();
        
        return newComputerSet.getProductId();
    }

}
