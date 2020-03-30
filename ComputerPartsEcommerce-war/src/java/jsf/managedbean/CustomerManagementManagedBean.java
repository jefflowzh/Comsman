package jsf.managedbean;

import ejb.session.stateless.CustomerSessionBeanLocal;
import entity.Customer;
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
import util.exception.CustomerNotFoundException;
import util.exception.CustomerOrderNotFoundException;

@Named(value = "customerManagementManagedBean")
@ViewScoped
public class CustomerManagementManagedBean implements Serializable
{

    @EJB(name = "CustomerSessionBeanLocal")
    private CustomerSessionBeanLocal customerSessionBeanLocal;

    private List<Customer> customers;
    private List<Customer> filteredCustomers;
    
    private Customer selectedCustomerEntityToUpdate;
    
    private Customer selectedCustomerEntityToDelete;

    private Customer newCustomerEntity;

    private Customer currentCustomer;
    
    public CustomerManagementManagedBean() {
        newCustomerEntity = new Customer();
    }
    
    @PostConstruct
    public void postConstruct() {
        setCustomers(customerSessionBeanLocal.retrieveAllCustomers(Boolean.FALSE, Boolean.TRUE));
    }
    
    public void createNewCustomer(ActionEvent event) {
        try {
            Long newCustomerId = customerSessionBeanLocal.createNewCustomer(getNewCustomerEntity());
            Customer newCustomer = customerSessionBeanLocal.retrieveCustomerById(newCustomerId, Boolean.FALSE, Boolean.TRUE);
            getCustomers().add(newCustomer);
            System.out.println("OLD EMAIL" + newCustomer.getEmail());
            newCustomer = new Customer();
            System.out.println("NEW EMAIL" + newCustomer.getEmail());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New customer created successfully (Customer ID: " + newCustomerId + ")", null));
        } catch (CustomerNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occured while creating the new customer: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void doUpdateCustomer(ActionEvent event) {
        System.out.println("******" + "NULL CHECK PRE");
        System.out.println("****** NULL CHECK" + (Customer)event.getComponent().getAttributes().get("customerEntityToUpdate"));
        setSelectedCustomerEntityToUpdate((Customer)event.getComponent().getAttributes().get("customerEntityToUpdate"));
    }
    
    public void updateCustomer(ActionEvent event) {
        try {
            customerSessionBeanLocal.updateCustomer(selectedCustomerEntityToUpdate, null, null);
            setSelectedCustomerEntityToUpdate(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer updated successfully", null));
        } catch (CustomerOrderNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer order not found", null));
        } catch (CustomerNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer not found", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occured: " + ex.getMessage(), null));
        }
    }
    
    public void doDeleteCustomer(ActionEvent event) {
        setSelectedCustomerEntityToDelete((Customer) event.getComponent().getAttributes().get("customerEntityToDelete"));
    }
    
    public void deleteCustomer(ActionEvent event) {
        try {
            customerSessionBeanLocal.deleteCustomer(selectedCustomerEntityToDelete.getUserId());
            setSelectedCustomerEntityToDelete(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer deleted successfully", null));
        } catch (CustomerNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer not found", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occured: " + ex.getMessage(), null));
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public Customer getSelectedCustomerEntityToUpdate() {
        return selectedCustomerEntityToUpdate;
    }

    public void setSelectedCustomerEntityToUpdate(Customer selectedCustomerEntityToUpdate) {
        this.selectedCustomerEntityToUpdate = selectedCustomerEntityToUpdate;
    }

    public Customer getSelectedCustomerEntityToDelete() {
        return selectedCustomerEntityToDelete;
    }

    public void setSelectedCustomerEntityToDelete(Customer selectedCustomerEntityToDelete) {
        this.selectedCustomerEntityToDelete = selectedCustomerEntityToDelete;
    }


    public Customer getNewCustomerEntity() {
        return newCustomerEntity;
    }


    public void setNewCustomerEntity(Customer newCustomerEntity) {
        this.newCustomerEntity = newCustomerEntity;
    }


    public Customer getCurrentCustomer() {
        return currentCustomer;
    }


    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
}
