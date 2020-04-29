import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { ViewAllProductsComponent } from './view-all-products/view-all-products.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';
import { ViewAllComputerCasesComponent } from './view-all-computer-cases/view-all-computer-cases.component';

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: IndexComponent },
  { path: 'viewAllComputerCases', component: ViewAllComputerCasesComponent },
  { path: 'viewAllProducts/:productType', component: ViewAllProductsComponent },
  { path: 'customerLogin', component: CustomerLoginComponent },
  { path: 'customerRegistration', component: CustomerRegistrationComponent },
  { path: 'customerCart', component: CustomerCartComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
