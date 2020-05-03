import { Component, OnInit } from '@angular/core';

import { ProductService } from '../product.service';
import { Peripheral } from '../peripheral';

@Component({
  selector: 'app-view-all-peripherals',
  templateUrl: './view-all-peripherals.component.html',
  styleUrls: ['./view-all-peripherals.component.css']
})
export class ViewAllPeripheralsComponent implements OnInit {

  productType: string;
  allPeripherals: Peripheral[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productType = "Peripheral"

    this.productService.retrieveAllPeripherals().subscribe(
      response => {
        this.allPeripherals = response.products;
      },
      error => {
        console.log('********** ViewAllPeripheralsComponent.ts: ' + error);
      }
    );
  }

}
