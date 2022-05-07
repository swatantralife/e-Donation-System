import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Donation } from '../common/donation';

@Injectable({
  providedIn: 'root'
})
export class DonationService {

  private baseUrl = 'http://localhost:8080/e-donation/api/v1/donations/by-id/';

  constructor(private httpClient: HttpClient) { }

  getDonationList(id: number): Observable<Donation[]>{
    return this.httpClient.get<DonationInterface[]>(this.baseUrl+id);
  }
}

export interface DonationInterface {

  donationId: number;
    donorId: number;
    ngoId: number;
    donationType: string;
    amount: number;
    donationDate: Date;
}
