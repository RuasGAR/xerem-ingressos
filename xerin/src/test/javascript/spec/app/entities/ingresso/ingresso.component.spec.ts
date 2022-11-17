/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import IngressoComponent from '@/entities/ingresso/ingresso.vue';
import IngressoClass from '@/entities/ingresso/ingresso.component';
import IngressoService from '@/entities/ingresso/ingresso.service';

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
  describe('Ingresso Management Component', () => {
    let wrapper: Wrapper<IngressoClass>;
    let comp: IngressoClass;
    let ingressoServiceStub: SinonStubbedInstance<IngressoService>;

    beforeEach(() => {
      ingressoServiceStub = sinon.createStubInstance<IngressoService>(IngressoService);
      ingressoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<IngressoClass>(IngressoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          ingressoService: () => ingressoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      ingressoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllIngressos();
      await comp.$nextTick();

      // THEN
      expect(ingressoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.ingressos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
