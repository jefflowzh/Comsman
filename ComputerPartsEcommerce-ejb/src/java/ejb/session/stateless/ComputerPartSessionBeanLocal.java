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
import javax.ejb.Local;
import util.exception.ComputerPartNotFoundException;

@Local
public interface ComputerPartSessionBeanLocal {

    public Long createNewComputerPart(ComputerPart newComputerPart);

    public Long createNewCPU(CPU cpu);

    public Long createNewMotherBoard(MotherBoard mb);

    public Long createNewRAM(RAM ram);

    public Long createNewPowerSupply(PowerSupply ps);

    public Long createNewComCase(ComputerCase computerCase);

    public Long createNewGPU(GPU gpu);

    public Long createNewHDD(HDD hdd);

    public List<CPU> retrieveAllCPU();

    public List<MotherBoard> retrieveAllMotherBoard();

    public void updateCPU(CPU cpu) throws ComputerPartNotFoundException;

    public void updateMotherBoard(MotherBoard motherboard) throws ComputerPartNotFoundException;

    public List<RAM> retrieveAllRAM();

    public void updateRAM(RAM ram) throws ComputerPartNotFoundException;

    public void updatePowerSupply(PowerSupply powerSupply) throws ComputerPartNotFoundException;

    public List<PowerSupply> retrieveAllPowerSupply();

    public void updateComCase(ComputerCase computerCase) throws ComputerPartNotFoundException;

    public List<ComputerCase> retrieveAllComCase();

    public List<GPU> retrieveAllGPU();

    public void updateGPU(GPU gpu) throws ComputerPartNotFoundException;

    public List<HDD> retrieveAllHDD();

    public void updateHDD(HDD hdd) throws ComputerPartNotFoundException;

    public Long createNewSSD(SSD ssd);

    public List<SSD> retrieveAllSSD();

    public void updateSSD(SSD ssd) throws ComputerPartNotFoundException;

    public List<CPUAirCooler> retrieveAllCPUAirCooler();

    public List<CPUWaterCooler> retrieveAllCPUWaterCooler();

    public Long createNewCPUAirCooler(CPUAirCooler cpuAirCooler);

    public Long createNewCPUWaterCooler(CPUWaterCooler cpuWaterCooler);

    public void updateCPUAirCooler(CPUAirCooler CPUAirCooler) throws ComputerPartNotFoundException;

    public void updateCPUWaterCooler(CPUWaterCooler CPUWaterCooler) throws ComputerPartNotFoundException;

    public CPU retrieveCPUById(Long productId) throws ComputerPartNotFoundException;

    public MotherBoard retrieveMotherBoardById(Long productId) throws ComputerPartNotFoundException;

    public RAM retrieveRAMById(Long productId) throws ComputerPartNotFoundException;

    public PowerSupply retrievePowerSupplyById(Long productId) throws ComputerPartNotFoundException;

    public ComputerCase retrieveComputerCaseById(Long productId) throws ComputerPartNotFoundException;

    public GPU retrieveGPUById(Long productId) throws ComputerPartNotFoundException;

    public HDD retrieveHDDById(Long productId) throws ComputerPartNotFoundException;

    public SSD retrieveSSDById(Long productId) throws ComputerPartNotFoundException;

    public CPUWaterCooler retrieveCPUWaterCoolerById(Long productId) throws ComputerPartNotFoundException;

    public CPUAirCooler retrieveCPUAirCoolerById(Long productId) throws ComputerPartNotFoundException;

    public void deleteProduct(String type, Long productId) throws ComputerPartNotFoundException;

    public List<String> retrieveAllStringValue(String type, long productId);

    public List<String> retrieveAllCCStringValue(String type, long productId);

    public ComputerPart retrieveComputerPartByName(String name);

}
