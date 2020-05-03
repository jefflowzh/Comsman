import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';
import { ViewAllComputerCasesComponent } from './view-all-computer-cases/view-all-computer-cases.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { ViewAllCPUComponent } from './view-all-cpu/view-all-cpu.component';
import { ViewAllGPUComponent } from './view-all-gpu/view-all-gpu.component';
import { ViewAllMotherboardComponent } from './view-all-motherboard/view-all-motherboard.component';
import { ViewAllPowerSupplyComponent } from './view-all-power-supply/view-all-power-supply.component';
import { ViewAllRAMComponent } from './view-all-ram/view-all-ram.component';
import { ViewProductComponent } from './view-product/view-product.component';
import { CustomerOrdersComponent } from './customer-orders/customer-orders.component';
import { AmateurSetMainPageComponent } from './amateur-set-main-page/amateur-set-main-page.component';
import { AmateurSetBuildPageComponent } from './amateur-set-build-page/amateur-set-build-page.component';
import { AdvancedSetBuildPageComponent } from './advanced-set-build-page/advanced-set-build-page.component';
import { ViewAllCPUCoolerComponent } from './view-all-cpucooler/view-all-cpucooler.component';
import { ViewAllInternalStorageComponent } from './view-all-internal-storage/view-all-internal-storage.component';
import { ViewAllPeripheralsComponent } from './view-all-peripherals/view-all-peripherals.component';
import { ViewOrderComponent } from './view-order/view-order.component';
import { CustomerCouponComponent } from './customer-coupon/customer-coupon.component';
import { ViewAllSearchResultsComponent } from './view-all-search-results/view-all-search-results.component';

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: IndexComponent },
  { path: 'viewAllComputerCases', component: ViewAllComputerCasesComponent },
  { path: 'viewAllCPU', component: ViewAllCPUComponent },
  { path: 'viewAllGPU', component: ViewAllGPUComponent },
  { path: 'viewAllMotherboard', component: ViewAllMotherboardComponent },
  { path: 'viewAllPowerSupply', component: ViewAllPowerSupplyComponent },
  { path: 'viewAllRAM', component: ViewAllRAMComponent },
  { path: 'customerLogin', component: CustomerLoginComponent },
  { path: 'customerRegistration', component: CustomerRegistrationComponent },
  { path: 'customerCart', component: CustomerCartComponent },
  { path: 'customerDetails', component: CustomerDetailsComponent },
  { path: 'customerOrders', component: CustomerOrdersComponent },
  { path: 'customerCoupons', component: CustomerCouponComponent },
  { path: 'viewOrder/:customerOrderId', component: ViewOrderComponent },
  { path: 'viewProduct/:productId', component: ViewProductComponent },
  { path: 'viewProduct/:productId', component: ViewProductComponent },
  { path: 'amateurSetMainPage', component: AmateurSetMainPageComponent },
  { path: 'amateurSetBuildPage', component: AmateurSetBuildPageComponent },
  { path: 'advancedSetBuildPage', component: AdvancedSetBuildPageComponent },
  { path: 'viewAllCPUCooler', component: ViewAllCPUCoolerComponent },
  { path: 'viewAllInternalStorage', component: ViewAllInternalStorageComponent },
  { path: 'viewAllPeripherals', component: ViewAllPeripheralsComponent },
  { path: 'viewAllSearchResults/:searchTerm', component: ViewAllSearchResultsComponent, runGuardsAndResolvers: 'paramsChange' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: 'reload'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
