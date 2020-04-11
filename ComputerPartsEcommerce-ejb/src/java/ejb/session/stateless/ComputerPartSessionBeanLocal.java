package ejb.session.stateless;

import entity.CPU;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.RAM;
import javax.ejb.Local;

@Local
public interface ComputerPartSessionBeanLocal {
    
    public Long createNewComputerPart(ComputerPart newComputerPart);

    public Long createNewCPU(CPU cpu);

    public Long createNewMotherBoard(MotherBoard mb);

    public Long createNewRAM(RAM ram);

    public Long createNewPowerSupply(PowerSupply ps);

    public Long createNewComCase(ComputerCase computerCase);


}
