package jsf.managedbean;

import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import entity.CustomerOrder;
import entity.Staff;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.CouponNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.StaffNotFoundException;

@Named(value = "deliveryTasksManagedBean")
@ViewScoped
public class DeliveryTasksManagedBean implements Serializable {

    @EJB(name = "CustomerOrderSessionBeanLocal")
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    private List<CustomerOrder> orders;
    private List<CustomerOrder> filteredOrders;
    
    public DeliveryTasksManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        Staff staff = (Staff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStaffEntity");
        setOrders(customerOrderSessionBeanLocal.retrieveCustomerOrdersByDeliveryStaff(staff.getUserId(), Boolean.FALSE));
    }
    
    public void markAsDelivered(ActionEvent event) {
        Long selectedOrderId = (Long) event.getComponent().getAttributes().get("selectedOrderId");
        try {
            System.out.println("**********pre find: " + selectedOrderId);
            CustomerOrder customerOrder = customerOrderSessionBeanLocal.retrieveCustomerOrderById(selectedOrderId, Boolean.FALSE);
            System.out.println("**********post find");
            customerOrder.setDelivered(Boolean.TRUE);
            customerOrderSessionBeanLocal.updateCustomerOrder(customerOrder, null, null, null, null);
            postConstruct();
            customerOrderSessionBeanLocal.updateOrderStatus(selectedOrderId);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Order " + selectedOrderId + " delivered!", null));
        } catch (CouponNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coupon does not exist!", null));
        } catch (CustomerNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer does not exist!", null));
        } catch (CustomerOrderNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer order does not exist!", null));
        } catch (StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff does not exist!", null));
        }
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    public List<CustomerOrder> getFilteredOrders() {
        return filteredOrders;
    }

    public void setFilteredOrders(List<CustomerOrder> filteredOrders) {
        this.filteredOrders = filteredOrders;
    }
}
