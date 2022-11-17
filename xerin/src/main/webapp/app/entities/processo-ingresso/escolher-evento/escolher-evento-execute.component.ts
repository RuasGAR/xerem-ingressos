import { Component, Vue, Inject } from 'vue-property-decorator';

import EscolherEventoService from './escolher-evento.service';
import { EscolherEventoContext } from './escolher-evento.model';

const validations: any = {
  taskContext: {
    processoIngresso: {
      ingresso: {
        horarioJogo: {},
        timeMandante: {},
        timeVisitante: {},
        data: {},
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
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
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

  public initRelationships(): void {}
}
