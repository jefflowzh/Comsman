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
  passwordSubmitted: boolean = false;
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
    }];
  }

  updateCustomerDetails(customerForm: NgForm) {
    this.submitted = true;
    if (customerForm.valid) {
      this.customerService.updateCustomerDetails(this.customer).subscribe(
        response => {
          let updatedCustomer = response.customer;
          updatedCustomer.password = "";
          this.sessionService.setCurrentCustomer(updatedCustomer);
          this.customer = this.sessionService.getCurrentCustomer();
          this.updateError = false;
          this.updateSuccess = true;
          console.log("Update customer details success!!")
        }, error => {
          this.updateError = true;
          this.updateSuccess = false;
        }
      );
    }
  }

  updateCustomerPassword(customerPasswordForm: NgForm) {
    this.passwordSubmitted = true;
    if (customerPasswordForm.valid) {
      this.customerService.updateCustomerPassword(this.customer).subscribe(
        response => {
          this.updateError = false;
          this.updateSuccess = true;
          console.log("Update customer password success!!")
        }, error => {
          this.updateError = true;
          this.updateSuccess = false;
        }
      );
    }
  }

}
