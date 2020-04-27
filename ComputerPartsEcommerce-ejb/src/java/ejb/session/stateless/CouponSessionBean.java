package ejb.session.stateless;

import entity.Coupon;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CouponNotFoundException;

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
    
    @Override
    public Coupon retrieveCouponById(Long couponId) throws CouponNotFoundException {
        Coupon coupon = em.find(Coupon.class, couponId);

        if (coupon != null) {           
            return coupon;
        } else {
            throw new CouponNotFoundException("Coupon ID " + couponId + " does not exist!");
        }
    }
    
    @Override
    public List<Coupon> retrieveAllCoupons() {
        Query query = em.createQuery("SELECT c FROM Coupon c WHERE c.isDisabled = false");
        return query.getResultList();
    }

    @Override
    public void updateCoupon(Coupon updatedCoupon) {
        em.merge(updatedCoupon);
    }
    
    @Override
    public void deleteCoupon(Long couponId) throws CouponNotFoundException { 
        Coupon coupon = retrieveCouponById(couponId);
        coupon.setIsDisabled(true);
    }

}