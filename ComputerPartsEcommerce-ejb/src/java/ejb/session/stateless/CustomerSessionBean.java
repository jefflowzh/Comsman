package ejb.session.stateless;

import entity.Customer;
import entity.CustomerOrder;
import entity.LineItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CustomerEmailExistException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;
import util.security.CryptographicHelper;

@Stateless
public class CustomerSessionBean implements CustomerSessionBeanLocal {

    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewCustomer(Customer newCustomer) throws CustomerEmailExistException, UnknownPersistenceException {
        try {
            em.persist(newCustomer);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException")) {
                if (ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) {
                    throw new CustomerEmailExistException("This email is already taken!");
                } else {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            } else {
                throw new UnknownPersistenceException(ex.getMessage());
            }
        }

        return newCustomer.getUserId();
    }

    @Override
    public Customer retrieveCustomerById(Long customerId, Boolean loadCart, Boolean loadOrders) throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, customerId);

        if (customer != null) {
            if (loadCart) {
                customer.getCart().size();
            }
            if (loadOrders) {
                customer.getOrders().size();
            }
            return customer;
        } else {
            throw new CustomerNotFoundException("Customer ID " + customerId + " does not exist!");
        }
    }

    @Override
    public Customer retrieveCustomerByEmail(String email, Boolean loadCart, Boolean loadOrders) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.email = :userInput");
        query.setParameter("userInput", email);

        try {
            Customer customer = (Customer) query.getSingleResult();
            if (loadCart) {
                customer.getCart().size();
            }
            if (loadOrders) {
                customer.getOrders().size();
            }
            return customer;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CustomerNotFoundException("Email " + email + " does not exist!");
        }
    }

    @Override
    public List<Customer> retrieveAllCustomers(Boolean loadCart, Boolean loadOrders) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE C.isDisabled = false");
        List<Customer> customers = query.getResultList();

        if (loadCart) {
            for (Customer customer : customers) {
                customer.getCart().size();
            }
        }

        if (loadOrders) {
            for (Customer customer : customers) {
                customer.getOrders().size();
            }
        }

        return customers;
    }

//    @Override
//    public void updateCustomer(Customer customer, Long customerOrderId, LineItem lineItem) throws CustomerNotFoundException, CustomerOrderNotFoundException {
//        if (customer == null || customer.getUserId() == null) {
//            throw new CustomerNotFoundException("Customer does not exist!");
//        }
//        Customer updatedCustomer = em.merge(customer);
//
//        // When adding or removing an order to/from a customer
//        if (customerOrderId != null) {
//            CustomerOrder customerOrder = customerOrderSessionBeanLocal.retrieveCustomerOrderById(customerOrderId, false);
//            if (!updatedCustomer.getOrders().contains(customerOrder)) {
//                // association
//                updatedCustomer.getOrders().add(customerOrder);
//                customerOrder.setCustomer(updatedCustomer);
//            } else {
//                // do disassociation
//                updatedCustomer.getOrders().remove(customerOrder);
//                customerOrder.setCustomer(null);
//            }
//        }
//
//        if (lineItem != null) {
//            updatedCustomer.getCart().add(lineItem);
//        }
//    }

    @Override
    public void updateCustomer(Customer customer, Boolean updateDetails, Boolean updatePassword, Boolean updateCart, Boolean updateCurrComputerBuild) throws CustomerNotFoundException {
        
        if (customer == null || customer.getUserId() == null) {
            throw new CustomerNotFoundException("Customer to be updated not provided");
        }
        
        Customer customerToUpdate = retrieveCustomerById(customer.getUserId(), true, true);
 
        // Update customer details other than password
        if (updateDetails) {
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setCardNumber(customer.getCardNumber());
        customerToUpdate.setCcv(customer.getCcv());
        customerToUpdate.setContactNumber(customer.getContactNumber());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        }
        
        // update password
        if (updatePassword) {
            customerToUpdate.setPassword(customer.getPassword());
        }
        
        // Update cart
        if (updateCart) {
            customerToUpdate.setCart(customer.getCart());
        }
        
        // Update customer current computer build
        if (updateCurrComputerBuild) {
            customerToUpdate.setCurrComputerBuild(customer.getCurrComputerBuild());
        }
       
    }
    
    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = retrieveCustomerById(customerId, false, false);
        customer.setIsDisabled(true);
    }

    @Override
    public Customer customerLogin(String email, String password) throws InvalidLoginCredentialException {
        try {
            Customer customer = retrieveCustomerByEmail(email, true, true);
            String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + customer.getSalt()));
            if (customer.getPassword().equals(passwordHash)) {
                return customer;
            } else {
                throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
            }
        } catch (CustomerNotFoundException ex) {
            throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
        }
    }

}
