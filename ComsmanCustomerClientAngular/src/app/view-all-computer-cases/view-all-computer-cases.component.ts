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

  // manufacturers: string[] = ['Manufacturer1', 'Manufacturer2', 'Manufacturer3', 'Manufacturer4'];
  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  //types: string[] = ['type1', 'type2', 'type3', 'type4'];
  types: string[] = []
  selectedTypes: string[] = []

  colours: string[] = []
  selectedColours: string[] = []

  sidePanelViews: string[] = []
  selectedSidePanelViews: string[] = []

  motherboardFormFactors: string[] = []
  selectedMotherboardFormFactors: string[] = []

  fullHeightExpansionSlot: string[] = []
  selectedFullHeightExpansionSlot: string[] = []

  // think about max video height length as a slider
  maxVideoheightLength: string[] = []
  selectedMaxVideoHeightLength: string[] = []

  topFanSupport: string[] = []
  selectedTopFanSupport: string[] = []

  frontFanSupport: string[] = []
  selectedFrontFanSupport: string[] = []

  rearFanSupport: string[] = []
  selectedRearFanSupport: string[] = []

  AllManufacturersCheckbox: boolean = true;
  AllTypesCheckbox: boolean = true;
  AllColourCheckbox: boolean = true;
  AllSidePanelViewCheckbox: boolean = true;
  AllMotherboardFormFactorsCheckbox: boolean = true;
  AllFullHeightExpansionSlotCheckbox: boolean = true;
  //AllMaxVideoHeightLength: boolean = true;
  AllTopFanSupportCheckbox: boolean = true;
  AllFrontFanSupportCheckbox: boolean = true;
  AllRearFanSupportCheckbox: boolean = true;


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Computer Case"

    this.productService.retrieveAllComputerCases().subscribe(
      response => {
        this.allComputerCases = response.products;
        this.filteredComputerCases = this.allComputerCases;

        for (let computerCase of this.allComputerCases) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(computerCase.manufacturer) < 0) {
            this.manufacturers.push(computerCase.manufacturer)
          }


          if (this.types.length == 0 || !this.types.includes(computerCase.type)) {
            this.types.push(computerCase.type)
          }

          //!computerCase.colours.some(r => this.selectedColours.includes(r)
          for (let c of computerCase.colours) {
            if (this.colours.length == 0 || !this.colours.includes(c)) {
              this.colours.push(c)
            }
          }

          //sidePanelViews: string[]
          if (this.sidePanelViews.length == 0 || !this.sidePanelViews.includes(computerCase.sidePanelView)) {
            this.sidePanelViews.push(computerCase.sidePanelView)
          }

          //motherboardFormFactors: string[]
          for (let c of computerCase.motherBoardFormFactor) {
            if (this.motherboardFormFactors.length == 0 || !this.motherboardFormFactors.includes(c)) {
              this.motherboardFormFactors.push(c)
            }
          }

          //fullHeightExpansionSlot: number[]
          if (this.fullHeightExpansionSlot.length == 0 || !this.fullHeightExpansionSlot.includes(computerCase.fullHeightExpansionSlot.toString())) {
            this.fullHeightExpansionSlot.push(computerCase.fullHeightExpansionSlot.toString())
          }

          //maxVideoheightLength: number[]
          if (this.maxVideoheightLength.length == 0 || !this.maxVideoheightLength.includes(computerCase.maxVideoCardLength.toString())) {
            this.maxVideoheightLength.push(computerCase.maxVideoCardLength.toString())
          }

          //topFanSupport: number[]
          if (this.topFanSupport.length == 0 || !this.topFanSupport.includes(computerCase.topFanSupport.toString())) {
            this.topFanSupport.push(computerCase.topFanSupport.toString())
          }

          //frontFanSupport: number[]
          if (this.frontFanSupport.length == 0 || !this.frontFanSupport.includes(computerCase.frontFanSupport.toString())) {
            this.frontFanSupport.push(computerCase.frontFanSupport.toString())
          }

          //rearFanSupport: number[]
          if (this.rearFanSupport.length == 0 || !this.rearFanSupport.includes(computerCase.rearFanSupport.toString())) {
            this.rearFanSupport.push(computerCase.rearFanSupport.toString())
          }
        }
      },
      error => {
        console.log('********** ViewAllComputerCasesComponent.ts: ' + error);
      }
    );
  }

  applyFilters() {
    //console.log(this.selectedManufacturers)

    //manufacturers
    if (this.selectedManufacturers.length == 0) {
      this.removeManufacturerFilters();
      this.selectedManufacturers = this.manufacturers;
      this.applyFilters();
      this.AllManufacturersCheckbox = true
      return;
    }
    //types
    if (this.selectedTypes.length == 0) {
      this.removeTypeFilters();
      this.selectedTypes = this.types;
      this.applyFilters();
      this.AllTypesCheckbox = true;
      return;
    }
    //colour
    if (this.selectedColours.length == 0) {
      this.removeColourFilters();
      this.selectedColours = this.colours;
      this.applyFilters();
      this.AllColourCheckbox = true;
      return;
    }
    //side panel view
    if (this.selectedSidePanelViews.length == 0) {
      this.removeSidePanelFilters();
      this.selectedSidePanelViews = this.sidePanelViews;
      this.applyFilters();
      this.AllSidePanelViewCheckbox = true;
      return;
    }
    //motherboard form factor
    if (this.selectedMotherboardFormFactors.length == 0) {
      this.removeFormFactorFilters();
      this.selectedMotherboardFormFactors = this.motherboardFormFactors;
      this.applyFilters();
      this.AllMotherboardFormFactorsCheckbox = true;
      return;
    }
    //full Height ExpansionSlot 
    if (this.selectedFullHeightExpansionSlot.length == 0) {
      console.log(this.selectedFullHeightExpansionSlot)
      this.removeExpansionSlotFilters();
      this.selectedFullHeightExpansionSlot = this.fullHeightExpansionSlot;
      console.log(this.selectedFullHeightExpansionSlot)
      this.applyFilters();
      this.AllFullHeightExpansionSlotCheckbox = true;
      return;
    }
    //top fan support
    if (this.selectedTopFanSupport.length == 0) {
      this.removeTopFanFilters();
      this.selectedTopFanSupport = this.topFanSupport;
      this.applyFilters();
      this.AllTopFanSupportCheckbox = true;
      return;
    }
    //front fan support 
    if (this.selectedFrontFanSupport.length == 0) {
      this.removeFrontFanFilters();
      this.selectedFrontFanSupport = this.frontFanSupport;
      this.applyFilters();
      this.AllFrontFanSupportCheckbox = true;
      return;
    }
    //rear fan support 
    if (this.selectedRearFanSupport.length == 0) {
      this.removeRearFanFilters();
      this.selectedRearFanSupport = this.rearFanSupport;
      this.applyFilters();
      this.AllRearFanSupportCheckbox = true;
      return;
    }

    // this.AllManufacturersCheckbox = false;
    // this.AllTypesCheckbox = false;
    // this.AllColourCheckbox = false;
    // this.AllSidePanelViewCheckbox = false;
    // this.AllMotherboardFormFactorsCheckbox = false;
    // this.AllFullHeightExpansionSlotCheckbox = false;
    // //AllTypesCheckbox: boolean = true;
    // this.AllTopFanSupportCheckbox = false;
    // this.AllFrontFanSupportCheckbox = false;
    // this.AllRearFanSupportCheckbox = false;   

    this.filteredComputerCases = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let computerCase of this.allComputerCases) {
      if (!this.selectedManufacturers.includes(computerCase.manufacturer)) {
        continue;
      }
      if (!this.selectedTypes.includes(computerCase.type) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      if (!computerCase.colours.some(r => this.selectedColours.includes(r)) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      if (!this.selectedSidePanelViews.includes(computerCase.sidePanelView) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      if (!computerCase.motherBoardFormFactor.some(r => this.selectedMotherboardFormFactors.includes(r)) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      if (!this.selectedFullHeightExpansionSlot.includes(computerCase.fullHeightExpansionSlot.toString()) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      if (!this.selectedTopFanSupport.includes(computerCase.topFanSupport.toString()) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      if (!this.selectedFrontFanSupport.includes(computerCase.frontFanSupport.toString()) && this.filteredComputerCases.indexOf(computerCase) < 0) {
        continue;
      }
      if (!this.selectedRearFanSupport.includes(computerCase.rearFanSupport.toString()) && this.filteredComputerCases.indexOf(computerCase) < 0) {
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

  removeRearFanFilters() {
    if (!this.AllRearFanSupportCheckbox) {
      this.selectedRearFanSupport = [];
      return;
    }

    this.selectedRearFanSupport = this.rearFanSupport;
    this.filteredComputerCases = [];
    this.updateFilters();
  }
  removeFrontFanFilters() {
    if (!this.AllFrontFanSupportCheckbox) {
      this.selectedFrontFanSupport = [];
      return;
    }

    this.selectedFrontFanSupport = this.frontFanSupport;
    this.filteredComputerCases = [];
    this.updateFilters();
  }
  removeTopFanFilters() {
    if (!this.AllTopFanSupportCheckbox) {
      this.selectedTopFanSupport = [];
      return;
    }

    this.selectedTopFanSupport = this.topFanSupport;
    this.filteredComputerCases = [];
    this.updateFilters();
  }
  removeExpansionSlotFilters() {
    if (!this.AllFullHeightExpansionSlotCheckbox) {
      this.selectedFullHeightExpansionSlot = [];
      return;
    }

    this.selectedFullHeightExpansionSlot = this.fullHeightExpansionSlot;
    this.filteredComputerCases = [];
    this.updateFilters();
  }
  removeFormFactorFilters() {
    if (!this.AllMotherboardFormFactorsCheckbox) {
      this.selectedMotherboardFormFactors = [];
      return;
    }

    this.selectedMotherboardFormFactors = this.motherboardFormFactors;
    this.filteredComputerCases = [];
    this.updateFilters();
  }
  removeSidePanelFilters() {
    if (!this.AllSidePanelViewCheckbox) {
      this.selectedSidePanelViews = [];
      return;
    }

    this.selectedSidePanelViews = this.sidePanelViews;
    this.filteredComputerCases = [];
    this.updateFilters();
  }
  removeColourFilters() {
    if (!this.AllColourCheckbox) {
      this.selectedColours = [];
      return;
    }

    this.selectedColours = this.colours;
    this.filteredComputerCases = [];
    this.updateFilters();
  }
}
