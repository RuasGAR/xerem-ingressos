import { Component, Vue, Inject } from 'vue-property-decorator';

import ConfirmacaoService from './confirmacao.service';
import { ConfirmacaoContext } from './confirmacao.model';

@Component
export default class ConfirmacaoDetailsComponent extends Vue {
  private confirmacaoService: ConfirmacaoService = new ConfirmacaoService();
  private taskContext: ConfirmacaoContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.confirmacaoService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
