import {Component, Input, OnInit} from '@angular/core';
import {Patient} from '../../model/patient';
import {PatientService} from '../../services/patient.service';
import {ActivatedRoute, Params, Router} from '@angular/router';

@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.css']
})
export class PatientDetailComponent implements OnInit {

  selectedPatient: Patient;
  id: number;


  constructor(private patientService: PatientService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params.id;
        this.selectedPatient = this.patientService.getPatient(this.id);
      }
    );
  }

  onEditPatient() {
    this.router.navigate(['edit'], {relativeTo: this.route});
  }
  onHistory() {
    this.router.navigate(['history'], {relativeTo: this.route});
  }
  onReferral() {
    this.router.navigate(['referrals'], {relativeTo: this.route});
  }

}
