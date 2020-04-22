import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SessionService } from '../session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  searchQuery: string;
  displayLoading: Boolean;

  constructor(private router: Router, public sessionService: SessionService) {
    this.displayLoading = false;
  }

  ngOnInit() {
  }

  customerLogout(): void {
    this.displayLoading = true;
    setTimeout(() => this.redirectUser(), 1000)

  }

  redirectUser(): void {
    this.displayLoading = false
    this.sessionService.setIsLogin(false);
    this.sessionService.setCurrentCustomer(null);

    this.router.navigate(["/index"]);
  }

}
