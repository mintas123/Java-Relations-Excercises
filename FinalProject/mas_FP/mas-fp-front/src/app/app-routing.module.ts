import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PatientSearchComponent } from './patient-search/patient-search.component';
import { VisitSearchComponent } from './visit-search/visit-search.component';
import { NewPatientComponent } from './new-patient/new-patient.component';


const routes: Routes = [

  {
    path: '', component: PatientSearchComponent
  },
  {
    path: 'visit',
    component: VisitSearchComponent
  },
  {
    path: "patient/new",
    component: NewPatientComponent
  },
  {
    path: '**',
    component: PatientSearchComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
