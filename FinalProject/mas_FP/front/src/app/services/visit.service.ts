import {Injectable} from '@angular/core';
import {PatientService} from './patient.service';
import {Visit} from '../model/visit';
import {Patient} from '../model/patient';
import {HttpClient} from '@angular/common/http';
import {Subject} from 'rxjs';

const API_URL = 'http://localhost:8080/api/';


@Injectable({
  providedIn: 'root'
})
export class VisitService {

  constructor(private http: HttpClient,
              private patientService: PatientService) {
  }

  visits: Visit[] = [
    // new Visit(0, 0, new Date(2020, 7, 26, 14, 15)),
    // new Visit(1, 3, new Date(2020, 7, 24, 13, 30)),
    // new Visit(1, 4, new Date(2020, 7, 24, 13, 45)),

  ];
  visitChanged = new Subject<Visit[]>();


  fetchVisits(patientId: number) {
    this.http.get<Visit[]>(API_URL + 'patient/history/' + patientId).subscribe(
      (response: Visit[]) => {
        this.visits = response;
        this.visitChanged.next(response);

      });
  }

  getVisits(): Visit[] {
    return this.visits.slice();
  }

  // findVisitsByPatient(patient: Patient): Visit[] {
  //   const visitCopy = this.visits.slice();
  //   // const patientId = this.patientService.getPatientId(patient);
  //   return visitCopy.filter((visit: Visit) => visit.patient === patient.personId);
  // }

  getDatesBetween(from: Date, to: Date) { // not working
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
