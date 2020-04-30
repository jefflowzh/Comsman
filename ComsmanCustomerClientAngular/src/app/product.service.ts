import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl: string = "/api/Product";

  constructor(private httpClient: HttpClient) {
  }

  retrieveAllPeripherals(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/retrieveAllPeripherals").pipe
      (
        catchError(this.handleError)
      );
  }

  retrieveAllComputerCases(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/retrieveAllComputerCases").pipe
      (
        catchError(this.handleError)
      );
  }

  retrieveAllCpu(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/retrieveAllCpu").pipe
      (
        catchError(this.handleError)
      );
  }
  retrieveAllGpu(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/retrieveAllGpu").pipe
      (
        catchError(this.handleError)
      );
  }
  retrieveAllMotherBoard(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/retrieveAllMotherBoard").pipe
      (
        catchError(this.handleError)
      );
  }

  retrieveAllPowerSupply(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/retrieveAllPowerSupply").pipe
      (
        catchError(this.handleError)
      );
  }

  retrieveAllRam(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/retrieveAllRam").pipe
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
}
