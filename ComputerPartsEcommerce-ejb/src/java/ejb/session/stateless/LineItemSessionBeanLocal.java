/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.LineItem;
import javax.ejb.Local;

/**
 *
 * @author jeffl
 */
@Local
public interface LineItemSessionBeanLocal {

    public Long createNewLineItem(LineItem newLineItem);
    
}
