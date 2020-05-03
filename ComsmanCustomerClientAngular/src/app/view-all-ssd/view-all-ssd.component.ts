import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { SSD } from '../ssd';

@Component({
  selector: 'app-view-all-ssd',
  templateUrl: './view-all-ssd.component.html',
  styleUrls: ['./view-all-ssd.component.css']
})
export class ViewAllSsdComponent implements OnInit {

  productType: string;
  allSSD: SSD[];
  filteredSSD: SSD[];

  // private String manufacturer;
  // private String speed;
  // private String type;
  // private Integer sticks; //(2 x 8GB)
  // private Integer perStickGB;
  // private Integer casLatency;

  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  type: string[] = []
  selectedType: string[] = []

  capacity: string[] = []
  selectedCapacity: string[] = []

  formFactor: string[] = []
  selectedFormFactor: string[] = []

  interfaceForm: string[] = []
  selectedInterfaceForm: string[] = []

  NVME: string[] = []
  selectedNVME: string[] = []


  AllManufacturersCheckbox: boolean = true;
  AllTypeCheckbox: boolean = true;
  AllCapacityCheckbox: boolean = true;
  AllFormFactorCheckbox: boolean = true;
  AllInterfaceFormCheckbox: boolean = true;
  AllNVMECheckbox: boolean = true;





  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "SSD"

    this.productService.retrieveAllSSD().subscribe(
      response => {
        this.allSSD = response.products;
        this.filteredSSD = this.allSSD;

        for (let ram of this.allSSD) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(ram.manufacturer) < 0) {
            this.manufacturers.push(ram.manufacturer)
          }


          if (this.type.length == 0 || !this.type.includes(ram.type)) {
            this.type.push(ram.type)
          }



          if (this.capacity.length == 0 || !this.capacity.includes(ram.capacity.toString())) {
            this.capacity.push(ram.capacity.toString())
          }



          if (this.formFactor.length == 0 || !this.formFactor.includes(ram.formFactor.toString())) {
            this.formFactor.push(ram.formFactor.toString())
          }



          if (this.interfaceForm.length == 0 || !this.interfaceForm.includes(ram.interfaceForm.toString())) {
            this.interfaceForm.push(ram.interfaceForm.toString())
          }



          if (this.NVME.length == 0 || !this.NVME.includes(ram.NVME.toString())) {
            this.NVME.push(ram.NVME.toString())
          }

          this.manufacturers.sort();
          this.type.sort();
          this.capacity.sort();
          this.formFactor.sort();
          this.interfaceForm.sort();
          this.NVME.sort();



          this.selectedManufacturers = this.manufacturers;

          this.selectedType = this.type;

          this.selectedCapacity = this.capacity;

          this.selectedFormFactor = this.formFactor;

          this.selectedInterfaceForm = this.interfaceForm;

          this.selectedNVME = this.NVME


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
    if (this.selectedType.length == 0) {
      this.removeCapacityFilters();
      this.selectedType = this.type;
      this.applyFilters();
      this.AllTypeCheckbox = true;
      return;
    }
    // if (this.selectedTypes.length ! = this.selectedTypes.length){
    //   this.AllTypesCheckbox = false
    // }
    //colour
    if (this.selectedCapacity.length == 0) {
      this.removeCapacityFilters();
      this.selectedCapacity = this.capacity;
      this.applyFilters();
      this.AllCapacityCheckbox = true;
      return;
    }
    // if (this.selectedColours.length ! = this.colours.length){
    //   this.AllColourCheckbox = false
    // }
    //side panel view
    if (this.selectedFormFactor.length == 0) {
      this.removeFormFactorFilters();
      this.selectedFormFactor = this.formFactor;
      this.applyFilters();
      this.AllFormFactorCheckbox = true;
      return;
    }
    // if (this.selectedSidePanelViews.length ! = this.sidePanelViews.length){
    //   this.AllSidePanelViewCheckbox = false
    // }
    //motherboard form factor
    if (this.selectedInterfaceForm.length == 0) {
      this.removeInterfaceFormFilters();
      this.selectedInterfaceForm = this.interfaceForm;
      this.applyFilters();
      this.AllInterfaceFormCheckbox = true;
      return;
    }
    // if (this.selectedMotherboardFormFactors.length ! = this.motherboardFormFactors.length){
    //   this.AllMotherboardFormFactorsCheckbox = false
    // }
    //full Height ExpansionSlot 
    if (this.selectedNVME.length == 0) {
      console.log(this.selectedNVME)
      this.removeNVMEFilters();
      this.selectedNVME = this.NVME;
      console.log(this.selectedNVME)
      this.applyFilters();
      this.AllNVMECheckbox = true;
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

    this.filteredSSD = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let ssd of this.allSSD) {
      if (!this.selectedManufacturers.includes(ssd.manufacturer) && this.filteredSSD.indexOf(ssd) < 0) {
        continue;
      }
      if (!this.selectedType.includes(ssd.type) && this.filteredSSD.indexOf(ssd) < 0) {
        continue;
      }
      if (!this.selectedCapacity.includes(ssd.capacity.toString()) && this.filteredSSD.indexOf(ssd) < 0) {
        continue;
      }
      if (!this.selectedFormFactor.includes(ssd.formFactor.toString()) && this.filteredSSD.indexOf(ssd) < 0) {
        continue;
      }
      if (!this.selectedInterfaceForm.includes(ssd.interfaceForm.toString()) && this.filteredSSD.indexOf(ssd) < 0) {
        continue;
      }
      if (!this.selectedNVME.includes(ssd.NVME.toString()) && this.filteredSSD.indexOf(ssd) < 0) {
        continue;
      }

      this.filteredSSD.push(ssd);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredSSD = [];
    this.updateFilters();
  }




  removeSpeedFilters() {
    if (!this.AllTypeCheckbox) {
      this.selectedType = [];
      return;
    }

    this.selectedType = this.type;
    this.filteredSSD = [];
    this.updateFilters();
  }

  removeNVMEFilters() {
    if (!this.AllNVMECheckbox) {
      this.selectedNVME = [];
      return;
    }

    this.selectedNVME = this.NVME;
    this.filteredSSD = [];
    this.updateFilters();
  }
  removeInterfaceFormFilters() {
    if (!this.AllInterfaceFormCheckbox) {
      this.selectedInterfaceForm = [];
      return;
    }

    this.selectedInterfaceForm = this.interfaceForm;
    this.filteredSSD = [];
    this.updateFilters();
  }
  removeFormFactorFilters() {
    if (!this.AllFormFactorCheckbox) {
      this.selectedFormFactor = [];
      return;
    }

    this.selectedFormFactor = this.formFactor;
    this.filteredSSD = [];
    this.updateFilters();
  }
  removeCapacityFilters() {
    if (!this.AllCapacityCheckbox) {
      this.selectedCapacity = [];
      return;
    }

    this.selectedCapacity = this.capacity;
    this.filteredSSD = [];
    this.updateFilters();
  }
}



