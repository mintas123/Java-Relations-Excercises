import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable, Subscription} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import {DoctorService} from '../services/doctor.service';
import {Doctor} from '../model/doctor';
import {Patient} from '../model/patient';
import {PatientService} from '../services/patient.service';
import {Location} from '@angular/common';
import {VisitHistory} from '../model/visitHistory';
import {VisitService} from '../services/visit.service';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {HttpClient} from '@angular/common/http';
import {NewVisit} from '../model/newVisit';


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

  patientId: number;
  selectedDoctor: Doctor;
  selectedPatient: Patient;
  checkedRef = false;

  bookingFinished = false;
  dateSearchFinished = false;

  dates: Date[] = [];
  selectedDate: Date;
  myControl = new FormControl();
  options: Doctor[];
  filteredOptions: Observable<Doctor[]>;

  subscription: Subscription;

  constructor(private doctorService: DoctorService,
              private patientService: PatientService,
              private visitService: VisitService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.initFilter();
    this.maxDateStart.setDate(this.minDateStart.getDate() + 90);
    this.maxDateEnd.setDate(this.minDateStart.getDate() + 90);


    this.doctorService.fetchDoctors();
    this.subscription = this.doctorService.doctorsChanged.subscribe(
      (doctors: Doctor[]) => {
        this.options = doctors;
      }
    );
    this.options = this.doctorService.getDoctors();

    this.doctorService.doctorSelected.subscribe(
      (doctor: Doctor) => {
        this.selectedDoctor = doctor;
      }
    );

    this.patientId = this.route.snapshot.params.id;
    this.selectedPatient = this.patientService.getPatient(this.patientId);
  }

  onDoctorClick() {
    this.initFilter();
  }

  onBackClick() {
    this.location.back();
  }

  onBookClick(ref: TemplateRef<any>) {
    this.openDialogWithRef(ref);

    const newVisit = new NewVisit(
      this.selectedDoctor.personId,
      +this.patientId,
      this.selectedDate
    );
    console.log(newVisit);
    this.visitService.addNewVisit(newVisit);
    this.bookingFinished = true;
    return true;
  }

  selectDoctor(value) {
    this.doctorService.doctorSelected.emit(value);
    this.selectedPatient = this.patientService.getPatient(this.patientId);

  }

  getDoctorId(doc) {
    return this.doctorService.getDoctorId(doc);
  }

  onDateSelection(date: Date) {
    this.selectedDate = date;
  }

  findVisits() {
    if (this.startDate && this.endDate && this.selectedDoctor && this.selectedPatient) {
      console.log('finding new dates..');
      this.visitService.getDatesBetween(this.selectedPatient.personId,
        this.selectedDoctor.personId,
        this.startDate, this.endDate, this.checkedRef);
      this.visitService.datesChanged.subscribe(
        (dates: Date[]) => {
          this.dates = dates;
          this.dateSearchFinished = true;
        }
      );
      this.dates = this.visitService.getDates();
    }
  }

  displayFn(subject) {
    return subject ? subject.name + ' ' + subject.lastName : undefined;
  }

  initFilter() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  private openDialogWithRef(ref: TemplateRef<any>) {
    this.dialog.open(ref);
  }

  private _filter(value: string): Doctor[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(
      option => option.name.toLowerCase().includes(filterValue) ||
        option.lastName.toLowerCase().includes(filterValue) ||
        option.divisionName.toLowerCase().includes(filterValue));
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
