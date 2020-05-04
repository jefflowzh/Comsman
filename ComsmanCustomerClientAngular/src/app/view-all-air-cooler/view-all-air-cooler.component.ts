import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { CPUAirCooler } from '../cpuair-cooler';

@Component({
  selector: 'app-view-all-air-cooler',
  templateUrl: './view-all-air-cooler.component.html',
  styleUrls: ['./view-all-air-cooler.component.css']
})
export class ViewAllAirCoolerComponent implements OnInit {

  productType: string;
  allAirCooler: CPUAirCooler[];
  filteredAirCooler: CPUAirCooler[];

  // private String Manufacturer;
  // private String colour;
  // private Integer noiseLevel; // (in dB)
  // private Double Height; // (mm) //this one not really useful if its water cooled
  // private String[] CPUChipCompatibility;

  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  colour: string[] = []
  selectedColour: string[] = []

  noiseLevel: string[] = []
  selectedNoiseLevel: string[] = []

  height: string[] = []
  selectedHeight: string[] = []

  CPUChipCompatibility: string[] = []
  selectedCPUChipCompatibility: string[] = []


  AllManufacturersCheckbox: boolean = true;
  AllNoiseLevelCheckbox: boolean = true;
  AllHeightCheckbox: boolean = true;
  AllColourCheckbox: boolean = true;
  AllCpuChipCompatibilityCheckbox: boolean = true;


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Air Cooler"

    this.productService.retrieveAllCPUAirCoolers().subscribe(
      response => {
        this.allAirCooler = response.products;
        this.filteredAirCooler = this.allAirCooler;

        for (let airCooler of this.allAirCooler) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(airCooler.manufacturer) < 0) {
            this.manufacturers.push(airCooler.manufacturer);
          }


          if (this.noiseLevel.length == 0 || !this.noiseLevel.includes(airCooler.noiseLevel.toString())) {
            this.noiseLevel.push(airCooler.noiseLevel.toString());
          }



          if (this.height.length == 0 || !this.height.includes(airCooler.height.toString())) {
            this.height.push(airCooler.height.toString());
          }



          if (this.colour.length == 0 || !this.colour.includes(airCooler.colour.toString())) {
            this.colour.push(airCooler.colour.toString());
          }


         

          for (let m of airCooler.CPUChipCompatibility) {
            if (this.CPUChipCompatibility.length == 0 || !this.CPUChipCompatibility.includes(m)) {
              this.CPUChipCompatibility.push(m);
            }
          }

          this.manufacturers.sort();
          this.noiseLevel.sort();
          this.height.sort();
          this.colour.sort();
          this.CPUChipCompatibility.sort();


          this.selectedManufacturers = this.manufacturers;
          this.selectedNoiseLevel = this.noiseLevel;
          this.selectedHeight = this.height;
          this.selectedColour = this.colour
          this.selectedCPUChipCompatibility = this.CPUChipCompatibility
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
      console.log(this.selectedManufacturers)
      this.removeManufacturerFilters();
      this.selectedManufacturers = this.manufacturers;
      this.applyFilters();
      this.AllManufacturersCheckbox = true
      console.log(this.selectedManufacturers)
      return;
    }
    // if (this.selectedManufacturers.length ! = this.manufacturers.length){
    //   this.AllManufacturersCheckbox = false

    // }
    //types
    
    // if (this.selectedColours.length ! = this.colours.length){
    //   this.AllColourCheckbox = false
    // }
    //side panel view
    if (this.selectedNoiseLevel.length == 0) {
      this.removeChipsetFilters();
      this.selectedNoiseLevel = this.noiseLevel;
      this.applyFilters();
      this.AllNoiseLevelCheckbox = true;
      return;
    }
    // if (this.selectedSidePanelViews.length ! = this.sidePanelViews.length){
    //   this.AllSidePanelViewCheckbox = false
    // }
    //motherboard form factor
    if (this.selectedHeight.length == 0) {
      this.removeMemorySlotFilters();
      this.selectedHeight = this.height;
      this.applyFilters();
      this.AllHeightCheckbox = true;
      return;
    }
    // if (this.selectedMotherboardFormFactors.length ! = this.motherboardFormFactors.length){
    //   this.AllMotherboardFormFactorsCheckbox = false
    // }
    //full Height ExpansionSlot 
    if (this.selectedColour.length == 0) {
      console.log(this.selectedColour)
      this.removeColourFilters();
      this.selectedColour = this.colour;
      console.log(this.selectedColour)
      this.applyFilters();
      this.AllColourCheckbox = true;
      return;
    }
    // if (this.selectedFullHeightExpansionSlot.length ! = this.fullHeightExpansionSlot.length){
    //   this.AllFullHeightExpansionSlotCheckbox = false
    // }
    //top fan support
    
    if (this.selectedCPUChipCompatibility.length == 0) {
      this.removeSupportedMemorySpeedFilters();
      this.selectedCPUChipCompatibility = this.CPUChipCompatibility;
      this.applyFilters();
      this.AllCpuChipCompatibilityCheckbox = true;
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

    this.filteredAirCooler = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let mb of this.allAirCooler) {
      if (!this.selectedManufacturers.includes(mb.manufacturer) && this.filteredAirCooler.indexOf(mb) < 0) {
        continue;
      }
     
      if (!this.selectedNoiseLevel.includes(mb.noiseLevel.toString()) && this.filteredAirCooler.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedHeight.includes(mb.height.toString()) && this.filteredAirCooler.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedColour.includes(mb.colour.toString()) && this.filteredAirCooler.indexOf(mb) < 0) {
        continue;
      }
      if (!mb.CPUChipCompatibility.some(r => this.selectedCPUChipCompatibility.includes(r)) && this.filteredAirCooler.indexOf(mb) < 0) {
        continue;
      }

      this.filteredAirCooler.push(mb);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredAirCooler = [];
    this.updateFilters();
  }

  

  removeSupportedMemorySpeedFilters() {
    if (!this.AllCpuChipCompatibilityCheckbox) {
      this.selectedCPUChipCompatibility = [];
      return;
    }

    this.selectedCPUChipCompatibility = this.CPUChipCompatibility;
    this.filteredAirCooler = [];
    this.updateFilters();
  }


  
  
  removeColourFilters() {
    if (!this.AllColourCheckbox) {
      this.selectedColour = [];
      return;
    }

    this.selectedColour = this.colour;
    this.filteredAirCooler = [];
    this.updateFilters();
  }
  removeMemorySlotFilters() {
    if (!this.AllHeightCheckbox) {
      this.selectedHeight = [];
      return;
    }

    this.selectedHeight = this.height;
    this.filteredAirCooler = [];
    this.updateFilters();
  }
  removeChipsetFilters() {
    if (!this.AllNoiseLevelCheckbox) {
      this.selectedNoiseLevel = [];
      return;
    }

    this.selectedNoiseLevel = this.noiseLevel;
    this.filteredAirCooler = [];
    this.updateFilters();
  }
  
}


