import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import {DoctorService} from '../services/doctor.service';
import {Doctor} from '../model/doctor';
import {Patient} from '../model/patient';
import {PatientService} from '../services/patient.service';
import {Location} from '@angular/common';


@Component({
  selector: 'app-patient-visit',
  templateUrl: './patient-visit.component.html',
  styleUrls: ['./patient-visit.component.css']
})
export class PatientVisitComponent implements OnInit {

// start date picker
  minDateStart = new Date();
  maxDateStart = new Date();
// end date picker
  minDateEnd = new Date();
  maxDateEnd = new Date();

  startDate: Date;
  endDate: Date;

  selectedDoctor: Doctor;
  selectedPatient: Patient;

  fControl = new FormControl();
  options = this.doctorService.getDoctors();
  filteredOptions: Observable<Doctor[]>;

  constructor(private doctorService: DoctorService,
              private patientService: PatientService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.initFilter();
    this.maxDateStart.setDate( this.minDateStart.getDate() + 90);
    this.maxDateEnd.setDate( this.minDateStart.getDate() + 90);

    this.doctorService.doctorSelected.subscribe(
      (doctor: Doctor) => {
        this.selectedDoctor = doctor;
      }
    );

    const id = this.route.snapshot.params.id;
    this.selectedPatient = this.patientService.getPatient(id);
  }

  onDoctorClick() {
    this.initFilter();
  }

  onBackClick() {
    this.location.back();
  }

  onBookClick() {
    return true;
  }

  selectDoctor(value) {
    this.selectedDoctor = value;
  }

  getDoctorId(doc) {
    return this.doctorService.getDoctorId(doc);
  }

  displayFn(subject) {
    return subject ? subject.name + ' ' + subject.lName : undefined;
  }

  initFilter() {
    this.filteredOptions = this.fControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  private _filter(value: string): Doctor[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(
      option => option.name.toLowerCase().includes(filterValue) ||
        option.lName.toLowerCase().includes(filterValue) ||
        option.division.name.toLowerCase().includes(filterValue));
  }

  // // for END picker
  // getMinEndDate() {
  //   console.log(' get min end date');
  //   if (this.startDate > this.minDateEnd) {
  //     console.log(' start date is bigger');
  //     console.log(this.startDate);
  //     return this.startDate;
  //   }
  //   console.log(' start date is not bigger');
  //   console.log(this.maxDateEnd);
  //   return this.maxDateEnd;
  // }
  //
  // // for START picker
  // getMaxStartDate() {
  //   if (this.endDate < this.maxDateStart) {
  //     return  this.endDate;
  //   }
  //   return  this.maxDateStart;
  // }

}
