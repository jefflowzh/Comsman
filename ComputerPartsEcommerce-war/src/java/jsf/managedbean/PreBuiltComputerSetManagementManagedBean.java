package jsf.managedbean;

import ejb.session.stateless.PreBuiltComputerSetModelSessionBeanLocal;
import entity.PreBuiltComputerSetModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.PreBuiltComputerSetModelNotFoundException;

@Named(value = "preBuiltComputerSetManagementManagedBean")
@ViewScoped
public class PreBuiltComputerSetManagementManagedBean implements Serializable {

    @EJB(name = "PreBuiltComputerSetModelSessionBeanLocal")
    private PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBeanLocal;
    
    private List<PreBuiltComputerSetModel> models;
    
    public PreBuiltComputerSetManagementManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        setModels(preBuiltComputerSetModelSessionBeanLocal.retrieveAllPreBuiltComputerSetModels());
    }
    
    public void updateModel() {
        try {
            for (PreBuiltComputerSetModel model : getModels()) {
                preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(model);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Models successfully updated", null));
            }
        } catch (PreBuiltComputerSetModelNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model does not exist!", null));
        }
    }

    public List<PreBuiltComputerSetModel> getModels() {
        return models;
    }

    public void setModels(List<PreBuiltComputerSetModel> models) {
        this.models = models;
    }
}
