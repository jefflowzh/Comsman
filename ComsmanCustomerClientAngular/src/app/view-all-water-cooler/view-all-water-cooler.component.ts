import { Component, OnInit } from '@angular/core';
import { CPUWaterCooler } from '../cpuwater-cooler';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-view-all-water-cooler',
  templateUrl: './view-all-water-cooler.component.html',
  styleUrls: ['./view-all-water-cooler.component.css']
})
export class ViewAllWaterCoolerComponent implements OnInit {

  productType: string;
  allWaterCooler: CPUWaterCooler[];
  filteredWaterCooler: CPUWaterCooler[];

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

  radiatorSize: string[] = []
  selectedRadiatorSize: string[] = []

  CPUChipCompatibility: string[] = []
  selectedCPUChipCompatibility: string[] = []


  AllManufacturersCheckbox: boolean = true;
  AllNoiseLevelCheckbox: boolean = true;
  AllRadiatorSizeCheckbox: boolean = true;
  AllColourCheckbox: boolean = true;
  AllCpuChipCompatibilityCheckbox: boolean = true;


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Water Cooler"

    this.productService.retrieveAllCPUWaterCoolers().subscribe(
      response => {
        this.allWaterCooler = response.products;
        this.filteredWaterCooler = this.allWaterCooler;

        for (let airCooler of this.allWaterCooler) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(airCooler.manufacturer) < 0) {
            this.manufacturers.push(airCooler.manufacturer);
          }


          if (this.noiseLevel.length == 0 || !this.noiseLevel.includes(airCooler.noiseLevel.toString())) {
            this.noiseLevel.push(airCooler.noiseLevel.toString());
          }



          if (this.radiatorSize.length == 0 || !this.radiatorSize.includes(airCooler.radiatorSize.toString())) {
            this.radiatorSize.push(airCooler.radiatorSize.toString());
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
          this.radiatorSize.sort();
          this.colour.sort();
          this.CPUChipCompatibility.sort();


          this.selectedManufacturers = this.manufacturers;
          this.selectedNoiseLevel = this.noiseLevel;
          this.selectedRadiatorSize = this.radiatorSize;
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

    //manufacturers
    if (this.selectedManufacturers.length == 0) {

      this.removeManufacturerFilters();
      this.selectedManufacturers = this.manufacturers;
      this.applyFilters();
      this.AllManufacturersCheckbox = true

      return;
    }

    //side panel view
    if (this.selectedNoiseLevel.length == 0) {
      this.removeChipsetFilters();
      this.selectedNoiseLevel = this.noiseLevel;
      this.applyFilters();
      this.AllNoiseLevelCheckbox = true;
      return;
    }

    //motherboard form factor
    if (this.selectedRadiatorSize.length == 0) {
      this.removeMemorySlotFilters();
      this.selectedRadiatorSize = this.radiatorSize;
      this.applyFilters();
      this.AllRadiatorSizeCheckbox = true;
      return;
    }

    //full Height ExpansionSlot 
    if (this.selectedColour.length == 0) {

      this.removeColourFilters();
      this.selectedColour = this.colour;

      this.applyFilters();
      this.AllColourCheckbox = true;
      return;
    }

    //top fan support
    if (this.selectedCPUChipCompatibility.length == 0) {
      this.removeSupportedMemorySpeedFilters();
      this.selectedCPUChipCompatibility = this.CPUChipCompatibility;
      this.applyFilters();
      this.AllCpuChipCompatibilityCheckbox = true;
      return;
    }

    this.filteredWaterCooler = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let mb of this.allWaterCooler) {
      if (!this.selectedManufacturers.includes(mb.manufacturer) && this.filteredWaterCooler.indexOf(mb) < 0) {
        continue;
      }

      if (!this.selectedNoiseLevel.includes(mb.noiseLevel.toString()) && this.filteredWaterCooler.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedRadiatorSize.includes(mb.radiatorSize.toString()) && this.filteredWaterCooler.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedColour.includes(mb.colour.toString()) && this.filteredWaterCooler.indexOf(mb) < 0) {
        continue;
      }
      if (!mb.CPUChipCompatibility.some(r => this.selectedCPUChipCompatibility.includes(r)) && this.filteredWaterCooler.indexOf(mb) < 0) {
        continue;
      }

      this.filteredWaterCooler.push(mb);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredWaterCooler = [];
    this.updateFilters();
  }

  removeSupportedMemorySpeedFilters() {
    if (!this.AllCpuChipCompatibilityCheckbox) {
      this.selectedCPUChipCompatibility = [];
      return;
    }

    this.selectedCPUChipCompatibility = this.CPUChipCompatibility;
    this.filteredWaterCooler = [];
    this.updateFilters();
  }

  removeColourFilters() {
    if (!this.AllColourCheckbox) {
      this.selectedColour = [];
      return;
    }

    this.selectedColour = this.colour;
    this.filteredWaterCooler = [];
    this.updateFilters();
  }
  removeMemorySlotFilters() {
    if (!this.AllRadiatorSizeCheckbox) {
      this.selectedRadiatorSize = [];
      return;
    }

    this.selectedRadiatorSize = this.radiatorSize;
    this.filteredWaterCooler = [];
    this.updateFilters();
  }
  removeChipsetFilters() {
    if (!this.AllNoiseLevelCheckbox) {
      this.selectedNoiseLevel = [];
      return;
    }

    this.selectedNoiseLevel = this.noiseLevel;
    this.filteredWaterCooler = [];
    this.updateFilters();
  }

}

