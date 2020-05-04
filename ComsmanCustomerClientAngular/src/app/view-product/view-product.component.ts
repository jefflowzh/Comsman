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

  displayAddtoCartSuccessModal: boolean;
  displayAddtoCartErrorModal: boolean;

  product: Product;
  quantitySelected: number;

  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService, public sessionService: SessionService) {
    this.displayAddtoCartSuccessModal = false;
    this.displayAddtoCartErrorModal = false;

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
      let newLineItem = new LineItem(null, product, this.quantitySelected, null);
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
