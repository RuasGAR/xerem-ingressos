export interface IIngresso {
  id?: number;
  horarioJogo?: string | null;
  timeMandante?: string | null;
  timeVisitante?: string | null;
  data?: Date | null;
  nomeEstadio?: string | null;
  setorEstadio?: string | null;
  assentoEstadio?: string | null;
  nomeComprador?: string | null;
  cpfComprador?: string | null;
  nascimentoComprador?: Date | null;
  numeroCartao?: number | null;
  validadeCartao?: string | null;
  codigoCartao?: number | null;
  emailComprador?: string | null;
}

export class Ingresso implements IIngresso {
  constructor(
    public id?: number,
    public horarioJogo?: string | null,
    public timeMandante?: string | null,
    public timeVisitante?: string | null,
    public data?: Date | null,
    public nomeEstadio?: string | null,
    public setorEstadio?: string | null,
    public assentoEstadio?: string | null,
    public nomeComprador?: string | null,
    public cpfComprador?: string | null,
    public nascimentoComprador?: Date | null,
    public numeroCartao?: number | null,
    public validadeCartao?: string | null,
    public codigoCartao?: number | null,
    public emailComprador?: string | null
  ) {}
}
