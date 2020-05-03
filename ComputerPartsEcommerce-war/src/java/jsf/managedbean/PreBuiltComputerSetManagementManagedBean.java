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

    private String stringRams;

    private List<StringValue> stringValuesRams;
    private String addRam;
    private List<StringValue> stringValuesGpus;
    private String addGpu;
    private List<StringValue> stringValuesHdds;
    private String addHdd;
    private List<StringValue> stringValuesSsds;
    private String addSsd;

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
        stringValuesRams = new ArrayList<>();
        stringValuesGpus = new ArrayList<>();
        stringValuesHdds = new ArrayList<>();
        stringValuesSsds = new ArrayList<>();
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
        if (!(currentModel.getRams().isEmpty())) {
            for (RAM r : currentModel.getRams()) {
                stringValuesRams.add(new StringValue(r.getName()));
            }
        }
        if (currentModel.getGpus() != null) {
            for (GPU gpu : currentModel.getGpus()) {
                currentGpus.add(gpu.getName());
            }
        }
        if (!(currentModel.getGpus().isEmpty())) {
            for (GPU gpu : currentModel.getGpus()) {
                stringValuesGpus.add(new StringValue(gpu.getName()));
            }
        }
        if (currentModel.getHdds() != null) {
            for (HDD hdd : currentModel.getHdds()) {
                currentHdds.add(hdd.getName());
            }
        }
        if (!(currentModel.getHdds().isEmpty())) {
            for (HDD hdd : currentModel.getHdds()) {
                stringValuesHdds.add(new StringValue(hdd.getName()));
            }
        }

        setExistingTierRAMs(Arrays.toString(currentModel.getRams().toArray()));
        setExistingTierGPUs(Arrays.toString(currentModel.getGpus().toArray()));
        setExistingTierHDDs(Arrays.toString(currentModel.getHdds().toArray()));
        setExistingTierSSDs(Arrays.toString(currentModel.getSsds().toArray()));
        currentPrice = currentModel.getPrice();
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

    public void testAddRams(final AjaxBehaviorEvent event) {
        System.out.println(addRam);
    }

    public void testAddGpus(final AjaxBehaviorEvent event) {
        System.out.println(addGpu);
    }

    public void addRams(ActionEvent event) {
        stringValuesRams.add(new StringValue(getAddRam()));
        currentRams.add(addRam);
        updateModelCollectionPart("formModel:rams", addRam, true);

        setAddRam(null);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ram added", null));
    }

    public void removeRam(final StringValue stringValue) {
        stringValuesRams.remove(stringValue);
        currentRams.remove(stringValue.getValue());

        updateModelCollectionPart("formModel:rams", stringValue.getValue(), false);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ram removed", null));
    }

    public void addGpus(ActionEvent event) {
        stringValuesGpus.add(new StringValue(getAddGpu()));
        currentGpus.add(addGpu);
        updateModelCollectionPart("formModel:gpus", addGpu, true);

        setAddGpu(null);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gpu added", null));
    }

    public void removeGpu(final StringValue stringValue) {
        stringValuesGpus.remove(stringValue);
        currentGpus.remove(stringValue.getValue());

        updateModelCollectionPart("formModel:gpus", stringValue.getValue(), false);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gpu removed", null));
    }

    public void addHdds(ActionEvent event) {
        stringValuesHdds.add(new StringValue(getAddHdd()));
        currentHdds.add(addHdd);
        updateModelCollectionPart("formModel:hdds", addHdd, true);

        setAddHdd(null);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hdd added", null));
    }

    public void removeHdd(final StringValue stringValue) {
        stringValuesHdds.remove(stringValue);
        currentHdds.remove(stringValue.getValue());

        updateModelCollectionPart("formModel:hdds", stringValue.getValue(), false);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hdd removed", null));
    }

    public void addSsds(ActionEvent event) {
        stringValuesSsds.add(new StringValue(getAddSsd()));
        currentSsds.add(addSsd);
        updateModelCollectionPart("formModel:ssds", addSsd, true);

        setAddSsd(null);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ssd added", null));
    }

    public void removeSsd(final StringValue stringValue) {
        stringValuesSsds.remove(stringValue);
        currentSsds.remove(stringValue.getValue());

        updateModelCollectionPart("formModel:ssds", stringValue.getValue(), false);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ssd removed", null));
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
//                currentModel.getRams().clear();
//                for (Long l : currentRams) {
//                    currentModel.getRams().add(computerPartSessionBeanLocal.retrieveRAMById(l));
//                }
            }
            if (!currentGpus.isEmpty()) {
//                currentModel.getGpus().clear();
//                for (Long l : currentGpus) {
//                    currentModel.getGpus().add(computerPartSessionBeanLocal.retrieveGPUById(l));
//                }
            }
            if (!currentHdds.isEmpty()) {
//                currentModel.getHdds().clear();
//                for (Long l : currentHdds) {
//                    currentModel.getHdds().add(computerPartSessionBeanLocal.retrieveHDDById(l));
//                }
            }
            if (!currentSsds.isEmpty()) {
//                currentModel.getSsds().clear();
//                for (Long l : currentSsds) {
//                    currentModel.getSsds().add(computerPartSessionBeanLocal.retrieveSSDById(l));
//                }
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
        }
//        catch (ComputerPartNotFoundException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Part does not exist!", null));
//        }
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
    public void updateModelCollectionPart(String componentId, String computerPartName, Boolean isAddition) {
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
    }
    

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

    public List<StringValue> getStringValuesRams() {
        return stringValuesRams;
    }

    public void setStringValuesRams(List<StringValue> stringValuesRams) {
        this.stringValuesRams = stringValuesRams;
    }

    public String getAddRam() {
        return addRam;
    }

    public void setAddRam(String addRam) {
        this.addRam = addRam;
    }

    public List<StringValue> getStringValuesGpus() {
        return stringValuesGpus;
    }

    public void setStringValuesGpus(List<StringValue> stringValuesGpus) {
        this.stringValuesGpus = stringValuesGpus;
    }

    public String getAddGpu() {
        return addGpu;
    }

    public void setAddGpu(String addGpu) {
        this.addGpu = addGpu;
    }

    public List<StringValue> getStringValuesHdds() {
        return stringValuesHdds;
    }

    public void setStringValuesHdds(List<StringValue> stringValuesHdds) {
        this.stringValuesHdds = stringValuesHdds;
    }

    public String getAddHdd() {
        return addHdd;
    }

    public void setAddHdd(String addHdd) {
        this.addHdd = addHdd;
    }

    public List<StringValue> getStringValuesSsds() {
        return stringValuesSsds;
    }

    public void setStringValuesSsds(List<StringValue> stringValuesSsds) {
        this.stringValuesSsds = stringValuesSsds;
    }

    public String getAddSsd() {
        return addSsd;
    }

    public void setAddSsd(String addSsd) {
        this.addSsd = addSsd;
    }

}
