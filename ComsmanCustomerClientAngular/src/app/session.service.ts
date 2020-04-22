import { Injectable } from '@angular/core';
import { Customer } from './Customer';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor() {
    // let customer = new Customer(1, "address", "contactNumber", "email", "jefferson", "low")
    // this.setCurrentCustomer(customer);
    // this.setIsLogin(true);
  }

  getIsLogin(): boolean {
    return sessionStorage.isLogin == "true";
  }

  setIsLogin(isLogin: boolean): void {
    sessionStorage.isLogin = isLogin;
  }

  getCurrentCustomer(): Customer {
    return JSON.parse(sessionStorage.currentCustomer);
  }

  setCurrentCustomer(currentCustomer: Customer): void {
    sessionStorage.currentCustomer = JSON.stringify(currentCustomer);
  }

}
