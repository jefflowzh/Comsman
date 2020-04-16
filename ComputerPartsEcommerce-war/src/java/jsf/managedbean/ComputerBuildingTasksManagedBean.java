package jsf.managedbean;

import ejb.session.stateless.ComputerSetSessionBeanLocal;
import entity.ComputerSet;
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
import util.exception.StaffNotFoundException;

@Named(value = "computerBuildingTasksManagedBean")
@ViewScoped
public class ComputerBuildingTasksManagedBean implements Serializable {

    @EJB(name = "ComputerSetSessionBeanLocal")
    private ComputerSetSessionBeanLocal computerSetSessionBeanLocal;
    
    private List<ComputerSet> sets;
    private List<ComputerSet> filteredSets;
    
    private ComputerSet currentTask;

    public ComputerBuildingTasksManagedBean() {
        
    }
    
    @PostConstruct
    public void postConstruct() {
        Staff staff = (Staff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStaffEntity");
        setSets(computerSetSessionBeanLocal.retrieveComputerSetsByStaffAssignedTo(staff.getUserId(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE));
    }
    
    public void markAsDone(ActionEvent event) {
        try {
            getCurrentTask().setAssemblyComplete(Boolean.TRUE);
            computerSetSessionBeanLocal.updateComputerSet(getCurrentTask(), null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Computer Set " + getCurrentTask().getComputerSetId() + " built!", null));
        } catch(StaffNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Staff not found!", null));
        }
    }

    public List<ComputerSet> getSets() {
        return sets;
    }

    public void setSets(List<ComputerSet> sets) {
        this.sets = sets;
    }

    public List<ComputerSet> getFilteredSets() {
        return filteredSets;
    }

    public void setFilteredSets(List<ComputerSet> filteredSets) {
        this.filteredSets = filteredSets;
    }

    public ComputerSet getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(ComputerSet currentTask) {
        this.currentTask = currentTask;
    }
}
