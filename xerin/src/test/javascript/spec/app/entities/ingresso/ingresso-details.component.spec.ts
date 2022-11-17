/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import IngressoDetailComponent from '@/entities/ingresso/ingresso-details.vue';
import IngressoClass from '@/entities/ingresso/ingresso-details.component';
import IngressoService from '@/entities/ingresso/ingresso.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Ingresso Management Detail Component', () => {
    let wrapper: Wrapper<IngressoClass>;
    let comp: IngressoClass;
    let ingressoServiceStub: SinonStubbedInstance<IngressoService>;

    beforeEach(() => {
      ingressoServiceStub = sinon.createStubInstance<IngressoService>(IngressoService);

      wrapper = shallowMount<IngressoClass>(IngressoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { ingressoService: () => ingressoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundIngresso = { id: 123 };
        ingressoServiceStub.find.resolves(foundIngresso);

        // WHEN
        comp.retrieveIngresso(123);
        await comp.$nextTick();

        // THEN
        expect(comp.ingresso).toBe(foundIngresso);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundIngresso = { id: 123 };
        ingressoServiceStub.find.resolves(foundIngresso);

        // WHEN
        comp.beforeRouteEnter({ params: { ingressoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.ingresso).toBe(foundIngresso);
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
