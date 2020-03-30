package ejb.session.stateless;

import entity.LineItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.LineItemNotFoundException;

@Stateless
public class LineItemSessionBean implements LineItemSessionBeanLocal {

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

}