import { Component, Vue, Inject } from 'vue-property-decorator';

import EscolherEventoService from './escolher-evento.service';
import { EscolherEventoContext } from './escolher-evento.model';

@Component
export default class EscolherEventoDetailsComponent extends Vue {
  private escolherEventoService: EscolherEventoService = new EscolherEventoService();
  private taskContext: EscolherEventoContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.escolherEventoService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
