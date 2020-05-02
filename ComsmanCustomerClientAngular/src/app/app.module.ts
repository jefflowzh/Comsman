import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DialogModule } from 'primeng/dialog';
import { MenuModule } from 'primeng/menu';
import { CardModule } from 'primeng/card';
import { DataViewModule } from 'primeng/dataview';
import { DropdownModule } from 'primeng/dropdown';
import { PanelModule } from 'primeng/panel';
import { SpinnerModule } from 'primeng/spinner';
import { CheckboxModule } from 'primeng/checkbox';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { CarouselModule } from 'primeng/carousel';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { ViewAllProductsComponent } from './view-all-products/view-all-products.component';
import { HeaderComponent } from './header/header.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { FooterComponent } from './footer/footer.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';
import { ViewAllComputerCasesComponent } from './view-all-computer-cases/view-all-computer-cases.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { ViewAllCPUComponent } from './view-all-cpu/view-all-cpu.component';
import { ViewAllGPUComponent } from './view-all-gpu/view-all-gpu.component';
import { ViewAllMotherboardComponent } from './view-all-motherboard/view-all-motherboard.component';
import { ViewAllPowerSupplyComponent } from './view-all-power-supply/view-all-power-supply.component';
import { ViewAllRAMComponent } from './view-all-ram/view-all-ram.component';
import { ViewProductComponent } from './view-product/view-product.component';
import { AmateurSetMainPageComponent } from './amateur-set-main-page/amateur-set-main-page.component';
import { AmateurSetBuildPageComponent } from './amateur-set-build-page/amateur-set-build-page.component';
import { AdvancedSetBuildPageComponent } from './advanced-set-build-page/advanced-set-build-page.component';
import { ViewAllCPUCoolerComponent } from './view-all-cpucooler/view-all-cpucooler.component';
import { ViewAllInternalStorageComponent } from './view-all-internal-storage/view-all-internal-storage.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ViewAllProductsComponent,
    HeaderComponent,
    CustomerLoginComponent,
    CustomerRegistrationComponent,
    FooterComponent,
    CustomerCartComponent,
    ViewAllComputerCasesComponent,
    CustomerDetailsComponent,
    ViewAllCPUComponent,
    ViewAllGPUComponent,
    ViewAllMotherboardComponent,
    ViewAllPowerSupplyComponent,
    ViewAllRAMComponent,
    ViewProductComponent,
    AmateurSetMainPageComponent,
    AmateurSetBuildPageComponent,
    AdvancedSetBuildPageComponent,
    ViewAllCPUCoolerComponent,
    ViewAllInternalStorageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    MenubarModule,
    ButtonModule,
    InputTextModule,
    HttpClientModule,
    DialogModule,
    MenuModule,
    CardModule,
    DataViewModule,
    DropdownModule,
    PanelModule,
    FontAwesomeModule,
    SpinnerModule,
    CheckboxModule,
    BreadcrumbModule,
    CarouselModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
