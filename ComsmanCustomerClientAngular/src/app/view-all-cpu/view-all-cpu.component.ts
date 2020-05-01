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

 
  manufacturers: string[] =[]
  selectedManufacturers: string[] = []

  coreCount: string[] = []
  selectedCoreCOunt: string[] = []

  TDP: string[] = []
  selectedTDP: string[] = []

  socket: string[] = []
  selectedSocket: string[] = []

  hasIntergratedGraphics: string[] = []
  selectedHasIntergratedGraphics: string[] = []

  includesCpuCooler: string[] = []
  selectedIncludesCpuCooler: string[] = []

  AllManufacturersCheckbox: boolean = true;
  AllCoreCountCheckbox: boolean = true;
  AllTDPCheckbox: boolean = true;
  AllSocketCheckbox: boolean = true;
  AllHasIntergratedGraphicsCheckbox: boolean = true;
  AllIncludesCpuCoolerCheckbox: boolean = true;
    

  constructor(private productService: ProductService) { }

  ngOnInit() {
  }

}
