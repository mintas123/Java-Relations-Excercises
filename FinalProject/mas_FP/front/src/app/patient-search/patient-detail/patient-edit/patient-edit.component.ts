import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PatientService} from '../../../services/patient.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Patient} from '../../../model/patient';

@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.css']
})
export class PatientEditComponent implements OnInit {

  patientId: number;
  patientForm: FormGroup;
  selectedPatient: Patient;

  today = new Date().toISOString().split('T')[0];


  constructor(private router: Router,
              private route: ActivatedRoute,
              private patientService: PatientService) {
  }

  ngOnInit(): void {
    this.patientId = this.route.snapshot.params.id;
    this.selectedPatient = this.patientService.getPatient(this.patientId);
    this.initForm();
  }


  private initForm() {
    this.patientForm = new FormGroup({
      name: new FormControl(this.selectedPatient.name, Validators.required),
      lastName: new FormControl(this.selectedPatient.lastName, Validators.required),
      pesel: new FormControl(this.selectedPatient.pesel, [Validators.required, Validators.pattern(/^[0-9]{11}$/)]),
      sex: new FormControl(this.selectedPatient.gender, Validators.required),
      email: new FormControl(this.selectedPatient.email, [Validators.required, Validators.email]),
      phone: new FormControl(this.selectedPatient.phone, Validators.required),
      birthday: new FormControl(this.selectedPatient.birthday, [Validators.required]),
      since: new FormControl(this.selectedPatient.clientSince, [Validators.required]),
      isVIP: new FormControl(this.selectedPatient.vip),
      insurance: new FormControl(this.selectedPatient.insuranceProvider, Validators.required),
    });

  }

  onUpdate() {
    const newPatient = new Patient(
      this.patientId,
      this.patientForm.value.name,
      this.patientForm.value.lastName,
      this.patientForm.value.pesel,
      this.patientForm.value.sex,
      this.patientForm.value.email,
      this.patientForm.value.phone,
      this.patientForm.value.birthday,
      this.patientForm.value.since,
      this.patientForm.value.isVIP,
      this.patientForm.value.insurance,
    );
    this.patientService.updatePatient(this.patientId, newPatient);
    this.onBackClick();
  }


  onBackClick() {
    this.router.navigate(['/search', this.patientId]);
  }


}
