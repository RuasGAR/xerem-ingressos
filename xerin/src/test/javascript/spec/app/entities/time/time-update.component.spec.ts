/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import TimeUpdateComponent from '@/entities/time/time-update.vue';
import TimeClass from '@/entities/time/time-update.component';
import TimeService from '@/entities/time/time.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Time Management Update Component', () => {
    let wrapper: Wrapper<TimeClass>;
    let comp: TimeClass;
    let timeServiceStub: SinonStubbedInstance<TimeService>;

    beforeEach(() => {
      timeServiceStub = sinon.createStubInstance<TimeService>(TimeService);

      wrapper = shallowMount<TimeClass>(TimeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          timeService: () => timeServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.time = entity;
        timeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(timeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.time = entity;
        timeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(timeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTime = { id: 123 };
        timeServiceStub.find.resolves(foundTime);
        timeServiceStub.retrieve.resolves([foundTime]);

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
