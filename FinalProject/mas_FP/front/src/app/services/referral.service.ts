import {Injectable} from '@angular/core';
import {Referral} from '../model/referral';
import {Patient} from '../model/patient';
import {PatientService} from './patient.service';
import {HttpClient} from '@angular/common/http';
import {Subject} from 'rxjs';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ReferralService {

  constructor(private http: HttpClient,
              private patientService: PatientService) {
  }

  referrals: Referral[] = [
    // new Referral(0, 0, 0, false,
    //   new Date(2020, 7, 15),
    //   new Date(2020, 9, 15),
    //   ),
    // new Referral(1, 0, 0, true,
    //   new Date(2020, 7, 15),
    //   new Date(2020, 9, 15),
    // ),
    // new Referral(0, 1, 0, false,
    //   new Date(2020, 7, 15),
    //   new Date(2020, 9, 15),
    // ),
  ];
  referralChanged = new Subject<Referral[]>();

  fetchReferrals(patientId: number) {
    this.http.get<Referral[]>(API_URL + 'patient/referrals/' + patientId).subscribe(
      (response: Referral[]) => {
        this.referrals = response;
        this.referralChanged.next(response);
      }
    );
  }

  getRefferals(): Referral[] {
    return this.referrals.slice();
  }

  // findReferralByPatient(patient: Patient): Referral[] {
  //   const referralCopy = this.referrals.slice();
  //   // const patientId = this.patientService.getPatientId(patient);
  //   return referralCopy.filter((referral: Referral) => referral.patient === patient.personId);
  // }
}
