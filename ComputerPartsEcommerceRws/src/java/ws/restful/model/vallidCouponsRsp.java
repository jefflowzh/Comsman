/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.Coupon;
import java.util.List;

/**
 *
 * @author zeplh
 */
public class vallidCouponsRsp {
    
    List<Coupon> coupons;

    public vallidCouponsRsp() {
    }

    public vallidCouponsRsp(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
    
    
}
