import { Component, OnInit } from '@angular/core';

import { ProductService } from '../product.service';
import { SessionService } from '../session.service';

import { PreBuiltComputerSetModel } from '../pre-built-computer-set-model';
import { ComputerSet } from '../computer-set';
import { LineItem } from '../line-item';

interface Warranty {
  name: string;
  cost: number;
}

@Component({
  selector: 'app-amateur-set-main-page',
  templateUrl: './amateur-set-main-page.component.html',
  styleUrls: ['./amateur-set-main-page.component.css']
})
export class AmateurSetMainPageComponent implements OnInit {

  budgetTierModel: PreBuiltComputerSetModel;
  regularTierModel: PreBuiltComputerSetModel;
  premiumTierModel: PreBuiltComputerSetModel;

  additionalWarrantyBudget: Warranty[];
  selectedAdditionalWarrantyBudget: Warranty;
  additionalWarrantyRegular: Warranty[];
  selectedAdditionalWarrantyRegular: Warranty;
  additionalWarrantyPremium: Warranty[];
  selectedAdditionalWarrantyPremium: Warranty;

  displayAddtoCartSuccessModal: boolean;

  constructor(private productService: ProductService, private sessionService: SessionService) {
    this.displayAddtoCartSuccessModal = false;

    let warranties = [
      { name: 'Nil', cost: 0 },
      { name: '+1 Year (+S$50)', cost: 50 },
      { name: '+2 Years (+S$75)', cost: 75 },
      { name: '+3 years (+S$85)', cost: 85 }
    ]
    this.additionalWarrantyBudget = warranties;
    this.additionalWarrantyRegular = warranties;
    this.additionalWarrantyPremium = warranties;

    this.selectedAdditionalWarrantyBudget = { name: 'Nil', cost: 0 };
    this.selectedAdditionalWarrantyRegular = { name: 'Nil', cost: 0 };
    this.selectedAdditionalWarrantyPremium = { name: 'Nil', cost: 0 };
  }

  ngOnInit() {
    this.productService.retrievePreBuiltComputerSetModels().subscribe(
      response => {
        for (let preBuiltComputerSetModel of response.preBuiltComputerSetModels) {
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

  addBudgetToCart() {
    this.addComputerSetToCart(this.budgetTierModel, "Budget");
  }

  addRegularToCart() {
    this.addComputerSetToCart(this.regularTierModel, "Regular");
  }

  addPremiumToCart() {
    this.addComputerSetToCart(this.premiumTierModel, "Premium");
  }

  addComputerSetToCart(preBuiltComputerSetModel: PreBuiltComputerSetModel, tier: string) {
    // transfer model computer parts to a new computer set to put in a line item
    let computerSet = new ComputerSet;
    computerSet.cpu = preBuiltComputerSetModel.cpu;
    computerSet.motherBoard = preBuiltComputerSetModel.motherboard;
    for (let ram of preBuiltComputerSetModel.rams) {
      computerSet.rams.push(ram);
    }
    computerSet.psu = preBuiltComputerSetModel.psu;
    computerSet.compCase = preBuiltComputerSetModel.compCase;
    for (let gpu of preBuiltComputerSetModel.gpus) {
      computerSet.gpus.push(gpu);
    }
    for (let hdd of preBuiltComputerSetModel.hdds) {
      computerSet.hdds.push(hdd);
    }
    for (let ssd of preBuiltComputerSetModel.ssds) {
      computerSet.ssds.push(ssd);
    }
    if (preBuiltComputerSetModel.waterCooler != null) {
      computerSet.waterCooler = preBuiltComputerSetModel.waterCooler;
    }
    if (preBuiltComputerSetModel.airCooler != null) {
      computerSet.airCooler = preBuiltComputerSetModel.airCooler;
    }

    let selectedAdditionalWarranty: Warranty;
    if (tier == "Budget") {
      selectedAdditionalWarranty = this.selectedAdditionalWarrantyBudget;
    } else if (tier == "Regular") {
      selectedAdditionalWarranty = this.selectedAdditionalWarrantyRegular;
    } else {
      selectedAdditionalWarranty = this.selectedAdditionalWarrantyPremium;
    }

    if (selectedAdditionalWarranty.name == 'Nil') {
      computerSet.warrantyLengthInYears = 2;
      computerSet.price = preBuiltComputerSetModel.price;
    } else if (selectedAdditionalWarranty.name == '+1 Year (+S$50)') {
      computerSet.warrantyLengthInYears = 3;
      computerSet.price = preBuiltComputerSetModel.price + 50;
    } else if (selectedAdditionalWarranty.name == '+2 Years (+S$75)') {
      computerSet.warrantyLengthInYears = 4;
      computerSet.price = preBuiltComputerSetModel.price + 75;
    } else {
      computerSet.warrantyLengthInYears = 5;
      computerSet.price = preBuiltComputerSetModel.price + 85;
    }

    computerSet.tier = tier;

    let newLineItem = new LineItem();
    newLineItem.computerSet = computerSet;
    newLineItem.quantity = 1;

    let currentCustomer = this.sessionService.getCurrentCustomer();
    currentCustomer.cart.push(newLineItem);
    this.sessionService.setCurrentCustomer(currentCustomer);

    this.displayAddtoCartSuccessModal = true;
  }

  confirmSuccess() {
    this.displayAddtoCartSuccessModal = false;
  }

}
