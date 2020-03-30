package ejb.session.stateless;

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

@Stateless
public class ComputerPartSessionBean implements ComputerPartSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    
    public Long createNewCPU(CPU cpu) {
        em.persist(cpu);
        em.flush();
        
        return cpu.getProductId();
    }

    public Long createNewCPUAirCooler(CPUAirCooler cpuAirCooler) {
        em.persist(cpuAirCooler);
        em.flush();
        
        return cpuAirCooler.getProductId();
    }
    
    public Long createNewCPUWaterCooler(CPUWaterCooler cpuWaterCooler) {
        em.persist(cpuWaterCooler);
        em.flush();
        
        return cpuWaterCooler.getProductId();
    }
    
    public Long createNewComCase(ComputerCase computerCase) {
        em.persist(computerCase);
        em.flush();
        
        return computerCase.getProductId();
    }
    
    public Long createNewGPU(GPU gpu) {
        em.persist(gpu);
        em.flush();
        
        return gpu.getProductId();
    }
    
    public Long createNewHDD(HDD hdd) {
        em.persist(hdd);
        em.flush();
        
        return hdd.getProductId();
    }
    
    public Long createNewMotherBoard(MotherBoard mb) {
        em.persist(mb);
        em.flush();
        
        return mb.getProductId();
    }
    
    public Long createNewPowerSupply(PowerSupply ps) {
        em.persist(ps);
        em.flush();
        
        return ps.getProductId();
    }
    
    public Long createNewRAM(RAM ram) {
        em.persist(ram);
        em.flush();
        
        return ram.getProductId();
    }
    
    public Long createNewSSD(SSD ssd) {
        em.persist(ssd);
        em.flush();
        
        return ssd.getProductId();
        
    }
    
    public List<CPU> retrieveAllCPU() {
        Query query = em.createQuery("SELECT c FROM CPU"); 
        return query.getResultList();
        
    }

    public List<CPUAirCooler> retrieveAllCPUAirCooler() {
        Query query = em.createQuery("SELECT c FROM CPUAirCooler"); 
        return query.getResultList();
        
    }
    
    public List<CPUWaterCooler> retrieveAllCPUWaterCooler() {
        Query query = em.createQuery("SELECT c FROM CPUWaterCooler"); 
        return query.getResultList();
        
    }
    
    public List<ComputerCase> retrieveAllComCase() {
        Query query = em.createQuery("SELECT c FROM ComptuerCase"); 
        return query.getResultList();
        
    }
    
    public List<GPU> retrieveAllGPU() {
        Query query = em.createQuery("SELECT c FROM GPU"); 
        return query.getResultList();
        
    }
    
    public List<HDD> retrieveAllHDD() {
        Query query = em.createQuery("SELECT c FROM HDD"); 
        return query.getResultList();
        
    }
    
    public List<MotherBoard> retrieveAllMotherBoard() {
        Query query = em.createQuery("SELECT c FROM MotherBoard"); 
        return query.getResultList();
        
    }
    
    public List<PowerSupply> retrieveAllPowerSupply() {
        Query query = em.createQuery("SELECT c FROM PowerSupply"); 
        return query.getResultList();
        
    }
    
    public List<RAM> retrieveAllRAM() {
        Query query = em.createQuery("SELECT c FROM RAM"); 
        return query.getResultList();
        
    }
    
    public List<SSD> retrieveAllSSD() {
        Query query = em.createQuery("SELECT c FROM SSD"); 
        return query.getResultList();
        
    }
    
    
    
  
    
}
