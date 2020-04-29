package ejb.session.stateless;

import entity.Coupon;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CouponInvalidException;
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
    public Coupon checkCouponByCode(String code) throws CouponInvalidException, CouponNotFoundException {
        Query query = em.createQuery("SELECT c FROM Coupon c WHERE c.code = :userInput");
        query.setParameter("userInput", code);

        try {
            Coupon coupon = (Coupon) query.getSingleResult();
            
            Date currDate = new Date();
            System.out.println(currDate);
            System.out.println(coupon.getStartDate());
            System.out.println(coupon.getEndDate());
            if ((currDate.compareTo(coupon.getStartDate()) >= 0) && (currDate.compareTo(coupon.getEndDate()) <= 0)) {
                return coupon;
            } else {
                throw new CouponInvalidException("Coupon with code " + code + " cannot be used now!");
            }
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CouponNotFoundException("Coupon with code " + code + " does not exist!");
        }
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