import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { CPU } from '../cpu';

@Component({
  selector: 'app-view-all-cpu',
  templateUrl: './view-all-cpu.component.html',
  styleUrls: ['./view-all-cpu.component.css']
})
export class ViewAllCPUComponent implements OnInit {

  productType: string;
  allCPU: CPU[];
  filteredCPU: CPU[];


  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  coreCount: string[] = []
  selectedCoreCount: string[] = []

  TDP: string[] = []
  selectedTDP: string[] = []

  socket: string[] = []
  selectedSocket: string[] = []

  hasIntergratedGraphics: string[] = []//["Yes","No"]
  selectedHasIntergratedGraphics: string[] = []

  includesCpuCooler: string[] = []//["Yes","No"]
  selectedIncludesCpuCooler: string[] = []

  AllManufacturersCheckbox: boolean = true;
  AllCoreCountCheckbox: boolean = true;
  AllTDPCheckbox: boolean = true;
  AllSocketCheckbox: boolean = true;
  AllHasIntergratedGraphicsCheckbox: boolean = true;
  AllIncludesCpuCoolerCheckbox: boolean = true;


  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productType = "CPU"

    this.productService.retrieveAllCpu().subscribe(
      response => {
        this.allCPU = response.products;
        this.filteredCPU = this.allCPU;

        for (let cpu of this.allCPU) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(cpu.manufacturer) < 0) {
            this.manufacturers.push(cpu.manufacturer)
          }


          if (this.coreCount.length == 0 || !this.coreCount.includes(cpu.coreCount.toString())) {
            this.coreCount.push(cpu.coreCount.toString())
          }


          if (this.TDP.length == 0 || !this.TDP.includes(cpu.TDP.toString())) {
            this.TDP.push(cpu.TDP.toString())
          }


          if (this.socket.length == 0 || !this.socket.includes(cpu.socket.toString())) {
            this.socket.push(cpu.socket.toString())
          }

          if (this.hasIntergratedGraphics.length == 0 || !this.hasIntergratedGraphics.includes(cpu.hasIntergratedGraphics.toString())) {
            this.hasIntergratedGraphics.push(cpu.hasIntergratedGraphics.toString())
          }

          if (this.includesCpuCooler.length == 0 || !this.includesCpuCooler.includes(cpu.includesCpuCooler.toString())) {
            this.includesCpuCooler.push(cpu.includesCpuCooler.toString())
          }

          this.manufacturers.sort();
          this.coreCount.sort();
          this.TDP.sort();
          this.socket.sort();
          this.hasIntergratedGraphics.sort();
          this.includesCpuCooler.sort();

          this.selectedManufacturers = this.manufacturers;
          this.selectedCoreCount = this.coreCount
          this.selectedTDP = this.TDP
          this.selectedSocket = this.socket
          this.selectedHasIntergratedGraphics = this.hasIntergratedGraphics
          this.selectedIncludesCpuCooler = this.includesCpuCooler
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
      //console.log(this.selectedManufacturers)
      this.removeManufacturerFilters();
      this.selectedManufacturers = this.manufacturers;
      this.applyFilters();
      this.AllManufacturersCheckbox = true
      //console.log(this.selectedManufacturers)
      return;
    }
    // if (this.selectedManufacturers.length ! = this.manufacturers.length){
    //   this.AllManufacturersCheckbox = false

    // }
    //corecount
    if (this.selectedCoreCount.length == 0) {
      this.removeCoreCountFilters();
      this.selectedCoreCount = this.coreCount;
      this.applyFilters();
      this.AllCoreCountCheckbox = true;
      return;
    }
    // if (this.selectedTypes.length ! = this.selectedTypes.length){
    //   this.AllTypesCheckbox = false
    // }
    //tdp
    if (this.selectedTDP.length == 0) {
      this.removeTDPFilters();
      this.selectedTDP = this.TDP;
      this.applyFilters();
      this.AllTDPCheckbox = true;
      return;
    }
    // if (this.selectedColours.length ! = this.colours.length){
    //   this.AllColourCheckbox = false
    // }
    //socket
    if (this.selectedSocket.length == 0) {
      this.removeSocketFilters();
      this.selectedSocket = this.socket;
      this.applyFilters();
      this.AllSocketCheckbox = true;
      return;
    }
    // if (this.selectedSidePanelViews.length ! = this.sidePanelViews.length){
    //   this.AllSidePanelViewCheckbox = false
    // }
    //hasintergratedgraphics
    if (this.selectedHasIntergratedGraphics.length == 0) {
      this.removeHasIntergratedGraphicsFilters();
      this.selectedHasIntergratedGraphics = this.hasIntergratedGraphics;
      this.applyFilters();
      this.AllHasIntergratedGraphicsCheckbox = true;
      return;
    }
    // if (this.selectedMotherboardFormFactors.length ! = this.motherboardFormFactors.length){
    //   this.AllMotherboardFormFactorsCheckbox = false
    // }
    //includescpucooler
    if (this.selectedIncludesCpuCooler.length == 0) {
      console.log(this.selectedIncludesCpuCooler)
      this.removeIncludesCpuCoolerFilters();
      this.selectedIncludesCpuCooler = this.includesCpuCooler;
      console.log(this.selectedIncludesCpuCooler)
      this.applyFilters();
      this.AllIncludesCpuCoolerCheckbox = true;
      return;
    }
    // if (this.selectedFullHeightExpansionSlot.length ! = this.fullHeightExpansionSlot.length){
    //   this.AllFullHeightExpansionSlotCheckbox = false
    // }

    this.filteredCPU = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let CPU of this.allCPU) {
      if (!this.selectedManufacturers.includes(CPU.manufacturer) && this.filteredCPU.indexOf(CPU) < 0) {
        continue;
      }
      if (!this.selectedCoreCount.includes(CPU.coreCount.toString()) && this.filteredCPU.indexOf(CPU) < 0) {
        continue;
      }
      if (!this.selectedTDP.includes(CPU.TDP.toString()) && this.filteredCPU.indexOf(CPU) < 0) {
        continue;
      }
      if (!this.selectedSocket.includes(CPU.socket.toString()) && this.filteredCPU.indexOf(CPU) < 0) {
        continue;
      }
      if (!this.selectedHasIntergratedGraphics.includes(CPU.hasIntergratedGraphics.toString()) && this.filteredCPU.indexOf(CPU) < 0) {
        continue;
      }
      if (!this.selectedIncludesCpuCooler.includes(CPU.includesCpuCooler.toString()) && this.filteredCPU.indexOf(CPU) < 0) {
        continue;
      }
      this.filteredCPU.push(CPU);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredCPU = [];
    this.updateFilters();
  }


  removeCoreCountFilters() {
    if (!this.AllCoreCountCheckbox) {
      this.selectedCoreCount = [];
      return;
    }

    this.selectedCoreCount = this.coreCount;
    this.filteredCPU = [];
    this.updateFilters();
  }

  removeTDPFilters() {
    if (!this.AllTDPCheckbox) {
      this.selectedTDP = [];
      return;
    }

    this.selectedTDP = this.TDP;
    this.filteredCPU = [];
    this.updateFilters();
  }
  removeSocketFilters() {
    if (!this.AllSocketCheckbox) {
      this.selectedSocket = [];
      return;
    }

    this.selectedSocket = this.socket;
    this.filteredCPU = [];
    this.updateFilters();
  }
  removeHasIntergratedGraphicsFilters() {
    if (!this.AllHasIntergratedGraphicsCheckbox) {
      this.selectedHasIntergratedGraphics = [];
      return;
    }

    this.selectedHasIntergratedGraphics = this.hasIntergratedGraphics;
    this.filteredCPU = [];
    this.updateFilters();
  }
  removeIncludesCpuCoolerFilters() {
    if (!this.AllIncludesCpuCoolerCheckbox) {
      this.selectedIncludesCpuCooler = [];
      return;
    }

    this.selectedIncludesCpuCooler = this.includesCpuCooler;
    this.filteredCPU = [];
    this.updateFilters();
  }


}
