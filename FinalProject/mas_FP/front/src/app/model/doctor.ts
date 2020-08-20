export class Doctor {
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
