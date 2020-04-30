package ejb.session.stateless;

import entity.CPU;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.RAM;
import java.util.List;
import javax.ejb.Local;

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

}
