import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';
import { ComputerSetService } from '../computer-set.service';
import { ComputerSet } from '../computer-set';
import { ComputerPart } from '../computer-part';
import { CPU } from '../cpu';

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
    private customerService: CustomerService, private computerSetService: ComputerSetService) {
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
          customer.password = "";
          
          if (customer != null) {

            this.sessionService.setIsLogin(true);
            this.sessionService.setCurrentCustomer(customer);
            this.loginError = false;
            console.log(customer.currComputerBuild);

            //let currComputerBuild = response.currComputerBuild

            //this.sessionService.setCurrentComputerSet(currComputerBuild);
            //console.log(this.sessionService.getCurrentComputerSet());
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
