package ejb.session.stateless;

import entity.LineItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LineItemSessionBean implements LineItemSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewLineItem(LineItem newLineItem) {
        em.persist(newLineItem);
        em.flush();
        
        return newLineItem.getLineItemId();
    }

}