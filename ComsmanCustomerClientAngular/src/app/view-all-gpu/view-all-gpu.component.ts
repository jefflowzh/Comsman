import { Component, OnInit } from '@angular/core';
import { GPU } from '../gpu';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-view-all-gpu',
  templateUrl: './view-all-gpu.component.html',
  styleUrls: ['./view-all-gpu.component.css']
})
export class ViewAllGPUComponent implements OnInit {
  productType: string;
  allGPU: GPU[];
  filteredGPU: GPU[];

  // private string manufacturer;
  // private string chipset; //(GTX 1660)
  // private string Interface;  //(PCI-Ex16) matches with motherboard
  // private Double length; //in mm
  // private Integer TDP;
  // private Integer expansionSlotWidth;
  // private string externalPower;
  // private Integer memory;
  // private string memoryType;


  // manufacturers: string[] = ['Manufacturer1', 'Manufacturer2', 'Manufacturer3', 'Manufacturer4'];
  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  chipset: string[] = []
  selectedChipset: string[] = []

  Interface: string[] = []
  selectedInterface: string[] = []

  length: string[] = []
  selectedLength: string[] = []

  TDP: string[] = []
  selectedTDP: string[] = []

  expansionSlotWidth: string[] = []
  selectedExpansionSlotWidth: string[] = []

  externalPower: string[] = []
  selectedExternalPower: string[] = []

  memory: string[] = []
  selectedMemory: string[] = []

  memoryType: string[] = []
  selectedMemoryType: string[] = []

  AllManufacturersCheckbox: boolean = true;
  AllChipsetCheckbox: boolean = true;
  AllInterfaceCheckbox: boolean = true;
  AllLengthCheckbox: boolean = true;
  AllTDPCheckbox: boolean = true;
  AllExpansionSlotWidthCheckbox: boolean = true;
  AllExternalPowerCheckbox: boolean = true;
  AllMemoryCheckbox: boolean = true;
  AllMemoryTypeCheckbox: boolean = true;


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Graphics Card"

    this.productService.retrieveAllGpu().subscribe(
      response => {
        this.allGPU = response.products;
        this.filteredGPU = this.allGPU;

        for (let gpu of this.allGPU) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(gpu.manufacturer) < 0) {
            this.manufacturers.push(gpu.manufacturer)
          }


          if (this.chipset.length == 0 || !this.chipset.includes(gpu.chipset)) {
            this.chipset.push(gpu.chipset)
          }



          if (this.Interface.length == 0 || !this.Interface.includes(gpu.Interface)) {
            this.Interface.push(gpu.Interface)
          }



          if (this.length.length == 0 || !this.length.includes(gpu.length.toString())) {
            this.length.push(gpu.length.toString())
          }



          if (this.TDP.length == 0 || !this.TDP.includes(gpu.TDP.toString())) {
            this.TDP.push(gpu.TDP.toString())
          }



          if (this.expansionSlotWidth.length == 0 || !this.expansionSlotWidth.includes(gpu.expansionSlotWidth.toString())) {
            this.expansionSlotWidth.push(gpu.expansionSlotWidth.toString())
          }


          if (this.externalPower.length == 0 || !this.externalPower.includes(gpu.externalPower.toString())) {
            this.externalPower.push(gpu.externalPower.toString())
          }


          if (this.memory.length == 0 || !this.memory.includes(gpu.memory.toString())) {
            this.memory.push(gpu.memory.toString())
          }


          if (this.memoryType.length == 0 || !this.memoryType.includes(gpu.memoryType)) {
            this.memoryType.push(gpu.memoryType)
          }

          this.manufacturers.sort();

          this.chipset.sort();

          this.Interface.sort();

          this.length.sort();

          this.TDP.sort();

          this.expansionSlotWidth.sort();

          this.externalPower.sort();

          this.memory.sort();

          this.memoryType.sort();


          this.selectedManufacturers = this.manufacturers;

          this.selectedChipset = this.chipset;

          this.selectedInterface = this.Interface;

          this.selectedLength = this.length;

          this.selectedTDP = this.TDP;

          this.selectedExpansionSlotWidth = this.expansionSlotWidth

          this.selectedExternalPower = this.externalPower

          this.selectedMemory = this.memory

          this.selectedMemoryType = this.memoryType
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
    if (this.selectedChipset.length == 0) {
      this.removeChipsetFilters();
      this.selectedChipset = this.chipset;
      this.applyFilters();
      this.AllChipsetCheckbox = true;
      return;
    }

    //colour
    if (this.selectedInterface.length == 0) {
      this.removeInterfaceFilters();
      this.selectedInterface = this.Interface;
      this.applyFilters();
      this.AllInterfaceCheckbox = true;
      return;
    }

    //side panel view
    if (this.selectedLength.length == 0) {
      this.removeLengthFilters();
      this.selectedLength = this.length;
      this.applyFilters();
      this.AllLengthCheckbox = true;
      return;
    }

    //motherboard form factor
    if (this.selectedTDP.length == 0) {
      this.removeTDPFilters();
      this.selectedTDP = this.TDP;
      this.applyFilters();
      this.AllTDPCheckbox = true;
      return;
    }

    //full Height ExpansionSlot 
    if (this.selectedExpansionSlotWidth.length == 0) {

      this.removeExpansionSlotFilters();
      this.selectedExpansionSlotWidth = this.expansionSlotWidth;

      this.applyFilters();
      this.AllExpansionSlotWidthCheckbox = true;
      return;
    }

    //top fan support
    if (this.selectedExternalPower.length == 0) {
      this.removeExternalPowerFilters();
      this.selectedExternalPower = this.externalPower;
      this.applyFilters();
      this.AllExternalPowerCheckbox = true;
      return;
    }

    //front fan support 
    if (this.selectedMemory.length == 0) {
      this.removeMemoryFilters();
      this.selectedMemory = this.memory;
      this.applyFilters();
      this.AllMemoryCheckbox = true;
      return;
    }

    //front fan support 
    if (this.selectedMemoryType.length == 0) {
      this.removeMemoryTypeFilters();
      this.selectedMemoryType = this.memoryType;
      this.applyFilters();
      this.AllMemoryTypeCheckbox = true;
      return;
    }

    this.filteredGPU = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let gpu of this.allGPU) {
      if (!this.selectedManufacturers.includes(gpu.manufacturer) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }
      if (!this.selectedChipset.includes(gpu.chipset) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }
      if (!this.selectedInterface.includes(gpu.Interface) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }
      if (!this.selectedLength.includes(gpu.length.toString()) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }
      if (!this.selectedTDP.includes(gpu.TDP.toString()) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }
      if (!this.selectedExpansionSlotWidth.includes(gpu.expansionSlotWidth.toString()) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }
      if (!this.selectedMemory.includes(gpu.memory.toString()) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }
      if (!this.selectedMemoryType.includes(gpu.memoryType.toString()) && this.filteredGPU.indexOf(gpu) < 0) {
        continue;
      }

      this.filteredGPU.push(gpu);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredGPU = [];
    this.updateFilters();
  }


  removeChipsetFilters() {
    if (!this.AllChipsetCheckbox) {
      this.selectedChipset = [];
      return;
    }

    this.selectedChipset = this.chipset;
    this.filteredGPU = [];
    this.updateFilters();
  }
  removeMemoryTypeFilters() {
    if (!this.AllMemoryTypeCheckbox) {
      this.selectedMemoryType = [];
      return;
    }

    this.selectedMemoryType = this.memoryType;
    this.filteredGPU = [];
    this.updateFilters();
  }

  removeMemoryFilters() {
    if (!this.AllMemoryCheckbox) {
      this.selectedMemory = [];
      return;
    }

    this.selectedMemory = this.memory;
    this.filteredGPU = [];
    this.updateFilters();
  }
  removeExternalPowerFilters() {
    if (!this.AllExternalPowerCheckbox) {
      this.selectedExternalPower = [];
      return;
    }

    this.selectedExternalPower = this.externalPower;
    this.filteredGPU = [];
    this.updateFilters();
  }
  removeExpansionSlotFilters() {
    if (!this.AllExpansionSlotWidthCheckbox) {
      this.selectedExpansionSlotWidth = [];
      return;
    }

    this.selectedExpansionSlotWidth = this.expansionSlotWidth;
    this.filteredGPU = [];
    this.updateFilters();
  }
  removeTDPFilters() {
    if (!this.AllTDPCheckbox) {
      this.selectedTDP = [];
      return;
    }

    this.selectedTDP = this.TDP;
    this.filteredGPU = [];
    this.updateFilters();
  }
  removeLengthFilters() {
    if (!this.AllLengthCheckbox) {
      this.selectedLength = [];
      return;
    }

    this.selectedLength = this.length;
    this.filteredGPU = [];
    this.updateFilters();
  }
  removeInterfaceFilters() {
    if (!this.AllInterfaceCheckbox) {
      this.selectedInterface = [];
      return;
    }

    this.selectedInterface = this.Interface;
    this.filteredGPU = [];
    this.updateFilters();
  }
}

