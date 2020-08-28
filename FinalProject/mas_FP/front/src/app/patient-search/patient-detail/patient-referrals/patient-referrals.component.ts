import { Component, OnInit } from '@angular/core';
import {Patient} from '../../../model/patient';
import {Referral} from '../../../model/referral';
import {PatientService} from '../../../services/patient.service';
import {VisitService} from '../../../services/visit.service';
import {DoctorService} from '../../../services/doctor.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {ReferralService} from '../../../services/referral.service';
import {Visit} from '../../../model/visit';
import {Doctor} from '../../../model/doctor';

@Component({
  selector: 'app-patient-referrals',
  templateUrl: './patient-referrals.component.html',
  styleUrls: ['./patient-referrals.component.css']
})
export class PatientReferralsComponent implements OnInit {

  selectedPatient: Patient;
  referrals: Referral[];

  constructor(private patientService: PatientService,
              private referralService: ReferralService,
              private doctorService: DoctorService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    const patientId = this.route.snapshot.params.id;
    this.selectedPatient = this.patientService.getPatient(patientId);
    this.referrals = this.referralService.findReferralByPatient(this.selectedPatient);
  }

  getVisitDoctorInfo(visit: Visit): Doctor {
    return this.doctorService.getDoctor(1); // todo broken
  }

  getReferralDoctorInfo(referral: Referral): Doctor {
    return this.doctorService.getDoctor(referral.doctor);
  }

  onBackClick() {
    this.location.back();
  }

}
