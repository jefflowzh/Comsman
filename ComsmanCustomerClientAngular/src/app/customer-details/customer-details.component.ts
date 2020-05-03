import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';
import { MenuItem } from 'primeng/api/menuitem';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  customer: Customer;
  success: boolean;
  submitted: boolean = false;
  updateError: boolean = false;
  updateSuccess: boolean = false;
  sidenavItems: MenuItem[];

  constructor(private router: Router, public sessionService: SessionService,
    private customerService: CustomerService) {

  }

  ngOnInit() {
    this.customer = this.sessionService.getCurrentCustomer();
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
    },
    {
      label: 'My Reviews',
      items: [
        { label: 'Posted Reviews', icon: 'pi pi-pencil' },
      ]
    }];
  }

  // updateCustomerDetails(customerForm: NgForm) {
  //   this.submitted = true;
  //   if (customerForm.valid) {
  //     this.customerService.customerUpdate(this.customer).subscribe(
  //       response => {
  //         let customer: Customer = response.customer;
  //         if (customer != null) {
  //           this.sessionService.setIsLogin(true);
  //           this.sessionService.setCurrentCustomer(customer);
  //           this.updateError = false;
  //           this.updateSuccess = true;
  //           console.log("update customer details success!!")
  //         }
  //       }, error => {
  //         this.updateError = true;
  //         this.updateSuccess = false;
  //       }
  //     );

  //   }
  // }

  updateCustomerDetails(customerForm: NgForm) {
    this.submitted = true;
    if (customerForm.valid) {
      this.customerService.updateCustomerDetails(this.customer).subscribe(
        response => {
          this.updateError = false;
          this.updateSuccess = true;
          console.log("update customer details success!!")
        }, error => {
          this.updateError = true;
          this.updateSuccess = false;
        }
      );

    }
  }

}
