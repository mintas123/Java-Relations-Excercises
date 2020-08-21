import {Division} from './division';
import {Visit} from './visit';
import {Referral} from './referral';

export class Doctor {


  public division: Division;
  public appointments: Visit[];
  public referrals: Referral[];

  constructor(public name: string,
              public lName: string,
              public pesel: number,
              public sex: boolean,
              public email: string,
              public phone: string,
              public birthday: Date,
              public salary: number,
              public isHead: boolean,
  ) {
  }
}
