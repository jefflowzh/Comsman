package ejb.session.stateless;

import entity.ComputerPart;
import javax.ejb.Local;

@Local
public interface ComputerPartSessionBeanLocal {
    
    public Long createNewComputerPart(ComputerPart newComputerPart);

}
