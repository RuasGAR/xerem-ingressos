import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITime } from '@/shared/model/time.model';

import TimeService from './time.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Time extends Vue {
  @Inject('timeService') private timeService: () => TimeService;
  private removeId: number = null;

  public times: ITime[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTimes();
  }

  public clear(): void {
    this.retrieveAllTimes();
  }

  public retrieveAllTimes(): void {
    this.isFetching = true;

    this.timeService()
      .retrieve()
      .then(
        res => {
          this.times = res.data;
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

  public prepareRemove(instance: ITime): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTime(): void {
    this.timeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('xeremIngressosApp.time.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTimes();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
