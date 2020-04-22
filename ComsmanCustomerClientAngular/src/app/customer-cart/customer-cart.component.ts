import { Component, OnInit } from '@angular/core';

import { SessionService } from '../session.service';
import { LineItem } from '../line-item';

@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.css']
})
export class CustomerCartComponent implements OnInit {

  cart: LineItem[]

  constructor(public sessionService: SessionService) { }

  ngOnInit() {
    this.cart = this.sessionService.getCurrentCustomer().cart;
  }

}
