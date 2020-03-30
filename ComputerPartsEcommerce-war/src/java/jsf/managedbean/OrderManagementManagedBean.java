/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import entity.CustomerOrder;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author weidonglim
 */
@Named(value = "orderManagementManagedBean")
@ViewScoped
public class OrderManagementManagedBean implements Serializable {

    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    private List<CustomerOrder> orderEntities;
    private List<CustomerOrder> filteredOrderEntities;
    
    private CustomerOrder selectedOrderEntityToUpdate;
    
    public OrderManagementManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        setOrderEntities(customerOrderSessionBeanLocal.retrieveAllOrders());
    }

    public List<CustomerOrder> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(List<CustomerOrder> orderEntities) {
        this.orderEntities = orderEntities;
    }

    public List<CustomerOrder> getFilteredOrderEntities() {
        return filteredOrderEntities;
    }

    public void setFilteredOrderEntities(List<CustomerOrder> filteredOrderEntities) {
        this.filteredOrderEntities = filteredOrderEntities;
    }

    public CustomerOrder getSelectedOrderEntityToUpdate() {
        return selectedOrderEntityToUpdate;
    }

    public void setSelectedOrderEntityToUpdate(CustomerOrder selectedOrderEntityToUpdate) {
        this.selectedOrderEntityToUpdate = selectedOrderEntityToUpdate;
    }
    
}
