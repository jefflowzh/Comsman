import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { PowerSupply } from '../powersupply';


@Component({
  selector: 'app-view-all-power-supply',
  templateUrl: './view-all-power-supply.component.html',
  styleUrls: ['./view-all-power-supply.component.css']
})
export class ViewAllPowerSupplyComponent implements OnInit {
  productType: string;
  allPowerSupply: PowerSupply[];
  filteredPowerSupply: PowerSupply[];

  // private String manufacturer;
  // private String formFactor;// (micro atx)
  // private String efficiency;
  // private Integer wattage;
  // private String modularity; //(wires)
  // private Integer SATAConnectors;
  // private Integer PCIe6plus2;

  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  formFactor: string[] = []
  selectedFormFactor: string[] = []

  efficiency: string[] = []
  selectedEfficiency: string[] = []

  wattage: string[] = []
  selectedWattage: string[] = []

  modularity: string[] = []
  selectedModularity: string[] = []

  SATAConnectors: string[] = []
  selectedSATAConnectors: string[] = []

  PCIe6plus2: string[] = []
  selectedPCIe6plus2: string[] = []




  AllManufacturersCheckbox: boolean = true;
  AllFormFactorCheckbox: boolean = true;
  AllEfficiencyCheckbox: boolean = true;
  AllWattageCheckbox: boolean = true;
  AllModularityCheckbox: boolean = true;
  AllSATAConnectorsCheckbox: boolean = true;
  AllPCIe6plus2Checkbox: boolean = true;




  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Power Supply Unit"

    this.productService.retrieveAllPowerSupply().subscribe(
      response => {
        this.allPowerSupply = response.products;
        this.filteredPowerSupply = this.allPowerSupply;

        for (let psu of this.allPowerSupply) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(psu.manufacturer) < 0) {
            this.manufacturers.push(psu.manufacturer)
          }


          if (this.formFactor.length == 0 || !this.formFactor.includes(psu.formFactor)) {
            this.formFactor.push(psu.formFactor)
          }



          if (this.efficiency.length == 0 || !this.efficiency.includes(psu.efficiency)) {
            this.efficiency.push(psu.efficiency)
          }



          if (this.wattage.length == 0 || !this.wattage.includes(psu.wattage.toString())) {
            this.wattage.push(psu.wattage.toString())
          }



          if (this.modularity.length == 0 || !this.modularity.includes(psu.modularity.toString())) {
            this.modularity.push(psu.modularity.toString())
          }



          if (this.SATAConnectors.length == 0 || !this.SATAConnectors.includes(psu.SATAConnectors.toString())) {
            this.SATAConnectors.push(psu.SATAConnectors.toString())
          }


          if (this.PCIe6plus2.length == 0 || !this.PCIe6plus2.includes(psu.PCIe6plus2.toString())) {
            this.PCIe6plus2.push(psu.PCIe6plus2.toString())
          }

          this.manufacturers.sort();

          this.formFactor.sort();

          this.efficiency.sort();

          this.wattage.sort();

          this.modularity.sort();

          this.SATAConnectors.sort();

          this.PCIe6plus2.sort();


          this.selectedManufacturers = this.manufacturers;

          this.selectedFormFactor = this.formFactor;

          this.selectedEfficiency = this.efficiency;

          this.selectedWattage = this.wattage;

          this.selectedModularity = this.modularity;

          this.selectedSATAConnectors = this.SATAConnectors

          this.selectedPCIe6plus2 = this.PCIe6plus2
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

    //types
    if (this.selectedFormFactor.length == 0) {
      this.removeFormFactorFilters();
      this.selectedFormFactor = this.formFactor;
      this.applyFilters();
      this.AllFormFactorCheckbox = true;
      return;
    }

    //colour
    if (this.selectedEfficiency.length == 0) {
      this.removeEfficiencyFilters();
      this.selectedEfficiency = this.efficiency;
      this.applyFilters();
      this.AllEfficiencyCheckbox = true;
      return;
    }

    //side panel view
    if (this.selectedWattage.length == 0) {
      this.removeWattageFilters();
      this.selectedWattage = this.wattage;
      this.applyFilters();
      this.AllWattageCheckbox = true;
      return;
    }

    //motherboard form factor
    if (this.selectedModularity.length == 0) {
      this.removeModularityFilters();
      this.selectedModularity = this.modularity;
      this.applyFilters();
      this.AllModularityCheckbox = true;
      return;
    }

    //full Height ExpansionSlot 
    if (this.selectedSATAConnectors.length == 0) {

      this.removeSATAConnectorsFilters();
      this.selectedSATAConnectors = this.SATAConnectors;

      this.applyFilters();
      this.AllSATAConnectorsCheckbox = true;
      return;
    }

    //top fan support
    if (this.selectedPCIe6plus2.length == 0) {
      this.removePCIe6plus2Filters();
      this.selectedPCIe6plus2 = this.PCIe6plus2;
      this.applyFilters();
      this.AllPCIe6plus2Checkbox = true;
      return;
    }

    this.filteredPowerSupply = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let mb of this.allPowerSupply) {
      if (!this.selectedManufacturers.includes(mb.manufacturer) && this.filteredPowerSupply.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedFormFactor.includes(mb.formFactor) && this.filteredPowerSupply.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedEfficiency.includes(mb.efficiency) && this.filteredPowerSupply.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedWattage.includes(mb.wattage.toString()) && this.filteredPowerSupply.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedModularity.includes(mb.modularity.toString()) && this.filteredPowerSupply.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedSATAConnectors.includes(mb.SATAConnectors.toString()) && this.filteredPowerSupply.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedPCIe6plus2.includes(mb.PCIe6plus2.toString()) && this.filteredPowerSupply.indexOf(mb) < 0) {
        continue;
      }
      this.filteredPowerSupply.push(mb);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredPowerSupply = [];
    this.updateFilters();
  }




  removeFormFactorFilters() {
    if (!this.AllFormFactorCheckbox) {
      this.selectedFormFactor = [];
      return;
    }

    this.selectedFormFactor = this.formFactor;
    this.filteredPowerSupply = [];
    this.updateFilters();
  }

  removePCIe6plus2Filters() {
    if (!this.AllPCIe6plus2Checkbox) {
      this.selectedPCIe6plus2 = [];
      return;
    }

    this.selectedPCIe6plus2 = this.PCIe6plus2;
    this.filteredPowerSupply = [];
    this.updateFilters();
  }
  removeSATAConnectorsFilters() {
    if (!this.AllSATAConnectorsCheckbox) {
      this.selectedSATAConnectors = [];
      return;
    }

    this.selectedSATAConnectors = this.SATAConnectors;
    this.filteredPowerSupply = [];
    this.updateFilters();
  }
  removeModularityFilters() {
    if (!this.AllModularityCheckbox) {
      this.selectedModularity = [];
      return;
    }

    this.selectedModularity = this.modularity;
    this.filteredPowerSupply = [];
    this.updateFilters();
  }
  removeWattageFilters() {
    if (!this.AllWattageCheckbox) {
      this.selectedWattage = [];
      return;
    }

    this.selectedWattage = this.wattage;
    this.filteredPowerSupply = [];
    this.updateFilters();
  }
  removeEfficiencyFilters() {
    if (!this.AllEfficiencyCheckbox) {
      this.selectedEfficiency = [];
      return;
    }

    this.selectedEfficiency = this.efficiency;
    this.filteredPowerSupply = [];
    this.updateFilters();
  }
}

