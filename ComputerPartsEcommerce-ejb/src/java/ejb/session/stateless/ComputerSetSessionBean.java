package ejb.session.stateless;

import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.ComputerSet;
import entity.GPU;
import entity.HDD;
import entity.LineItem;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.RAM;
import entity.SSD;
import entity.Staff;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ComputerPartNotFoundException;
import util.exception.ComputerSetNotFoundException;
import util.exception.LineItemNotFoundException;
import util.exception.IncompatiblePartException;
import util.exception.IncompleteComputerSetException;
import util.exception.StaffNotFoundException;

@Stateless
public class ComputerSetSessionBean implements ComputerSetSessionBeanLocal {

    @EJB
    private LineItemSessionBeanLocal lineItemSessionBeanLocal;

    @EJB
    private StaffSessionBeanLocal staffSessionBeanLocal;

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    // create set first, assign staff seperately later
    public Long createNewComputerSet(ComputerSet newComputerSet, Long lineItemId) throws LineItemNotFoundException {
        LineItem lineItem = lineItemSessionBeanLocal.retrieveLineItemById(lineItemId);
        newComputerSet.setLineItem(lineItem);
        lineItem.setComputerSet(newComputerSet);

        em.persist(newComputerSet);
        em.flush();

        return newComputerSet.getComputerSetId();
    }

    @Override
    public List<Long> createNewComputerSet(LineItem lineItem) {
        //Make X line items and computer sets where X is the quantity of lineItem, each line item should contain only 1 computer set as each computer set can only be assigned to 1 staff (i.e. it will be inappropriate to assigned a computer set with quantity 100 to 1 staff.
        ComputerSet computerSetModel = lineItem.getComputerSet();
        Integer quantity = lineItem.getQuantity();
        List<Long> computerSetIds = new ArrayList<>();

        for (int computerSet = 0; computerSet < quantity; computerSet++) {
            LineItem newLineItem = lineItemSessionBeanLocal.createNewLineItem(new LineItem(1));

            ComputerSet newComputerSet = new ComputerSet();
            if (computerSetModel.getAirCooler() != null) {
                newComputerSet.setAirCooler(computerSetModel.getAirCooler());
            }
            if (computerSetModel.getCompCase() != null) {
                newComputerSet.setCompCase(computerSetModel.getCompCase());
            }
            if (computerSetModel.getCpu() != null) {
                newComputerSet.setCpu(computerSetModel.getCpu());
            }
            if (computerSetModel.getGpus() != null) {
                newComputerSet.setGpus(computerSetModel.getGpus());
            }
            if (computerSetModel.getHdds() != null) {
                newComputerSet.setHdds(computerSetModel.getHdds());
            }
            if (computerSetModel.getMotherBoard() != null) {
                newComputerSet.setMotherBoard(computerSetModel.getMotherBoard());
            }
            if (computerSetModel.getPrice() != null) {
                newComputerSet.setPrice(computerSetModel.getPrice());
            }
            if (computerSetModel.getPsu() != null) {
                newComputerSet.setPsu(computerSetModel.getPsu());
            }
            if (computerSetModel.getRams() != null) {
                newComputerSet.setRams(computerSetModel.getRams());
            }
            if (computerSetModel.getSsds() != null) {
                newComputerSet.setSsds(computerSetModel.getSsds());
            }
            if (computerSetModel.getWarrantyLengthInYears() != null) {
                newComputerSet.setWarrantyLengthInYears(computerSetModel.getWarrantyLengthInYears());
            }
            if (computerSetModel.getWaterCooler() != null) {
                newComputerSet.setWaterCooler(computerSetModel.getWaterCooler());
            }
            newComputerSet.setLineItem(newLineItem);
            newLineItem.setComputerSet(newComputerSet);

            em.persist(newComputerSet);
            em.flush();
            computerSetIds.add(newComputerSet.getComputerSetId());
        }

        return computerSetIds;
    }

    @Override
    public ComputerSet retrieveComputerSetById(Long computerSetId) throws ComputerSetNotFoundException {
        ComputerSet computerSet = em.find(ComputerSet.class, computerSetId);

        if (computerSet != null) {
            return computerSet;
        } else {
            throw new ComputerSetNotFoundException("Computer Set ID " + computerSetId + " does not exist!");
        }
    }

    @Override
    // Put staffId as non-null if there is need for association/disassociation/replacement
    // Put computerPartId as non-null to add a computer part
    public void updateComputerSet(ComputerSet computerSet, Long staffId) throws StaffNotFoundException {

        ComputerSet updatedComputerSet = em.merge(computerSet);

        // When need to change staff and computer assignments
        if (staffId != null) {
            Staff staff = staffSessionBeanLocal.retrieveStaffById(staffId, false, true);
            if (updatedComputerSet.getAssemblyAssignedTo() == null) {
                // do association
                updatedComputerSet.setAssemblyAssignedTo(staff);
                staff.getAssignedComputerSets().add(updatedComputerSet);
            } else if (updatedComputerSet.getAssemblyAssignedTo() == staff) {
                // do disassociation
                updatedComputerSet.setAssemblyAssignedTo(null);
                staff.getAssignedComputerSets().remove(updatedComputerSet);
            } else {
                // do replacement
                updatedComputerSet.getAssemblyAssignedTo().getAssignedComputerSets().remove(updatedComputerSet);
                updatedComputerSet.setAssemblyAssignedTo(staff);
                staff.getAssignedComputerSets().add(updatedComputerSet);
            }
        }
    }

    /*
    *This method checks the compatibility of a given part against a given computerset
    *The part is given in a long id of a computer part
    *if the method returns true, then the part can be added into the computerset.
    *if the method throws an error, either the part does not exist or the part is not compatible
     */
    public boolean compatibilityCheck(ComputerSet set, Long current) throws IncompatiblePartException, ComputerPartNotFoundException {
        boolean flag = true;
        ComputerPart currentComputerPart = em.find(ComputerPart.class, current);
        if (currentComputerPart instanceof CPU) {

        } else if (currentComputerPart instanceof CPU) {
            CPU currentPart = (CPU) currentComputerPart;
            flag = cpuCheck(set, currentPart);
        } else if (currentComputerPart instanceof MotherBoard) {
            MotherBoard currentPart = (MotherBoard) currentComputerPart;
            flag = motherBoardCheck(set, currentPart);
        } else if (currentComputerPart instanceof RAM) {
            RAM currentPart = (RAM) currentComputerPart;
            flag = ramCheck(set, currentPart);
        } else if (currentComputerPart instanceof GPU) {
            GPU currentPart = (GPU) currentComputerPart;
            flag = gpuCheck(set, currentPart);
        } else if (currentComputerPart instanceof HDD) {
            HDD currentPart = (HDD) currentComputerPart;
            flag = hddCheck(set, currentPart);
        } else if (currentComputerPart instanceof SSD) {
            SSD currentPart = (SSD) currentComputerPart;
            flag = ssdCheck(set, currentPart);
        } else if (currentComputerPart instanceof PowerSupply) {
            PowerSupply currentPart = (PowerSupply) currentComputerPart;
            flag = psuCheck(set, currentPart);
        } else if (currentComputerPart instanceof ComputerCase) {
            ComputerCase currentPart = (ComputerCase) currentComputerPart;
            flag = caseCheck(set, currentPart);
        } else if (currentComputerPart instanceof CPUAirCooler) {
            CPUAirCooler currentPart = (CPUAirCooler) currentComputerPart;
            flag = airCoolerCheck(set, currentPart);
        } else if (currentComputerPart instanceof CPUWaterCooler) {
            CPUWaterCooler currentPart = (CPUWaterCooler) currentComputerPart;
            flag = waterCoolerCheck(set, currentPart);
        } else {
            throw new ComputerPartNotFoundException("Computer Part Cannot be found. Please try another part.");
        }

        return flag;
    }

    //return true if all checks are compatible
    public boolean cpuCheck(ComputerSet set, CPU current) throws IncompatiblePartException {
        boolean flag = true;

        if (set.getMotherBoard() == null || set.getMotherBoard().getSocket().equals(current.getSocket())) {
            flag = true;
        } else {
            throw new IncompatiblePartException("selected CPU has compatibliity issues with computer set's MotherBoard.");
        }

        if (set.getAirCooler() != null) {
            flag = false;
            for (String chip : set.getAirCooler().getCPUChipCompatibility()) {
                if (current.getSocket().equalsIgnoreCase(chip)) {
                    flag = true;
                    break;
                }
            }
        }

        if (set.getWaterCooler() != null) {
            flag = false;
            for (String chip : set.getWaterCooler().getCPUChipCompatibility()) {
                if (current.getSocket().equalsIgnoreCase(chip)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean motherBoardCheck(ComputerSet set, MotherBoard current) throws IncompatiblePartException {

        boolean flag = true;

        //check that motherboard and cpu sockets match
        if (set.getCpu() == null || set.getCpu().getSocket().equals(current.getSocket())) {
            flag = true;
        } else {
            throw new IncompatiblePartException("selected Mother board has compatibliity issues with computer set's CPU.");
        }

        //check the motherboard and the case form factor
        if (set.getCompCase() != null) {
            boolean formfactor = false;
            for (String s : set.getCompCase().getMotherBoardFormFactor()) {
                if (s.equals(current.getFormFactor())) {
                    formfactor = true;
                }
            }
            if (formfactor == false) {
                throw new IncompatiblePartException("selected Mother board has compatiblity issues with computer set's case.");
            } else {
                flag = true;
            }

        }

        //check the motherboard and the ram
        if (set.getRams() != null && set.getRams().isEmpty() == false) {

            int totalSticks = 0;
            for (RAM ram : set.getRams()) {
                totalSticks += ram.getSticks();
                if (totalSticks > current.getMemorySlot()) {
                    throw new IncompatiblePartException("selected Mother board does not have enough RAM slots for selected RAM. Please choose another mother board or reduce the number of memory modules");
                }
            }

            boolean totalRamCheck = true;
            for (RAM ram : set.getRams()) {
                boolean currentRamCheck = false;
                for (String ramspeed : current.getSuportedMemorySpeed()) {
                    if (ram.getSpeed().equals(ramspeed)) {
                        currentRamCheck = true;
                        break;
                    }
                }
                if (currentRamCheck == false) {
                    totalRamCheck = false;
                    throw new IncompatiblePartException("selected Mother Board has compatibility issues with computer set's case.");
                }
            }
        }

        //check the mother board and the GPU
        if (set.getGpus() != null) {
            if (set.getGpus().size() > current.getPCIEx16()) {
                throw new IncompatiblePartException("Mother board does not have enough PCIex16 slots. Please choose another mother board or reduce number of GPUs and try again");
            } else {
                flag = true;
            }

        }
        //check that the motherboard supports NVME and has enough slots
        if (set.getSsds().isEmpty() == false) {
            int totalNvme = 0;
            for (SSD ssd : set.getSsds()) {
                if (ssd.getNVME() == true) {
                    totalNvme += 1;
                }
            }

            if (totalNvme > current.getM2Slot()) {
                throw new IncompatiblePartException("MotherBoard does not have enough m.2 slots. Please select another MotherBoard or reduse the amount of NVME SSDs");
            }
        }

        return flag;
    }

    public boolean ramCheck(ComputerSet set, RAM current) throws IncompatiblePartException {
        boolean flag = true;

        //checks that the number of sticks do not exceed 8
        int totalSticks = 0;
        if (set.getRams() != null && set.getRams().isEmpty() == false) {
            for (RAM ram : set.getRams()) {
                totalSticks += ram.getSticks();
            }
            totalSticks += current.getSticks();

            if (totalSticks > 8) {
                throw new IncompatiblePartException("Total RAM Sticks exceed 8. Please do select more that 8 RAM modules");
            }
        }

        // check that the selected motherboard in set has enough RAM slots
        // check that the motherBoard supports the RAM speed
        if (set.getMotherBoard() != null) {
            if (totalSticks > set.getMotherBoard().getMemorySlot()) {
                throw new IncompatiblePartException("Selected Mother Board does not have enough RAM slots");
            }

            boolean supportedSpeed = false;
            for (String speed : set.getMotherBoard().getSuportedMemorySpeed()) {
                if (speed.equals(current.getSpeed())) {
                    supportedSpeed = true;
                    break;
                }
            }

            if (supportedSpeed = false) {
                throw new IncompatiblePartException("RAM Speed is not supported by selected motherboard.");
            }
        }

        return flag;
    }

    public boolean hddCheck(ComputerSet set, HDD current) throws IncompatiblePartException {
        boolean flag = true;

        if (set.getPsu() != null) {
            int totalStorage = 1;
            if (set.getHdds().isEmpty() == false) {
                totalStorage += set.getHdds().size();
            }
            if (set.getSsds().isEmpty() == false) {
                for (SSD ssd : set.getSsds()) {
                    if (ssd.getNVME() == false) {
                        totalStorage += 1;
                    }
                }
            }

            if (totalStorage > set.getPsu().getSATAConnectors()) {
                throw new IncompatiblePartException("Selected power supply does not have enough SATA power cables to support more storage.");
            }
        }

        return flag;
    }

    public boolean ssdCheck(ComputerSet set, SSD current) throws IncompatiblePartException {
        boolean flag = true;

        if (set.getPsu() != null && current.getNVME() == false) {
            int totalStorage = 1;
            if (set.getHdds().isEmpty() == false) {
                totalStorage += set.getHdds().size();
            }
            if (set.getSsds().isEmpty() == false) {
                for (SSD ssd : set.getSsds()) {
                    if (ssd.getNVME() == false) {
                        totalStorage += 1;
                    }
                }
            }

            if (totalStorage > set.getPsu().getSATAConnectors()) {
                throw new IncompatiblePartException("Selected power supply does not have enough SATA power cables to support more storage.");
            }
        } else if (set.getMotherBoard() != null && current.getNVME() == true) {
            int totalNvme = 1;
            if (set.getSsds().isEmpty() == false) {
                for (SSD ssd : set.getSsds()) {
                    if (ssd.getNVME() == true) {
                        totalNvme += 1;
                    }
                }
            }
            if (totalNvme > set.getMotherBoard().getM2Slot()) {
                throw new IncompatiblePartException("Mother board does not have enough m.2 slots to support more NVME SSDs.");
            }
        }

        return flag;
    }

    public boolean gpuCheck(ComputerSet set, GPU current) throws IncompatiblePartException {
        boolean flag = true;

        if (set.getCompCase() != null) {
            if (current.getLength() > set.getCompCase().getMaxVideoCardLength()) {
                throw new IncompatiblePartException("Selected GPU does not fit into selcted case.");
            }

            //total expansion slots
            int totalExpansionSlot = current.getExpansionSlotWidth();
            if (set.getGpus().isEmpty() == false) {
                for (GPU gpu : set.getGpus()) {
                    totalExpansionSlot += gpu.getExpansionSlotWidth();
                }
                if (totalExpansionSlot > set.getCompCase().getFullHeightExpansionSlot()) {
                    throw new IncompatiblePartException("Selected computer case does not have enough expansion slots to support GPU");
                }
            }
        }

        if (set.getMotherBoard() != null) {
            int totalPcie16 = 1;
            totalPcie16 += set.getGpus().size();
            if (set.getMotherBoard().getPCIEx16() < totalPcie16) {
                throw new IncompatiblePartException("Selected mother board does not have enough PCIex16 slots.");
            }
        }

        return flag;
    }

    public boolean psuCheck(ComputerSet set, PowerSupply current) throws IncompatiblePartException {
        boolean flag = true;

        int totalStorage = 0;
        if (set.getHdds().isEmpty() == false) {
            totalStorage += set.getHdds().size();
        }
        if (set.getSsds().isEmpty() == false) {
            for (SSD ssd : set.getSsds()) {
                if (ssd.getNVME() == false) {
                    totalStorage += 1;
                }
            }

            if (totalStorage > current.getSATAConnectors()) {
                throw new IncompatiblePartException("Selected power supply does not have enough SATA power cables to support more storage.");
            }
        }

        return flag;
    }

    public boolean caseCheck(ComputerSet set, ComputerCase current) throws IncompatiblePartException {
        boolean flag = true;
        //mother board
        if (set.getMotherBoard() != null) {
            boolean formfactor = false;
            for (String s : current.getMotherBoardFormFactor()) {
                if (s.equals(set.getMotherBoard().getFormFactor())) {
                    formfactor = true;
                }
            }
            if (formfactor == false) {
                throw new IncompatiblePartException("selected Mother board has compatiblity issues with computer set's case.");
            }
        }

        //gpu
        if (set.getGpus() != null && set.getGpus().isEmpty() == false) {
            int totalExpansionLength = 0;
            for (GPU gpu : set.getGpus()) {
                totalExpansionLength += gpu.getExpansionSlotWidth();
                if (gpu.getLength() > current.getMaxVideoCardLength()) {
                    throw new IncompatiblePartException("Selected GPU does not fit into selcted case.");
                }
                if (totalExpansionLength > current.getFullHeightExpansionSlot()) {
                    throw new IncompatiblePartException("Selected computer case does not have enough expansion slots to support GPU");
                }
            }
        }

        //aio
        if (set.getWaterCooler() != null) {
            if (set.getWaterCooler().getRadiatorSize() > current.getFrontFanSupport() || set.getWaterCooler().getRadiatorSize() > current.getTopFanSupport() || set.getWaterCooler().getRadiatorSize() > current.getRearFanSupport()) {
                throw new IncompatiblePartException("Selected case is not compatible with selected water cooler.");
            }
        }

        return flag;
    }

    public boolean airCoolerCheck(ComputerSet set, CPUAirCooler current) throws IncompatiblePartException {
        boolean flag = true;

        //cpu
        if (set.getCpu() != null) {
            flag = false;
            for (String chip : current.getCPUChipCompatibility()) {
                if (set.getCpu().getSocket().equalsIgnoreCase(chip)) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new IncompatiblePartException("Air cooler is not compatible with selected cpu chip set.");
            }
        }

        return flag;
    }

    public boolean waterCoolerCheck(ComputerSet set, CPUWaterCooler current) throws IncompatiblePartException {
        boolean flag = true;

        //cpu
        if (set.getCpu() != null) {
            flag = false;
            for (String chip : current.getCPUChipCompatibility()) {
                if (set.getCpu().getSocket().equalsIgnoreCase(chip)) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new IncompatiblePartException("Water cooler is not compatible with selected cpu chip set.");
            }
        }

        if (set.getCompCase() != null) {
            if (current.getRadiatorSize() > set.getCompCase().getFrontFanSupport() || current.getRadiatorSize() > set.getCompCase().getTopFanSupport() || current.getRadiatorSize() > set.getCompCase().getRearFanSupport()) {
                throw new IncompatiblePartException("Selected case is not compatible with selected water cooler.");
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
    public boolean finalComputerSetCheck(ComputerSet set) throws IncompleteComputerSetException {

        /*
     compulsory parts that must be there. 
     cpu
     motherboard
     case
     powersupply
     RAM
         */
        if (set.getCpu() == null) {
            throw new IncompleteComputerSetException("Computer set is missing a CPU");
        }

        if (set.getMotherBoard() == null) {
            throw new IncompleteComputerSetException("Computer set is missing a Mother Board");
        }

        if (set.getCompCase() == null) {
            throw new IncompleteComputerSetException("Computer set is missing a case");
        }

        if (set.getPsu() == null) {
            throw new IncompleteComputerSetException("Computer set is missing a Power Supply");
        }

        if (set.getRams() == null || set.getRams().isEmpty() == true) {
            throw new IncompleteComputerSetException("Computer set is missing RAM");
        }

        /*
     there is a need to ensure that the power supply choosen can power all the components that have been selected. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
         */
        int requiredPower = 400;
        for (GPU gpu : set.getGpus()) {
            requiredPower += gpu.getTDP();
        }
        if (set.getPsu().getWattage() < requiredPower) {
            throw new IncompleteComputerSetException("Computer set's required power is greater than power supply's output.");
        }

        /*
     there is a need to ensure that the computer has some form of graphics. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
         */
        if ((set.getGpus() == null || set.getGpus().isEmpty() == true) && set.getCpu().getHasIntergratedGraphics() == false) {
            throw new IncompleteComputerSetException("Computer set's Does not have graphics.");
        }

        /*
     there is a need to ensure that the computer has some form of storage. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
         */
        if ((set.getHdds() == null || set.getHdds().isEmpty() == true) && (set.getSsds() == null || set.getSsds().isEmpty() == true)) {
            throw new IncompleteComputerSetException("Computer set's Does not have storage.");
        }

        /*
     there is a need to ensure that the computer has some form of cpu cooling. 
     It should be done at the end and not affect the picking process.
     It should not be done when a new component is added to the list.
         */
        if ((set.getWaterCooler() == null) && (set.getAirCooler() == null) && (set.getCpu().getHasIntergratedGraphics() == false)) {
            throw new IncompleteComputerSetException("Computer set's Does not have a CPU cooler.");
        }

        return true;
    }

    @Override
    public List<ComputerSet> retrieveComputerSetsByStaffAssignedTo(Long staffId, Boolean loadRams, Boolean loadGpus, Boolean loadHdds, Boolean loadSsds, Boolean fetchCompleted) {
        Query query;

        if (fetchCompleted) {
            query = em.createQuery("SELECT c FROM ComputerSet c WHERE c.assemblyAssignedTo.userId = :inStaffId and c.assemblyComplete = false");
        } else {
            query = em.createQuery("SELECT c FROM ComputerSet c WHERE c.assemblyAssignedTo.userId = :inStaffId");
        }

        query.setParameter("inStaffId", staffId);
        List<ComputerSet> computerSets = query.getResultList();
        if (loadRams) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getRams().size();
            }
        }
        if (loadGpus) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getGpus().size();
            }
        }
        if (loadHdds) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getHdds().size();
            }
        }
        if (loadSsds) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getSsds().size();
            }
        }
        return computerSets;
    }

    @Override
    public List<ComputerSet> retrieveComputerSetsByOrderId(Long orderId, Boolean loadRams, Boolean loadGpus, Boolean loadHdds, Boolean loadSsds) {
        Query query = em.createQuery("SELECT c FROM ComputerSet c WHERE c.lineItem.customerOrder.customerOrderId = :inOrderId");
        query.setParameter("inOrderId", orderId);
        List<ComputerSet> computerSets = query.getResultList();
        if (loadRams) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getRams().size();
            }
        }
        if (loadGpus) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getGpus().size();
            }
        }
        if (loadHdds) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getHdds().size();
            }
        }
        if (loadSsds) {
            for (ComputerSet computerSet : computerSets) {
                computerSet.getSsds().size();
            }
        }
        return computerSets;
    }

}
