/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import EstadioUpdateComponent from '@/entities/estadio/estadio-update.vue';
import EstadioClass from '@/entities/estadio/estadio-update.component';
import EstadioService from '@/entities/estadio/estadio.service';

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
  describe('Estadio Management Update Component', () => {
    let wrapper: Wrapper<EstadioClass>;
    let comp: EstadioClass;
    let estadioServiceStub: SinonStubbedInstance<EstadioService>;

    beforeEach(() => {
      estadioServiceStub = sinon.createStubInstance<EstadioService>(EstadioService);

      wrapper = shallowMount<EstadioClass>(EstadioUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          estadioService: () => estadioServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.estadio = entity;
        estadioServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(estadioServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.estadio = entity;
        estadioServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(estadioServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundEstadio = { id: 123 };
        estadioServiceStub.find.resolves(foundEstadio);
        estadioServiceStub.retrieve.resolves([foundEstadio]);

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
