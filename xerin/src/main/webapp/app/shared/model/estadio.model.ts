export interface IEstadio {
  id?: number;
  nome?: string | null;
}

export class Estadio implements IEstadio {
  constructor(public id?: number, public nome?: string | null) {}
}
