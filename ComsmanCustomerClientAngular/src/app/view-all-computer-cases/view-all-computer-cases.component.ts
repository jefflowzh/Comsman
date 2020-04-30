import { Component, OnInit } from '@angular/core';

import { ComputerCase } from '../computer-case';

import { ProductService } from '../product.service';

@Component({
  selector: 'app-view-all-computer-cases',
  templateUrl: './view-all-computer-cases.component.html',
  styleUrls: ['./view-all-computer-cases.component.css']
})
export class ViewAllComputerCasesComponent implements OnInit {

  productType: string;
  allComputerCases: ComputerCase[];
  filteredComputerCases: ComputerCase[];

  manufacturers: string[] = ['Manufacturer1', 'Manufacturer2', 'Manufacturer3', 'Manufacturer4'];
  selectedManufacturers: string[] = []

  types: string[] = ['type1', 'type2', 'type3', 'type4'];
  selectedTypes: string[] = []

  AllManufacturersCheckbox: boolean = true;
  AllTypesCheckbox: boolean = true;


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Computer Case"

    this.productService.retrieveAllComputerCases().subscribe(
      response => {
        this.allComputerCases = response.products;
        this.filteredComputerCases = this.allComputerCases;
      },
      error => {
        console.log('********** ViewAllComputerCasesComponent.ts: ' + error);
      }
    );
  }

  applyFilters() {
    console.log(this.selectedManufacturers)

    if (this.selectedManufacturers.length == 0) {
      this.removeManufacturerFilters();
      this.selectedManufacturers = this.manufacturers;
      this.applyFilters();
      this.AllManufacturersCheckbox = true
      return;
    }
    if (this.selectedTypes.length == 0) {
      this.removeTypeFilters();
      this.selectedTypes = this.types;
      this.applyFilters();
      this.AllTypesCheckbox = true;
      return;
    }

    this.AllManufacturersCheckbox = false;
    this.AllTypesCheckbox = false;
    this.filteredComputerCases = [];
    this.updateFilters();
  }

  updateFilters(){
    for (let computerCase of this.allComputerCases) {
      if (!this.selectedManufacturers.includes(computerCase.manufacturer)) {
        continue;
      }
      if (!this.selectedTypes.includes(computerCase.type) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      this.filteredComputerCases.push(computerCase);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;

    // console.log("remove:")
    // console.log(this.selectedManufacturers)
    // // this.AllManufacturersCheckbox = !this.AllManufacturersCheckbox;
    // console.log(!this.AllManufacturersCheckbox)

    this.filteredComputerCases = [];
    this.updateFilters();
  }

  // types: string[] = ['type1', 'type2', 'type3', 'type4'];
  // selectedTypes: string[] = []
  // AllTypesCheckbox: boolean = true;

  removeTypeFilters() {
    if (!this.AllTypesCheckbox) {
      this.selectedTypes = [];
      return;
    }

    this.selectedTypes = this.types;

    // console.log("remove:")
    // console.log(this.selectedTypes)
    // // this.AllManufacturersCheckbox = !this.AllManufacturersCheckbox;
    // console.log(!this.AllTypesCheckbox)

    this.filteredComputerCases = [];
    this.updateFilters();
  }

}
