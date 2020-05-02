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

  customerRegistration(newCustomer: Customer): Observable<any> {

    let customerRegistrationReq = { "newCustomer": newCustomer }

    return this.httpClient.put<any>(this.baseUrl + "/customerRegistration", customerRegistrationReq, httpOptions).pipe
      (
        catchError(this.handleError)
      );
  }

  updateCustomerDetails(): Observable<any> {

    let updateCustomerReq = {
      "customer": this.sessionService.getCurrentCustomer()
    }

    return this.httpClient.post<any>(this.baseUrl + "/updateCustomerDetails", updateCustomerReq, httpOptions).pipe
      (
        catchError(this.handleError)
      );

  }

  updateCustomerCart(): Observable<any> {

    console.log(this.sessionService.getCurrentCustomer())
    // let customer = {
    //   address: "NUS",
    //   cart: [],
    //   contactNumber: "12345678",
    //   currComputerBuild: [],
    //   email: "c@gmail.com",
    //   firstName: "Jeff",
    //   isDisabled: false,
    //   lastName: "Low",
    //   loyaltyPoints: 0,
    //   orders: [],
    //   password: "c5344cd99287b3a73c245f06c58739c3",
    //   salt: "NH2V8HWFWi3I4di0TrFVkpfawlBIPy3l",
    //   userId: 4,
    // }

    // let updateCustomerReq = {
    //   "customer": customer
    // }

    let updateCustomerReq = {
      "customer": this.sessionService.getCurrentCustomer()
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

  customerUpdate(currentCustomer: Customer): Observable<any> {
    let customerUpdateReq = { "customer": currentCustomer };

    return this.httpClient.post<any>(this.baseUrl, customerUpdateReq).pipe();
  }

}
