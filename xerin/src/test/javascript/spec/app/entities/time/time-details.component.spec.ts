/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TimeDetailComponent from '@/entities/time/time-details.vue';
import TimeClass from '@/entities/time/time-details.component';
import TimeService from '@/entities/time/time.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Time Management Detail Component', () => {
    let wrapper: Wrapper<TimeClass>;
    let comp: TimeClass;
    let timeServiceStub: SinonStubbedInstance<TimeService>;

    beforeEach(() => {
      timeServiceStub = sinon.createStubInstance<TimeService>(TimeService);

      wrapper = shallowMount<TimeClass>(TimeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { timeService: () => timeServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTime = { id: 123 };
        timeServiceStub.find.resolves(foundTime);

        // WHEN
        comp.retrieveTime(123);
        await comp.$nextTick();

        // THEN
        expect(comp.time).toBe(foundTime);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTime = { id: 123 };
        timeServiceStub.find.resolves(foundTime);

        // WHEN
        comp.beforeRouteEnter({ params: { timeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.time).toBe(foundTime);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
