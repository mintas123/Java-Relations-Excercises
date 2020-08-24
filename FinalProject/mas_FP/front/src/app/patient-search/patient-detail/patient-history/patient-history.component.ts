import { Component, OnInit } from '@angular/core';
import {Visit} from '../../../model/visit';
import {PatientService} from '../../../services/patient.service';
import {VisitService} from '../../../services/visit.service';
import {DoctorService} from '../../../services/doctor.service';
import {Patient} from '../../../model/patient';
import {Doctor} from '../../../model/doctor';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrls: ['./patient-history.component.css']
})
export class PatientHistoryComponent implements OnInit {


  selectedPatient: Patient;
  history: Visit[];

  constructor(private patientService: PatientService,
              private visitService: VisitService,
              private doctorService: DoctorService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    const patientId = this.route.snapshot.params.id;
    this.selectedPatient = this.patientService.getPatient(patientId);
    this.history = this.visitService.findVisitsByPatient(this.selectedPatient);
  }


  getVisitDoctorInfo(visit: Visit): Doctor {
    return this.doctorService.getDoctor(visit.doctor);
  }

  onBackClick() {
    this.location.back();
  }
}
