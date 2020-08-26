import {Visit} from './visit';
import {Referral} from './referral';

export class Patient {

  public appointments: Visit[];
  public referrals: Referral[];

  constructor(public name: string,
              public lName: string,
              public pesel: string,
              public sex: boolean, //
              public email: string,
              public phone: string,
              public birthday: Date,
              public since: Date,
              public isVIP: boolean,
              public insurance: string
  ) {
  }
}
