/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Staff;
import javax.ejb.Local;

/**
 *
 * @author jeffl
 */
@Local
public interface StaffSessionBeanLocal {

    public Long createNewStaff(Staff newStaff);
    
}
