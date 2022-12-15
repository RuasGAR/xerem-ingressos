export interface ITime {
  id?: number;
  nome?: string | null;
  nomeCasa?: string | null;
  cidade?: string | null;
}

export class Time implements ITime {
  constructor(public id?: number, public nome?: string | null, public nomeCasa?: string | null, public cidade?: string | null) {}
}
