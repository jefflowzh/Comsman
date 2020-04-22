import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';

@Component({
  selector: 'app-customer-registration',
  templateUrl: './customer-registration.component.html',
  styleUrls: ['./customer-registration.component.css']
})
export class CustomerRegistrationComponent implements OnInit {

  newCustomer: Customer;
  submitted: boolean;

  registrationError: boolean;
  errorMessage: string;

  displayRegistrationSuccessDialog: boolean;

  constructor(private router: Router, public sessionService: SessionService,
    private customerService: CustomerService) {
    this.newCustomer = new Customer;
    this.submitted = false;
    this.registrationError = false;
    this.displayRegistrationSuccessDialog = false;
  }

  ngOnInit() {
  }

  customerRegistration(customerRegistrationForm: NgForm) {
    this.submitted = true;

    if (customerRegistrationForm.valid) {
      this.customerService.customerRegistration(this.newCustomer).subscribe(
        response => {
          let newCustomerId: number = response.customerId;
          if (newCustomerId != null) {
            this.registrationError = false;
            this.displayRegistrationSuccessDialog = true;

            this.customerService.customerLogin(this.newCustomer.email, this.newCustomer.password);

            this.router.navigate(["/index"]);
          } else {
            this.registrationError = true;
          }
        }, error => {
          this.registrationError = true;
          this.errorMessage = error;
        }
      );
    }
  }

}
