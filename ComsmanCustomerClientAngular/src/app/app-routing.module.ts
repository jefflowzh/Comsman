import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { ViewAllProductsComponent } from './view-all-products/view-all-products.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';
import { ViewAllComputerCasesComponent } from './view-all-computer-cases/view-all-computer-cases.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { ViewProductComponent } from './view-product/view-product.component';
import { AmateurSetMainPageComponent } from './amateur-set-main-page/amateur-set-main-page.component';
import { AmateurSetBuildPageComponent } from './amateur-set-build-page/amateur-set-build-page.component';
import { AdvancedSetBuildPageComponent } from './advanced-set-build-page/advanced-set-build-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: IndexComponent },
  { path: 'viewAllComputerCases', component: ViewAllComputerCasesComponent },
  { path: 'viewAllProducts/:productType', component: ViewAllProductsComponent },
  { path: 'customerLogin', component: CustomerLoginComponent },
  { path: 'customerRegistration', component: CustomerRegistrationComponent },
  { path: 'customerCart', component: CustomerCartComponent },
  { path: 'customerDetails', component: CustomerDetailsComponent },
  { path: 'viewProduct/:productId', component: ViewProductComponent },
  { path: 'amateurSetMainPage', component: AmateurSetMainPageComponent },
  { path: 'amateurSetBuildPage', component: AmateurSetBuildPageComponent },
  { path: 'advancedSetBuildPage', component: AdvancedSetBuildPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
