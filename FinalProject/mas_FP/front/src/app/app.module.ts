import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { PatientSearchComponent } from './patient-search/patient-search.component';
import { PatientNewComponent } from './patient-new/patient-new.component';
import { PatientVisitComponent } from './patient-visit/patient-visit.component';
import { PatientEditComponent } from './patient-search/patient-edit/patient-edit.component';
import { PatientHistoryComponent } from './patient-search/patient-history/patient-history.component';
import { PatientRefferalsComponent } from './patient-search/patient-refferals/patient-refferals.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PatientSearchComponent,
    PatientNewComponent,
    PatientVisitComponent,
    PatientEditComponent,
    PatientHistoryComponent,
    PatientRefferalsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
