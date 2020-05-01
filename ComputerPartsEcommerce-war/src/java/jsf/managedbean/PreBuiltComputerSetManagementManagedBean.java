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
import util.enumeration.PreBuiltComputerSetTierEnum;
import util.exception.PreBuiltComputerSetModelNotFoundException;

@Named(value = "preBuiltComputerSetManagementManagedBean")
@ViewScoped
public class PreBuiltComputerSetManagementManagedBean implements Serializable {

    @EJB(name = "ComputerPartSessionBeanLocal")
    private ComputerPartSessionBeanLocal computerPartSessionBeanLocal;

    @EJB(name = "PreBuiltComputerSetModelSessionBeanLocal")
    private PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBeanLocal;
    
    private List<PreBuiltComputerSetModel> models;
    
    private PreBuiltComputerSetTierEnum currentTier;
    private Boolean currentIsEnabled;
    private String currentCpu;
    private String currentMotherboard;
    private List<String> currentRams;
    private String currentPsu;
    private String currentCompCase;
    private List<String> currentGpus;
    private List<String> currentHdds;
    private List<String> currentSsds;
    private String currentWaterCooler;
    private String currentAirCooler;
    private Double currentPrice;
    
    private PreBuiltComputerSetTierEnum[] preBuiltComputerSetTiers;
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
    
    public PreBuiltComputerSetManagementManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        setModels(preBuiltComputerSetModelSessionBeanLocal.retrieveAllPreBuiltComputerSetModels());
        setPreBuiltComputerSetTiers(PreBuiltComputerSetTierEnum.values());
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
    
    public void changeTier(AjaxBehaviorEvent event) {
        Integer currentIndex;
        switch (currentTier) {
            case PREMIUM:
                currentIndex = 0;
                break;
            case REGULAR:
                currentIndex = 1;
                break;
            default:
                currentIndex = 2;
                break;
        }
        
        currentIsEnabled = models.get(currentIndex).getIsEnabled();
        currentCpu = models.get(currentIndex).getCpu().getName();
        currentMotherboard = models.get(currentIndex).getMotherboard().getName();
        currentPsu = models.get(currentIndex).getPsu().getName();
        currentCompCase = models.get(currentIndex).getCompCase().getName();
        currentWaterCooler = models.get(currentIndex).getWaterCooler().getName();
        currentAirCooler = models.get(currentIndex).getAirCooler().getName();
        currentPrice = models.get(currentIndex).getPrice();
        for (RAM ram : models.get(currentIndex).getRams()) {
            currentRams.add(ram.getName());
        }
        for (GPU gpu : models.get(currentIndex).getGpus()) {
            currentGpus.add(gpu.getName());
        }
        for (HDD hdd : models.get(currentIndex).getHdds()) {
            currentHdds.add(hdd.getName());
        }
        for (SSD ssd : models.get(currentIndex).getSsds()) {
            currentSsds.add(ssd.getName());
        }
    }
    
    // Can be used for SelectCheckboxMenu, the RAMs field is an example which I have already tested, every time an option is clicked, this method triggers.
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

    public PreBuiltComputerSetTierEnum getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(PreBuiltComputerSetTierEnum currentTier) {
        this.currentTier = currentTier;
    }
    
    public Boolean getCurrentIsEnabled() {
        return currentIsEnabled;
    }

    public void setCurrentIsEnabled(Boolean currentIsEnabled) {
        this.currentIsEnabled = currentIsEnabled;
    }

    public String getCurrentCpu() {
        return currentCpu;
    }

    public void setCurrentCpu(String currentCpu) {
        this.currentCpu = currentCpu;
    }

    public String getCurrentMotherboard() {
        return currentMotherboard;
    }

    public void setCurrentMotherboard(String currentMotherboard) {
        this.currentMotherboard = currentMotherboard;
    }

    public List<String> getCurrentRams() {
        return currentRams;
    }

    public void setCurrentRams(List<String> currentRams) {
        this.currentRams = currentRams;
    }

    public String getCurrentPsu() {
        return currentPsu;
    }

    public void setCurrentPsu(String currentPsu) {
        this.currentPsu = currentPsu;
    }

    public String getCurrentCompCase() {
        return currentCompCase;
    }

    public void setCurrentCompCase(String currentCompCase) {
        this.currentCompCase = currentCompCase;
    }

    public List<String> getCurrentGpus() {
        return currentGpus;
    }

    public void setCurrentGpus(List<String> currentGpus) {
        this.currentGpus = currentGpus;
    }

    public List<String> getCurrentHdds() {
        return currentHdds;
    }

    public void setCurrentHdds(List<String> currentHdds) {
        this.currentHdds = currentHdds;
    }

    public List<String> getCurrentSsds() {
        return currentSsds;
    }

    public void setCurrentSsds(List<String> currentSsds) {
        this.currentSsds = currentSsds;
    }

    public String getCurrentWaterCooler() {
        return currentWaterCooler;
    }

    public void setCurrentWaterCooler(String currentWaterCooler) {
        this.currentWaterCooler = currentWaterCooler;
    }

    public String getCurrentAirCooler() {
        return currentAirCooler;
    }

    public void setCurrentAirCooler(String currentAirCooler) {
        this.currentAirCooler = currentAirCooler;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
    
    public PreBuiltComputerSetTierEnum[] getPreBuiltComputerSetTiers() {
        return preBuiltComputerSetTiers;
    }

    public void setPreBuiltComputerSetTiers(PreBuiltComputerSetTierEnum[] preBuiltComputerSetTiers) {
        this.preBuiltComputerSetTiers = preBuiltComputerSetTiers;
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
}
