import { IIngresso } from '@/shared/model/ingresso.model';

export interface IProcessoIngresso {
  id?: number;
  processInstance?: any | null;
  ingresso?: IIngresso | null;
}

export class ProcessoIngresso implements IProcessoIngresso {
  constructor(public id?: number, public processInstance?: any | null, public ingresso?: IIngresso | null) {}
}
