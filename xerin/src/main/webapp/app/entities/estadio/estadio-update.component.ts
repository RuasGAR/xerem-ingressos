import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEstadio, Estadio } from '@/shared/model/estadio.model';
import EstadioService from './estadio.service';

const validations: any = {
  estadio: {
    nome: {},
  },
};

@Component({
  validations,
})
export default class EstadioUpdate extends Vue {
  @Inject('estadioService') private estadioService: () => EstadioService;
  public estadio: IEstadio = new Estadio();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.estadioId) {
        vm.retrieveEstadio(to.params.estadioId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.estadio.id) {
      this.estadioService()
        .update(this.estadio)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('xeremIngressosApp.estadio.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.estadioService()
        .create(this.estadio)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('xeremIngressosApp.estadio.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveEstadio(estadioId): void {
    this.estadioService()
      .find(estadioId)
      .then(res => {
        this.estadio = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
