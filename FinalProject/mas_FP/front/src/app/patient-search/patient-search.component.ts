import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {Patient} from '../model/patient';
import {PatientService} from '../services/patient.service';
import {ActivatedRoute, Router} from '@angular/router';
import {VisitService} from '../services/visit.service';
import {Visit} from '../model/visit';

@Component({
  selector: 'app-patient-search',
  templateUrl: './patient-search.component.html',
  styleUrls: ['./patient-search.component.css']
})
export class PatientSearchComponent implements OnInit {

// todo temp
  history: Visit[];
  dates: Date[] = [];

  selectedPatient: Patient;
  myControl = new FormControl();
  options = this.patientService.getPatients();
  filteredOptions: Observable<Patient[]>;

  constructor(private patientService: PatientService,
              private visitService: VisitService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.initFilter();

    this.patientService.patientSelected.subscribe(
      (patient: Patient) => {
        this.selectedPatient = patient;
      }
    );
  }

  onNewPatient() {
    this.router.navigate(['/new'], {relativeTo: this.route});
  }


  initFilter() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  onPatientClick() {
    this.initFilter();
    this.selectedPatient = null;

  }

  selectPatient(value) {
    this.patientService.patientSelected.emit(value);
    this.getPatientVisits();
  }
  getPatientId(patient){
    return this.patientService.getPatientId(patient);
  }
  // todo temp
  getPatientVisits() {
    this.history = this.visitService.findVisitsByPatient(this.selectedPatient);

    this.history.forEach(
      (visit: Visit) => {
        this.dates.push(visit.date);
      }
    );
  }

  displayFn(subject) {
    return subject ? subject.name + ' ' + subject.lName : undefined;
  }

  private _filter(value: string): Patient[] {
    const filterValue = value.toLowerCase();
    if (value.length < 3) {
      return [];
    }

    return this.options.filter(
      option => option.name.toLowerCase().includes(filterValue) ||
        option.lName.toLowerCase().includes(filterValue) ||
        option.pesel.toString().indexOf(filterValue) === 0);
  }
}
