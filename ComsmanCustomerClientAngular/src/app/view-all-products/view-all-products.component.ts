import { Component, OnInit, Input } from '@angular/core';

import { SelectItem } from 'primeng/api';
import { MenuItem } from 'primeng/api';

import { Product } from '../product';
import { LineItem } from '../line-item';
import { SessionService } from '../session.service';

@Component({
  selector: 'app-view-all-products',
  templateUrl: './view-all-products.component.html',
  styleUrls: ['./view-all-products.component.css']
})
export class ViewAllProductsComponent implements OnInit {

  displayAddtoCartSuccessModal: boolean;
  displayAddtoCartErrorModal: boolean;

  home: MenuItem;
  breadcrumbItems: MenuItem[];

  @Input() products: Product[];
  sortOptions: SelectItem[];
  sortKey: string;
  sortField: string;
  sortOrder: number;
  @Input() productType: string;

  constructor(public sessionService: SessionService) {
    this.displayAddtoCartSuccessModal = false;
    this.displayAddtoCartErrorModal = false;

    this.home = { icon: 'pi pi-home', routerLink: "/index" };

    this.sortOptions = [
      { label: 'Lowest Price', value: 'price' },
      { label: 'Highest Price', value: '!price' },
    ];
  }

  ngOnInit() {
    this.breadcrumbItems = [{ label: this.productType }]
  }

  onSortChange(event) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    }
    else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }

  addToCart(product: Product) {
    let currentCustomer = this.sessionService.getCurrentCustomer();

    // check if product already in cart
    let inCart = false;
    for (let li of currentCustomer.cart) {
      // is a product and not a computer set
      if (li.product != null) {
        if (li.product.productId == product.productId) {
          inCart = true;
          this.displayAddtoCartErrorModal = true;
          break;
        }
      }
    }
    if (!inCart) {
      let newLineItem = new LineItem(null, product, 1, null);
      currentCustomer.cart.push(newLineItem);
      this.sessionService.setCurrentCustomer(currentCustomer);
      this.displayAddtoCartSuccessModal = true;
    }
  }

  confirmSuccess() {
    this.displayAddtoCartSuccessModal = false;
  }

  confirmError() {
    this.displayAddtoCartErrorModal = false;
  }

}
