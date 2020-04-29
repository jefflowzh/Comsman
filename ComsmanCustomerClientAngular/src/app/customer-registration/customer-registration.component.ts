import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';

@Component({
  selector: 'app-customer-registration',
  templateUrl: './customer-registration.component.html',
  styleUrls: ['./customer-registration.component.css'],
})
export class CustomerRegistrationComponent implements OnInit {

  newCustomer: Customer;
  submitted: boolean;

  registrationError: boolean;
  errorMessage: string;

  displayRegistrationSuccessModal: boolean;

  constructor(private router: Router, public sessionService: SessionService,
    private customerService: CustomerService) {
    this.newCustomer = new Customer;
    this.submitted = false;
    this.registrationError = false;
    this.displayRegistrationSuccessModal = false;
  }

  ngOnInit() {
  }

  customerRegistration(customerRegistrationForm: NgForm) {
    this.submitted = true;

    if (customerRegistrationForm.valid) {
      this.customerService.customerRegistration(this.newCustomer).subscribe(
        response => {
          let newCustomerId: number = response.newCustomerId;
          if (newCustomerId != null) {
            this.registrationError = false;
            this.displayRegistrationSuccessModal = true;
          } else {
            this.registrationError = true;
            this.errorMessage = "Null customer error";
          }
        }, error => {
          this.registrationError = true;
          console.log('********** CustomerRegistrationComponent.ts customerRegistration(): ' + error);
          this.errorMessage = "Error: " + error.slice(37);
        }
      );
    }
  }

  confirm() {
    this.displayRegistrationSuccessModal = false;
    this.router.navigate(["/index"])
  }

}
