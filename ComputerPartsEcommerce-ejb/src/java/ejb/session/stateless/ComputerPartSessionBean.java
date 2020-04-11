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

import util.exception.ComputerPartNotFoundException;

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
        Query query = em.createQuery("SELECT c FROM CPU c"); 
        return query.getResultList();
        
    }

    public List<CPUAirCooler> retrieveAllCPUAirCooler() {
        Query query = em.createQuery("SELECT c FROM CPUAirCooler c"); 
        return query.getResultList();
        
    }
    
    public List<CPUWaterCooler> retrieveAllCPUWaterCooler() {
        Query query = em.createQuery("SELECT c FROM CPUWaterCooler c"); 
        return query.getResultList();
        
    }
    
    public List<ComputerCase> retrieveAllComCase() {
        Query query = em.createQuery("SELECT c FROM ComptuerCase c"); 
        return query.getResultList();
        
    }
    
    public List<GPU> retrieveAllGPU() {
        Query query = em.createQuery("SELECT c FROM GPU c"); 
        return query.getResultList();
        
    }
    
    public List<HDD> retrieveAllHDD() {
        Query query = em.createQuery("SELECT c FROM HDD c"); 
        return query.getResultList();
        
    }
    
    public List<MotherBoard> retrieveAllMotherBoard() {
        Query query = em.createQuery("SELECT c FROM MotherBoard c"); 
        return query.getResultList();
        
    }
    
    public List<PowerSupply> retrieveAllPowerSupply() {
        Query query = em.createQuery("SELECT c FROM PowerSupply c"); 
        return query.getResultList();
        
    }
    
    public List<RAM> retrieveAllRAM() {
        Query query = em.createQuery("SELECT c FROM RAM c"); 
        return query.getResultList();
        
    }
    
    public List<SSD> retrieveAllSSD() {
        Query query = em.createQuery("SELECT c FROM SSD c"); 
        return query.getResultList();
        
    }
    
    
    public CPU retrieveCPUById(Long id) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(CPU.class, id);
        if (computerPart != null && computerPart instanceof CPU){
            return (CPU) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof CPU)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a CPU");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
            
        
        
    }

    public CPUAirCooler retrieveCPUAirCoolerById(Long id) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(CPUAirCooler.class, id);
        if (computerPart != null && computerPart instanceof CPUAirCooler){
            return (CPUAirCooler) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof CPUAirCooler)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a CPU Air Cooler");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public CPUWaterCooler retrieveCPUWaterCoolerById(Long id) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(CPUWaterCooler.class, id);
        if (computerPart != null && computerPart instanceof CPUWaterCooler){
            return (CPUWaterCooler) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof CPUWaterCooler)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a CPU Water Cooler");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public ComputerCase retrieveComCaseById(Long id) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(ComputerCase.class, id);
        if (computerPart != null && computerPart instanceof ComputerCase){
            return (ComputerCase) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof ComputerCase)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a Computer Case");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public GPU retrieveGPUById(Long id) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(GPU.class, id);
        if (computerPart != null && computerPart instanceof GPU){
            return (GPU) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof GPU)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a GPU");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public HDD retrieveAllHDDById(Long id) throws ComputerPartNotFoundException {
       ComputerPart computerPart = em.find(HDD.class, id);
        if (computerPart != null && computerPart instanceof HDD){
            return (HDD) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof HDD)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a HDD");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public MotherBoard retrieveMotherBoardById(Long id) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(MotherBoard.class, id);
        if (computerPart != null && computerPart instanceof MotherBoard){
            return (MotherBoard) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof MotherBoard)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a Mother Board");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public PowerSupply retrievePowerSupplyById(Long id) throws ComputerPartNotFoundException {
        ComputerPart computerPart = em.find(PowerSupply.class, id);
        if (computerPart != null && computerPart instanceof PowerSupply){
            return (PowerSupply) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof PowerSupply)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a Power Supply");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public RAM retrieveRAMById(Long id) throws ComputerPartNotFoundException {
       ComputerPart computerPart = em.find(RAM.class, id);
        if (computerPart != null && computerPart instanceof RAM){
            return (RAM) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof RAM)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a RAM");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public SSD retrieveSSDById(Long id) throws ComputerPartNotFoundException {
       ComputerPart computerPart = em.find(SSD.class, id);
        if (computerPart != null && computerPart instanceof SSD){
            return (SSD) computerPart;
        }
        else if(computerPart != null && !(computerPart instanceof SSD)){
            throw new ComputerPartNotFoundException("Part Id: " + id + " is not a SSD");
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
    public ComputerPart retrieveComputerPartById(Long id) throws ComputerPartNotFoundException
    {
        ComputerPart computerPart = em.find(SSD.class, id);
        if (computerPart != null){
            return computerPart;
        }
        else{
            throw new ComputerPartNotFoundException("Part Id: " + id + " cannot be found");
        }
    }
    
  
    
    @Override
    public Long createNewComputerPart(ComputerPart newComputerPart) {
        em.persist(newComputerPart);
        em.flush();

        return newComputerPart.getProductId();
    }
    
}
