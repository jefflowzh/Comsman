/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.StaffSessionBeanLocal;
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

/**
 *
 * @author weidonglim
 */
@Named(value = "staffManagementManagedBean")
@ViewScoped
public class StaffManagementManagedBean implements Serializable {

    @EJB
    private StaffSessionBeanLocal staffSessionBeanLocal;

    private List<Staff> staffEntities;
    private List<Staff> filteredStaffEntities;

    private Staff selectedStaffEntityToDelete;

    private Staff newStaffEntity;

    private Staff currentStaff;

    public StaffManagementManagedBean() {
        newStaffEntity = new Staff();
    }

    @PostConstruct
    public void postConstruct() {
        staffEntities = staffSessionBeanLocal.retrieveAllStaffs();
    }

    public void doDeleteStaff(ActionEvent event) {
        selectedStaffEntityToDelete = (Staff) event.getComponent().getAttributes().get("staffEntityToDelete");
    }

    public void deleteStaff(ActionEvent event) {
        try {
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    public List<Staff> getStaffEntities() {
        return staffEntities;
    }

    public void setStaffEntities(List<Staff> staffEntities) {
        this.staffEntities = staffEntities;
    }

    public List<Staff> getFilteredStaffEntities() {
        return filteredStaffEntities;
    }

    public void setFilteredStaffEntities(List<Staff> filteredStaffEntities) {
        this.filteredStaffEntities = filteredStaffEntities;
    }

    public Staff getNewStaffEntity() {
        return newStaffEntity;
    }

    public void setNewStaffEntity(Staff newStaffEntity) {
        this.newStaffEntity = newStaffEntity;
    }

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

}
