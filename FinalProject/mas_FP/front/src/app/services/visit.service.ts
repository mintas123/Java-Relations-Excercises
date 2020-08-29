import {Injectable} from '@angular/core';
import {PatientService} from './patient.service';
import {Visit} from '../model/visit';
import {HttpClient} from '@angular/common/http';
import {Subject} from 'rxjs';
import {DatePipe} from '@angular/common';

const API_URL = 'http://localhost:8080/api/';


@Injectable({
  providedIn: 'root'
})
export class VisitService {

  constructor(private http: HttpClient,
              private datePipe: DatePipe) {
  }

  visits: Visit[] = [];
  dates: Date[] = [];
  visitChanged = new Subject<Visit[]>();
  datesChanged = new Subject<Date[]>();


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

  getDatesBetween(patientId: number, doctorId: number, dateFrom: Date, dateTo: Date, hasReferral: boolean) {

    const dateFromFormatted = this.datePipe.transform(dateFrom, 'yyyy-MM-dd');
    const dateToFormatted = this.datePipe.transform(dateTo, 'yyyy-MM-dd');

    this.http.get<Date[]>(API_URL + 'appointment/' + doctorId + '/' + patientId + '/' +
      dateFromFormatted + '/' + dateToFormatted + '/' + hasReferral)
      .subscribe(
        (response: Date[]) => {
          this.dates = response;
          this.datesChanged.next(response);
        }
      );


  }

  getDates(): Date[] {
    return this.dates.slice();
  }

}
