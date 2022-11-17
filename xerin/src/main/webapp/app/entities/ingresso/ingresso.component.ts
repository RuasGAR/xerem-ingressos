import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IIngresso } from '@/shared/model/ingresso.model';

import IngressoService from './ingresso.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Ingresso extends Vue {
  @Inject('ingressoService') private ingressoService: () => IngressoService;
  private removeId: number = null;

  public ingressos: IIngresso[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllIngressos();
  }

  public clear(): void {
    this.retrieveAllIngressos();
  }

  public retrieveAllIngressos(): void {
    this.isFetching = true;

    this.ingressoService()
      .retrieve()
      .then(
        res => {
          this.ingressos = res.data;
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

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
