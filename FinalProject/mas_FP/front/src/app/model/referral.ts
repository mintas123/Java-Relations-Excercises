
export class Referral {
  constructor(public doctor: number,
              public patient: number,
              public visit: number,
              public isUsed: boolean,
              public dateFrom: Date,
              public dateTo: Date,
              ) {
  }
}
