import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SessionService } from '../session.service';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  searchQuery: string;
  displayLoading: Boolean;

  constructor(private router: Router, public sessionService: SessionService, private customerService: CustomerService) {
    this.displayLoading = false;
  }

  ngOnInit() {
  }

  customerLogout(): void {
    this.displayLoading = true;
    setTimeout(() => this.cleanup(), 1000)

  }

  cleanup(): void {
    this.displayLoading = false
    this.sessionService.setIsLogin(false);

    // persist changes (if any) of customer cart and currComputerSetParts to database
    this.customerService.updateCustomerCart().subscribe(
      response => {
        console.log('********** HeaderComponent.ts updateCustomerCart() successful!');
      },
      error => {
        console.log('********** HeaderComponent.ts updateCustomerCart() error:' + error);
      }
    );

    // // persist changes (if any) of customer currComputerSetParts to database
    // this.customerService.updateCustomerBuild(this.sessionService.getCurrentCustomer().currComputerSetParts).subscribe(
    //   response => {
    //     console.log('********** HeaderComponent.ts updateCustomerBuild() successful!');
    //   },
    //   error => {
    //     console.log('********** HeaderComponent.ts updateCustomerBuild() error:' + error);
    //   }
    // );

    this.sessionService.setCurrentCustomer(null);

    this.router.navigate(["/index"]);
  }

}
