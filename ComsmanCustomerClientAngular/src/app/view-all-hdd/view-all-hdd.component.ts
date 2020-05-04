import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { HDD } from '../hdd';

@Component({
  selector: 'app-view-all-hdd',
  templateUrl: './view-all-hdd.component.html',
  styleUrls: ['./view-all-hdd.component.css']
})
export class ViewAllHddComponent implements OnInit {

  productType: string;
  allHDD: HDD[];
  filteredHDD: HDD[];

  // private String Manufacturer;
  // private String colour;
  // private Integer noiseLevel; // (in dB)
  // private Double Height; // (mm) //this one not really useful if its water cooled
  // private String[] CPUChipCompatibility;

  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  type: string[] = []
  selectedType: string[] = []

  capacity: string[] = []
  selectedCapacity: string[] = []

  formFactor: string[] = []
  selectedFormFactor: string[] = []

  Interface: string[] = []
  selectedInterface: string[] = []


  AllManufacturersCheckbox: boolean = true;
  AllTypeCheckbox: boolean = true;
  AllCapacityCheckbox: boolean = true;
  AllFormFactorCheckbox: boolean = true;
  AllInterfaceCheckbox: boolean = true;


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Hard Drive"

    this.productService.retrieveAllHDD().subscribe(
      response => {
        this.allHDD = response.products;
        this.filteredHDD = this.allHDD;

        for (let hdd of this.allHDD) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(hdd.manufacturer) < 0) {
            this.manufacturers.push(hdd.manufacturer);
          }


          if (this.capacity.length == 0 || !this.capacity.includes(hdd.capacity.toString())) {
            this.capacity.push(hdd.capacity.toString());
          }



          if (this.formFactor.length == 0 || !this.formFactor.includes(hdd.formFactor.toString())) {
            this.formFactor.push(hdd.formFactor.toString());
          }



          if (this.type.length == 0 || !this.type.includes(hdd.type.toString())) {
            this.type.push(hdd.type.toString());
          }





          if (this.Interface.length == 0 || !this.Interface.includes(hdd.interfaceForm)) {
            this.Interface.push(hdd.interfaceForm);
          }


          this.manufacturers.sort();
          this.capacity.sort();
          this.formFactor.sort();
          this.type.sort();
          this.Interface.sort();


          this.selectedManufacturers = this.manufacturers;
          this.selectedCapacity = this.capacity;
          this.selectedFormFactor = this.formFactor;
          this.selectedType = this.type
          this.selectedInterface = this.Interface
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
    if (this.selectedCapacity.length == 0) {
      this.removeCapacityFilters();
      this.selectedCapacity = this.capacity;
      this.applyFilters();
      this.AllTypeCheckbox = true;
      return;
    }

    //motherboard form factor
    if (this.selectedFormFactor.length == 0) {
      this.removeFormFactorFilters();
      this.selectedFormFactor = this.formFactor;
      this.applyFilters();
      this.AllCapacityCheckbox = true;
      return;
    }

    //full Height ExpansionSlot 
    if (this.selectedType.length == 0) {

      this.removeTypeFilters();
      this.selectedType = this.type;

      this.applyFilters();
      this.AllFormFactorCheckbox = true;
      return;
    }

    //top fan support
    if (this.selectedInterface.length == 0) {
      this.removeInterfaceFilters();
      this.selectedInterface = this.Interface;
      this.applyFilters();
      this.AllInterfaceCheckbox = true;
      return;
    }

    this.filteredHDD = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let mb of this.allHDD) {
      if (!this.selectedManufacturers.includes(mb.manufacturer) && this.filteredHDD.indexOf(mb) < 0) {
        continue;
      }

      if (!this.selectedCapacity.includes(mb.capacity.toString()) && this.filteredHDD.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedFormFactor.includes(mb.formFactor.toString()) && this.filteredHDD.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedType.includes(mb.type.toString()) && this.filteredHDD.indexOf(mb) < 0) {
        continue;
      }
      if (this.selectedInterface.includes(mb.interfaceForm) && this.filteredHDD.indexOf(mb) < 0) {
        continue;
      }

      this.filteredHDD.push(mb);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredHDD = [];
    this.updateFilters();
  }



  removeInterfaceFilters() {
    if (!this.AllInterfaceCheckbox) {
      this.selectedInterface = [];
      return;
    }

    this.selectedInterface = this.Interface;
    this.filteredHDD = [];
    this.updateFilters();
  }




  removeTypeFilters() {
    if (!this.AllFormFactorCheckbox) {
      this.selectedType = [];
      return;
    }

    this.selectedType = this.type;
    this.filteredHDD = [];
    this.updateFilters();
  }
  removeFormFactorFilters() {
    if (!this.AllCapacityCheckbox) {
      this.selectedFormFactor = [];
      return;
    }

    this.selectedFormFactor = this.formFactor;
    this.filteredHDD = [];
    this.updateFilters();
  }
  removeCapacityFilters() {
    if (!this.AllTypeCheckbox) {
      this.selectedCapacity = [];
      return;
    }

    this.selectedCapacity = this.capacity;
    this.filteredHDD = [];
    this.updateFilters();
  }

}


