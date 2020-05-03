import { Injectable } from '@angular/core';
import { Customer } from './Customer';
import { Product } from './product';
import { CustomerOrder } from './customer-order';

@Injectable({
  providedIn: 'root'
})

// probably should be LocalService
export class SessionService {

  constructor() { }

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

  // getCurrentOrder(): CustomerOrder[]{
  //   return JSON.parse(localStorage.currentCustomerOrder)
  // }

  // setCurrentOrder(currentOrder: CustomerOrder[]): void {
  //   localStorage.currentCustomer = JSON.stringify(currentOrder);
  // }

}
