package ejb.session.stateless;

import entity.LineItem;
import javax.ejb.Local;
import util.exception.CustomerOrderNotFoundException;
import util.exception.LineItemNotFoundException;

@Local
public interface LineItemSessionBeanLocal {

    public LineItem createNewLineItem(LineItem newLineItem);

    public LineItem retrieveLineItemById(Long lineItemId) throws LineItemNotFoundException;
  
    public void deleteLineItemById(Long lineItemId, Long orderId) throws LineItemNotFoundException, CustomerOrderNotFoundException;
}
