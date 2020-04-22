package ejb.session.stateless;

import entity.Peripheral;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PeripheralSessionBean implements PeripheralSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewPeripheral(Peripheral newPeripheral) {
        em.persist(newPeripheral);
        em.flush();
        
        return newPeripheral.getProductId();
    }
    
    @Override
    public List<Peripheral> retrieveAllPeripherals() {
        Query query = em.createQuery("SELECT p FROM Peripheral p");
        return query.getResultList();
    }
    
}
