import {Component, OnInit} from '@angular/core';
import {VisitHistory} from '../../../model/visitHistory';
import {PatientService} from '../../../services/patient.service';
import {VisitService} from '../../../services/visit.service';
import {DoctorService} from '../../../services/doctor.service';
import {Patient} from '../../../model/patient';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrls: ['./patient-history.component.css']
})
export class PatientHistoryComponent implements OnInit {


  selectedPatient: Patient;
  history: VisitHistory[];

  constructor(private patientService: PatientService,
              private visitService: VisitService,
              private doctorService: DoctorService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    const patientId = this.route.snapshot.params.id;

    this.visitService.fetchVisits(patientId);
    this.visitService.visitChanged.subscribe(
      (visits: VisitHistory[]) => {
        this.history = visits;
      }
    );
    this.history = this.visitService.getVisits();

    this.selectedPatient = this.patientService.getPatient(patientId);
    this.history = this.visitService.visits;
  }

  onBackClick() {
    this.location.back();
  }
}
