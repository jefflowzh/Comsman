import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { SelectItem } from 'primeng/api';

import { ProductService } from '../product.service';
import { Product } from '../product';

@Component({
  selector: 'app-view-all-products',
  templateUrl: './view-all-products.component.html',
  styleUrls: ['./view-all-products.component.css']
})
export class ViewAllProductsComponent implements OnInit {

  products: Product[];
  sortOptions: SelectItem[];
  sortKey: string;
  sortField: string;
  sortOrder: number;
  header: string;

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute) {
    this.sortOptions = [
      { label: 'Lowest Price', value: 'price' },
      { label: 'Highest Price', value: '!price' },
    ];
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => this.loadProducts(params["productType"]));
  }

  loadProducts(productType: string) {
    if (productType == "Peripheral") {
      this.header = "Peripherals";
      this.productService.retrieveAllPeripherals().subscribe(
        response => {
          this.products = response.peripherals;
        },
        error => {
          console.log('********** ViewAllPeripheralsComponent.ts: ' + error);
        }
      );
    }
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
