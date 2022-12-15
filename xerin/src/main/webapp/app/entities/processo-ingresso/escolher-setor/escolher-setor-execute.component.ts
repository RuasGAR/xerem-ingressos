import { Component, Vue, Inject } from 'vue-property-decorator';

import EstadioService from '@/entities/estadio/estadio.service';
import { IEstadio } from '@/shared/model/estadio.model';

import EscolherSetorService from './escolher-setor.service';
import { EscolherSetorContext } from './escolher-setor.model';

const validations: any = {
  taskContext: {
    processoIngresso: {
      ingresso: {
        setorEstadio: {},
        assentoEstadio: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class EscolherSetorExecuteComponent extends Vue {
  private escolherSetorService: EscolherSetorService = new EscolherSetorService();
  private taskContext: EscolherSetorContext = {};

  @Inject('EstadioService') private EstadioService: () => EstadioService;

  public Estadios: IEstadio[] = [];
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
    this.escolherSetorService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.escolherSetorService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.EstadioService()
      .retrieve()
      .then(res => {
        this.Estadios = res.data;
      });
  }
}
