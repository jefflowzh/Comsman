import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';

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

  constructor(private router: Router, public sessionService: SessionService,
    private customerService: CustomerService) {

  }

  ngOnInit() {
    this.customer = this.sessionService.getCurrentCustomer();
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
