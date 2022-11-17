import { Component, Vue, Inject } from 'vue-property-decorator';

import EscolherSetorService from './escolher-setor.service';
import { EscolherSetorContext } from './escolher-setor.model';

@Component
export default class EscolherSetorDetailsComponent extends Vue {
  private escolherSetorService: EscolherSetorService = new EscolherSetorService();
  private taskContext: EscolherSetorContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.escolherSetorService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
