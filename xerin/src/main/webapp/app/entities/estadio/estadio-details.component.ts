import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEstadio } from '@/shared/model/estadio.model';
import EstadioService from './estadio.service';

@Component
export default class EstadioDetails extends Vue {
  @Inject('estadioService') private estadioService: () => EstadioService;
  public estadio: IEstadio = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.estadioId) {
        vm.retrieveEstadio(to.params.estadioId);
      }
    });
  }

  public retrieveEstadio(estadioId) {
    this.estadioService()
      .find(estadioId)
      .then(res => {
        this.estadio = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
