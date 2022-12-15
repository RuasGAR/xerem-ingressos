import { Component, Vue, Inject } from 'vue-property-decorator';

import TimeService from '@/entities/time/time.service';
import { ITime } from '@/shared/model/time.model';

import EscolherEventoService from './escolher-evento.service';
import { EscolherEventoContext } from './escolher-evento.model';

const validations: any = {
  taskContext: {
    processoIngresso: {
      ingresso: {
        horarioJogo: {},
        data: {},
        timeVisitante: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class EscolherEventoExecuteComponent extends Vue {
  private escolherEventoService: EscolherEventoService = new EscolherEventoService();
  private taskContext: EscolherEventoContext = {};

  @Inject('TimeService') private TimeService: () => TimeService;

  public Times: ITime[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.escolherEventoService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.escolherEventoService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.TimeService()
      .retrieve()
      .then(res => {
        this.Times = res.data;
      });
  }
}
