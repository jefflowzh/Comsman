package ejb.session.stateless;

import entity.ComputerSet;
import javax.ejb.Local;
import util.exception.ComputerPartNotFoundException;
import util.exception.ComputerSetNotFoundException;
import util.exception.LineItemNotFoundException;
import util.exception.StaffNotFoundException;

@Local
public interface ComputerSetSessionBeanLocal {

    public Long createNewComputerSet(ComputerSet newComputerSet, Long lineItemId) throws LineItemNotFoundException;

    public ComputerSet retrieveComputerSetById(Long computerSetId) throws ComputerSetNotFoundException;

    public void updateComputerSet(ComputerSet computerSet, Long staffId) throws ComputerPartNotFoundException, StaffNotFoundException;

//    public void deleteComputerSet(Long computerSetId) throws ComputerSetNotFoundException;

}
