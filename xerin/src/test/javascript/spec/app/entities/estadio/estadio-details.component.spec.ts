/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import EstadioDetailComponent from '@/entities/estadio/estadio-details.vue';
import EstadioClass from '@/entities/estadio/estadio-details.component';
import EstadioService from '@/entities/estadio/estadio.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Estadio Management Detail Component', () => {
    let wrapper: Wrapper<EstadioClass>;
    let comp: EstadioClass;
    let estadioServiceStub: SinonStubbedInstance<EstadioService>;

    beforeEach(() => {
      estadioServiceStub = sinon.createStubInstance<EstadioService>(EstadioService);

      wrapper = shallowMount<EstadioClass>(EstadioDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { estadioService: () => estadioServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundEstadio = { id: 123 };
        estadioServiceStub.find.resolves(foundEstadio);

        // WHEN
        comp.retrieveEstadio(123);
        await comp.$nextTick();

        // THEN
        expect(comp.estadio).toBe(foundEstadio);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundEstadio = { id: 123 };
        estadioServiceStub.find.resolves(foundEstadio);

        // WHEN
        comp.beforeRouteEnter({ params: { estadioId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.estadio).toBe(foundEstadio);
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
