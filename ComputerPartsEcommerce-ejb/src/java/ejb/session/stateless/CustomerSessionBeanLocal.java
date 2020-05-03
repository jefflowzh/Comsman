package ejb.session.stateless;

import entity.Customer;
import entity.LineItem;
import java.util.List;
import javax.ejb.Local;
import util.exception.CustomerEmailExistException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

@Local
public interface CustomerSessionBeanLocal {

    public Long createNewCustomer(Customer newCustomer) throws CustomerEmailExistException, UnknownPersistenceException;

    public Customer retrieveCustomerById(Long customerId, Boolean loadCart, Boolean loadOrders) throws CustomerNotFoundException;

    public Customer retrieveCustomerByEmail(String email, Boolean loadCart, Boolean loadOrders) throws CustomerNotFoundException;

    public List<Customer> retrieveAllCustomers(Boolean loadCart, Boolean loadOrders);

    public void updateCustomer(Customer customer, Boolean updateDetails, Boolean updatePassword, Boolean updateCart, Boolean updateCurrComputerBuild) throws CustomerNotFoundException;

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException;

    public Customer customerLogin(String email, String password) throws InvalidLoginCredentialException;

    public void updateCustomerMerge(Customer updatedCustomer);

}
