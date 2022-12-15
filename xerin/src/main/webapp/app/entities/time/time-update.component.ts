import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITime, Time } from '@/shared/model/time.model';
import TimeService from './time.service';

const validations: any = {
  time: {
    nome: {},
    cidade: {},
  },
};

@Component({
  validations,
})
export default class TimeUpdate extends Vue {
  @Inject('timeService') private timeService: () => TimeService;
  public time: ITime = new Time();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.timeId) {
        vm.retrieveTime(to.params.timeId);
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
    if (this.time.id) {
      this.timeService()
        .update(this.time)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('xeremIngressosApp.time.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.timeService()
        .create(this.time)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('xeremIngressosApp.time.created', { param: param.id });
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

  public retrieveTime(timeId): void {
    this.timeService()
      .find(timeId)
      .then(res => {
        this.time = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
