package ejb.session.stateless;

import entity.ComputerSet;
import entity.LineItem;
import java.util.List;
import javax.ejb.Local;
import util.exception.ComputerPartNotFoundException;
import util.exception.ComputerSetNotFoundException;
import util.exception.LineItemNotFoundException;
import util.exception.StaffNotFoundException;

@Local
public interface ComputerSetSessionBeanLocal {

    public List<Long> createNewComputerSet(ComputerSet computerSetModel, LineItem lineItem);

    public ComputerSet retrieveComputerSetById(Long computerSetId) throws ComputerSetNotFoundException;

    public void updateComputerSet(ComputerSet computerSet, Long staffId) throws StaffNotFoundException;

//    public void deleteComputerSet(Long computerSetId) throws ComputerSetNotFoundException;

    public List<ComputerSet> retrieveComputerSetsByStaffAssignedTo(Long staffId, Boolean loadRams, Boolean loadGpus, Boolean loadHdds, Boolean loadSsds, Boolean fetchCompleted);

    public List<ComputerSet> retrieveComputerSetsByOrderId(Long orderId, Boolean loadRams, Boolean loadGpus, Boolean loadHdds, Boolean loadSsds);
}
