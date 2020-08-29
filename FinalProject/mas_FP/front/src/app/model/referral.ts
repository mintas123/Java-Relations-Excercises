
export class Referral {
  constructor(public doctorName: string,
              public doctorLastName: string,
              public divisionName: string,
              public isUsed: boolean,
              public dateFrom: Date,
              public dateTo: Date,
              ) {
  }
}
