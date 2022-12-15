import { Component, Vue, Inject } from 'vue-property-decorator';

import ConfirmacaoService from './confirmacao.service';
import { ConfirmacaoContext } from './confirmacao.model';

const validations: any = {
  taskContext: {
    processoIngresso: {
      ingresso: {
        timeMandante: {},
        timeVisitante: {},
        horarioJogo: {},
        data: {},
        estadio: {},
        setorEstadio: {},
        assentoEstadio: {},
        nomeComprador: {},
        cpfComprador: {},
        nascimentoComprador: {},
        emailComprador: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class ConfirmacaoExecuteComponent extends Vue {
  private confirmacaoService: ConfirmacaoService = new ConfirmacaoService();
  private taskContext: ConfirmacaoContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.confirmacaoService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.confirmacaoService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
