import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { SessionService } from './session.service';
import { Customer } from './customer';
import { ComputerPart } from './computer-part';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl: string = "/api/Customer";

  customer: Customer;

  constructor(private httpClient: HttpClient, private sessionService: SessionService) {
  }

  customerLogin(email: string, password: string): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/customerLogin?email=" + email + "&password=" + password).pipe
      (
        catchError(this.handleError)
      );
  }

  getCurrentComputerSetPartsList(email: string): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/getCurrentComputerSetPartsList?email=" + email).pipe
      (
        catchError(this.handleError)
      );
  }

  customerRegistration(newCustomer: Customer): Observable<any> {

    let customerRegistrationReq = { "newCustomer": newCustomer }

    return this.httpClient.put<any>(this.baseUrl + "/customerRegistration", customerRegistrationReq, httpOptions).pipe
      (
        catchError(this.handleError)
      );
  }

  updateCustomerDetails(updatedCustomer: Customer): Observable<any> {

    let updateCustomerReq = {
      "userId": updatedCustomer.userId,
      "firstName": updatedCustomer.firstName,
      "lastName": updatedCustomer.lastName,
      "address": updatedCustomer.address,
      "email": updatedCustomer.email,
      "contactNumber": updatedCustomer.contactNumber,
      "cardNumber": updatedCustomer.cardNumber,
      "ccv": updatedCustomer.ccv
    }

    return this.httpClient.post<any>(this.baseUrl + "/updateCustomerDetails", updateCustomerReq, httpOptions).pipe
      (
        catchError(this.handleError)
      );

  }

  updateCustomerPassword(updatedCustomer: Customer): Observable<any> {

    let updateCustomerReq = {
      "userId": updatedCustomer.userId,
      "password": updatedCustomer.password
    }

    return this.httpClient.post<any>(this.baseUrl + "/updateCustomerPassword", updateCustomerReq, httpOptions).pipe
      (
        catchError(this.handleError)
      );

  }

  updateCustomerCart(): Observable<any> {

    let cartProductIds: number[] = [];
    let cartProductQuantities: number[] = [];
    let cartComputerSetsPartIds: number[][] = [];
    let cartComputerSetsQuantities: number[] = [];
    let cartComputerSetsWarrantyLengths: number[] = [];
    let cartComputerSetsPrices: number[] = [];

    for (let li of this.sessionService.getCurrentCustomer().cart) {
      if (li.product) {
        cartProductIds.push(li.product.productId);
        cartProductQuantities.push(li.quantity);
      }

      if (li.computerSet) {
        let computerSetPartIds: number[] = [];
        computerSetPartIds.push(li.computerSet.compCase.productId);
        computerSetPartIds.push(li.computerSet.cpu.productId);
        if (li.computerSet.airCooler) {
          computerSetPartIds.push(li.computerSet.airCooler.productId);
        }
        if (li.computerSet.waterCooler) {
          computerSetPartIds.push(li.computerSet.waterCooler.productId);
        }
        for (let gpu of li.computerSet.gpus) {
          computerSetPartIds.push(gpu.productId);
        }
        for (let hdd of li.computerSet.hdds) {
          computerSetPartIds.push(hdd.productId);
        }
        computerSetPartIds.push(li.computerSet.motherBoard.productId);
        computerSetPartIds.push(li.computerSet.psu.productId);
        for (let ram of li.computerSet.rams) {
          computerSetPartIds.push(ram.productId);
        }
        for (let ssd of li.computerSet.ssds) {
          computerSetPartIds.push(ssd.productId);
        }

        cartComputerSetsPartIds.push(computerSetPartIds);
        cartComputerSetsQuantities.push(li.quantity);
        cartComputerSetsWarrantyLengths.push(li.computerSet.warrantyLengthInYears);
        cartComputerSetsPrices.push(li.computerSet.price);
      }

    }

    let updateCustomerReq = {
      "userId": this.sessionService.getCurrentCustomer().userId,
      "cartProductIds": cartProductIds,
      "cartProductQuantities": cartProductQuantities,
      "cartComputerSetsPartIds": cartComputerSetsPartIds,
      "cartComputerSetsQuantities": cartComputerSetsQuantities,
      "cartComputerSetsWarrantyLengths": cartComputerSetsWarrantyLengths,
      "cartComputerSetsPrices": cartComputerSetsPrices
    }

    return this.httpClient.post<any>(this.baseUrl + "/updateCustomerCart", updateCustomerReq, httpOptions).pipe
      (
        catchError(this.handleError)
      );

  }

  // updateCustomerBuild(updatedCustomer: Customer): Observable<any> {
  // }

  private handleError(error: HttpErrorResponse) {
    let errorMessage: string = "";

    if (error.error instanceof ErrorEvent) {
      errorMessage = "An unknown error has occurred: " + error.error.message;
    }
    else {
      errorMessage = "A HTTP error has occurred: " + `HTTP ${error.status}: ${error.error.message}`;
    }

    console.error(errorMessage);

    return throwError(errorMessage);
  }

  customerOrders(email: string): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/customerOrders?email=" + email).pipe
      (
        catchError(this.handleError)
      );
  }

}
