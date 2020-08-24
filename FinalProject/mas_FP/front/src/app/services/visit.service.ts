import {Injectable} from '@angular/core';
import {PatientService} from './patient.service';
import {Visit} from '../model/visit';
import {Patient} from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class VisitService {

  constructor(private patientService: PatientService) {
    // this.initData();
  }

  visits: Visit[] = [
    new Visit(0, 0, new Date(2020, 7, 26, 14, 15)),
    new Visit(1, 3, new Date(2020, 7, 24, 13, 30)),
    new Visit(1, 4, new Date(2020, 7, 24, 13, 45)),

  ];

  findVisitsByPatient(patient: Patient): Visit[] {
    const visitCopy = this.visits.slice();
    const patientId = this.patientService.getPatientId(patient);
    return visitCopy.filter((visit: Visit) => visit.patient === patientId);
  }

  // initData() {
  //   let visit1 = new Visit()
  // }

  // private createDatesBetween(from: Date, to: Date) {
  //   let dateArray: Date[];
  //
  //   let day;
  //   for ( day = from; day < to; day++) {
  //     let time;
  //     for (time = day.) {
  //
  //     }
  //
  //   }
  // }
  getDatesBetween(from: Date, to: Date) {
    let dates: Date[] = [];
    console.log(from);
    console.log(to);

    const theDate = new Date(from);
    while (theDate <= to) {
      console.log(theDate);
      dates = [...dates, new Date(theDate)];
      theDate.setDate(theDate.getDate() + 1);
    }
    return dates;
  }

}
