/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Staff;
import java.util.List;
import javax.ejb.Local;
import util.exception.InvalidLoginCredentialException;
import util.exception.StaffNotFoundException;

/**
 *
 * @author jeffl
 */
@Local
public interface StaffSessionBeanLocal {

    public Long createNewStaff(Staff newStaff);
    
    public Staff retrieveStaffByEmail(String email) throws StaffNotFoundException;
    
    public Staff staffLogin(String email, String password) throws InvalidLoginCredentialException;
    
    public List<Staff> retrieveAllStaffs();
    
    
}
