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

  searchTerm: string;
  displayLoading: boolean;

  constructor(private router: Router, public sessionService: SessionService, private customerService: CustomerService) {
    this.searchTerm = "";
    this.displayLoading = false;
  }

  ngOnInit() {
  }

  search() {
    this.router.navigate(["/viewAllSearchResults/" + this.searchTerm]);
  }

  customerLogout(): void {
    this.displayLoading = true;
    setTimeout(() => this.cleanup(), 1000)
  }

  cleanup(): void {
    this.displayLoading = false
    this.sessionService.setIsLogin(false);

    // persist changes (if any) of customer cart to database
    this.customerService.updateCustomerCart().subscribe(
      response => {
        console.log('********** HeaderComponent.ts updateCustomerCart() successful!');
      },
      error => {
        console.log('********** HeaderComponent.ts updateCustomerCart() error:' + error);
      }
    );

    this.sessionService.setCurrentCustomer(null);
    this.sessionService.setCurrentComputerSet(null);
    this.sessionService.setLastAddedComputerPart(null);

    this.router.navigate(["/index"]);
  }

}
