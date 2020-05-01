package jsf.managedbean;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.PreBuiltComputerSetModelSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.PreBuiltComputerSetModel;
import entity.RAM;
import entity.SSD;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import util.exception.IncompatiblePartException;
import util.exception.PreBuiltComputerSetModelNotFoundException;

@Named(value = "preBuiltComputerSetManagementManagedBean")
@ViewScoped
public class PreBuiltComputerSetManagementManagedBean implements Serializable {

    @EJB(name = "ComputerPartSessionBeanLocal")
    private ComputerPartSessionBeanLocal computerPartSessionBeanLocal;

    @EJB(name = "PreBuiltComputerSetModelSessionBeanLocal")
    private PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBeanLocal;
    
    private List<PreBuiltComputerSetModel> models;
    private PreBuiltComputerSetModel testModel1;
    private PreBuiltComputerSetModel testModel2;
    private PreBuiltComputerSetModel testModel3;
    
    private List<CPU> cpus;
    private List<MotherBoard> motherboards;
    private List<RAM> rams;
    private List<PowerSupply> psus;
    private List<ComputerCase> compCases;
    private List<GPU> gpus;
    private List<HDD> hdds;
    private List<SSD> ssds;
    private List<CPUWaterCooler> waterCoolers;
    private List<CPUAirCooler> airCoolers;
    
    private Long selectedComputerPartId;
    private List<Long> selectedComputerPartIds;
    
    public PreBuiltComputerSetManagementManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        setModels(preBuiltComputerSetModelSessionBeanLocal.retrieveAllPreBuiltComputerSetModels());
        setTestModel1(models.get(0));
        setTestModel2(models.get(1));
        setTestModel3(models.get(2));
        setCpus(computerPartSessionBeanLocal.retrieveAllCPU());
        setMotherboards(computerPartSessionBeanLocal.retrieveAllMotherBoard());
        setRams(computerPartSessionBeanLocal.retrieveAllRAM());
        setPsus(computerPartSessionBeanLocal.retrieveAllPowerSupply());
        setCompCases(computerPartSessionBeanLocal.retrieveAllComCase());
        setGpus(computerPartSessionBeanLocal.retrieveAllGPU());
        setHdds(computerPartSessionBeanLocal.retrieveAllHDD());
        setSsds(computerPartSessionBeanLocal.retrieveAllSSD());
        setWaterCoolers(computerPartSessionBeanLocal.retrieveAllCPUWaterCooler());
        setAirCoolers(computerPartSessionBeanLocal.retrieveAllCPUAirCooler());
        System.out.println("*******postcon " + models.get(0).getCpu().getName());
    }
    
    public void checkSingleComputerPartUpdate(PreBuiltComputerSetModel model, ComputerPart computerPart, UIComponent component) {
        FacesContext.getCurrentInstance().addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Incom", "incom"));
        System.out.println("*******MGDBEAN REACHED" + model.getCpu());
        System.out.println("*******MGDBEAN REACHED" + model.getPreBuiltComputerSetTier());
        System.out.println("*******MGDBEAN REACHED" + model.getPrice());
        System.out.println("*******MGDBEAN REACHED" + model.getIsEnabled());
//        try {
//            preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(model, computerPart.getProductId());
//        } catch (IncompatiblePartException ex) {
            
//        }
    }
    
    public void selectEvent(AjaxBehaviorEvent event) {
        System.out.println("***********selected");
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

    public List<CPU> getCpus() {
        return cpus;
    }

    public void setCpus(List<CPU> cpus) {
        this.cpus = cpus;
    }

    public List<MotherBoard> getMotherboards() {
        return motherboards;
    }

    public void setMotherboards(List<MotherBoard> motherboards) {
        this.motherboards = motherboards;
    }

    public List<RAM> getRams() {
        return rams;
    }

    public void setRams(List<RAM> rams) {
        this.rams = rams;
    }

    public List<PowerSupply> getPsus() {
        return psus;
    }

    public void setPsus(List<PowerSupply> psus) {
        this.psus = psus;
    }

    public List<ComputerCase> getCompCases() {
        return compCases;
    }

    public void setCompCases(List<ComputerCase> compCases) {
        this.compCases = compCases;
    }

    public List<GPU> getGpus() {
        return gpus;
    }

    public void setGpus(List<GPU> gpus) {
        this.gpus = gpus;
    }

    public List<HDD> getHdds() {
        return hdds;
    }

    public void setHdds(List<HDD> hdds) {
        this.hdds = hdds;
    }

    public List<SSD> getSsds() {
        return ssds;
    }

    public void setSsds(List<SSD> ssds) {
        this.ssds = ssds;
    }

    public List<CPUWaterCooler> getWaterCoolers() {
        return waterCoolers;
    }

    public void setWaterCoolers(List<CPUWaterCooler> waterCoolers) {
        this.waterCoolers = waterCoolers;
    }

    public List<CPUAirCooler> getAirCoolers() {
        return airCoolers;
    }

    public void setAirCoolers(List<CPUAirCooler> airCoolers) {
        this.airCoolers = airCoolers;
    }

    public PreBuiltComputerSetModel getTestModel1() {
        return testModel1;
    }

    public void setTestModel1(PreBuiltComputerSetModel testModel1) {
        this.testModel1 = testModel1;
    }

    public PreBuiltComputerSetModel getTestModel2() {
        return testModel2;
    }

    public void setTestModel2(PreBuiltComputerSetModel testModel2) {
        this.testModel2 = testModel2;
    }

    public PreBuiltComputerSetModel getTestModel3() {
        return testModel3;
    }

    public void setTestModel3(PreBuiltComputerSetModel testModel3) {
        this.testModel3 = testModel3;
    }

    public Long getSelectedComputerPartId() {
        return selectedComputerPartId;
    }

    public void setSelectedComputerPartId(Long selectedComputerPartId) {
        this.selectedComputerPartId = selectedComputerPartId;
    }

    public List<Long> getSelectedComputerPartIds() {
        return selectedComputerPartIds;
    }

    public void setSelectedComputerPartIds(List<Long> selectedComputerPartIds) {
        this.selectedComputerPartIds = selectedComputerPartIds;
    }
}
