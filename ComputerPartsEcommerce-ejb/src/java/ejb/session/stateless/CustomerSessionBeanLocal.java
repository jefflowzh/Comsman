package ejb.session.stateless;

import entity.Customer;
import entity.LineItem;
import java.util.List;
import javax.ejb.Local;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.InvalidLoginCredentialException;

@Local
public interface CustomerSessionBeanLocal {

    public Long createNewCustomer(Customer newCustomer);

    public Customer retrieveCustomerById(Long customerId, Boolean loadCart, Boolean loadOrders) throws CustomerNotFoundException;

    public Customer retrieveCustomerByEmail(String email, Boolean loadCart, Boolean loadOrders) throws CustomerNotFoundException;

    public List<Customer> retrieveAllCustomers(Boolean loadCart, Boolean loadOrders);

    public void updateCustomer(Customer customer, Long customerOrderId, LineItem lineItem) throws CustomerOrderNotFoundException;

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException;

    public Customer customerLogin(String email, String password) throws InvalidLoginCredentialException;

}
