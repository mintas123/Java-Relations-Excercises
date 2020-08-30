import {VisitHistory} from './visitHistory';
import {Referral} from './referral';

export class Patient {

  constructor(
    public personId: number,
    public name: string,
    public lastName: string,
    public pesel: string,
    public gender: string,
    public email: string,
    public phone: string,
    public birthday: Date,
    public clientSince: Date,
    public vip: boolean,
    public insuranceProvider: string,
  ) {
  }
}
