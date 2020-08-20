import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {PatientVisitComponent} from './patient-visit/patient-visit.component';
import {PatientDetailComponent} from './patient-search/patient-detail/patient-detail.component';
import {PatientEditComponent} from './patient-search/patient-detail/patient-edit/patient-edit.component';
import {PatientReferralsComponent} from './patient-search/patient-detail/patient-referrals/patient-referrals.component';
import {PatientHistoryComponent} from './patient-search/patient-detail/patient-history/patient-history.component';
import {PatientSearchComponent} from './patient-search/patient-search.component';
import {PatientNewComponent} from './patient-new/patient-new.component';


const appRoutes: Routes = [
  {
    path: 'search',
    component: PatientSearchComponent,
    children: [
      {
        path: ':id',
        component: PatientDetailComponent
      },
      {
        path: ':id/edit',
        component: PatientEditComponent
      },
      {
        path: ':id/referrals',
        component: PatientReferralsComponent
      },
      {
        path: ':id/history',
        component: PatientHistoryComponent
      },
    ]
  },
  {
    path: 'new',
    component: PatientNewComponent
  },
  {
    path: ':id/visit',
    component: PatientVisitComponent
  },
  {
    path: '',
    redirectTo: '/search',
    pathMatch: 'full'
  },
];



@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
