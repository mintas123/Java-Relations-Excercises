import {EventEmitter, Injectable} from '@angular/core';
import {Doctor} from '../model/doctor';
import {HttpClient} from '@angular/common/http';
import {Subject} from 'rxjs';


const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) {
  }

  doctorSelected = new EventEmitter<Doctor>();
  doctorsChanged = new Subject<Doctor[]>();


  private doctorList: Doctor[] = [
  ];


  fetchDoctors() {
    this.http.get<Doctor[]>(API_URL + 'doctor').subscribe(
      (response: Doctor[]) => {
        this.doctorList = response;
        this.doctorsChanged.next(response);
      });
  }


  getDoctors(): Doctor[] {
    return this.doctorList.slice();
  }

  getDoctor(id: number): Doctor {
    this.fetchDoctors();
    return this.doctorList.find(patient => patient.personId == id);
  }

  getDoctorId(patient: Doctor): number {
    return this.doctorList.indexOf(patient);
  }


}
