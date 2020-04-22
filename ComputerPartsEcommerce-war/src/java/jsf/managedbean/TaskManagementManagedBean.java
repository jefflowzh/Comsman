/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import entity.CustomerOrder;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author weidonglim
 */
@Named(value = "taskManagementManagedBean")
@RequestScoped
public class TaskManagementManagedBean {

    @EJB
    private LineItemSessionBeanLocal lineItemSessionBeanLocal;

    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    private List<CustomerOrder> tasks;
    private List<CustomerOrder> filteredTasks;

    public TaskManagementManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        // those voided / delivered will not be shown
        setTasks(getCustomerOrderSessionBeanLocal().retrieveAllTasks());
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

    
    
    

}
