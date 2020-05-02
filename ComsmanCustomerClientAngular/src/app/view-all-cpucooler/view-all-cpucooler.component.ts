import { Component, OnInit } from '@angular/core';

import { CPUAirCooler } from '../cpuair-cooler';
import { CPUWaterCooler } from '../cpuwater-cooler';
import { ComputerPart } from '../computer-part';

import { ProductService } from '../product.service';

@Component({
  selector: 'app-view-all-cpucooler',
  templateUrl: './view-all-cpucooler.component.html',
  styleUrls: ['./view-all-cpucooler.component.css']
})
export class ViewAllCPUCoolerComponent implements OnInit {

  productType: string;
  allCPUCoolers: ComputerPart[];
  filteredCPUCoolers: ComputerPart[];

  manufacturers: string[] = []
  selectedManufacturers: string[] = []

  colours: string[] = []
  selectedColours: string[] = []

  compatibleCPUChips: string[] = []
  selectedCompatibleCPUChips: string[] = []

  noiseLevels: string[] = []
  selectednoiseLevels: string[] = []

  // For air cooler only
  heights: string[] = []
  selectedHeights: string[] = []

  // For water cooler only
  radiatorSizes: string[] = []
  selectedRadiatorSizes: string[] = []

  AllManufacturersCheckbox: boolean = true;
  AllColoursCheckbox: boolean = true;
  AllCompatibleCPUChipsCheckbox: boolean = true;
  AllNoiseLevelsCheckbox: boolean = true;
  AllHeightsCheckbox: boolean = true;
  AllRadiatorSizesCheckbox: boolean = true;


  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productType = "CPU Cooler"

    this.productService.retrieveAllCPUCoolers().subscribe(
      response => {
        this.allCPUCoolers = response.products;
        this.filteredCPUCoolers = this.allCPUCoolers;

        // for (let cpuCooler of this.allCPUCoolers) {

        //   if (this.manufacturers.length == 0 || this.manufacturers.indexOf(cpuCooler.manufacturer) < 0) {
        //     this.manufacturers.push(cpu.manufacturer)
        //   }

        // if (this.coreCount.length == 0 || !this.coreCount.includes(cpu.coreCount.toString())) {
        //   this.coreCount.push(cpu.coreCount.toString())
        // }


        //   if (this.TDP.length == 0 || !this.TDP.includes(cpu.TDP.toString())) {
        //     this.TDP.push(cpu.TDP.toString())
        //   }


        //   if (this.socket.length == 0 || !this.socket.includes(cpu.socket.toString())) {
        //     this.socket.push(cpu.socket.toString())
        //   }

        //   if (this.hasIntergratedGraphics.length == 0 || !this.hasIntergratedGraphics.includes(cpu.hasIntergratedGraphics.toString())) {
        //     this.hasIntergratedGraphics.push(cpu.hasIntergratedGraphics.toString())
        //   }

        //   if (this.includesCpuCooler.length == 0 || !this.includesCpuCooler.includes(cpu.includesCpuCooler.toString())) {
        //     this.includesCpuCooler.push(cpu.includesCpuCooler.toString())
        //   }

        //   this.manufacturers.sort();
        //   this.coreCount.sort();
        //   this.TDP.sort();
        //   this.socket.sort();
        //   this.hasIntergratedGraphics.sort();
        //   this.includesCpuCooler.sort();

        //   this.selectedManufacturers = this.manufacturers;
        //   this.selectedCoreCount = this.coreCount
        //   this.selectedTDP = this.TDP
        //   this.selectedSocket = this.socket
        //   this.selectedHasIntergratedGraphics = this.hasIntergratedGraphics
        //   this.selectedIncludesCpuCooler = this.includesCpuCooler
        // }
      },
      error => {
        console.log('********** ViewAllComputerCasesComponent.ts: ' + error);
      }
    );
  }

}
