import { Injectable } from '@angular/core';
import {Division} from '../model/division';

@Injectable({
  providedIn: 'root'
})
export class DivisionService {

  divisionList = [
      new Division('Dermatology', 'blablabla'),
      new Division('Cardiology', 'blablabla'),
      new Division('Office', 'blablabla'),
      new Division('Pneumology', 'blablabla'),
  ];

  getDivisions(): Division[] {
    return this.divisionList.slice();
  }

  getDivision(index: number): Division {
    return this.divisionList[index];
  }
  getDivisionId(div: Division): number {
    return this.divisionList.indexOf(div);
  }

  constructor() { }
}
