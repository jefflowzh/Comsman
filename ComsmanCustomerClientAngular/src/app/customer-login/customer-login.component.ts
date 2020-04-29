import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {

  email: string;
  password: string;
  submitted: boolean;
  loginError: boolean;
  errorMessage: string;

  constructor(private router: Router, public sessionService: SessionService,
    private customerService: CustomerService) {
    this.submitted = false;
    this.loginError = false;
  }

  ngOnInit() {
  }

  clear() {
    this.submitted = false;
    this.email = "";
    this.password = "";
  }

  customerLogin(customerLoginForm: NgForm) {
    this.submitted = true;

    if (customerLoginForm.valid) {
      this.customerService.customerLogin(this.email, this.password).subscribe(
        response => {
          let customer: Customer = response.customer;

          if (customer != null) {

            this.sessionService.setIsLogin(true);
            this.sessionService.setCurrentCustomer(customer);
            this.loginError = false;

            this.router.navigate(["/index"]);
          } else {
            this.loginError = true;
            this.errorMessage = "Null customer error";
          }
        }, error => {
          this.loginError = true;
          console.log('********** CustomerLoginComponent.ts customerLogin(): ' + error);
          this.errorMessage = "Error: " + error.slice(37);
        }
      );
    }
  }

}
