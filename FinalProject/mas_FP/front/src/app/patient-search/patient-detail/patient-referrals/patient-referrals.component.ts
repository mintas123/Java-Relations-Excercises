import {Component, OnInit} from '@angular/core';
import {Patient} from '../../../model/patient';
import {Referral} from '../../../model/referral';
import {PatientService} from '../../../services/patient.service';
import {DoctorService} from '../../../services/doctor.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {ReferralService} from '../../../services/referral.service';
import {VisitHistory} from '../../../model/visitHistory';
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
              private location: Location) {
  }

  ngOnInit(): void {
    const patientId = this.route.snapshot.params.id;

    this.referralService.fetchReferrals(patientId);
    this.referralService.referralChanged.subscribe(
      (referrals: Referral[]) => {
        this.referrals = referrals;
      }
    );
    this.referrals = this.referralService.getRefferals();
    console.log(this.referrals);
    this.selectedPatient = this.patientService.getPatient(patientId);
  }

  onBackClick() {
    this.location.back();
  }

}
