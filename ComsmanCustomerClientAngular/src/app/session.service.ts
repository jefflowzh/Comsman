import { Injectable } from '@angular/core';
import { Customer } from './Customer';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})

// probably should be LocalService
export class SessionService {

  constructor() {
    // let customer = new Customer(1, "address", "contactNumber", "email", "jefferson", "low")
    // this.setCurrentCustomer(customer);
    // this.setIsLogin(true);
  }

  getIsLogin(): boolean {
    return localStorage.isLogin == "true";
  }

  setIsLogin(isLogin: boolean): void {
    localStorage.isLogin = isLogin;
  }

  getCurrentCustomer(): Customer {
    return JSON.parse(localStorage.currentCustomer);
  }

  setCurrentCustomer(currentCustomer: Customer): void {
    localStorage.currentCustomer = JSON.stringify(currentCustomer);
  }

}
