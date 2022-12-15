import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITime } from '@/shared/model/time.model';
import TimeService from './time.service';

@Component
export default class TimeDetails extends Vue {
  @Inject('timeService') private timeService: () => TimeService;
  public time: ITime = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.timeId) {
        vm.retrieveTime(to.params.timeId);
      }
    });
  }

  public retrieveTime(timeId) {
    this.timeService()
      .find(timeId)
      .then(res => {
        this.time = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
