import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientRefferalsComponent } from './patient-refferals.component';

describe('PatientRefferalsComponent', () => {
  let component: PatientRefferalsComponent;
  let fixture: ComponentFixture<PatientRefferalsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientRefferalsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientRefferalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
