import { Component, OnInit } from '@angular/core';

import { ProductService } from '../product.service';

@Component({
  selector: 'app-amateur-set-main-page',
  templateUrl: './amateur-set-main-page.component.html',
  styleUrls: ['./amateur-set-main-page.component.css']
})
export class AmateurSetMainPageComponent implements OnInit {

  // budgetTierModel: BudgetTierModel;
  // regularTierModel: RegularTierModel;
  // premiumTierModel: PremiumTierModel;

  constructor(private productService: ProductService) { }

  ngOnInit() {

  }

}
