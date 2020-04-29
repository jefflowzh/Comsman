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

  updateCustomerCart(): Observable<any> {

    let updateCustomerCartReq = {
      "customerId": this.sessionService.getCurrentCustomer().userId,
      "updatedCart": this.sessionService.getCurrentCustomer().cart
    }

    console.log('********* DEBUG 1')
    console.log(this.sessionService.getCurrentCustomer())

    return this.httpClient.post<any>(this.baseUrl + "/updateCustomerCartReq", updateCustomerCartReq, httpOptions).pipe
      (
        catchError(this.handleError)
      );

  }

  // updateCustomerBuild(updatedCustomer: Customer): Observable<any> {

  //   let updateCustomerReq = {
  //     "updatedCustomer": updatedCustomer
  //   }

  //   console.log('********* DEBUG 1')
  //   console.log(updatedCustomer)
  //   console.log(updatedCustomer.userId)

  //   return this.httpClient.post<any>(this.baseUrl + "/updateCustomer", updateCustomerReq, httpOptions).pipe
  //     (
  //       catchError(this.handleError)
  //     );
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
}
