/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TimeComponent from '@/entities/time/time.vue';
import TimeClass from '@/entities/time/time.component';
import TimeService from '@/entities/time/time.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Time Management Component', () => {
    let wrapper: Wrapper<TimeClass>;
    let comp: TimeClass;
    let timeServiceStub: SinonStubbedInstance<TimeService>;

    beforeEach(() => {
      timeServiceStub = sinon.createStubInstance<TimeService>(TimeService);
      timeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TimeClass>(TimeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          timeService: () => timeServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      timeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTimes();
      await comp.$nextTick();

      // THEN
      expect(timeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.times[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      timeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeTime();
      await comp.$nextTick();

      // THEN
      expect(timeServiceStub.delete.called).toBeTruthy();
      expect(timeServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
