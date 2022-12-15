import { ITime } from '@/shared/model/time.model';
import { IEstadio } from '@/shared/model/estadio.model';

export interface IIngresso {
  id?: number;
  horarioJogo?: string | null;
  data?: Date | null;
  setorEstadio?: string | null;
  assentoEstadio?: string | null;
  timeVisitante?: string | null;
  nomeComprador?: string | null;
  cpfComprador?: string | null;
  nascimentoComprador?: Date | null;
  numeroCartao?: number | null;
  validadeCartao?: string | null;
  codigoCartao?: number | null;
  emailComprador?: string | null;
  timeMandante?: ITime | null;
  estadio?: IEstadio | null;
}

export class Ingresso implements IIngresso {
  constructor(
    public id?: number,
    public horarioJogo?: string | null,
    public data?: Date | null,
    public setorEstadio?: string | null,
    public assentoEstadio?: string | null,
    public timeVisitante?: string | null,
    public nomeComprador?: string | null,
    public cpfComprador?: string | null,
    public nascimentoComprador?: Date | null,
    public numeroCartao?: number | null,
    public validadeCartao?: string | null,
    public codigoCartao?: number | null,
    public emailComprador?: string | null,
    public timeMandante?: ITime | null,
    public estadio?: IEstadio | null
  ) {}
}
