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
import util.enumeration.StaffAccessRightEnum;
import util.exception.ComputerSetNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.StaffAlreadyExistsException;
import util.exception.StaffNotFoundException;

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

    private Staff selectedStaffEntityToUpdate;
    private Staff selectedStaffEntityToDelete;

    private Staff newStaffEntity;

    private Staff currentStaff;
    
    private String tempEnum = "";
    private StaffAccessRightEnum roleEnumUpdate;
    
    private StaffAccessRightEnum[] roles;

    public StaffManagementManagedBean() {
        newStaffEntity = new Staff();
        roles = StaffAccessRightEnum.values();
    }

    @PostConstruct
    public void postConstruct() {
        staffEntities = staffSessionBeanLocal.retrieveAllStaffs();
    }

    public void createNewStaff(ActionEvent event) {
        try {
            StaffAccessRightEnum roleEnum = StaffAccessRightEnum.valueOf(tempEnum);
            newStaffEntity.setRole(roleEnum);
            
            Long newStaffId = staffSessionBeanLocal.createNewStaff(newStaffEntity);
            Staff newStaff = staffSessionBeanLocal.retrieveStaffById(newStaffId, true, true);
            staffEntities.add(newStaff);

            tempEnum = "";
            newStaffEntity = new Staff();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New staff created successfully (Product ID: " + newStaff.getUserId() + ")", null));
        } catch (StaffAlreadyExistsException ex) {
            newStaffEntity = new Staff();
            tempEnum = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff already exists!", null));
        } catch (StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new staff: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void doUpdateStaff(ActionEvent event) {
        setSelectedStaffEntityToUpdate((Staff) event.getComponent().getAttributes().get("staffEntityToUpdate"));
    }
    
    public void updateStaff(ActionEvent event) {
        try {
            selectedStaffEntityToUpdate.setRole(roleEnumUpdate);
            
            // verify if its null
            staffSessionBeanLocal.updateStaff(selectedStaffEntityToUpdate, null, null);
            
            selectedStaffEntityToUpdate = new Staff();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff updated successfully", null));
            
        } catch (ComputerSetNotFoundException | CustomerOrderNotFoundException | StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not found exception has occurred: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred: " + ex.getMessage(), null));
        }
    }

    public void doDeleteStaff(ActionEvent event) {
        setSelectedStaffEntityToDelete((Staff) event.getComponent().getAttributes().get("staffEntityToDelete"));
    }

    public void deleteStaff(ActionEvent event) {
        try {
            staffSessionBeanLocal.deleteStaff(getSelectedStaffEntityToDelete().getUserId());

            staffEntities.remove(getSelectedStaffEntityToDelete());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff deleted successfully", null));
        } catch (StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting staff: " + ex.getMessage(), null));
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

    public Staff getSelectedStaffEntityToDelete() {
        return selectedStaffEntityToDelete;
    }

    public void setSelectedStaffEntityToDelete(Staff selectedStaffEntityToDelete) {
        this.selectedStaffEntityToDelete = selectedStaffEntityToDelete;
    }

    public StaffAccessRightEnum[] getRoles() {
        return roles;
    }

    public void setRoles(StaffAccessRightEnum[] roles) {
        this.roles = roles;
    }

    public String getTempEnum() {
        return tempEnum;
    }

    public void setTempEnum(String tempEnum) {
        this.tempEnum = tempEnum;
    }

    public Staff getSelectedStaffEntityToUpdate() {
        return selectedStaffEntityToUpdate;
    }

    public void setSelectedStaffEntityToUpdate(Staff selectedStaffEntityToUpdate) {
        this.selectedStaffEntityToUpdate = selectedStaffEntityToUpdate;
    }

    public StaffAccessRightEnum getRoleEnumUpdate() {
        return roleEnumUpdate;
    }

    public void setRoleEnumUpdate(StaffAccessRightEnum roleEnumUpdate) {
        this.roleEnumUpdate = roleEnumUpdate;
    }

}
