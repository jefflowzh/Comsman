import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ComputerSetService {

  baseUrl: string = "/api/ComputerSet";

  constructor(private httpClient: HttpClient) { }

//   checkCouponByCode(code: string): Observable<any> {
//     return this.httpClient.get<any>(this.baseUrl + "/checkCouponByCode?code=" + code).pipe
//       (
//         catchError(this.handleError)
//       );
//   }

  getCurrentComputerSetPartsList(email: string): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl + "/getCurrentComputerSetPartsList?email=" + email).pipe
      (
        catchError(this.handleError)
      );
  }

  //compatibilityCheck
  compatibilityCheck(computerPartIdList: number[], latestAddedComputerPartId:number): Observable<any> {

    let CompatibilityCheckReq = { "currentComputerBuildPartIds": computerPartIdList ,"addedPartId" : latestAddedComputerPartId};

    return this.httpClient.post<any>(this.baseUrl + "/compatibilityCheck", CompatibilityCheckReq, httpOptions).pipe
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




