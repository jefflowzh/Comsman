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
import javax.ejb.Local;
import util.exception.ComputerPartNotFoundException;

@Local
public interface ComputerPartSessionBeanLocal {
    
    public Long createNewComputerPart(ComputerPart newComputerPart);

    public Long createNewComCase(ComputerCase computerCase);

    public List<ComputerCase> retrieveAllComCase();

    public List<CPU> retrieveAllCPU();

    public List<GPU> retrieveAllGPU();

    public List<MotherBoard> retrieveAllMotherBoard();

    public List<PowerSupply> retrieveAllPowerSupply();

    public List<RAM> retrieveAllRAM();

    public Long createNewCPU(CPU cpu);

    public Long createNewGPU(GPU gpu);

    public Long createNewMotherBoard(MotherBoard mb);

    public Long createNewPowerSupply(PowerSupply ps);

    public Long createNewRAM(RAM ram);

    public List<CPUAirCooler> retrieveAllCPUAirCooler();

    public List<CPUWaterCooler> retrieveAllCPUWaterCooler();

    public Long createNewCPUAirCooler(CPUAirCooler cpuAirCooler);

    public Long createNewCPUWaterCooler(CPUWaterCooler cpuWaterCooler);

    public Long createNewHDD(HDD hdd);

    public Long createNewSSD(SSD ssd);

    public List<SSD> retrieveAllSSD();

    public List<HDD> retrieveAllHDD();

    public ComputerPart retrieveComputerPartById(Long computerPartId) throws ComputerPartNotFoundException;

}
