/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import EstadioComponent from '@/entities/estadio/estadio.vue';
import EstadioClass from '@/entities/estadio/estadio.component';
import EstadioService from '@/entities/estadio/estadio.service';

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
  describe('Estadio Management Component', () => {
    let wrapper: Wrapper<EstadioClass>;
    let comp: EstadioClass;
    let estadioServiceStub: SinonStubbedInstance<EstadioService>;

    beforeEach(() => {
      estadioServiceStub = sinon.createStubInstance<EstadioService>(EstadioService);
      estadioServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<EstadioClass>(EstadioComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          estadioService: () => estadioServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      estadioServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllEstadios();
      await comp.$nextTick();

      // THEN
      expect(estadioServiceStub.retrieve.called).toBeTruthy();
      expect(comp.estadios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      estadioServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeEstadio();
      await comp.$nextTick();

      // THEN
      expect(estadioServiceStub.delete.called).toBeTruthy();
      expect(estadioServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
