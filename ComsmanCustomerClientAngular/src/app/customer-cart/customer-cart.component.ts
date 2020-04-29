import { Component, OnInit } from '@angular/core';

import { SessionService } from '../session.service';
import { CouponService } from '../coupon.service';
import { LineItem } from '../line-item';
import { Coupon } from '../coupon';

@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.css']
})
export class CustomerCartComponent implements OnInit {

  cart: LineItem[];
  couponCodeInput: string;
  coupon: Coupon;
  retrieveCouponError: boolean;
  errorMessage: string;
  deliveryFee: number;
  isPercentageCoupon: Boolean;
  isFlatAmountCoupon: Boolean;
  isFreeDeliveryCoupon: Boolean;
  total: number;

  constructor(public sessionService: SessionService, public couponService: CouponService) {
    this.couponCodeInput = "";
    this.retrieveCouponError = false;
    this.deliveryFee = 10;
    this.isPercentageCoupon = false;
    this.isFlatAmountCoupon = false;
    this.isFreeDeliveryCoupon = false;
  }

  ngOnInit() {
    this.cart = this.sessionService.getCurrentCustomer().cart;
    this.calculateSubtotal();
  }

  updateSessionCart() {
    let currCustomer = this.sessionService.getCurrentCustomer();

    // update session cart
    currCustomer.cart = this.cart;
    // restringify currentCustomer with updated cart
    this.sessionService.setCurrentCustomer(currCustomer);

    // update cart subtotal
    this.calculateSubtotal();
  }

  removeLineItem(index: number) {
    this.cart.splice(index, 1);
    this.updateSessionCart();
  }

  calculateSubtotal() {
    let subtotal = 0;
    for (let lineItem of this.cart) {
      subtotal += lineItem.product.price * lineItem.quantity;
    }

    return subtotal;
  }

  checkCouponByCode() {
    if (this.couponCodeInput != "") {
      this.couponService.checkCouponByCode(this.couponCodeInput).subscribe(
        response => {
          let coupon = response.coupon;
          if (coupon != null) {
            this.coupon = coupon;
            this.retrieveCouponError = false;
            this.calculateTotal();
          } else {
            this.retrieveCouponError = true;
            this.errorMessage = "Null coupon error";

            this.coupon = null;
            this.calculateTotal();
          }
        }, error => {
          this.retrieveCouponError = true;
          console.log('********** CustomerCartComponent.ts checkCouponByCode(): ' + error);
          this.errorMessage = "Error: " + error.slice(37);

          this.coupon = null;
          this.calculateTotal();
        }
      );
    }
  }

  calculateTotal() {
    let total = this.calculateSubtotal();

    if (this.coupon == null) {
      // reset all display flags
      this.isPercentageCoupon = false;
      this.isFlatAmountCoupon = false;
      this.isFreeDeliveryCoupon = false;
    }

    // else
    if (this.coupon != null) {
      if (this.coupon.couponType == "PERCENTAGE") {
        total = total * (1 - this.coupon.percentageRate);
        this.isPercentageCoupon = true;
        this.isFlatAmountCoupon = false;
        this.isFreeDeliveryCoupon = false;
      } else if (this.coupon.couponType == "FLATAMOUNT") {
        total = total - this.coupon.flatAmount;
        this.isFlatAmountCoupon = true;
        this.isPercentageCoupon = false;
        this.isFreeDeliveryCoupon = false;
      }
    }
    total += this.deliveryFee;
    if (this.coupon != null && this.coupon.couponType == "FREEDELIVERY") {
      total -= this.deliveryFee;
      this.isFlatAmountCoupon = false;
      this.isPercentageCoupon = false;
      this.isFreeDeliveryCoupon = true;
    }

    this.total = total;

    return total;
  }

  createNewCustomerOrder() {
    let rounded2DP = parseFloat(this.total.toFixed(2))

  }

}
