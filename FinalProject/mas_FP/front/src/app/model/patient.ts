export class Patient {
  constructor(public name: string,
              public lName: string,
              public pesel: number,
              public sex: boolean,
              public email: string,
              public phone: string,
              public birthday: Date,
              public since: Date,
              public isVIP: boolean,
              public insurance: string
  ) {
  }
}
