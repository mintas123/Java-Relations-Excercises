import {EventEmitter, Injectable} from '@angular/core';
import {Doctor} from '../model/doctor';
import {DivisionService} from './division.service';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  doctorSelected = new EventEmitter<Doctor>();
  doctorList = [
    new Doctor('Daniel', 'Tanajno',
      66053034556, true,
      'dt123@gmail.com', '723333123',
      new Date(1966, 4, 30),
      5000, false),
    new Doctor('Ewa', 'Przygłup',
      65053034556, false,
      'ep432@gmail.com', '603434153',
      new Date(1963, 4, 30),
      8000, true),
    new Doctor('Piotr', 'Macierewicz',
      76053034556, true,
      'dt123@gmail.com', '432532555',
      new Date(1976, 4, 30),
      7000, false),
    new Doctor('Andrzej', 'Dupa',
      80053034556, true,
      'dt123@gmail.com', '432532555',
      new Date(1976, 4, 30),
      7000, false),
  ];


  getDoctors(): Doctor[] {
    return this.doctorList.slice();
  }

  getDoctor(index: number): Doctor {
    return this.doctorList[index];
  }
  getDoctorId(patient: Doctor): number {
    return this.doctorList.indexOf(patient);
  }


  constructor(private divService: DivisionService) {
    for (const doc of this.doctorList){
        doc.division = this.divService.getDivision(this.doctorList.indexOf(doc));
    }
  }
}
