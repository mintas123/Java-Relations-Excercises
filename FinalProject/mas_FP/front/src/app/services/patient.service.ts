import {EventEmitter, Injectable} from '@angular/core';
import {Patient} from '../model/patient';
import {Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';


const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class PatientService {


  constructor(private http: HttpClient) {
  }

  patientSelected = new EventEmitter<Patient>();
  patientsChanged = new Subject<Patient[]>();

  private patientList: Patient[] = [];


  fetchPatients() {
    this.http.get<Patient[]>(API_URL + 'patient').subscribe(
      (response: Patient[]) => {
        this.patientList = response;
        this.patientsChanged.next(response);
      });
  }

  getPatients(): Patient[] {
    return this.patientList.slice();
  }

  getPatient(id: number): Patient {
    this.fetchPatients();
    return this.patientList.find(patient => patient.personId == id);
  }

  addPatient(patient: Patient) {
    this.http.post(API_URL + 'patient', patient).subscribe();

    this.patientList.push(patient);
    this.refreshList();
  }

  updatePatient(id: number, patient: Patient) {
    this.http.put(API_URL + 'patient/' + id + '/edit', patient).subscribe(
      response => console.log(response)
    );

    const updatedPatient = this.getPatient(id);
    const index = this.patientList.indexOf(updatedPatient);
    this.patientList[index] = patient;
    this.refreshList();
  }

  private refreshList() {
    this.patientsChanged.next(this.patientList.slice());
  }


}
