import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class IndexComponent implements OnInit {

  sidenavItems: MenuItem[];
  carouselItems: string[] = ["https://pictures.topspeed.com/IMG/crop_webp/201907/origin-s-big-o-platf-1_1600x0.webp",
    "https://www.sony.com.sg/image/eb0062b3db03748efc7f5ca3fd82ccc5?fmt=pjpeg&wid=330&bgcolor=FFFFFF&bgc=FFFFFF"]

  constructor() { }

  ngOnInit() {
    this.sidenavItems = [{
      label: 'Computer Parts',
      items: [
        { label: 'CPU', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllCPU'] },
        { label: 'CPU Cooler', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllProducts/CPUCooler'] },
        { label: 'Computer Case', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllComputerCases'] },
        { label: 'GPU', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllGPU'] },
        { label: 'Internal Storage (SSD/HDD)', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllProducts/HDD'] },
        { label: 'Motherboard', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllMotherboard'] },
        { label: 'Power Supply', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllPowerSupply'] },
        { label: 'RAM', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllRAM'] }
      ]
    },
    {
      label: 'Peripherals',
      items: [
        { label: 'Peripherals', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/viewAllProducts/Peripheral'] },
      ]
    },
    {
      label: 'Build My Own Computer Set',
      items: [
        { label: 'Amateur', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/amateurSetMainPage'] },
        { label: 'Advanced', icon: 'pi pi-fw pi-chevron-circle-right', routerLink: ['/advancedSetBuildPage'] }
      ]
    }];
  }

}
