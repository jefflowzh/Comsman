import { Component, OnInit } from '@angular/core';
import { RAM } from '../ram';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-view-all-ram',
  templateUrl: './view-all-ram.component.html',
  styleUrls: ['./view-all-ram.component.css']
})
export class ViewAllRAMComponent implements OnInit {
  productType: string;
  allRAM: RAM[];
  filteredRAM: RAM[];

  // private String manufacturer;
  // private String speed;
  // private String type;
  // private Integer sticks; //(2 x 8GB)
  // private Integer perStickGB;
  // private Integer casLatency;

  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  speed: string[] = []
  selectedSpeed: string[] = []

  type: string[] = []
  selectedType: string[] = []

  sticks: string[] = []
  selectedSticks: string[] = []

  perStickGB: string[] = []
  selectedPerStickGB: string[] = []

  casLatency: string[] = []
  selectedCasLatency: string[] = []


  AllManufacturersCheckbox: boolean = true;
  AllSpeedCheckbox: boolean = true;
  AllTypeCheckbox: boolean = true;
  AllSticksCheckbox: boolean = true;
  AllPerStickGBCheckbox: boolean = true;
  AllCasLatencyCheckbox: boolean = true;
  

  


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Power Supply Unit"

    this.productService.retrieveAllRam().subscribe(
      response => {
        this.allRAM = response.products;
        this.filteredRAM = this.allRAM;

        for (let ram of this.allRAM) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(ram.manufacturer) < 0) {
            this.manufacturers.push(ram.manufacturer)
          }


          if (this.speed.length == 0 || !this.speed.includes(ram.speed)) {
            this.speed.push(ram.speed)
          }

         
           
          if (this.type.length == 0 || !this.type.includes(ram.type)) {
            this.type.push(ram.type)
          }
          

      
          if (this.sticks.length == 0 || !this.sticks.includes(ram.sticks.toString())) {
            this.sticks.push(ram.sticks.toString())
          }

       
        
          if (this.perStickGB.length == 0 || !this.perStickGB.includes(ram.perStickGB.toString())) {
            this.perStickGB.push(ram.perStickGB.toString())
          }
          

        
          if (this.casLatency.length == 0 || !this.casLatency.includes(ram.casLatency.toString())) {
            this.casLatency.push(ram.casLatency.toString())
          }

          this.manufacturers.sort();
          this.speed.sort();
          this.type.sort();
          this.sticks.sort();
          this.perStickGB.sort();
          this.casLatency.sort();
          
          
          
          this.selectedManufacturers = this.manufacturers;
          
          this.selectedSpeed= this.speed;
        
          this.selectedType =  this.type;
          
          this.selectedSticks = this.sticks;
          
          this.selectedPerStickGB = this.perStickGB;
          
          this.selectedCasLatency = this.casLatency

          
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
    if (this.selectedSpeed.length == 0) {
      this.removeSpeedFilters();
      this.selectedSpeed = this.speed;
      this.applyFilters();
      this.AllSpeedCheckbox = true;
      return;
    }
    // if (this.selectedTypes.length ! = this.selectedTypes.length){
    //   this.AllTypesCheckbox = false
    // }
    //colour
    if (this.selectedType.length == 0) {
      this.removeTypeFilters();
      this.selectedType = this.type;
      this.applyFilters();
      this.AllTypeCheckbox = true;
      return;
    }
    // if (this.selectedColours.length ! = this.colours.length){
    //   this.AllColourCheckbox = false
    // }
    //side panel view
    if (this.selectedSticks.length == 0) {
      this.removeSticksFilters();
      this.selectedSticks = this.sticks;
      this.applyFilters();
      this.AllSticksCheckbox = true;
      return;
    }
    // if (this.selectedSidePanelViews.length ! = this.sidePanelViews.length){
    //   this.AllSidePanelViewCheckbox = false
    // }
    //motherboard form factor
    if (this.selectedPerStickGB.length == 0) {
      this.removePerStickGBFilters();
      this.selectedPerStickGB = this.perStickGB;
      this.applyFilters();
      this.AllPerStickGBCheckbox = true;
      return;
    }
    // if (this.selectedMotherboardFormFactors.length ! = this.motherboardFormFactors.length){
    //   this.AllMotherboardFormFactorsCheckbox = false
    // }
    //full Height ExpansionSlot 
    if (this.selectedCasLatency.length == 0) {
      console.log(this.selectedCasLatency)
      this.removeCasLatencyFilters();
      this.selectedCasLatency = this.casLatency;
      console.log(this.selectedCasLatency)
      this.applyFilters();
      this.AllCasLatencyCheckbox = true;
      return;
    }
    // if (this.selectedFullHeightExpansionSlot.length ! = this.fullHeightExpansionSlot.length){
    //   this.AllFullHeightExpansionSlotCheckbox = false
    // }
    
    
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

    this.filteredRAM = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let mb of this.allRAM) {
      if (!this.selectedManufacturers.includes(mb.manufacturer)&& this.filteredRAM.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedSpeed.includes(mb.speed) && this.filteredRAM.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedType.includes(mb.type) && this.filteredRAM.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedSticks.includes(mb.sticks.toString()) && this.filteredRAM.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedPerStickGB.includes(mb.perStickGB.toString()) && this.filteredRAM.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedCasLatency.includes(mb.casLatency.toString()) && this.filteredRAM.indexOf(mb) < 0) {
        continue;
      }
     
      this.filteredRAM.push(mb);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredRAM = [];
    this.updateFilters();
  }




  removeSpeedFilters() {
    if (!this.AllSpeedCheckbox) {
      this.selectedSpeed = [];
      return;
    }

    this.selectedSpeed = this.speed;
    this.filteredRAM = [];
    this.updateFilters();
  }
   
  removeCasLatencyFilters() {
    if (!this.AllCasLatencyCheckbox) {
      this.selectedCasLatency = [];
      return;
    }

    this.selectedCasLatency = this.casLatency;
    this.filteredRAM = [];
    this.updateFilters();
  }
  removePerStickGBFilters() {
    if (!this.AllPerStickGBCheckbox) {
      this.selectedPerStickGB = [];
      return;
    }

    this.selectedPerStickGB = this.perStickGB;
    this.filteredRAM = [];
    this.updateFilters();
  }
  removeSticksFilters() {
    if (!this.AllSticksCheckbox) {
      this.selectedSticks = [];
      return;
    }

    this.selectedSticks = this.sticks;
    this.filteredRAM = [];
    this.updateFilters();
  }
  removeTypeFilters() {
    if (!this.AllTypeCheckbox) {
      this.selectedType = [];
      return;
    }

    this.selectedType = this.type;
    this.filteredRAM = [];
    this.updateFilters();
  }
}


