import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { SelectItem } from 'primeng/api';

import { ProductService } from '../product.service';
import { Product } from '../product';
import { ComputerCase } from '../computer-case';

@Component({
  selector: 'app-view-all-products',
  templateUrl: './view-all-products.component.html',
  styleUrls: ['./view-all-products.component.css']
})
export class ViewAllProductsComponent implements OnInit {

  @Input() products: Product[];
  sortOptions: SelectItem[];
  sortKey: string;
  sortField: string;
  sortOrder: number;
  header: string;
  @Input() productType: string;

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute) {
    this.sortOptions = [
      { label: 'Lowest Price', value: 'price' },
      { label: 'Highest Price', value: '!price' },
    ];
  }

  ngOnInit() {
    if (this.productType == "Computer Case") {
      this.header = "All Computer Cases";
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
