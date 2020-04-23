import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Customer } from './customer'

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl: string = "/api/Customer";

  customer: Customer;

  constructor(private httpClient: HttpClient) {
  }

  customerLogin(email: string, password: string): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/customerLogin?email=" + email + "&password=" + password).pipe
      (
        catchError(this.handleError)
      );
  }

  customerRegistration(newCustomer: Customer): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/customerRegistration?customer=" + newCustomer).pipe
      (
        catchError(this.handleError)
      );
  }

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

  customerUpdate(currentCustomer: Customer): Observable<any>{
    let customerUpdateReq = {"customer" : currentCustomer};

    return this.httpClient.post<any>(this.baseUrl, customerUpdateReq).pipe();
  }

}
