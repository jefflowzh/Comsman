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
            if (computerSetModel.getWarrentyLengthInYears() != null) {
                newComputerSet.setWarrentyLengthInYears(computerSetModel.getWarrentyLengthInYears());
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

//    @Override
//    public void deleteComputerSet(Long computerSetId) throws ComputerSetNotFoundException { 
//        ComputerSet computerSet = retrieveComputerSetById(computerSetId);     
//        computerSet.setIsDisabled(true);
//    }
}
