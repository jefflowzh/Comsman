package jsf.managedbean;

import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.StaffSessionBeanLocal;
import entity.ComputerSet;
import entity.CustomerOrder;
import entity.Staff;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;

@Named(value = "taskManagementManagedBean")
@RequestScoped
public class TaskManagementManagedBean {

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
    private Long[] assignedStaff;
    
    private Long selectedOrderId;

    public TaskManagementManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        // those voided / delivered will not be shown
        tasks = customerOrderSessionBeanLocal.retrieveAllTasks();
    }
    
    public void loadComputerSetsOfOrder(ActionEvent event) {
        selectedOrderId = (Long) event.getComponent().getAttributes().get("selectedOrderId");
        setComputerSets(computerSetSessionBeanLocal.retrieveComputerSetsByOrderId(getSelectedOrderId(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        setAssignableStaff(staffSessionBeanLocal.retrieveAllStaffs());
        setAssignedStaff(new Long[computerSets.size()]);
        System.out.println("Loaded");
        System.out.println(assignedStaff);
    }
    
    public void confirmAssignment(ActionEvent event) {
        if (assignedStaff == null) {
            System.out.println("IS NULL");
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

    public Long[] getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(Long[] assignedStaff) {
        this.assignedStaff = assignedStaff;
    }
    
    public Long getSelectedOrderId() {
        return selectedOrderId;
    }

    public void setSelectedOrderId(Long selectedOrderId) {
        this.selectedOrderId = selectedOrderId;
    }
}
