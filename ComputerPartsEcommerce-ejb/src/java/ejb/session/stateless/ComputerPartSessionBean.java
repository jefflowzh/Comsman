package ejb.session.stateless;

import entity.ComputerPart;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ComputerPartSessionBean implements ComputerPartSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewComputerPart(ComputerPart newComputerPart) {
        em.persist(newComputerPart);
        em.flush();
        
        return newComputerPart.getProductId();
    }

}
