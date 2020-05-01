import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Product } from '../product';
import { LineItem } from '../line-item';

import { ProductService } from '../product.service';
import { SessionService } from '../session.service';


@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit {

  product: Product;
  description: string;
  quantitySelected: number;

  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService, public sessionService: SessionService) {
    this.description = "this is a description"
    this.quantitySelected = 1;
  }

  ngOnInit() {
    let productId = this.activatedRoute.snapshot.paramMap.get('productId');

    this.productService.retrieveProductById(productId).subscribe(
      response => {
        this.product = response.product;
      },
      error => {
        console.log('********** ViewProductComponent.ts: ' + error);
      }
    );
  }

  // addToCart() {
  //   let lineItem = new LineItem(this.product, this.quantitySelected);

  //   let currCustomer = this.sessionService.getCurrentCustomer();
  //   currCustomer.cart.push(lineItem);
  //   this.sessionService.setCurrentCustomer(currCustomer);

  //   console.log("pushed")
  // }

}
