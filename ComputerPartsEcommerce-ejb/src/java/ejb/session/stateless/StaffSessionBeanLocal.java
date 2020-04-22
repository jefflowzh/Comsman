package ejb.session.stateless;

import entity.Staff;
import java.util.List;
import javax.ejb.Local;
import util.exception.ComputerSetNotFoundException;
import util.exception.CustomerOrderNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.StaffAlreadyExistsException;
import util.exception.StaffNotFoundException;

@Local
public interface StaffSessionBeanLocal {

    public Long createNewStaff(Staff newStaff) throws StaffAlreadyExistsException;

    public Staff retrieveStaffById(Long staffId, Boolean loadDeliveries, Boolean loadAssignedComputerSets) throws StaffNotFoundException;

    public Staff retrieveStaffByEmail(String email, Boolean loadDeliveries, Boolean loadAssignedComputerSets) throws StaffNotFoundException;

    public Staff staffLogin(String email, String password) throws InvalidLoginCredentialException;

    public void updateStaff(Staff staff, Long customerOrderId, Long computerSetId) throws StaffNotFoundException, CustomerOrderNotFoundException, ComputerSetNotFoundException;

    public void deleteStaff(Long staffId) throws StaffNotFoundException, CustomerOrderNotFoundException, ComputerSetNotFoundException;
    
    public List<Staff> retrieveAllStaffs();

    public List<Staff> retrieveAllStaffsIncludingDisabled();
    
}
