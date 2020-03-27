package ejb.session.stateless;

import entity.ComputerPart;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.ComputerPartNotFoundException;
import util.exception.CustomerNotFoundException;

@Stateless
public class ComputerPartSessionBean implements ComputerPartSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;
    
}
