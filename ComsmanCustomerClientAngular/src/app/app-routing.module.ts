import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { ViewAllProductsComponent } from './view-all-products/view-all-products.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: IndexComponent },
  { path: 'viewAllProducts/:productType', component: ViewAllProductsComponent },
  { path: 'customerLogin', component: CustomerLoginComponent },
  { path: 'customerRegistration', component: CustomerRegistrationComponent },
  { path: 'customerCart', component: CustomerCartComponent },
  { path: 'customerDetails', component: CustomerDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
