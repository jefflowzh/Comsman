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
        Query query = em.createQuery("SELECT p FROM Peripheral p WHERE p.isDisabled = false");
        return query.getResultList();
    }

    @Override
    public Peripheral retrievePeripheralById(Long productId) {
        Query query = em.createQuery("SELECT p FROM Peripheral p WHERE p.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (Peripheral) query.getSingleResult();
    }

    @Override
    public void updatePeripheral(Peripheral peripheral) {
        em.merge(peripheral);
    }

    @Override
    public void deletePeripheral(Long productId) {
        Peripheral peripheralToDelete = retrievePeripheralById(productId);
        peripheralToDelete.setIsDisabled(Boolean.TRUE);
    }
    
}
