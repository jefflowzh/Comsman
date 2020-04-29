/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.Coupon;

/**
 *
 * @author jeffl
 */
public class CheckCouponByCodeRsp {
    
    Coupon coupon;

    public CheckCouponByCodeRsp() {
    }

    public CheckCouponByCodeRsp(Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    } 
    
}
