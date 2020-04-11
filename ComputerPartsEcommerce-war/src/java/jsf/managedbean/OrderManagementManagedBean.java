/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import entity.CustomerOrder;
import entity.LineItem;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    private LineItemSessionBeanLocal lineItemSessionBeanLocal;

    @EJB
    private CustomerOrderSessionBeanLocal customerOrderSessionBeanLocal;

    private List<CustomerOrder> orderEntities;
    private List<CustomerOrder> filteredOrderEntities;

    private CustomerOrder selectedOrderEntityToUpdate;

    private LineItem lineEntityToDelete;

    private List<LineItem> selectedLineItemsToUpdate;

    private Boolean[] booleanList = {true, false};

    public OrderManagementManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        setOrderEntities(customerOrderSessionBeanLocal.retrieveAllOrders());
    }

    public void doUpdateOrder(ActionEvent event) {
        setSelectedOrderEntityToUpdate((CustomerOrder) event.getComponent().getAttributes().get("orderEntityToUpdate"));
    }

    public void updateOrder(ActionEvent event) {
        try {

            customerOrderSessionBeanLocal.updateCustomerOrder(selectedOrderEntityToUpdate, null, null, null, null);

            selectedOrderEntityToUpdate = new CustomerOrder();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Order updated successfully", null));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
    }

    public void doUpdateLineItems(ActionEvent event) {
        setSelectedOrderEntityToUpdate((CustomerOrder) event.getComponent().getAttributes().get("lineItemsToUpdate"));
        setSelectedLineItemsToUpdate(selectedOrderEntityToUpdate.getLineItems());
    }

    public void updateLineItems(ActionEvent event) {
        try {

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
    }

    public void doDeleteLineItems(ActionEvent event) {
        lineEntityToDelete = (LineItem) event.getComponent().getAttributes().get("lineItemToDelete");
    }

    public void deleteLineItem(ActionEvent event) {
        try {
            for (int i = 0; i < getSelectedLineItemsToUpdate().size(); i++) {
                if (getSelectedLineItemsToUpdate().get(i).getLineItemId().equals(lineEntityToDelete.getLineItemId())) {
                    System.out.println(getSelectedOrderEntityToUpdate().getCustomerOrderId());
                    lineItemSessionBeanLocal.deleteLineItemById(getSelectedLineItemsToUpdate().get(i).getLineItemId(), getSelectedOrderEntityToUpdate().getCustomerOrderId());
                    getSelectedLineItemsToUpdate().remove(i);
                    break;
                }
            }

            CustomerOrder updateOrder = customerOrderSessionBeanLocal.retrieveCustomerOrderById(getSelectedOrderEntityToUpdate().getCustomerOrderId(), true);
            double tempTotalPrice = 0;
            
            for (LineItem l : getSelectedLineItemsToUpdate()) {
                tempTotalPrice += (l.getProduct().getPrice() * l.getQuantity());
            }

            selectedOrderEntityToUpdate.setTotalPrice(tempTotalPrice);
            updateOrder.setTotalPrice(tempTotalPrice);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Line Item deleted successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
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

    public Boolean[] getBooleanList() {
        return booleanList;
    }

    public void setBooleanList(Boolean[] booleanList) {
        this.booleanList = booleanList;
    }

    public List<LineItem> getSelectedLineItemsToUpdate() {
        return selectedLineItemsToUpdate;
    }

    public void setSelectedLineItemsToUpdate(List<LineItem> selectedLineItemsToUpdate) {
        this.selectedLineItemsToUpdate = selectedLineItemsToUpdate;
    }

    public LineItem getLineEntityToDelete() {
        return lineEntityToDelete;
    }

    public void setLineEntityToDelete(LineItem lineEntityToDelete) {
        this.lineEntityToDelete = lineEntityToDelete;
    }

}
