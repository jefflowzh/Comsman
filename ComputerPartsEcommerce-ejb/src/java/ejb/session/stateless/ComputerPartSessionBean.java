package ejb.session.stateless;

import datamodel.StringValue;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.RAM;
import entity.SSD;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import util.exception.ComputerPartNotFoundException;

@Stateless
public class ComputerPartSessionBean implements ComputerPartSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;
    
    @Override
    public Long createNewCPU(CPU cpu) {
        em.persist(cpu);
        em.flush();

        return cpu.getProductId();
    }

    @Override
    public Long createNewCPUAirCooler(CPUAirCooler cpuAirCooler) {
        em.persist(cpuAirCooler);
        em.flush();

        return cpuAirCooler.getProductId();
    }

    @Override
    public Long createNewCPUWaterCooler(CPUWaterCooler cpuWaterCooler) {
        em.persist(cpuWaterCooler);
        em.flush();

        return cpuWaterCooler.getProductId();
    }

    @Override
    public Long createNewComCase(ComputerCase computerCase) {
        em.persist(computerCase);
        em.flush();

        return computerCase.getProductId();
    }

    @Override
    public Long createNewGPU(GPU gpu) {
        em.persist(gpu);
        em.flush();

        return gpu.getProductId();
    }

    @Override
    public Long createNewHDD(HDD hdd) {
        em.persist(hdd);
        em.flush();

        return hdd.getProductId();
    }

    @Override
    public Long createNewMotherBoard(MotherBoard mb) {
        em.persist(mb);
        em.flush();

        return mb.getProductId();
    }

    @Override
    public Long createNewPowerSupply(PowerSupply ps) {
        em.persist(ps);
        em.flush();

        return ps.getProductId();
    }

    @Override
    public Long createNewRAM(RAM ram) {
        em.persist(ram);
        em.flush();

        return ram.getProductId();
    }

    @Override
    public Long createNewSSD(SSD ssd) {
        em.persist(ssd);
        em.flush();

        return ssd.getProductId();

    }

    @Override
    public List<CPU> retrieveAllCPU() {
        Query query = em.createQuery("SELECT c FROM CPU c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<CPUAirCooler> retrieveAllCPUAirCooler() {
        Query query = em.createQuery("SELECT c FROM CPUAirCooler c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<CPUWaterCooler> retrieveAllCPUWaterCooler() {
        Query query = em.createQuery("SELECT c FROM CPUWaterCooler c WHERE c.isDisabled = false");
        return query.getResultList();

    }
    
    @Override
    public List<ComputerCase> retrieveAllComCase() {
        Query query = em.createQuery("SELECT c FROM ComputerCase c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<GPU> retrieveAllGPU() {
        Query query = em.createQuery("SELECT c FROM GPU c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<HDD> retrieveAllHDD() {
        Query query = em.createQuery("SELECT c FROM HDD c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<MotherBoard> retrieveAllMotherBoard() {
        Query query = em.createQuery("SELECT c FROM MotherBoard c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<PowerSupply> retrieveAllPowerSupply() {
        Query query = em.createQuery("SELECT c FROM PowerSupply c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<RAM> retrieveAllRAM() {
        Query query = em.createQuery("SELECT c FROM RAM c WHERE c.isDisabled = false");
        return query.getResultList();

    }

    @Override
    public List<SSD> retrieveAllSSD() {
        Query query = em.createQuery("SELECT c FROM SSD c WHERE c.isDisabled = false");
        return query.getResultList();
    }

    @Override
    public List<String> retrieveAllStringValue(String type, long productId) {
        Query query;
        if (type.equals("CPUWaterCooler")) {
            query = em.createQuery("SELECT c.CPUChipCompatibility FROM CPUWaterCooler c WHERE c.productId = :inProductId");
            query.setParameter("inProductId", productId);

            return query.getResultList();
        } else if (type.equals("CPUAirCooler")) {
            query = em.createQuery("SELECT c.CPUChipCompatibility FROM CPUAirCooler c WHERE c.productId = :inProductId");
            query.setParameter("inProductId", productId);

            return query.getResultList();
        } else if (type.equals("MotherBoard")) {
            query = em.createQuery("SELECT m.suportedMemorySpeed FROM MotherBoard m WHERE m.productId = :inProductId");
            query.setParameter("inProductId", productId);

            return query.getResultList();
        } else if (type.equals("ComputerCase")) {
            query = em.createQuery("SELECT c.motherBoardFormFactor FROM ComputerCase c WHERE c.productId = :inProductId");
            query.setParameter("inProductId", productId);

            return query.getResultList();
        }

        return null;
    }
    
    @Override
    public ComputerPart retrieveComputerPartById(Long computerPartId) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(ComputerPart.class, computerPartId);

        if (computerPart != null) {
            return computerPart;
        } else {
            throw new ComputerPartNotFoundException("Computer Part ID " + computerPartId + " does not exist!");
        }
    }
   
    @Override
    public List<String> retrieveAllCCStringValue(String type, long productId) {
        Query query;
        if (type.equals("motherBoardFormFactor")) {
            query = em.createQuery("SELECT c.motherBoardFormFactor FROM ComputerCase c WHERE c.productId = :inProductId");
            query.setParameter("inProductId", productId);

            return query.getResultList();
        } else if (type.equals("colours")) {
            query = em.createQuery("SELECT c.colours FROM ComputerCase c WHERE c.productId = :inProductId");
            query.setParameter("inProductId", productId);

            return query.getResultList();
        }

        return null;
    }


    @Override
    public CPU retrieveCPUById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT c FROM CPU c WHERE c.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (CPU) query.getSingleResult();
    }

    @Override
    public MotherBoard retrieveMotherBoardById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT m FROM MotherBoard m WHERE m.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (MotherBoard) query.getSingleResult();
    }

    @Override
    public RAM retrieveRAMById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT r FROM RAM r WHERE r.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (RAM) query.getSingleResult();
    }

    @Override
    public PowerSupply retrievePowerSupplyById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT p FROM PowerSupply p WHERE p.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (PowerSupply) query.getSingleResult();
    }

    @Override
    public ComputerCase retrieveComputerCaseById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT c FROM ComputerCase c WHERE c.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (ComputerCase) query.getSingleResult();
    }

    @Override
    public GPU retrieveGPUById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT g FROM GPU g WHERE g.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (GPU) query.getSingleResult();
    }

    @Override
    public HDD retrieveHDDById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT h FROM HDD h WHERE h.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (HDD) query.getSingleResult();
    }

    @Override
    public SSD retrieveSSDById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT s FROM SSD s WHERE s.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (SSD) query.getSingleResult();
    }

    @Override
    public CPUWaterCooler retrieveCPUWaterCoolerById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT c FROM CPUWaterCooler c WHERE c.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (CPUWaterCooler) query.getSingleResult();
    }

    @Override
    public CPUAirCooler retrieveCPUAirCoolerById(Long productId) throws ComputerPartNotFoundException {
        Query query = em.createQuery("SELECT c FROM CPUAirCooler c WHERE c.productId = :inProductId");
        query.setParameter("inProductId", productId);

        return (CPUAirCooler) query.getSingleResult();
    }
    
    @Override
    public ComputerPart retrieveComputerPartByName(String name) {
        Query query = em.createQuery("SELECT c FROM ComputerPart c WHERE c.name = :inName");
        query.setParameter("inName", name);
        
        return (ComputerPart) query.getSingleResult();
    }

    @Override
    public void updateCPU(CPU cpu) throws ComputerPartNotFoundException {
        em.merge(cpu);
    }

    @Override
    public void updateMotherBoard(MotherBoard motherboard) throws ComputerPartNotFoundException {
        em.merge(motherboard);
    }

    @Override
    public void updateRAM(RAM ram) throws ComputerPartNotFoundException {
        em.merge(ram);
    }

    @Override
    public void updatePowerSupply(PowerSupply powerSupply) throws ComputerPartNotFoundException {
        em.merge(powerSupply);
    }

    @Override
    public void updateComCase(ComputerCase computerCase) throws ComputerPartNotFoundException {
        em.merge(computerCase);
    }

    @Override
    public void updateGPU(GPU gpu) throws ComputerPartNotFoundException {
        em.merge(gpu);
    }

    @Override
    public void updateHDD(HDD hdd) throws ComputerPartNotFoundException {
        em.merge(hdd);
    }

    @Override
    public void updateSSD(SSD ssd) throws ComputerPartNotFoundException {
        em.merge(ssd);
    }

    @Override
    public void updateCPUWaterCooler(CPUWaterCooler CPUWaterCooler) throws ComputerPartNotFoundException {
        em.merge(CPUWaterCooler);
    }

    @Override
    public void updateCPUAirCooler(CPUAirCooler CPUAirCooler) throws ComputerPartNotFoundException {
        em.merge(CPUAirCooler);
    }

    @Override
    public Long createNewComputerPart(ComputerPart newComputerPart) {
        em.persist(newComputerPart);
        em.flush();

        return newComputerPart.getProductId();
    }

    @Override
    public void deleteProduct(String type, Long productId) throws ComputerPartNotFoundException {
        try {
            if (type.equals("CPU")) {
                CPU cpuToDelete = retrieveCPUById(productId);
                cpuToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("MotherBoard")) {
                MotherBoard motherBoardToDelete = retrieveMotherBoardById(productId);
                motherBoardToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("RAM")) {
                RAM ramToDelete = retrieveRAMById(productId);
                ramToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("PowerSupply")) {
                PowerSupply powerSupplyToDelete = retrievePowerSupplyById(productId);
                powerSupplyToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("ComputerCase")) {
                ComputerCase computerCaseToDelete = retrieveComputerCaseById(productId);
                computerCaseToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("GPU")) {
                GPU gpuToDelete = retrieveGPUById(productId);
                gpuToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("HDD")) {
                HDD hddToDelete = retrieveHDDById(productId);
                hddToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("SSD")) {
                SSD ssdToDelete = retrieveSSDById(productId);
                ssdToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("CPUWaterCooler")) {
                CPUWaterCooler cpuWaterCoolerToDelete = retrieveCPUWaterCoolerById(productId);
                cpuWaterCoolerToDelete.setIsDisabled(Boolean.TRUE);
            } else if (type.equals("CPUAirCooler")) {
                CPUAirCooler cpuAirCoolerToDelete = retrieveCPUAirCoolerById(productId);
                cpuAirCoolerToDelete.setIsDisabled(Boolean.TRUE);
            }
        } catch (ComputerPartNotFoundException ex) {
            throw new ComputerPartNotFoundException("Product ID not found");
        }
    }

}
