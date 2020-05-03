import { Component, OnInit } from '@angular/core';
import { Customer } from '../Customer';
import { MenuItem } from 'primeng/api/menuitem';
import { Router } from '@angular/router';
import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';
import { CustomerOrder } from '../customer-order';
import { LineItem } from '../line-item';
import { OrderStatusEnum } from '../order-status-enum.enum';
import { Coupon } from '../coupon';

@Component({
  selector: 'app-customer-orders',
  templateUrl: './customer-orders.component.html',
  styleUrls: ['./customer-orders.component.css']
})
export class CustomerOrdersComponent implements OnInit {

  customer: Customer;
  lineitems: LineItem[] = []
  customerOrders: CustomerOrder[] = [];
  // today = new Date()
  // coupon: Coupon = null
  // co:CustomerOrder  = new CustomerOrder(
  //   1,
  //   this.today,
  //   this.today,
  //   100,
  //   true,
  //   "home",
  //   "home",
  //   this.lineitems,
  //   this.customer,
  //   this.coupon,
  //   OrderStatusEnum.COMPLETED
  // );
  
  sidenavItems: MenuItem[];

  constructor(private router: Router, public sessionService: SessionService,
    private customerService: CustomerService) { }

  ngOnInit() {
    this.customer = this.sessionService.getCurrentCustomer();

    this.customerService.customerOrders(this.customer.email).subscribe(
      response => {
        this.customerOrders = response.orders;
        console.log(this.customerOrders);
        //this.sessionService.setCurrentOrder(this.customerOrders);
        //this.customerOrders.push(this.co);
      }, error => {
        // this.router.navigate(["/customerLogin"]);
        console.log('********** CustomerLoginComponent.ts customerOrders(): ' + error);
        
      }
    );

    this.sidenavItems = [{
      label: 'Manage My Account',
      items: [
        { label: 'Account Details', icon: 'pi pi-user', routerLink: ['/customerDetails'] },
        {  label: 'View Available Coupons', icon: 'pi pi-ticket', routerLink: ['/customerCoupons'] },
      ]
    },
    {
      label: 'My Orders',
      items: [
        { label: 'Past Orders', icon: 'pi pi-file-o', routerLink: ['/customerOrders'] },
      ]
    },
    {
      label: 'My Reviews',
      items: [
        { label: 'Posted Reviews', icon: 'pi pi-pencil' },
      ]
    }];

    
  }
  

}
