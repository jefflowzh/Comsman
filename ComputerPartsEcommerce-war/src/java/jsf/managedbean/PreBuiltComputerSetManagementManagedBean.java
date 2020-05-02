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
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.enumeration.PreBuiltComputerSetTierEnum;
import util.exception.ComputerPartNotFoundException;
import util.exception.IncompatiblePartException;
import util.exception.PreBuiltComputerSetModelNotFoundException;

@Named(value = "preBuiltComputerSetManagementManagedBean")
@ViewScoped
public class PreBuiltComputerSetManagementManagedBean implements Serializable {

    public PreBuiltComputerSetModel getCurrentModel() {
        return currentModel;
    }

    public void setCurrentModel(PreBuiltComputerSetModel currentModel) {
        this.currentModel = currentModel;
    }

    @EJB(name = "ComputerPartSessionBeanLocal")
    private ComputerPartSessionBeanLocal computerPartSessionBeanLocal;

    @EJB(name = "PreBuiltComputerSetModelSessionBeanLocal")
    private PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBeanLocal;

    private List<PreBuiltComputerSetModel> models;

    private PreBuiltComputerSetModel currentModel;

    private PreBuiltComputerSetTierEnum currentTier;
    private String currentTierString;
    private Boolean currentIsEnabled;
    private String currentCpu;
    private String currentMotherboard;
    private List<String> currentRams;
    private String currentPsu;
    private String currentCompCase;
    private List<Long> currentGpus;
    private List<Long> currentHdds;
    private List<Long> currentSsds;
    private String currentWaterCooler;
    private String currentAirCooler;
    private Double currentPrice;

    private String stringRams;

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

    private CPU selectedCPU;
    private Long selectedId;

    private CPU existingTierCPU;
    private MotherBoard existingTierMotherboard;
    private PowerSupply existingTierPowerSupply;
    private ComputerCase existingTierComputerCase;
    private CPUWaterCooler existingTierCPUWaterCooler;
    private CPUAirCooler existingTierCPUAirCooler;
    // converting them to Arrays.toString(list.toArray())
    private String existingTierRAMs;
    private String existingTierGPUs;
    private String existingTierHDDs;
    private String existingTierSSDs;

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

    public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
        System.out.println("------------ " + currentTierString);
        if (currentTierString.equals("PREMIUM")) {
            currentModel = models.get(0);
            System.out.println("----------- " + currentModel.getPreBuiltComputerSetTier());
        } else if (currentTierString.equals("REGULAR")) {
            currentModel = models.get(1);
            System.out.println("----------- " + currentModel.getPreBuiltComputerSetTier());
        } else if (currentTierString.equals("BUDGET")) {
            currentModel = models.get(2);
            System.out.println("----------- " + currentModel.getPreBuiltComputerSetTier());
        }

        currentCpu = null;
        currentMotherboard = null;
        currentPsu = null;
        currentCompCase = null;
        currentWaterCooler = null;
        currentAirCooler = null;
        
        // set currentModel parts to models
        if (currentModel.getCpu() != null) currentCpu = currentModel.getCpu().getName();
        if (currentModel.getMotherboard()!= null) currentMotherboard = currentModel.getMotherboard().getName();
        if (currentModel.getPsu()!= null) currentPsu = currentModel.getPsu().getName();
        if (currentModel.getCompCase()!= null) currentCompCase = currentModel.getCompCase().getName();
        if (currentModel.getWaterCooler()!= null) currentWaterCooler = currentModel.getWaterCooler().getName();
        if (currentModel.getAirCooler()!= null) currentAirCooler = currentModel.getAirCooler().getName();
        setExistingTierRAMs(Arrays.toString(currentModel.getRams().toArray()));
        setExistingTierGPUs(Arrays.toString(currentModel.getGpus().toArray()));
        setExistingTierHDDs(Arrays.toString(currentModel.getHdds().toArray()));
        setExistingTierSSDs(Arrays.toString(currentModel.getSsds().toArray()));
        currentPrice = currentModel.getPrice();
    }

    public void setAjaxCPU(final AjaxBehaviorEvent event) {
        try {
            Long cpuId = Long.parseLong(currentCpu);
            currentModel.setCpu(computerPartSessionBeanLocal.retrieveCPUById(cpuId));
//            System.out.println("---------------- " + currentModel.getCpu().getName());
        } catch (ComputerPartNotFoundException ex) {
            System.out.println("Error: No computer part exists");
        }
    }

    public void setAjaxMotherboard(final AjaxBehaviorEvent event) {
        try {
            Long motherboardId = Long.parseLong(currentMotherboard);
            currentModel.setMotherboard(computerPartSessionBeanLocal.retrieveMotherBoardById(motherboardId));
//            System.out.println("---------------- " + currentModel.getCpu().getName());
//            System.out.println("---------------- " + currentModel.getMotherboard().getName());
        } catch (ComputerPartNotFoundException ex) {
            System.out.println("Error message: " + ex);
        }
    }

    public void setAjaxPSU(final AjaxBehaviorEvent event) {
        try {
            Long psuId = Long.parseLong(currentPsu);
            currentModel.setPsu(computerPartSessionBeanLocal.retrievePowerSupplyById(psuId));
//            System.out.println("---------------- " + currentModel.getCpu().getName());
//            System.out.println("---------------- " + currentModel.getMotherboard().getName());
//            System.out.println("---------------- " + currentModel.getPsu().getName());
        } catch (ComputerPartNotFoundException ex) {
            System.out.println("Error message: " + ex);
        }
    }

    public void setAjaxCompCase(final AjaxBehaviorEvent event) {
        try {
            Long compCaseId = Long.parseLong(currentCompCase);
            currentModel.setCompCase(computerPartSessionBeanLocal.retrieveComputerCaseById(compCaseId));
//            System.out.println("---------------- " + currentModel.getCpu().getName());
//            System.out.println("---------------- " + currentModel.getMotherboard().getName());
//            System.out.println("---------------- " + currentModel.getPsu().getName());
//            System.out.println("---------------- " + currentModel.getCompCase().getName());
        } catch (ComputerPartNotFoundException ex) {
            System.out.println("Error message: " + ex);
        }
    }

    public void setAjaxWaterCooler(final AjaxBehaviorEvent event) {
        try {
            Long waterCoolerId = Long.parseLong(currentWaterCooler);
            currentModel.setWaterCooler(computerPartSessionBeanLocal.retrieveCPUWaterCoolerById(waterCoolerId));
//            System.out.println("---------------- " + currentModel.getCpu().getName());
//            System.out.println("---------------- " + currentModel.getMotherboard().getName());
//            System.out.println("---------------- " + currentModel.getPsu().getName());
//            System.out.println("---------------- " + currentModel.getCompCase().getName());
//            System.out.println("---------------- " + currentModel.getWaterCooler().getName());
        } catch (ComputerPartNotFoundException ex) {
            System.out.println("Error message: " + ex);
        }
    }

    public void setAjaxAirCooler(final AjaxBehaviorEvent event) {
        try {
            Long airCoolerId = Long.parseLong(currentAirCooler);
            currentModel.setAirCooler(computerPartSessionBeanLocal.retrieveCPUAirCoolerById(airCoolerId));
//            System.out.println("---------------- " + currentModel.getCpu().getName());
//            System.out.println("---------------- " + currentModel.getMotherboard().getName());
//            System.out.println("---------------- " + currentModel.getPsu().getName());
//            System.out.println("---------------- " + currentModel.getCompCase().getName());
//            System.out.println("---------------- " + currentModel.getWaterCooler().getName());
//            System.out.println("---------------- " + currentModel.getAirCooler().getName());
        } catch (ComputerPartNotFoundException ex) {
            System.out.println("Error message: " + ex);
        }
    }

    // This is for testing multiple to see if its inside the List
    // Those with multiple cannot set first, wait till update then set it
    public void setAjaxTestMulti(final AjaxBehaviorEvent event) {
        if (currentRams != null) {
            System.out.println("------------- ram list size " + currentRams.size());
        }

        if (currentGpus != null) {
            System.out.println("------------- gpu list size " + currentGpus.size());
        }

        if (currentHdds != null) {
            System.out.println("------------- hdd list size " + currentHdds.size());
        }

        if (currentSsds != null) {
            System.out.println("------------- ssd list size " + currentSsds.size());
        }

    }

//    public void changeTier(AjaxBehaviorEvent event) {
//        Integer currentIndex;
//        switch (currentTier) {
//            case PREMIUM:
//                currentIndex = 0;
//                break;
//            case REGULAR:
//                currentIndex = 1;
//                break;
//            default:
//                currentIndex = 2;
//                break;
//        }
//
//        currentIsEnabled = models.get(currentIndex).getIsEnabled();
//        currentCpu = models.get(currentIndex).getCpu().getName();
//        currentMotherboard = models.get(currentIndex).getMotherboard().getName();
//        currentPsu = models.get(currentIndex).getPsu().getName();
//        currentCompCase = models.get(currentIndex).getCompCase().getName();
//        currentWaterCooler = models.get(currentIndex).getWaterCooler().getName();
//        currentAirCooler = models.get(currentIndex).getAirCooler().getName();
//        currentPrice = models.get(currentIndex).getPrice();
//        for (RAM ram : models.get(currentIndex).getRams()) {
//            currentRams.add(ram.getName());
//        }
//        for (GPU gpu : models.get(currentIndex).getGpus()) {
//            currentGpus.add(gpu.getName());
//        }
//        for (HDD hdd : models.get(currentIndex).getHdds()) {
//            currentHdds.add(hdd.getName());
//        }
//        for (SSD ssd : models.get(currentIndex).getSsds()) {
//            currentSsds.add(ssd.getName());
//        }
//    }
    // Can be used for SelectCheckboxMenu, the RAMs field is an example which I have already tested, every time an option is clicked, this method triggers.
    public void selectEvent(AjaxBehaviorEvent event) {
        System.out.println("***********selected");
    }

    public void updateModel(ActionEvent event) {
        try {
            // link those multiple to currentModel
            if (!currentRams.isEmpty()) {
                // empty current rams attached to the tier
                currentModel.getRams().clear();
                for (Long l : currentRams) {
                    currentModel.getRams().add(computerPartSessionBeanLocal.retrieveRAMById(l));
                }
            }
            if (!currentGpus.isEmpty()) {
                currentModel.getGpus().clear();
                for (Long l : currentGpus) {
                    currentModel.getGpus().add(computerPartSessionBeanLocal.retrieveGPUById(l));
                }
            }
            if (!currentHdds.isEmpty()) {
                currentModel.getHdds().clear();
                for (Long l : currentHdds) {
                    currentModel.getHdds().add(computerPartSessionBeanLocal.retrieveHDDById(l));
                }
            }
            if (!currentSsds.isEmpty()) {
                currentModel.getSsds().clear();
                for (Long l : currentSsds) {
                    currentModel.getSsds().add(computerPartSessionBeanLocal.retrieveSSDById(l));
                }
            }

            preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(currentModel);

            // update to show changes on ajax
            existingTierCPU = currentModel.getCpu();
            existingTierMotherboard = currentModel.getMotherboard();
            existingTierPowerSupply = currentModel.getPsu();
            existingTierComputerCase = currentModel.getCompCase();
            existingTierCPUWaterCooler = currentModel.getWaterCooler();
            existingTierCPUAirCooler = currentModel.getAirCooler();
            setExistingTierRAMs(Arrays.toString(currentModel.getRams().toArray()));
            setExistingTierGPUs(Arrays.toString(currentModel.getGpus().toArray()));
            setExistingTierHDDs(Arrays.toString(currentModel.getHdds().toArray()));
            setExistingTierSSDs(Arrays.toString(currentModel.getSsds().toArray()));

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Models successfully updated", null));
        } catch (PreBuiltComputerSetModelNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model does not exist!", null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Part does not exist!", null));
        }
    }
    
    public void updateModelSinglePart(AjaxBehaviorEvent event, UIComponent component) {
        String computerPartName = "";
        Field field;
        Class thisClass = PreBuiltComputerSetManagementManagedBean.class;
        String clientId = component.getClientId();
        String fieldName = clientId.substring(clientId.indexOf(':') + 1);
        System.out.println("*********" + fieldName);
        try {
            field = thisClass.getDeclaredField(fieldName);
            System.out.println("*************field name: " + field.getName());
            computerPartName = (String) field.get(this);
            System.out.println("*************par tname: " + computerPartName);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error occured: " + ex.getMessage(), null));
        }
        ComputerPart partToAdd = null;
        if (computerPartName != null) {
            partToAdd = computerPartSessionBeanLocal.retrieveComputerPartByName(computerPartName);
        
            if (partToAdd instanceof CPU) {
                CPU castedPartToAdd = (CPU) partToAdd;
                currentModel.setCpu(castedPartToAdd);
            } else if (partToAdd instanceof MotherBoard) {
                MotherBoard castedPartToAdd = (MotherBoard) partToAdd;
                currentModel.setMotherboard(castedPartToAdd);
            } else if (partToAdd instanceof PowerSupply) {
                PowerSupply castedPartToAdd = (PowerSupply) partToAdd;
                currentModel.setPsu(castedPartToAdd);
            } else if (partToAdd instanceof ComputerCase) {
                ComputerCase castedPartToAdd = (ComputerCase) partToAdd;
                currentModel.setCompCase(castedPartToAdd);
            } else if (partToAdd instanceof CPUWaterCooler) {
                CPUWaterCooler castedPartToAdd = (CPUWaterCooler) partToAdd;
                currentModel.setWaterCooler(castedPartToAdd);
            } else if (partToAdd instanceof CPUAirCooler) {
                CPUAirCooler castedPartToAdd = (CPUAirCooler) partToAdd;
                currentModel.setAirCooler(castedPartToAdd);
            }
        } else {
            if (currentCpu == null) currentModel.setCpu(null);
            if (currentMotherboard == null) currentModel.setMotherboard(null);
            if (currentPsu == null) currentModel.setPsu(null);
            if (currentCompCase == null) currentModel.setCompCase(null);
            if (currentWaterCooler == null) currentModel.setWaterCooler(null);
            if (currentAirCooler == null) currentModel.setAirCooler(null);
        }
        updatePrice();
        try {
            preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(currentModel);
            if (partToAdd != null) {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToAdd.getProductId());
            }
        } catch (IncompatiblePartException ex) {
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
        } catch (PreBuiltComputerSetModelNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model does not exist!", null));
        }
        System.out.println(" set switch if need");
    }
    
    /*public void updateModelCollection(AjaxBehaviorEvent event, UIComponent component) {
        List<String> computerPartNames;
        Field field;
        Class thisClass = PreBuiltComputerSetManagementManagedBean.class;
        String clientId = component.getClientId();
        String fieldName = clientId.substring(clientId.indexOf(':') + 1);
        try {
            field = thisClass.getDeclaredField(fieldName);
            System.out.println("*************field name: " + field.getName());
            computerPartNames = (List<String>) field.get(this);
            System.out.println("*************par tname: " + computerPartName);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error occured: " + ex.getMessage(), null));
        }
    }*/
    /*public void updateModelRAM(UIComponent component) {
        RAM ram = computerPartSessionBeanLocal.retrieveComputerPartByName(addRAM);
        currentModel.getRams().add(ram);
        preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(currentModel);
        updatePrice();
        try {
            preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, ram.getProductId());
        } catch (IncompatiblePartException ex) {
            FacesContext.getCurrentInstance().addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
        }
    }*/
    
    private void updatePrice() {
        currentPrice = 0.0;
        if (currentModel.getCpu() != null) currentPrice += currentModel.getCpu().getPrice();
        if (currentModel.getMotherboard() != null) currentPrice += currentModel.getMotherboard().getPrice();
        if (currentModel.getPsu() != null) currentPrice += currentModel.getPsu().getPrice();
        if (currentModel.getCompCase() != null) currentPrice += currentModel.getCompCase().getPrice();
        if (currentModel.getWaterCooler() != null) currentPrice += currentModel.getWaterCooler().getPrice();
        if (currentModel.getAirCooler() != null) currentPrice += currentModel.getAirCooler().getPrice();
        currentModel.setPrice(currentPrice);
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

    public List<Long> getCurrentRams() {
        return currentRams;
    }

    public void setCurrentRams(List<Long> currentRams) {
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

    public List<Long> getCurrentGpus() {
        return currentGpus;
    }

    public void setCurrentGpus(List<Long> currentGpus) {
        this.currentGpus = currentGpus;
    }

    public List<Long> getCurrentHdds() {
        return currentHdds;
    }

    public void setCurrentHdds(List<Long> currentHdds) {
        this.currentHdds = currentHdds;
    }

    public List<Long> getCurrentSsds() {
        return currentSsds;
    }

    public void setCurrentSsds(List<Long> currentSsds) {
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

    public CPU getSelectedCPU() {
        return selectedCPU;
    }

    public void setSelectedCPU(CPU selectedCPU) {
        this.selectedCPU = selectedCPU;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public String getCurrentTierString() {
        return currentTierString;
    }

    public void setCurrentTierString(String currentTierString) {
        this.currentTierString = currentTierString;
    }

    public String getStringRams() {
        return stringRams;
    }

    public void setStringRams(String stringRams) {
        this.stringRams = stringRams;
    }

    public CPU getExistingTierCPU() {
        return existingTierCPU;
    }

    public void setExistingTierCPU(CPU existingTierCPU) {
        this.existingTierCPU = existingTierCPU;
    }

    public MotherBoard getExistingTierMotherboard() {
        return existingTierMotherboard;
    }

    public void setExistingTierMotherboard(MotherBoard existingTierMotherboard) {
        this.existingTierMotherboard = existingTierMotherboard;
    }

    public PowerSupply getExistingTierPowerSupply() {
        return existingTierPowerSupply;
    }

    public void setExistingTierPowerSupply(PowerSupply existingTierPowerSupply) {
        this.existingTierPowerSupply = existingTierPowerSupply;
    }

    public ComputerCase getExistingTierComputerCase() {
        return existingTierComputerCase;
    }

    public void setExistingTierComputerCase(ComputerCase existingTierComputerCase) {
        this.existingTierComputerCase = existingTierComputerCase;
    }

    public CPUWaterCooler getExistingTierCPUWaterCooler() {
        return existingTierCPUWaterCooler;
    }

    public void setExistingTierCPUWaterCooler(CPUWaterCooler existingTierCPUWaterCooler) {
        this.existingTierCPUWaterCooler = existingTierCPUWaterCooler;
    }

    public CPUAirCooler getExistingTierCPUAirCooler() {
        return existingTierCPUAirCooler;
    }

    public void setExistingTierCPUAirCooler(CPUAirCooler existingTierCPUAirCooler) {
        this.existingTierCPUAirCooler = existingTierCPUAirCooler;
    }

    public String getExistingTierRAMs() {
        return existingTierRAMs;
    }

    public void setExistingTierRAMs(String existingTierRAMs) {
        this.existingTierRAMs = existingTierRAMs;
    }

    public String getExistingTierGPUs() {
        return existingTierGPUs;
    }

    public void setExistingTierGPUs(String existingTierGPUs) {
        this.existingTierGPUs = existingTierGPUs;
    }

    public String getExistingTierHDDs() {
        return existingTierHDDs;
    }

    public void setExistingTierHDDs(String existingTierHDDs) {
        this.existingTierHDDs = existingTierHDDs;
    }

    public String getExistingTierSSDs() {
        return existingTierSSDs;
    }

    public void setExistingTierSSDs(String existingTierSSDs) {
        this.existingTierSSDs = existingTierSSDs;
    }

}
