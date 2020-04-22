package ejb.session.stateless;

import entity.CustomerOrder;
import entity.LineItem;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.CustomerOrderNotFoundException;
import util.exception.LineItemNotFoundException;

@Stateless
public class LineItemSessionBean implements LineItemSessionBeanLocal {

    @EJB(name = "CustomerOrderSessionBeanLocal")
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewLineItem(LineItem newLineItem) {       
        em.persist(newLineItem);
        em.flush();
        
        return newLineItem.getLineItemId();
    }
    
    @Override
    public LineItem retrieveLineItemById(Long lineItemId) throws LineItemNotFoundException {
        LineItem lineItem = em.find(LineItem.class, lineItemId);

        if (lineItem != null) {
            return lineItem;
        } else {
            throw new LineItemNotFoundException("LineItem ID " + lineItemId + " does not exist!");
        }
    }
    
    @Override
    public void deleteLineItemById(Long lineItemId, Long orderId) throws LineItemNotFoundException, CustomerOrderNotFoundException {
        LineItem lineItemToDelete = em.find(LineItem.class, lineItemId);
        
        CustomerOrder customerOrder = customerOrderSessionBeanLocal.retrieveCustomerOrderById(orderId, true);
        customerOrder.getLineItems().remove(lineItemToDelete);
        
        em.remove(lineItemToDelete);
    }

}
