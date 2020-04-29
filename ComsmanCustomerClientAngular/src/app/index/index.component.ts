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

  constructor() { }

  ngOnInit() {
    this.sidenavItems = [{
      label: 'Computer Parts',
      items: [
        { label: 'CPU Cooler', icon: 'pi pi-fw pi-plus', routerLink: ['/viewAllProducts/CPUCooler'] },
        { label: 'Computer Case', icon: 'pi pi-fw pi-download', routerLink: ['/viewAllComputerCases'] },
        { label: 'GPU', icon: 'pi pi-fw pi-plus', routerLink: ['/viewAllProducts/GPU'] },
        { label: 'HDD', icon: 'pi pi-fw pi-download', routerLink: ['/viewAllProducts/HDD'] },
        { label: 'Motherboard', icon: 'pi pi-fw pi-plus', routerLink: ['/viewAllProducts/Motherboard'] },
        { label: 'Power Supply', icon: 'pi pi-fw pi-download', routerLink: ['/viewAllProducts/PowerSupply'] },
        { label: 'RAM', icon: 'pi pi-fw pi-plus', routerLink: ['/viewAllProducts/RAM'] },
        { label: 'SSD', icon: 'pi pi-fw pi-download', routerLink: ['/viewAllProducts/SSD'] }
      ]
    },
    {
      label: 'Peripherals',
      items: [
        { label: 'View all peripherals', icon: 'pi pi-fw pi-user-plus', routerLink: ['/viewAllProducts/Peripheral'] },
      ]
    },
    {
      label: 'Build My Own Computer Set',
      items: [
        { label: 'Amateur', icon: 'pi pi-fw pi-user-plus' },
        { label: 'Advanced', icon: 'pi pi-fw pi-user-minus' }
      ]
    }];
  }

}
