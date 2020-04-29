package ejb.session.stateless;

import entity.CPU;
import entity.ComputerCase;
import entity.ComputerPart;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ComputerPartSessionBeanLocal {
    
    public Long createNewComputerPart(ComputerPart newComputerPart);

    public Long createNewComCase(ComputerCase computerCase);

    public List<ComputerCase> retrieveAllComCase();

}
