import { Component, Vue, Inject } from 'vue-property-decorator';

import { IIngresso } from '@/shared/model/ingresso.model';
import IngressoService from './ingresso.service';

@Component
export default class IngressoDetails extends Vue {
  @Inject('ingressoService') private ingressoService: () => IngressoService;
  public ingresso: IIngresso = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ingressoId) {
        vm.retrieveIngresso(to.params.ingressoId);
      }
    });
  }

  public retrieveIngresso(ingressoId) {
    this.ingressoService()
      .find(ingressoId)
      .then(res => {
        this.ingresso = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
