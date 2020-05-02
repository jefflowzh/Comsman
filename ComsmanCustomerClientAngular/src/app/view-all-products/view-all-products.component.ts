import { Component, OnInit, Input } from '@angular/core';

import { SelectItem } from 'primeng/api';
import { MenuItem } from 'primeng/api';

import { Product } from '../product';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-view-all-products',
  templateUrl: './view-all-products.component.html',
  styleUrls: ['./view-all-products.component.css']
})
export class ViewAllProductsComponent implements OnInit {

  home: MenuItem;
  breadcrumbItems: MenuItem[];

  @Input() products: Product[];
  sortOptions: SelectItem[];
  sortKey: string;
  sortField: string;
  sortOrder: number;
  @Input() productType: string;

  constructor() {
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

}
