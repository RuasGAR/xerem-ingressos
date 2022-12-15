import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEstadio } from '@/shared/model/estadio.model';

import EstadioService from './estadio.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Estadio extends Vue {
  @Inject('estadioService') private estadioService: () => EstadioService;
  private removeId: number = null;

  public estadios: IEstadio[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllEstadios();
  }

  public clear(): void {
    this.retrieveAllEstadios();
  }

  public retrieveAllEstadios(): void {
    this.isFetching = true;

    this.estadioService()
      .retrieve()
      .then(
        res => {
          this.estadios = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IEstadio): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeEstadio(): void {
    this.estadioService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('xeremIngressosApp.estadio.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllEstadios();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
