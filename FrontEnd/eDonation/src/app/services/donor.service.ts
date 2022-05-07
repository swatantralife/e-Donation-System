import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Donor } from '../common/donor';

@Injectable({
  providedIn: 'root'
})
export class DonorService {

  private baseUrl = 'http://localhost:8080/e-donation/api/v1/donors/findAll';
  private donorUrl = 'http://localhost:8080/e-donation/api/v1/donors/by-donor-id/';

  constructor(private httpClient: HttpClient) { }

  getDonorList(): Observable<Donor[]>{
    return this.httpClient.get<DonorInterface[]>(this.baseUrl);
  }

  getDonor(id: number): Observable<Donor>{
    return this.httpClient.get<DonorInterface>(this.donorUrl +id);
  }
}



export interface DonorInterface {

  donarId: number;
    ngoId: number;
    donarName: string;
    userName: string;
    password: string;
    email: string;
    phoneNumber: number;
    address: string
}


