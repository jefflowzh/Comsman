package ejb.session.stateless;

import entity.CustomerOrder;
import entity.LineItem;
import java.util.List;
import javax.ejb.Local;
import util.exception.CouponNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.StaffNotFoundException;

@Local
public interface CustomerOrderSessionBeanLocal {

    public Long createNewCustomerOrder(CustomerOrder newCustomerOrder, Long customerId) throws CustomerNotFoundException;

    public CustomerOrder retrieveCustomerOrderById(Long customerOrderId, Boolean loadLineItems) throws CustomerOrderNotFoundException;

    public void updateCustomerOrder(CustomerOrder customerOrder, Long customerId, Long staffId, Long couponId, LineItem lineItem) throws CustomerNotFoundException, StaffNotFoundException, CouponNotFoundException;

    public void deleteCustomerOrder(Long customerOrderId) throws CustomerOrderNotFoundException;
    
    public List<CustomerOrder> retrieveAllOrders();
    
    public List<CustomerOrder> retrieveAllTasks();
 
}
