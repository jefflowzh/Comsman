import { Component, OnInit } from '@angular/core';
import { Coupon } from '../coupon';
import { Router } from '@angular/router';
import { SessionService } from '../session.service';
import { CouponService } from '../coupon.service';
import { MenuItem } from 'primeng/api/menuitem';

@Component({
  selector: 'app-customer-coupon',
  templateUrl: './customer-coupon.component.html',
  styleUrls: ['./customer-coupon.component.css']
})
export class CustomerCouponComponent implements OnInit {
  coupons: Coupon[];
  sidenavItems: MenuItem[];

  constructor(private router: Router, public sessionService: SessionService, public couponService: CouponService) { }

  ngOnInit() {
    this.couponService.validCoupons().subscribe(
      response => {
        this.coupons = response.coupons;
      }, error => {
        console.log('********** CustomerCoupons.ts validCoupons(): ' + error);

      }
    );

    this.sidenavItems = [{
      label: 'Manage My Account',
      items: [
        { label: 'Account Details', icon: 'pi pi-user', routerLink: ['/customerDetails'] },
        { label: 'View Available Coupons', icon: 'pi pi-ticket', routerLink: ['/customerCoupons'] },
      ]
    },
    {
      label: 'My Orders',
      items: [
        { label: 'Past Orders', icon: 'pi pi-file-o', routerLink: ['/customerOrders'] },
      ]
    }];

  }

}
