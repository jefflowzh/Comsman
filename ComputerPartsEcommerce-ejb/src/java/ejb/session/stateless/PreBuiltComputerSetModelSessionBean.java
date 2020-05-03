package ejb.session.stateless;

import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.ComputerSet;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.PreBuiltComputerSetModel;
import entity.RAM;
import entity.SSD;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ComputerPartNotFoundException;
import util.exception.ComputerSetTierAlreadyExistsException;
import util.exception.IncompatiblePartException;
import util.exception.IncompleteComputerSetException;
import util.exception.PreBuiltComputerSetModelNotFoundException;

@Stateless
public class PreBuiltComputerSetModelSessionBean implements PreBuiltComputerSetModelSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewPreBuiltComputerSetModel(PreBuiltComputerSetModel preBuiltComputerSetModel) throws ComputerSetTierAlreadyExistsException {
        Query query = em.createQuery("SELECT c FROM PreBuiltComputerSetModel c");
        List<PreBuiltComputerSetModel> models = query.getResultList();
        for (PreBuiltComputerSetModel model : models) {
            if (model.getPreBuiltComputerSetTier().equals(preBuiltComputerSetModel.getPreBuiltComputerSetTier())) {
                throw new ComputerSetTierAlreadyExistsException(model.getPreBuiltComputerSetTier() + " tier already exists!");
            }
        }
        em.persist(preBuiltComputerSetModel);
        em.flush();
        return preBuiltComputerSetModel.getPreBuiltComputerSetModelId();
    }
    
    @Override
    public List<PreBuiltComputerSetModel> retrieveAllPreBuiltComputerSetModels() {
        Query query = em.createQuery("SELECT c FROM PreBuiltComputerSetModel c");
        List<PreBuiltComputerSetModel> models = query.getResultList();
        for (PreBuiltComputerSetModel model : models) {
            model.getRams().size();
            model.getGpus().size();
            model.getHdds().size();
            model.getSsds().size();
        }
        return models;
    }
    
    @Override
    public void updatePreBuiltComputerSetModel(PreBuiltComputerSetModel preBuiltComputerSetModel) throws PreBuiltComputerSetModelNotFoundException {
        PreBuiltComputerSetModel model = em.find(PreBuiltComputerSetModel.class, preBuiltComputerSetModel.getPreBuiltComputerSetModelId());
        if (model == null) {
            throw new PreBuiltComputerSetModelNotFoundException("Model does not exist!");
        }
        em.merge(preBuiltComputerSetModel);
    }
    
    /*
    *This method checks the compatibility of a given part against a given computerset
    *The part is given in a long id of a computer part
    *if the method returns true, then the part can be added into the computerset.
    *if the method throws an error, either the part does not exist or the part is not compatible
    */
    
    @Override
    public boolean compatibilityCheck(PreBuiltComputerSetModel model, Long current) throws IncompatiblePartException, ComputerPartNotFoundException{
        System.out.println("******************Check started");
        boolean flag = true;
        ComputerPart currentComputerPart = em.find(ComputerPart.class, current);
        if(currentComputerPart instanceof CPU){
            
        }
        else if(currentComputerPart instanceof CPU){
            CPU currentPart = (CPU) currentComputerPart;
            flag = cpuCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof MotherBoard){
            MotherBoard currentPart = (MotherBoard) currentComputerPart;
            flag = motherboardCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof RAM){
            RAM currentPart = (RAM) currentComputerPart;
            flag = ramCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof GPU){
            GPU currentPart = (GPU) currentComputerPart;
            flag = gpuCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof HDD){
            HDD currentPart = (HDD) currentComputerPart;
            flag = hddCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof SSD){
            SSD currentPart = (SSD) currentComputerPart;
            flag = ssdCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof PowerSupply){
            PowerSupply currentPart = (PowerSupply) currentComputerPart;
            flag = psuCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof ComputerCase){
            ComputerCase currentPart = (ComputerCase) currentComputerPart;
            flag = caseCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof CPUAirCooler){
            CPUAirCooler currentPart = (CPUAirCooler) currentComputerPart;
            flag = airCoolerCheck(model, currentPart);
        }
        else if(currentComputerPart instanceof CPUWaterCooler){
            CPUWaterCooler currentPart = (CPUWaterCooler) currentComputerPart;
            flag = waterCoolerCheck(model, currentPart);
        }
        else{
            throw new ComputerPartNotFoundException("Computer part cannot be found. Please try another part.");
        }
        
        
        return flag;
    }
    
    
    //return true if all checks are compatible
    private boolean cpuCheck(PreBuiltComputerSetModel model, CPU current) throws IncompatiblePartException{
       boolean flag = true;
        
        if(model.getMotherboard() == null || model.getMotherboard().getSocket().equals(current.getSocket())){
            flag = true;
        } else {
            throw new IncompatiblePartException("Incompatible with Motherboard, please select another CPU or Motherboard");
        }
        
        if(model.getAirCooler() != null){
            flag = false;
            for(String chip : model.getAirCooler().getCPUChipCompatibility()){
                if(current.getSocket().equalsIgnoreCase(chip)){
                    flag = true;
                    break;
                }
            }
        }
        
        if(model.getWaterCooler() != null){
            flag = false;
            for(String chip : model.getWaterCooler().getCPUChipCompatibility()){
                if(current.getSocket().equalsIgnoreCase(chip)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    
    private boolean motherboardCheck(PreBuiltComputerSetModel model, MotherBoard current) throws IncompatiblePartException{
        System.out.println("****************** moterhboard Check started");
        boolean flag = true;
        
        //check that motherboard and cpu sockets match
        if(model.getCpu() == null || model.getMotherboard().getSocket().equals(current.getSocket())){
            flag = true;
        }
        else{
            throw new IncompatiblePartException("Incompatible with CPU, please select another motherboard or CPU");
        }
        
        //check the motherboard and the case form factor
        if(model.getCompCase() != null){
            boolean formfactor = false;
            for(String s : model.getCompCase().getMotherBoardFormFactor()){
                if(s.equals(current.getFormFactor())){
                    formfactor = true;
                }
            }
            if(formfactor == false){
                System.out.println("*************form factor issue");
                throw new IncompatiblePartException("Incompatible with Computer Case, please select another Motherboard or Computer Case");
            }
            else{
                flag = true;
            }
            
        }
        
        //check the motherboard and the ram
       
        if(model.getRams() != null && model.getRams().isEmpty() == false){
            
            int totalSticks = 0;
            for (RAM ram : model.getRams()){
                totalSticks += ram.getSticks();
                if(totalSticks > current.getMemorySlot()){
                    throw new IncompatiblePartException("Insufficient RAM slots for selected RAM, please select another Motherboard or reduce the number of RAM modules");
                }
             }
            
            boolean totalRamCheck = true;
            for(RAM ram : model.getRams()){  
                boolean currentRamCheck = false;
                for(String ramspeed : current.getSuportedMemorySpeed()){
                    if(ram.getSpeed().equals(ramspeed)){
                        currentRamCheck = true;
                        break;
                    }
                }
                if (currentRamCheck == false){
                    System.out.println("**********RAM CHECK ISSUE");
                    totalRamCheck = false;
                    throw new IncompatiblePartException("Incompatible with RAM, please select another Motherboard or RAM");
                }
            }
        }
        
        //check the mother board and the GPU
        if(model.getGpus() != null){
            if(model.getGpus().size() > current.getPCIEx16()){
                throw new IncompatiblePartException("Insufficient PCIex16 slots, please select another Motherboard or reduce the number of GPUs");
            }
            else{
                flag = true;
            }
                
        }
        //check that the motherboard supports NVME and has enough slots
        if(model.getSsds().isEmpty() == false){
            int totalNvme = 0;
            for(SSD ssd : model.getSsds()){
                if(ssd.getNVME() == true){
                    totalNvme += 1;
                }
            }
            
            if(totalNvme > current.getM2Slot()){
                throw new IncompatiblePartException("Insufficient M.2 slots, please select another MotherBoard or reduce the number of NVME SSDs");
            }
        }
        
        
        return flag;
    }
    
    private boolean ramCheck(PreBuiltComputerSetModel model, RAM current) throws IncompatiblePartException{
        boolean flag = true;
        
        //checks that the number of sticks do not exceed 8
        int totalSticks = 0;
        if(model.getRams() != null && model.getRams().isEmpty() == false){
            for(RAM ram : model.getRams()){
                totalSticks += ram.getSticks();
            }
            totalSticks += current.getSticks();
            
            if(totalSticks > 8){
                throw new IncompatiblePartException("Number of RAM sticks exceeds 8, please reduce the number of RAM sticks");
            }
        } 
        
        // check that the selected motherboard in model has enough RAM slots
        // check that the motherBoard supports the RAM speed
        if(model.getMotherboard() != null){
            if(totalSticks > model.getMotherboard().getMemorySlot()){
                throw new IncompatiblePartException("Insufficient RAM slots in Motherboard, please reduce the number of RAM sticks or select another Motherboard");
            }
            
            boolean supportedSpeed = false;
            for(String speed : model.getMotherboard().getSuportedMemorySpeed()){
                if(speed.equals(current.getSpeed())){
                    supportedSpeed = true;
                    break;
                }
            }
            
            if(supportedSpeed = false){
                throw new IncompatiblePartException("RAM speed not supported by Motherboard, please select another RAM or Motherboard");
            }
        }
        
        
        return flag;
    }
     
    private boolean hddCheck(PreBuiltComputerSetModel model, HDD current) throws IncompatiblePartException{
        boolean flag = true;
        
        if(model.getPsu() != null){
            int totalStorage = 1;
            if(model.getHdds().isEmpty() == false){
                totalStorage += model.getHdds().size();
            }
            if(model.getSsds().isEmpty() == false){
                for(SSD ssd : model.getSsds()){
                    if(ssd.getNVME() == false){
                        totalStorage += 1;
                    }
                }
            }
            
            if (totalStorage > model.getPsu().getSATAConnectors()){
                throw new IncompatiblePartException("Insufficient SATA power cables in power supply unit, please select another HDD or Power Supply Unit");
            }
        }
        
        
        return flag;
    }
     
    private boolean ssdCheck(PreBuiltComputerSetModel model, SSD current) throws IncompatiblePartException{
        boolean flag = true;
        
        if(model.getPsu() != null && current.getNVME() == false){
            int totalStorage = 1;
            if(model.getHdds().isEmpty() == false){
                totalStorage += model.getHdds().size();
            }
            if(model.getSsds().isEmpty() == false){
                for(SSD ssd : model.getSsds()){
                    if(ssd.getNVME() == false){
                        totalStorage += 1;
                    }
                }
            }
            
            if (totalStorage > model.getPsu().getSATAConnectors()){
                throw new IncompatiblePartException("Insufficient SATA power cables in power supply unit, please select another SSD or Power Supply Unit");
            }
        }
        else if(model.getMotherboard() != null && current.getNVME() == true){
            int totalNvme = 1;
            if(model.getSsds().isEmpty() == false){
                for(SSD ssd : model.getSsds()){
                    if(ssd.getNVME() == true){
                        totalNvme += 1;
                    }
                }
            }
            if (totalNvme > model.getMotherboard().getM2Slot()){
                throw new IncompatiblePartException("Insufficient M.2 slots, please reduce the number of NVME SSDs or select another MotherBoard");
            }
        }
        
        
        return flag;
    }
     
    private boolean gpuCheck(PreBuiltComputerSetModel model, GPU current) throws IncompatiblePartException{
        boolean flag = true;
        
        if(model.getCompCase() != null){
            if(current.getLength() > model.getCompCase().getMaxVideoCardLength()){
                throw new IncompatiblePartException("Incompatible with Computer Case, please select another GPU or Computer Case");
            }
            
            //total expansion slots
            int totalExpansionSlot = current.getExpansionSlotWidth();
            if(model.getGpus().isEmpty() == false){
                for(GPU gpu : model.getGpus()){
                    totalExpansionSlot += gpu.getExpansionSlotWidth();
                }
                if(totalExpansionSlot > model.getCompCase().getFullHeightExpansionSlot()){
                    throw new IncompatiblePartException("Insufficient expansion slots to support GPU, please select another GPU or Computer Case");
                }
            }
        }
        
        if(model.getMotherboard() != null){
            int totalPcie16 = 1;
            totalPcie16 += model.getGpus().size();
            if(model.getMotherboard().getPCIEx16() < totalPcie16){
                throw new IncompatiblePartException("Insufficient PCIe x16 slots, please select another Motherboard or GPU");
            }
        }
        
        return flag;
    }
     
    private boolean psuCheck(PreBuiltComputerSetModel model, PowerSupply current) throws IncompatiblePartException{
        boolean flag = true;
            
            
            int totalStorage = 0;
            if(model.getHdds().isEmpty() == false){
                totalStorage += model.getHdds().size();
            }
            if(model.getSsds().isEmpty() == false){
                for(SSD ssd : model.getSsds()){
                    if(ssd.getNVME() == false){
                        totalStorage += 1;
                    }
                }
            
            
                if (totalStorage > current.getSATAConnectors()){
                    throw new IncompatiblePartException("Insufficient SATA power cables to support storage, please select another Power Supply Unit, HDD, or SSD");
                }
            }
        
        return flag;
    }
     
    private boolean caseCheck(PreBuiltComputerSetModel model, ComputerCase current) throws IncompatiblePartException{
        boolean flag = true;
            //mother board
            if(model.getMotherboard() != null){
                boolean formfactor = false;
                for(String s : current.getMotherBoardFormFactor()){
                    if(s.equals(model.getMotherboard().getFormFactor())){
                        formfactor = true;
                    }
                }
                if(formfactor == false){
                    throw new IncompatiblePartException("Incompatible with Motherboard, please select another Computer Case or Motherboard");
                }
            }
            
            //gpu
            if(model.getGpus() != null && model.getGpus().isEmpty() == false){
                int totalExpansionLength = 0;
                for(GPU gpu : model.getGpus()){
                    totalExpansionLength += gpu.getExpansionSlotWidth();
                    if (gpu.getLength() > current.getMaxVideoCardLength()){
                        throw new IncompatiblePartException("Incompatible with GPU, please select another Computer Case or GPU");
                    }
                    if(totalExpansionLength > current.getFullHeightExpansionSlot()){
                        throw new IncompatiblePartException("Incompatible with GPU, please select another Computer Case or GPU");
                    }
                }
            }
            
            //aio
            if(model.getWaterCooler() != null){
                if(model.getWaterCooler().getRadiatorSize() > current.getFrontFanSupport() || model.getWaterCooler().getRadiatorSize() > current.getTopFanSupport() || model.getWaterCooler().getRadiatorSize() > current.getRearFanSupport() ){
                    throw new IncompatiblePartException("Incompatible with Water Cooler, pleaser select another Computer Case or Water Cooler");
                } 
            }
        
        return flag;
    }
     
    private boolean airCoolerCheck(PreBuiltComputerSetModel model, CPUAirCooler current) throws IncompatiblePartException{
        boolean flag = true;
            
            //cpu
            if(model.getCpu() != null){
            flag = false;
            for(String chip : current.getCPUChipCompatibility()){
                if(model.getCpu().getSocket().equalsIgnoreCase(chip)){
                    flag = true;
                    break;
                }
            }
            if (flag == false){
                throw new IncompatiblePartException("Incompatible with CPU, please select another Air Cooler or CPU");
            }
        }
        
        return flag;
    }
     
    private boolean waterCoolerCheck(PreBuiltComputerSetModel model, CPUWaterCooler current) throws IncompatiblePartException{
        boolean flag = true;
            
            //cpu
        if(model.getCpu() != null){
        flag = false;
            for(String chip : current.getCPUChipCompatibility()){
                if(model.getCpu().getSocket().equalsIgnoreCase(chip)){
                    flag = true;
                    break;
                }
            }
            if (flag == false){
                throw new IncompatiblePartException("Incompatible with CPU, please select another Water Cooler or CPU");
            }
        }
        
         if(model.getCompCase() != null){
                if(current.getRadiatorSize() > model.getCompCase().getFrontFanSupport() || current.getRadiatorSize() > model.getCompCase().getTopFanSupport() || current.getRadiatorSize() > model.getCompCase().getRearFanSupport() ){
                    throw new IncompatiblePartException("Incompatible with Computer Case, please select another Water Cooler or Computer Case");
                } 
            }
        
        
        
        return flag;
    }
     
     /*
     *this method should be done before the computerset is persisted into the database.
     *the idea is that this does not run coompatibibility checks but ensures that all parts of a computer are indeed there
     *returns true if the computer can be persisted
     *if it returns false or error, it means there is a necessary part of the computer that has not been selected and the set should not be persisted
     */
    @Override
    public boolean finalComputerSetCheck(PreBuiltComputerSetModel set)throws IncompleteComputerSetException{
         
    /*
     compulsory parts that must be there. 
     cpu
     motherboard
     case
     powersupply
     RAM
     */
    
        if(set.getCpu() == null){
            throw new IncompleteComputerSetException("Computer set is missing a CPU");
        }
        
        if(set.getMotherboard() == null){
            throw new IncompleteComputerSetException("Computer set is missing a Motherboard");
        }
        
        if(set.getCompCase() == null){
            throw new IncompleteComputerSetException("Computer set is missing a Computer Case");
        }
        
        if(set.getPsu() == null){
            throw new IncompleteComputerSetException("Computer set is missing a Power Supply Unit");
        }
        
        if(set.getRams() == null || set.getRams().isEmpty() == true){
            throw new IncompleteComputerSetException("Computer set is missing RAM");
        }
        
        
         
     /*
     there is a need to ensure that the power supply choosen can power all the components that have been selected. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
     */
        int requiredPower = 400;
        for(GPU gpu : set.getGpus()){
            requiredPower += gpu.getTDP();
        }
        if(set.getPsu().getWattage() < requiredPower){
            throw new IncompleteComputerSetException("Computer set's required power is greater than Power Supply Unit output");
        }
     
     /*
     there is a need to ensure that the computer has some form of graphics. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
     */
        if((set.getGpus() == null || set.getGpus().isEmpty() == true) && set.getCpu().getHasIntergratedGraphics() == false){
            throw new IncompleteComputerSetException("Computer set requires GPUs");
        }
     
     /*
     there is a need to ensure that the computer has some form of storage. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
     */
        if((set.getHdds() == null || set.getHdds().isEmpty() == true) && (set.getSsds() == null || set.getSsds().isEmpty() == true)){
            throw new IncompleteComputerSetException("Computer set requires HDD or SSD storage");
        }
     
     /*
     there is a need to ensure that the computer has some form of cpu cooling. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
     */
        if((set.getWaterCooler() == null) && (set.getAirCooler() == null ) && (set.getCpu().getHasIntergratedGraphics() == false)){
            throw new IncompleteComputerSetException("Computer set requires a Water Cooler or Air Cooler");
        }
     
     
        return true;
    }
}
