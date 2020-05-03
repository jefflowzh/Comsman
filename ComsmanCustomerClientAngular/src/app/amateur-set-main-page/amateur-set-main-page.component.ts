import { Component, OnInit } from '@angular/core';

import { ProductService } from '../product.service';

import { PreBuiltComputerSetModel } from '../pre-built-computer-set-model';

@Component({
  selector: 'app-amateur-set-main-page',
  templateUrl: './amateur-set-main-page.component.html',
  styleUrls: ['./amateur-set-main-page.component.css']
})
export class AmateurSetMainPageComponent implements OnInit {

  budgetTierModel: PreBuiltComputerSetModel;
  regularTierModel: PreBuiltComputerSetModel;
  premiumTierModel: PreBuiltComputerSetModel;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.retrievePreBuiltComputerSetModels().subscribe(
      response => {
        console.log(response.preBuiltComputerSetModels)
        for (let preBuiltComputerSetModel of response.preBuiltComputerSetModels) {
          console.log(preBuiltComputerSetModel.preBuiltComputerSetTier)
          if (preBuiltComputerSetModel.preBuiltComputerSetTier == "PREMIUM") {
            this.premiumTierModel = preBuiltComputerSetModel;
          } else if (preBuiltComputerSetModel.preBuiltComputerSetTier == "REGULAR") {
            this.regularTierModel = preBuiltComputerSetModel;
          } else {
            this.budgetTierModel = preBuiltComputerSetModel;
          }
        }
      },
      error => {
        console.log('********** AmateurSetMainPageComponent.ts: ' + error);
      }
    );
  }

}
