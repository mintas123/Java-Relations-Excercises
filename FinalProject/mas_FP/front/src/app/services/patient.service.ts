import {EventEmitter, Injectable} from '@angular/core';
import {Patient} from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  patientSelected = new EventEmitter<Patient>();

  // placeholder
  private patientList: Patient[] = [
    new Patient('Jakub', 'Mróz', 99042608556,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1999, 1, 26),
      new Date(2005, 1, 2),
      true, 'AXA'),

    new Patient('Jakub', 'Mróż', 99042608123,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1965, 7, 26),
      new Date(2005, 7, 2),
      false, 'VERRA'),

    new Patient('Juba', 'Mruz', 99042234656,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1996, 6, 26),
      new Date(2005, 6, 2),
      true, 'DUPA'),

    new Patient('AAAA', 'Mróz', 99042608556,
      true, 'kumroz@gmail.com', '787312931',
      new Date(2001, 6, 26),
      new Date(2005, 6, 2),
      false, 'AXA'),

    new Patient('AAac', 'Mróż', 99042608123,
      true, 'kumroz@gmail.com', '787312931',
      new Date(2002, 3, 26),
      new Date(2005, 3, 2),
      false, 'DDD'),

    new Patient('Acc', 'Mruz', 99042234656,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1976, 6, 26),
      new Date(2005, 5, 2),
      false, 'XXX'),

    new Patient('Aeee', 'Mróz', 99042608556,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1973, 4, 1),
      new Date(2020, 5, 2),
      false, 'DSF'),

    new Patient('AWE', 'Mróż', 99042608123,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1969, 11, 23),
      new Date(2002, 5, 2),
      false, 'UPC'),

    new Patient('EEE', 'Mruz', 99042234656,
      true, 'kumroz@gmail.com', '787312931',
      new Date(1989, 5, 26),
      new Date(2012, 5, 2),
      false, 'UPS'),

    new Patient('Zofia', 'Śliwińska', 10310112454,
      false, 'zsliwinska8@gmail.com', '123123123',
      new Date(1994, 2, 26),
      new Date(2015, 5, 2),
      false, 'AXA'),
  ];

  getPatients(): Patient[] {
    return this.patientList.slice();
  }

  getPatient(index: number): Patient {
    return this.patientList[index];
  }
  getPatientId(patient: Patient): number {
    return this.patientList.indexOf(patient);
  }

  constructor() {
  }
}
