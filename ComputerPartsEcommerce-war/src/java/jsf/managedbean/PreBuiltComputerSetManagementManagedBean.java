package jsf.managedbean;

import datamodel.StringValue;
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
import java.util.ArrayList;
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
import util.exception.IncompleteComputerSetException;
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
        currentRams = new ArrayList<>();
        currentGpus = new ArrayList<>();
        currentHdds = new ArrayList<>();
        currentSsds = new ArrayList<>();

        // set currentModel parts to models
        if (currentModel.getCpu() != null) {
            currentCpu = currentModel.getCpu().getName();
        }
        if (currentModel.getMotherboard() != null) {
            currentMotherboard = currentModel.getMotherboard().getName();
        }
        if (currentModel.getPsu() != null) {
            currentPsu = currentModel.getPsu().getName();
        }
        if (currentModel.getCompCase() != null) {
            currentCompCase = currentModel.getCompCase().getName();
        }
        if (currentModel.getWaterCooler() != null) {
            currentWaterCooler = currentModel.getWaterCooler().getName();
        }
        if (currentModel.getAirCooler() != null) {
            currentAirCooler = currentModel.getAirCooler().getName();
        }
        if (currentModel.getRams() != null) {
            for (RAM ram : currentModel.getRams()) {
                currentRams.add(ram.getName());
            }
        }
        if (currentModel.getGpus() != null) {
            for (GPU gpu : currentModel.getGpus()) {
                currentGpus.add(gpu.getName());
            }
        }
        if (currentModel.getHdds() != null) {
            for (HDD hdd : currentModel.getHdds()) {
                currentHdds.add(hdd.getName());
            }
        }
        if (currentModel.getSsds()!= null) {
            for (SSD ssd : currentModel.getSsds()) {
                currentSsds.add(ssd.getName());
            }
        }
        currentPrice = currentModel.getPrice();
        currentIsEnabled = currentModel.getIsEnabled();
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
    
    // Can be used for SelectCheckboxMenu, the RAMs field is an example which I have already tested, every time an option is clicked, this method triggers.
    public void selectEvent(AjaxBehaviorEvent event) {
        System.out.println("***********selected");
    }
    
    public void checkEnabledSwitch(AjaxBehaviorEvent event) {
        if (currentIsEnabled && !finalModelCheck()) {
            currentIsEnabled = false;
            currentModel.setIsEnabled(false);
        } else {
            currentModel.setIsEnabled(currentIsEnabled);
        }
        try {
            preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(currentModel);
        } catch (PreBuiltComputerSetModelNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model does not exist!", null));
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
            if (currentCpu == null) {
                currentModel.setCpu(null);
            }
            if (currentMotherboard == null) {
                currentModel.setMotherboard(null);
            }
            if (currentPsu == null) {
                currentModel.setPsu(null);
            }
            if (currentCompCase == null) {
                currentModel.setCompCase(null);
            }
            if (currentWaterCooler == null) {
                currentModel.setWaterCooler(null);
            }
            if (currentAirCooler == null) {
                currentModel.setAirCooler(null);
            }
        }
        if (fieldName.equals("currentCpu") && currentModel.getCpu() == null) {
            currentIsEnabled = false;
            currentModel.setIsEnabled(false);
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, "CPU is required", null));
        } else if (fieldName.equals("currentMotherboard") && currentModel.getMotherboard() == null) {
            currentIsEnabled = false;
            currentModel.setIsEnabled(false);
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, "Motherboard is required", null));
        } else if (fieldName.equals("currentCompCase") && currentModel.getCompCase() == null) {
            currentIsEnabled = false;
            currentModel.setIsEnabled(false);
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, "Computer Case is required", null));
        } else if (fieldName.equals("currentPsu") && currentModel.getPsu() == null) {
            currentIsEnabled = false;
            currentModel.setIsEnabled(false);
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, "Power Supply is required", null));
        }
        updatePrice();
        try {
            if (partToAdd != null) {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToAdd.getProductId());
            }
        } catch (IncompatiblePartException ex) {
            currentIsEnabled = false;
            currentModel.setIsEnabled(false);
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
        }
        try {
            preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(currentModel);
        } catch (PreBuiltComputerSetModelNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model does not exist!", null));
        }
    }
    
    public void updateModelCollection(AjaxBehaviorEvent event, UIComponent component) {
        List<String> computerPartNames = new ArrayList<>();
        Field field;
        Class thisClass = PreBuiltComputerSetManagementManagedBean.class;
        String clientId = component.getClientId();
        String fieldName = clientId.substring(clientId.indexOf(':') + 1);
        try {
            field = thisClass.getDeclaredField(fieldName);
            System.out.println("*************field name: " + field.getName());
            computerPartNames = (List<String>) field.get(this);
            System.out.println("*************par tname: " + computerPartNames);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error occured: " + ex.getMessage(), null));
        }
        
        ComputerPart partToAdd = null;
        if (fieldName.equals("currentRams")) {
            currentModel.getRams().clear();
        } else if (fieldName.equals("currentGpus")) {
            currentModel.getGpus().clear();
        } else if (fieldName.equals("currentHdds")) {
            currentModel.getHdds().clear();
        } else {
            currentModel.getSsds().clear();
        }
        for (String part : computerPartNames) {
            partToAdd = computerPartSessionBeanLocal.retrieveComputerPartByName(part);
            if (partToAdd instanceof RAM) {
                RAM castedPartToAdd = (RAM) partToAdd;
                currentModel.getRams().add(castedPartToAdd);
            } else if (partToAdd instanceof GPU) {
                GPU castedPartToAdd = (GPU) partToAdd;
                currentModel.getGpus().add(castedPartToAdd);
            } else if (partToAdd instanceof HDD) {
                HDD castedPartToAdd = (HDD) partToAdd;
                currentModel.getHdds().add(castedPartToAdd);
            } else {
                SSD castedPartToAdd = (SSD) partToAdd;
                currentModel.getSsds().add(castedPartToAdd);
            }
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToAdd.getProductId());
            } catch (IncompatiblePartException ex) {
                currentIsEnabled = false;
                currentModel.setIsEnabled(false);
                FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            }
        }
        if (fieldName.equals("currentRams") && currentModel.getRams().isEmpty()) {
            currentIsEnabled = false;
            currentModel.setIsEnabled(false);
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, "RAM is required", null));
        }
        updatePrice();
        try {
            preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(currentModel);
        } catch (PreBuiltComputerSetModelNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model does not exist!", null));
        }
    }
    /*public void updateModelCollectionPart(String componentId, String computerPartName, Boolean isAddition) {
        System.out.println("**************** part name: " + computerPartName);
        ComputerPart computerPart = computerPartSessionBeanLocal.retrieveComputerPartByName(computerPartName);
        if (computerPart instanceof RAM) {
            RAM castedComputerPart = (RAM) computerPart;
            if (isAddition) {
                currentModel.getRams().add(castedComputerPart);
            } else {
                currentModel.getRams().remove(castedComputerPart);
            }
        } else if (computerPart instanceof GPU) {
            GPU castedComputerPart = (GPU) computerPart;
            if (isAddition) {
                currentModel.getGpus().add(castedComputerPart);
            } else {
                currentModel.getGpus().remove(castedComputerPart);
            }
        } else if (computerPart instanceof HDD) {
            HDD castedComputerPart = (HDD) computerPart;
            if (isAddition) {
                currentModel.getHdds().add(castedComputerPart);
            } else {
                currentModel.getHdds().remove(castedComputerPart);
            }
        } else {
            SSD castedComputerPart = (SSD) computerPart;
            if (isAddition) {
                currentModel.getSsds().add(castedComputerPart);
            } else {
                currentModel.getSsds().remove(castedComputerPart);
            }
        }
        updatePrice();
        try {
            preBuiltComputerSetModelSessionBeanLocal.updatePreBuiltComputerSetModel(currentModel);
            if (isAddition) preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, computerPart.getProductId());
        } catch (IncompatiblePartException ex) {
            FacesContext.getCurrentInstance().addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        } catch (ComputerPartNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
        } catch (PreBuiltComputerSetModelNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model does not exist!", null));
        }
    }*/
    

    private void updatePrice() {
        currentPrice = 0.0;
        if (currentModel.getCpu() != null) {
            currentPrice += currentModel.getCpu().getPrice();
        }
        if (currentModel.getMotherboard() != null) {
            currentPrice += currentModel.getMotherboard().getPrice();
        }
        if (currentModel.getPsu() != null) {
            currentPrice += currentModel.getPsu().getPrice();
        }
        if (currentModel.getCompCase() != null) {
            currentPrice += currentModel.getCompCase().getPrice();
        }
        if (currentModel.getWaterCooler() != null) {
            currentPrice += currentModel.getWaterCooler().getPrice();
        }
        if (currentModel.getAirCooler() != null) {
            currentPrice += currentModel.getAirCooler().getPrice();
        }
        for (RAM ram : currentModel.getRams()) {
            currentPrice += ram.getPrice();
        }
        for (GPU gpu : currentModel.getGpus()) {
            currentPrice += gpu.getPrice();
        }
        for (HDD hdd : currentModel.getHdds()) {
            currentPrice += hdd.getPrice();
        }
        for (SSD ssd : currentModel.getSsds()) {
            currentPrice += ssd.getPrice();
        }
        currentModel.setPrice(currentPrice);
    }
    
    private Boolean finalModelCheck() {
        System.out.println("************FINAL MODEL CHECK ENTERED");
        try {
            preBuiltComputerSetModelSessionBeanLocal.finalComputerSetCheck(currentModel);
        } catch (IncompleteComputerSetException ex) {
            FacesContext.getCurrentInstance().addMessage("formModel:currentIsEnabled", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            return false;
        }
        System.out.println("************FINAL MODEL CHECK ENTERED +");
        Boolean modelValid = true;
        if (currentModel.getCpu() != null) {
            CPU partToTest = currentModel.getCpu();
            currentModel.setCpu(null);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentCpu", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentCpu", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.setCpu(partToTest);
            }
        }
        if (currentModel.getMotherboard() != null) {
            System.out.println("****MB CHECK");
            MotherBoard partToTest = currentModel.getMotherboard();
            currentModel.setMotherboard(null);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                System.out.println("*****MB CHECK FLAGGED");
                FacesContext.getCurrentInstance().addMessage("formModel:currentMotherboard", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentMotherboard", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.setMotherboard(partToTest);
            }
        }
        if (currentModel.getPsu() != null) {
            PowerSupply partToTest = currentModel.getPsu();
            currentModel.setPsu(null);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentPsu", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentPsu", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.setPsu(partToTest);
            }
        }
        if (currentModel.getCompCase() != null) {
            ComputerCase partToTest = currentModel.getCompCase();
            currentModel.setCompCase(null);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentCompCase", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentCompCase", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.setCompCase(partToTest);
            }
        }
        if (currentModel.getWaterCooler() != null) {
            CPUWaterCooler partToTest = currentModel.getWaterCooler();
            currentModel.setWaterCooler(null);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentWaterCooler", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentWaterCooler", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.setWaterCooler(partToTest);
            }
        }
        if (currentModel.getAirCooler() != null) {
            CPUAirCooler partToTest = currentModel.getAirCooler();
            currentModel.setAirCooler(null);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentAirCooler", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentAirCooler", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.setAirCooler(partToTest);
            }
        }
        
        List<RAM> tempRamsList = new ArrayList<>();
        for (RAM computerPart : currentModel.getRams()) {
            tempRamsList.add(computerPart);
        }
        for (RAM partToTest : tempRamsList) {
            currentModel.getRams().remove(partToTest);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentRams", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentRams", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.getRams().add(partToTest);
            }
        }
        
        List<GPU> tempGpusList = new ArrayList<>();
        for (GPU computerPart : currentModel.getGpus()) {
            tempGpusList.add(computerPart);
        }
        for (GPU partToTest : tempGpusList) {
            currentModel.getGpus().remove(partToTest);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentGpus", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentGpus", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.getGpus().add(partToTest);
            }
        }
        
        List<HDD> tempHddsList = new ArrayList<>();
        for (HDD computerPart : currentModel.getHdds()) {
            tempHddsList.add(computerPart);
        }
        for (HDD partToTest : tempHddsList) {
            currentModel.getHdds().remove(partToTest);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentHdds", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentHdds", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.getHdds().add(partToTest);
            }
        }
        
        List<SSD> tempSsdsList = new ArrayList<>();
        for (SSD computerPart : currentModel.getSsds()) {
            tempSsdsList.add(computerPart);
        }
        for (SSD partToTest : tempSsdsList) {
            currentModel.getSsds().remove(partToTest);
            try {
                preBuiltComputerSetModelSessionBeanLocal.compatibilityCheck(currentModel, partToTest.getProductId());
            } catch (IncompatiblePartException ex) {
                modelValid = false;
                FacesContext.getCurrentInstance().addMessage("formModel:currentSsds", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            } catch (ComputerPartNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage("formModel:currentSsds", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer part does not exist!", null));
            } finally {
                currentModel.getSsds().add(partToTest);
            }
        }
        return modelValid;
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
