import { Injectable } from '@angular/core';
import { Customer } from './Customer';
import { Product } from './product';
import { CustomerOrder } from './customer-order';
import { ComputerSet } from './computer-set';
import { ComputerPart } from './computer-part';

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

  getCurrentComputerSet(): ComputerSet {
    return JSON.parse(localStorage.currentComputerSet);
  }

  setCurrentComputerSet(currentComputerSet: ComputerSet): void {
    localStorage.currentComputerSet = JSON.stringify(currentComputerSet);
  }

  getLastAddedComputerPart(): ComputerPart {
    return JSON.parse(localStorage.lastAddedComputerPart);
  }

  setLastAddedComputerPart(lastAddedComputerPart: ComputerPart): void {
    localStorage.lastAddedComputerPart = JSON.stringify(lastAddedComputerPart);
  }

  // getCurrentOrder(): CustomerOrder[]{
  //   return JSON.parse(localStorage.currentCustomerOrder)
  // }

  // setCurrentOrder(currentOrder: CustomerOrder[]): void {
  //   localStorage.currentCustomer = JSON.stringify(currentOrder);
  // }

}
