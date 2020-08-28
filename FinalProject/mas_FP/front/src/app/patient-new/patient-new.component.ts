import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Patient} from '../model/patient';
import {PatientService} from '../services/patient.service';

@Component({
  selector: 'app-patient-new',
  templateUrl: './patient-new.component.html',
  styleUrls: ['./patient-new.component.css']
})

export class PatientNewComponent implements OnInit {

  id: number;
  patientForm: FormGroup;

  constructor(private location: Location,
              private route: ActivatedRoute,
              private router: Router,
              private patientService: PatientService) { }

  ngOnInit(): void {
    this.initForm();
  }

  private initForm() {

    // const name = '';
    // const lName = '';
    // const pesel = '';
    // const sex = '';
    // const email = '';
    // const phone  = '';
    // const birthday = '';
    // const isVIP = '';
    // const insurance = '';

    this.patientForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      lName: new FormControl(null, Validators.required),
      pesel: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]{11}$/)]),
      sex: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      phone: new FormControl(null, Validators.required),
      birthday: new FormControl(null, [Validators.required]),
      isVIP: new FormControl(false),
      insurance: new FormControl(null, Validators.required),
    });
  }
  onSubmit() {
    const  newPatient = new Patient(
      null,
      this.patientForm.value.name,
      this.patientForm.value.lName,
      this.patientForm.value.pesel,
      this.patientForm.value.sex,
      this.patientForm.value.email,
      this.patientForm.value.phone,
      new Date(Date.parse(this.patientForm.value.birthday)),
      new Date(Date.now()),
      this.patientForm.value.isVIP,
      this.patientForm.value.insurance
    );
    this.patientService.addPatient(newPatient);

    this.onReturn();
  }

  onReturn() {
    this.router.navigate(['/'], {relativeTo: this.route});
  }


}
