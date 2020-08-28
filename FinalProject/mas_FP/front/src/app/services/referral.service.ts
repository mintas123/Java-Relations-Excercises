import { Injectable } from '@angular/core';
import {Referral} from '../model/referral';
import {Patient} from '../model/patient';
import {Visit} from '../model/visit';
import {PatientService} from './patient.service';

@Injectable({
  providedIn: 'root'
})
export class ReferralService {

  constructor(private patientService: PatientService) { }

  referrals: Referral[] = [
    new Referral(0, 0, 0, false,
      new Date(2020, 7, 15),
      new Date(2020, 9, 15),
      ),
    new Referral(1, 0, 0, true,
      new Date(2020, 7, 15),
      new Date(2020, 9, 15),
    ),
    new Referral(0, 1, 0, false,
      new Date(2020, 7, 15),
      new Date(2020, 9, 15),
    ),
  ];

  findReferralByPatient(patient: Patient): Referral[] {
    const referralCopy = this.referrals.slice();
    // const patientId = this.patientService.getPatientId(patient);
    return referralCopy.filter((referral: Referral) => referral.patient === patient.personId);
  }
}
