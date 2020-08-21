import {Doctor} from './doctor';

export class Division {

  public head: Doctor;
  public staff: Doctor[];


  constructor(
    public name: string,
    public desc: string
  ) {
  }
}
