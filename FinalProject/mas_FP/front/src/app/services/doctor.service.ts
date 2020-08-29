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
    // new Doctor('Daniel', 'Tanajno',
    //   66053034556, true,
    //   'dt123@gmail.com', '723333123',
    //   new Date(1966, 4, 30),
    //   5000, false),
    // new Doctor('Ewa', 'Przygłup',
    //   65053034556, false,
    //   'ep432@gmail.com', '603434153',
    //   new Date(1963, 4, 30),
    //   8000, true),
    // new Doctor('Piotr', 'Macierewicz',
    //   76053034556, true,
    //   'dt123@gmail.com', '432532555',
    //   new Date(1976, 4, 30),
    //   7000, false),
    // new Doctor('Andrzej', 'Dupa',
    //   80053034556, true,
    //   'dt123@gmail.com', '432532555',
    //   new Date(1976, 4, 30),
    //   7000, false),
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
