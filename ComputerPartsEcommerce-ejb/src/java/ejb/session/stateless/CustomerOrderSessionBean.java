package ejb.session.stateless;

import entity.CustomerOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerOrderSessionBean implements CustomerOrderSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewCustomerOrder(CustomerOrder newCustomerOrder) {
        em.persist(newCustomerOrder);
        em.flush();
        
        return newCustomerOrder.getCustomerOrderId();
    }

}