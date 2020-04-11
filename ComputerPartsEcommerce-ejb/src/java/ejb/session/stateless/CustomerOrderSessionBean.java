package ejb.session.stateless;

import entity.Coupon;
import entity.Customer;
import entity.CustomerOrder;
import entity.LineItem;
import entity.Staff;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.OrderStatusEnum;
import util.exception.CouponNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.StaffNotFoundException;

@Stateless
public class CustomerOrderSessionBean implements CustomerOrderSessionBeanLocal {

    @EJB
    private CouponSessionBeanLocal couponSessionBeanLocal;

    @EJB
    private StaffSessionBeanLocal staffSessionBeanLocal;

    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewCustomerOrder(CustomerOrder newCustomerOrder, Long customerId) throws CustomerNotFoundException {
        System.out.println("ENTER HERE");
        Customer customer = customerSessionBeanLocal.retrieveCustomerById(customerId, false, true);
        
        newCustomerOrder.setCustomer(customer);
        customer.getOrders().add(newCustomerOrder);
        
        System.out.println("INSIDE CREATENEWCUSTOMERORDER");
        
        // boolean therescomputerset = false
        // for (lineItem: newcustomerorder.getlineItem) -> lineItem.getComputerSet = not null -> unassigned, setCSflag = true break;
        
        // if (setCSflag == false) -> status -> completed
        
        
        em.persist(newCustomerOrder);
        em.flush();
        
        System.out.println("*******************************" + newCustomerOrder.getCustomerOrderId());
        
        return newCustomerOrder.getCustomerOrderId();
    }
    
    @Override
    public CustomerOrder retrieveCustomerOrderById(Long customerOrderId, Boolean loadLineItems) throws CustomerOrderNotFoundException {
        CustomerOrder customerOrder = em.find(CustomerOrder.class, customerOrderId);

        if (customerOrder != null) {
            if (loadLineItems) {
                customerOrder.getLineItems().size();
            }
            return customerOrder;
        } else {
            throw new CustomerOrderNotFoundException("Customer Order ID " + customerOrderId + " does not exist!");
        }
    }

    @Override
    public void updateCustomerOrder(CustomerOrder customerOrder, Long customerId, Long staffId, Long couponId, LineItem lineItem) throws CustomerNotFoundException, StaffNotFoundException, CouponNotFoundException {

        CustomerOrder updatedCustomerOrder = em.merge(customerOrder);
        
        // When adding or removing the order to/from a customer
        if (customerId != null) {
            Customer customer = customerSessionBeanLocal.retrieveCustomerById(customerId, false, true);
            if(updatedCustomerOrder.getCustomer() == null) {
                // association
                updatedCustomerOrder.setCustomer(customer);
                customer.getOrders().add(updatedCustomerOrder);
            } else if (updatedCustomerOrder.getCustomer() == customer){
                // do disassociation
                updatedCustomerOrder.setCustomer(null);
                customer.getOrders().remove(updatedCustomerOrder);
            }
        }
        
        // When need to change order and staff delivery assignments
        if (staffId != null) {
            Staff staff = staffSessionBeanLocal.retrieveStaffById(staffId, true, false);
            if(updatedCustomerOrder.getDeliveryAssignedTo() == null) {
                // association
                updatedCustomerOrder.setDeliveryAssignedTo(staff);
                staff.getDeliveries().add(updatedCustomerOrder);
            } else if (updatedCustomerOrder.getDeliveryAssignedTo() == staff){
                // do disassociation
                updatedCustomerOrder.setDeliveryAssignedTo(null);
                staff.getDeliveries().remove(updatedCustomerOrder);
            } else {
                // do replacement
                updatedCustomerOrder.getDeliveryAssignedTo().getDeliveries().remove(updatedCustomerOrder);
                updatedCustomerOrder.setDeliveryAssignedTo(staff);
                staff.getDeliveries().add(updatedCustomerOrder);
            }
        }
        
        if (couponId != null) {
            Coupon coupon = couponSessionBeanLocal.retrieveCouponById(couponId);
            updatedCustomerOrder.setCoupon(coupon);
        }
        
        if (lineItem != null) {
            updatedCustomerOrder.getLineItems().add(lineItem);
        }
    }
    
    @Override
    // For deleting orders on the staff interface view
    public void deleteCustomerOrder(Long customerOrderId) throws CustomerOrderNotFoundException { 
        CustomerOrder customerOrder = retrieveCustomerOrderById(customerOrderId, false);
        
        if (customerOrder.getDeliveryAssignedTo() != null) {
            customerOrder.getDeliveryAssignedTo().getDeliveries().remove(customerOrder);
            customerOrder.setDeliveryAssignedTo(null);
        }
        if (customerOrder.getCustomer() != null) {
            customerOrder.getCustomer().getOrders().remove(customerOrder);
            customerOrder.setCustomer(null);
        }
        em.remove(customerOrder);
    }
    
    @Override
    public List<CustomerOrder> retrieveAllOrders() {
        Query query = em.createQuery("SELECT o FROM CustomerOrder o");
        
        return query.getResultList();
    }
    
    @Override
    public List<CustomerOrder> retrieveAllTasks() {
        Query query = em.createQuery("SELECT o FROM CustomerOrder o WHERE o.orderStatus <> :inStatus and o.orderStatus <> :inVoid");
        query.setParameter("inStatus", OrderStatusEnum.DELIVERED);
        query.setParameter("inVoid", OrderStatusEnum.VOIDED);
        
        return query.getResultList();
    }

}