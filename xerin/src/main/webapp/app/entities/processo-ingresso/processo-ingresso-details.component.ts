import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProcessoIngresso } from '@/shared/model/processo-ingresso.model';
import ProcessoIngressoService from './processo-ingresso.service';

@Component
export default class ProcessoIngressoDetailsComponent extends Vue {
  @Inject('processoIngressoService') private processoIngressoService: () => ProcessoIngressoService;
  public processoIngresso: IProcessoIngresso = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveProcessoIngresso(to.params.processInstanceId);
      }
    });
  }

  public retrieveProcessoIngresso(processoIngressoId) {
    this.isFetching = true;
    this.processoIngressoService()
      .find(processoIngressoId)
      .then(
        res => {
          this.processoIngresso = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
