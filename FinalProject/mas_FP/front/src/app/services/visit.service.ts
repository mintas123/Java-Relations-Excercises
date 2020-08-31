import {Injectable} from '@angular/core';
import {VisitHistory} from '../model/visitHistory';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Subject} from 'rxjs';
import {DatePipe} from '@angular/common';
import {VisitNew} from '../model/visitNew';

const API_URL = 'http://localhost:8080/api/';


@Injectable({
  providedIn: 'root'
})
export class VisitService {

  constructor(private http: HttpClient,
              private datePipe: DatePipe) {
  }

  visits: VisitHistory[] = [];
  dates: Date[] = [];
  visitChanged = new Subject<VisitHistory[]>();
  datesChanged = new Subject<Date[]>();


  fetchVisits(patientId: number) {
    this.http.get<VisitHistory[]>(API_URL + 'patient/history/' + patientId).subscribe(
      (response: VisitHistory[]) => {
        this.visits = response;
        this.visitChanged.next(response);

      });
  }

  addNewVisit(newVisit: VisitNew) {
    const header = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    this.http.post(API_URL + 'appointment', newVisit, {headers: header}).subscribe(
      response => console.log(response)
    );
    this.dates = this.dates.slice(this.dates.indexOf(newVisit.date), 1);
    this.datesChanged.next(this.dates);
  }


  getVisits(): VisitHistory[] {
    return this.visits.slice();
  }
  getDates(): Date[] {
    return this.dates.slice();
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



}
