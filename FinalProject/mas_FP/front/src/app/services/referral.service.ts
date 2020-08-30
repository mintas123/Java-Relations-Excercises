import {Injectable} from '@angular/core';
import {Referral} from '../model/referral';
import {HttpClient} from '@angular/common/http';
import {Subject} from 'rxjs';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ReferralService {

  constructor(private http: HttpClient) {
  }

  referrals: Referral[] = [];
  referralChanged = new Subject<Referral[]>();

  fetchReferrals(patientId: number) {
    this.http.get<Referral[]>(API_URL + 'patient/referrals/' + patientId).subscribe(
      (response: Referral[]) => {
        this.referrals = response;
        this.referralChanged.next(response);
      }
    );
  }

  getReferrals(): Referral[] {
    return this.referrals.slice();
  }

}
