import { Component, OnInit } from '@angular/core';
import { MotherBoard } from '../motherboard';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-view-all-motherboard',
  templateUrl: './view-all-motherboard.component.html',
  styleUrls: ['./view-all-motherboard.component.css']
})
export class ViewAllMotherboardComponent implements OnInit {
  productType: string;
  allMotherboard: MotherBoard[];
  filteredMotherboard: MotherBoard[];

  // private string manufacturer;
  // private string formFactor;
  // private string socket;
  // private string chipset;
  // private Integer memorySlot;
  // private string colour;
  // private boolean SLICrossFire;
  // private Integer PCIEx16;
  // private Integer m2Slot;
  // private boolean wiFi;
  // private string[] suportedMemorySpeed;

  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  formFactor: string[] = []
  selectedFormFactor: string[] = []

  socket: string[] = []
  selectedSocket: string[] = []

  chipset: string[] = []
  selectedChipset: string[] = []

  memorySlot: string[] = []
  selectedMemorySlot: string[] = []

  colour: string[] = []
  selectedColour: string[] = []

  SLICrossFire: string[] = []
  selectedSLICrossFire: string[] = []

  PCIEx16: string[] = []
  selectedPCIEx16: string[] = []

  m2Slot: string[] = []
  selectedM2Slot: string[] = []

  wiFi: string[] = []
  selectedWiFi: string[] = []

  supportedMemorySpeed: string[] = []
  selectedSupportedMemorySpeed: string[] = []


  AllManufacturersCheckbox: boolean = true;
  AllFormFactorCheckbox: boolean = true;
  AllSocketCheckbox: boolean = true;
  AllChipsetCheckbox: boolean = true;
  AllMemorySlotCheckbox: boolean = true;
  AllColourCheckbox: boolean = true;
  AllSLICrossFireCheckbox: boolean = true;
  AllPCIEx16Checkbox: boolean = true;
  AllM2SlotCheckbox: boolean = true;
  AllWiFiCheckbox: boolean = true;
  AllSupportedMemorySpeedCheckbox: boolean = true;


  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productType = "Motherboard"

    this.productService.retrieveAllMotherBoard().subscribe(
      response => {
        this.allMotherboard = response.products;
        this.filteredMotherboard = this.allMotherboard;

        for (let mb of this.allMotherboard) {

          if (this.manufacturers.length == 0 || this.manufacturers.indexOf(mb.manufacturer) < 0) {
            this.manufacturers.push(mb.manufacturer)
          }


          if (this.formFactor.length == 0 || !this.formFactor.includes(mb.formFactor)) {
            this.formFactor.push(mb.formFactor)
          }



          if (this.socket.length == 0 || !this.socket.includes(mb.socket)) {
            this.socket.push(mb.socket)
          }



          if (this.chipset.length == 0 || !this.chipset.includes(mb.chipset.toString())) {
            this.chipset.push(mb.chipset.toString())
          }



          if (this.memorySlot.length == 0 || !this.memorySlot.includes(mb.memorySlot.toString())) {
            this.memorySlot.push(mb.memorySlot.toString())
          }



          if (this.colour.length == 0 || !this.colour.includes(mb.colour.toString())) {
            this.colour.push(mb.colour.toString())
          }


          if (this.SLICrossFire.length == 0 || !this.SLICrossFire.includes(mb.SLICrossFire.toString())) {
            this.SLICrossFire.push(mb.SLICrossFire.toString())
          }


          if (this.PCIEx16.length == 0 || !this.PCIEx16.includes(mb.PCIEx16.toString())) {
            this.PCIEx16.push(mb.PCIEx16.toString())
          }


          if (this.m2Slot.length == 0 || !this.m2Slot.includes(mb.m2Slot.toString())) {
            this.m2Slot.push(mb.m2Slot.toString())
          }

          if (this.wiFi.length == 0 || !this.wiFi.includes(mb.wiFi.toString())) {
            this.wiFi.push(mb.wiFi.toString())
          }

          for (let m of mb.suportedMemorySpeed) {
            if (this.supportedMemorySpeed.length == 0 || !this.supportedMemorySpeed.includes(m)) {
              this.supportedMemorySpeed.push(m)
            }
          }

          this.manufacturers.sort();

          this.formFactor.sort();

          this.socket.sort();

          this.chipset.sort();

          this.memorySlot.sort();

          this.colour.sort();

          this.SLICrossFire.sort()

          this.PCIEx16.sort()

          this.m2Slot.sort()

          this.wiFi.sort()

          this.supportedMemorySpeed.sort();


          this.selectedManufacturers = this.manufacturers;

          this.selectedFormFactor = this.formFactor;

          this.selectedSocket = this.socket;

          this.selectedChipset = this.chipset;

          this.selectedMemorySlot = this.memorySlot;

          this.selectedColour = this.colour

          this.selectedSLICrossFire = this.SLICrossFire

          this.selectedPCIEx16 = this.PCIEx16

          this.selectedM2Slot = this.m2Slot

          this.selectedWiFi = this.wiFi

          this.selectedSupportedMemorySpeed = this.supportedMemorySpeed
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
    if (this.selectedFormFactor.length == 0) {
      this.removeFormFactorFilters();
      this.selectedFormFactor = this.formFactor;
      this.applyFilters();
      this.AllFormFactorCheckbox = true;
      return;
    }
    // if (this.selectedTypes.length ! = this.selectedTypes.length){
    //   this.AllTypesCheckbox = false
    // }
    //colour
    if (this.selectedSocket.length == 0) {
      this.removeSocketFilters();
      this.selectedSocket = this.socket;
      this.applyFilters();
      this.AllSocketCheckbox = true;
      return;
    }
    // if (this.selectedColours.length ! = this.colours.length){
    //   this.AllColourCheckbox = false
    // }
    //side panel view
    if (this.selectedChipset.length == 0) {
      this.removeChipsetFilters();
      this.selectedChipset = this.chipset;
      this.applyFilters();
      this.AllChipsetCheckbox = true;
      return;
    }
    // if (this.selectedSidePanelViews.length ! = this.sidePanelViews.length){
    //   this.AllSidePanelViewCheckbox = false
    // }
    //motherboard form factor
    if (this.selectedMemorySlot.length == 0) {
      this.removeMemorySlotFilters();
      this.selectedMemorySlot = this.memorySlot;
      this.applyFilters();
      this.AllMemorySlotCheckbox = true;
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
    if (this.selectedSLICrossFire.length == 0) {
      this.removeSLICrossFireFilters();
      this.selectedSLICrossFire = this.SLICrossFire;
      this.applyFilters();
      this.AllSLICrossFireCheckbox = true;
      return;
    }
    // if (this.selectedTopFanSupport.length ! = this.topFanSupport.length){
    //   this.AllTopFanSupportCheckbox = false
    // }
    //front fan support 
    if (this.selectedPCIEx16.length == 0) {
      this.removePCIEx16Filters();
      this.selectedPCIEx16 = this.PCIEx16;
      this.applyFilters();
      this.AllPCIEx16Checkbox = true;
      return;
    }
    // if (this.selectedFrontFanSupport.length ! = this.frontFanSupport.length){
    //   this.AllFrontFanSupportCheckbox = false
    // }
    //front fan support 
    if (this.selectedM2Slot.length == 0) {
      this.removeM2SlotFilters();
      this.selectedM2Slot = this.m2Slot;
      this.applyFilters();
      this.AllM2SlotCheckbox = true;
      return;
    }
    // if (this.selectedFrontFanSupport.length ! = this.frontFanSupport.length){
    //   this.AllFrontFanSupportCheckbox = false
    // }
    if (this.selectedWiFi.length == 0) {
      this.removeWiFiFilters();
      this.selectedWiFi = this.wiFi;
      this.applyFilters();
      this.AllWiFiCheckbox = true;
      return;
    }
    if (this.selectedSupportedMemorySpeed.length == 0) {
      this.removeSupportedMemorySpeedFilters();
      this.selectedSupportedMemorySpeed = this.supportedMemorySpeed;
      this.applyFilters();
      this.AllSupportedMemorySpeedCheckbox = true;
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

    this.filteredMotherboard = [];
    this.updateFilters();
  }

  updateFilters() {
    for (let mb of this.allMotherboard) {
      if (!this.selectedManufacturers.includes(mb.manufacturer) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedFormFactor.includes(mb.formFactor) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedSocket.includes(mb.socket) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedChipset.includes(mb.chipset.toString()) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedMemorySlot.includes(mb.memorySlot.toString()) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedColour.includes(mb.colour.toString()) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedPCIEx16.includes(mb.PCIEx16.toString()) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedM2Slot.includes(mb.m2Slot.toString()) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!this.selectedWiFi.includes(mb.wiFi.toString()) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }
      if (!mb.suportedMemorySpeed.some(r => this.selectedSupportedMemorySpeed.includes(r)) && this.filteredMotherboard.indexOf(mb) < 0) {
        continue;
      }

      this.filteredMotherboard.push(mb);
    }
  }

  removeManufacturerFilters() {
    if (!this.AllManufacturersCheckbox) {
      this.selectedManufacturers = [];
      return;
    }

    this.selectedManufacturers = this.manufacturers;
    this.filteredMotherboard = [];
    this.updateFilters();
  }

  removeWiFiFilters() {
    if (!this.AllWiFiCheckbox) {
      this.selectedWiFi = [];
      return;
    }

    this.selectedWiFi = this.wiFi;
    this.filteredMotherboard = [];
    this.updateFilters();
  }

  removeSupportedMemorySpeedFilters() {
    if (!this.AllSupportedMemorySpeedCheckbox) {
      this.selectedSupportedMemorySpeed = [];
      return;
    }

    this.selectedSupportedMemorySpeed = this.supportedMemorySpeed;
    this.filteredMotherboard = [];
    this.updateFilters();
  }


  removeFormFactorFilters() {
    if (!this.AllFormFactorCheckbox) {
      this.selectedFormFactor = [];
      return;
    }

    this.selectedFormFactor = this.formFactor;
    this.filteredMotherboard = [];
    this.updateFilters();
  }
  removeM2SlotFilters() {
    if (!this.AllM2SlotCheckbox) {
      this.selectedM2Slot = [];
      return;
    }

    this.selectedM2Slot = this.m2Slot;
    this.filteredMotherboard = [];
    this.updateFilters();
  }

  removePCIEx16Filters() {
    if (!this.AllPCIEx16Checkbox) {
      this.selectedPCIEx16 = [];
      return;
    }

    this.selectedPCIEx16 = this.PCIEx16;
    this.filteredMotherboard = [];
    this.updateFilters();
  }
  removeSLICrossFireFilters() {
    if (!this.AllSLICrossFireCheckbox) {
      this.selectedSLICrossFire = [];
      return;
    }

    this.selectedSLICrossFire = this.SLICrossFire;
    this.filteredMotherboard = [];
    this.updateFilters();
  }
  removeColourFilters() {
    if (!this.AllColourCheckbox) {
      this.selectedColour = [];
      return;
    }

    this.selectedColour = this.colour;
    this.filteredMotherboard = [];
    this.updateFilters();
  }
  removeMemorySlotFilters() {
    if (!this.AllMemorySlotCheckbox) {
      this.selectedMemorySlot = [];
      return;
    }

    this.selectedMemorySlot = this.memorySlot;
    this.filteredMotherboard = [];
    this.updateFilters();
  }
  removeChipsetFilters() {
    if (!this.AllChipsetCheckbox) {
      this.selectedChipset = [];
      return;
    }

    this.selectedChipset = this.chipset;
    this.filteredMotherboard = [];
    this.updateFilters();
  }
  removeSocketFilters() {
    if (!this.AllSocketCheckbox) {
      this.selectedSocket = [];
      return;
    }

    this.selectedSocket = this.socket;
    this.filteredMotherboard = [];
    this.updateFilters();
  }
}

