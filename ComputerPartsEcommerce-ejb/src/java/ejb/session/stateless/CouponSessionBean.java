package ejb.session.stateless;

import entity.Coupon;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CouponSessionBean implements CouponSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewCoupon(Coupon newCoupon) {
        em.persist(newCoupon);
        em.flush();
        
        return newCoupon.getCouponId();
    }

}