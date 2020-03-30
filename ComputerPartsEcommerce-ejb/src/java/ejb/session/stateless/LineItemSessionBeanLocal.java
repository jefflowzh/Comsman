package ejb.session.stateless;

import entity.LineItem;
import javax.ejb.Local;
import util.exception.LineItemNotFoundException;

@Local
public interface LineItemSessionBeanLocal {

    public Long createNewLineItem(LineItem newLineItem);

    public LineItem retrieveLineItemById(Long lineItemId) throws LineItemNotFoundException;
  
}
