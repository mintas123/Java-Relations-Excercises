import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {Patient} from '../model/patient';

@Component({
  selector: 'app-patient-search',
  templateUrl: './patient-search.component.html',
  styleUrls: ['./patient-search.component.css']
})
export class PatientSearchComponent implements OnInit {

  selectedPatient: Patient;
  myControl = new FormControl();
  options: Patient[] = [
    new Patient('Jakub Mróz', 99042608556,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('Jakub Mróż', 99042608123,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('Juba Mruz', 99042234656,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('AAAA Mróz', 99042608556,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('AAac Mróż', 99042608123,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('Acc Mruz', 99042234656,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('Aeee Mróz', 99042608556,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('AWE Mróż', 99042608123,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
    new Patient('EEE Mruz', 99042234656,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 3, 26)),
  ];
  filteredOptions: Observable<Patient[]>;

  constructor() {
  }

  ngOnInit() {
    this.initFilter();
  }

  initFilter(){
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }
  selectPatient(value){
    this.selectedPatient = value;
  }

  displayFn(subject) {
    return subject ? subject.name : undefined;
  }

  private _filter(value: string): Patient[] {
    const filterValue = value.toLowerCase();
    if (value.length < 3) { return null; }

    return this.options.filter(
      option => option.name.toLowerCase().includes(filterValue) || option.pesel.toString().indexOf(filterValue) === 0); }
}
