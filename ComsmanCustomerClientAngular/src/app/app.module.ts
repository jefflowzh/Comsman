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

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { ViewCustomerProfileComponent } from './view-customer-profile/view-customer-profile.component';
import { ViewAllProductsComponent } from './view-all-products/view-all-products.component';
import { HeaderComponent } from './header/header.component';
import { ViewCustomerAccountComponent } from './view-customer-account/view-customer-account.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { FooterComponent } from './footer/footer.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';
import { ViewAllComputerCasesComponent } from './view-all-computer-cases/view-all-computer-cases.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ViewCustomerProfileComponent,
    ViewAllProductsComponent,
    HeaderComponent,
    ViewCustomerAccountComponent,
    CustomerLoginComponent,
    CustomerRegistrationComponent,
    FooterComponent,
    CustomerCartComponent,
    ViewAllComputerCasesComponent,
    CustomerDetailsComponent
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
    CheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
