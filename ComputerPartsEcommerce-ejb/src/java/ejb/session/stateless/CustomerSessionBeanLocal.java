package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Local;
import util.exception.CustomerNotFoundException;

@Local
public interface CustomerSessionBeanLocal {

    public Long createNewCustomer(Customer newCustomer);

    public Customer retrieveCustomerByEmail(String email) throws CustomerNotFoundException;
    
    public List<Customer> retrieveAllCustomers();

    public void updateCustomer(Customer customer);
  
}
