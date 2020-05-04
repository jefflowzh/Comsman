import { Component, OnInit, Input } from '@angular/core';

import { SelectItem } from 'primeng/api';
import { MenuItem } from 'primeng/api';

import { Product } from '../product';
import { RouterLink, Router } from '@angular/router';
import { SessionService } from '../session.service';
import { ComputerSet } from '../computer-set';
import { CPU } from '../cpu';

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

  constructor(private sessionService : SessionService, private router: Router) {
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

  addToBuild(product){
    let computerSet : ComputerSet = this.sessionService.getCurrentComputerSet();
    if(this.productType == "CPU"){
      computerSet.cpu = product;
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "Air Cooler"){
      computerSet.airCooler = product;
      computerSet.waterCooler = null;
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "Computer Case"){
      computerSet.compCase = product;
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "Graphics Card"){
      computerSet.gpus.push(product);
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "Hard Drive"){
      computerSet.hdds.push(product);
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "Motherboard"){
      computerSet.motherBoard = product;
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "Power Supply Unit"){
      computerSet.psu = product;
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "RAM"){
      computerSet.rams.push(product);
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "SSD"){
      computerSet.ssds.push(product);
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }
    if(this.productType == "Water Cooler"){
      computerSet.waterCooler = product;
      computerSet.airCooler = null;
      this.sessionService.setLastAddedComputerPart(product);
      this.sessionService.setCurrentComputerSet(computerSet);
      this.router.navigate(["/advancedSetBuildPage"]);
    }

  }

}
