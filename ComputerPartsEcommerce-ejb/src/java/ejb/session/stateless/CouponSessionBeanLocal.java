package ejb.session.stateless;

import entity.Coupon;
import javax.ejb.Local;
import util.exception.CouponInvalidException;
import util.exception.CouponNotFoundException;

@Local
public interface CouponSessionBeanLocal {

    public Long createNewCoupon(Coupon newCoupon);
    
    public Coupon retrieveCouponById(Long couponId) throws CouponNotFoundException;

    public void updateCoupon(Coupon updatedCoupon);

    public void deleteCoupon(Long couponId) throws CouponNotFoundException;

    public Coupon checkCouponByCode(String code) throws CouponInvalidException, CouponNotFoundException;
  
}
