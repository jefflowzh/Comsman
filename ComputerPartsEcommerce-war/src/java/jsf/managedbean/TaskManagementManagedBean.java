package jsf.managedbean;

import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.ComputerSet;
import entity.CustomerOrder;
import entity.Staff;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import util.exception.CouponNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.StaffNotFoundException;

@Named(value = "taskManagementManagedBean")
@ViewScoped
public class TaskManagementManagedBean implements Serializable {

    @EJB(name = "StaffSessionBeanLocal")
    private StaffSessionBeanLocal staffSessionBeanLocal;

    @EJB(name = "ComputerSetSessionBeanLocal")
    private ComputerSetSessionBeanLocal computerSetSessionBeanLocal;

    @EJB
    private LineItemSessionBeanLocal lineItemSessionBeanLocal;

    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    private List<CustomerOrder> tasks;
    private List<CustomerOrder> filteredTasks;
    private List<ComputerSet> computerSets;
    private List<Staff> assignableStaff;
    
    private CustomerOrder orderToAssignDelivery;
    
    private Long selectedOrderId;
    private Long temporaryHoldingStaffId;
    private Long negativeOne;
    
    private HashMap<ComputerSet, Long> computerSetsWithStaff;

    public TaskManagementManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        // those voided / fulfilled will not be shown
        tasks = customerOrderSessionBeanLocal.retrieveAllTasks();
        negativeOne = new Long(-1);
    }
    
    public void loadComputerSetsOfOrder(ActionEvent event) {
        selectedOrderId = (Long) event.getComponent().getAttributes().get("selectedOrderId");
        setComputerSets(computerSetSessionBeanLocal.retrieveComputerSetsByOrderId(getSelectedOrderId(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        computerSetsWithStaff = new HashMap<>();
        for (ComputerSet computerSet : computerSets) {
            computerSetsWithStaff.put(computerSet, null);
            System.out.println("******* " + computerSet.getAssemblyAssignedTo());
        }
        setAssignableStaff(staffSessionBeanLocal.retrieveAllStaffs());
    }
    
    public void doMatchComputerSetWithStaffId(ComputerSet computerSet) {
        System.out.println("******* " + temporaryHoldingStaffId);
        if (temporaryHoldingStaffId == null || (computerSet.getAssemblyAssignedTo() != null && temporaryHoldingStaffId.equals(computerSet.getAssemblyAssignedTo().getUserId()))) {
        } else if (temporaryHoldingStaffId.equals(Long.valueOf(-1))) {
            computerSetsWithStaff.put(computerSet, computerSet.getAssemblyAssignedTo().getUserId());
        } else {
            computerSetsWithStaff.put(computerSet, temporaryHoldingStaffId);
        }
        temporaryHoldingStaffId = null;
        System.out.println("******** " + temporaryHoldingStaffId);
    }
    
    public void matchComputerSetsWithStaffId(ActionEvent event) { // Sends to backend for matching between computer sets and staff
        try {
            for (ComputerSet computerSet : computerSets) {
                computerSetSessionBeanLocal.updateComputerSet(computerSet, computerSetsWithStaff.get(computerSet));
            }
            temporaryHoldingStaffId = null;
            customerOrderSessionBeanLocal.updateOrderStatus(selectedOrderId);
            tasks = customerOrderSessionBeanLocal.retrieveAllTasks();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Computer sets for order " + selectedOrderId + " assigned!", null));
        } catch (StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff does not exist!", null));
        } catch (CustomerOrderNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer order does not exist!", null));
        }
    }
    
    public void doAssignDelivery(ActionEvent event) {
        selectedOrderId = (Long) event.getComponent().getAttributes().get("selectedOrderId");
        setAssignableStaff(staffSessionBeanLocal.retrieveAllStaffs());
        try {
            orderToAssignDelivery = customerOrderSessionBeanLocal.retrieveCustomerOrderById(selectedOrderId, Boolean.FALSE);
        } catch (CustomerOrderNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer order does not exist!", null));
        }
    }

    public void assignDelivery(ActionEvent event) {
        System.out.println("**********" + temporaryHoldingStaffId);
        if (temporaryHoldingStaffId == null || (orderToAssignDelivery.getDeliveryAssignedTo()!= null && temporaryHoldingStaffId.equals(orderToAssignDelivery.getDeliveryAssignedTo().getUserId()))) {
            System.out.println("**********null entered");
            temporaryHoldingStaffId = null; // ensure that no change is made to the deliveryAssignedTo attribute
        } else if (temporaryHoldingStaffId.equals(Long.valueOf(-1))) {
            System.out.println("**********-1 entered");
            temporaryHoldingStaffId = orderToAssignDelivery.getDeliveryAssignedTo().getUserId();
        }
        try {
            customerOrderSessionBeanLocal.updateCustomerOrder(orderToAssignDelivery, null, temporaryHoldingStaffId, null, null);
            temporaryHoldingStaffId = null;
            tasks = customerOrderSessionBeanLocal.retrieveAllTasks();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Order " + orderToAssignDelivery.getCustomerOrderId() + " delivery assigned!", null));
        } catch (CouponNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coupon does not exist!", null));
        } catch (CustomerNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer does not exist!", null));
        } catch (StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff does not exist!", null));
        }
    }
    
    public CustomerOrderSessionBeanLocal getCustomerOrderSessionBeanLocal() {
        return customerOrderSessionBeanLocal;
    }

    public void setCustomerOrderSessionBeanLocal(CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal) {
        this.customerOrderSessionBeanLocal = customerOrderSessionBeanLocal;
    }

    public LineItemSessionBeanLocal getLineItemSessionBeanLocal() {
        return lineItemSessionBeanLocal;
    }

    public void setLineItemSessionBeanLocal(LineItemSessionBeanLocal lineItemSessionBeanLocal) {
        this.lineItemSessionBeanLocal = lineItemSessionBeanLocal;
    }

    public List<CustomerOrder> getTasks() {
        return tasks;
    }

    public void setTasks(List<CustomerOrder> tasks) {
        this.tasks = tasks;
    }

    public List<CustomerOrder> getFilteredTasks() {
        return filteredTasks;
    }

    public void setFilteredTasks(List<CustomerOrder> filteredTasks) {
        this.filteredTasks = filteredTasks;
    }

    public List<ComputerSet> getComputerSets() {
        return computerSets;
    }

    public void setComputerSets(List<ComputerSet> computerSets) {
        this.computerSets = computerSets;
    }

    public List<Staff> getAssignableStaff() {
        return assignableStaff;
    }

    public void setAssignableStaff(List<Staff> assignableStaff) {
        this.assignableStaff = assignableStaff;
    }
    
    public CustomerOrder getOrderToAssignDelivery() {
        return orderToAssignDelivery;
    }

    public void setOrderToAssignDelivery(CustomerOrder orderToAssignDelivery) {
        this.orderToAssignDelivery = orderToAssignDelivery;
    }
    
    public Long getSelectedOrderId() {
        return selectedOrderId;
    }

    public void setSelectedOrderId(Long selectedOrderId) {
        this.selectedOrderId = selectedOrderId;
    }

    public Long getTemporaryHoldingStaffId() {
        return temporaryHoldingStaffId;
    }

    public void setTemporaryHoldingStaffId(Long temporaryHoldingStaffId) {
        this.temporaryHoldingStaffId = temporaryHoldingStaffId;
    }

    public Long getNegativeOne() {
        return negativeOne;
    }

    public void setNegativeOne(Long negativeOne) {
        this.negativeOne = negativeOne;
    }
    
    public HashMap<ComputerSet, Long> getComputerSetsWithStaff() {
        return computerSetsWithStaff;
    }

    public void setComputerSetsWithStaff(HashMap<ComputerSet, Long> computerSetsWithStaff) {
        this.computerSetsWithStaff = computerSetsWithStaff;
    }
}
