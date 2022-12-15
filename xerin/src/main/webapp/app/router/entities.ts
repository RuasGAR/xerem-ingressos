import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Ingresso = () => import('@/entities/ingresso/ingresso.vue');
// prettier-ignore
const IngressoDetails = () => import('@/entities/ingresso/ingresso-details.vue');
// prettier-ignore
const ProcessoIngressoDetails = () => import('@/entities/processo-ingresso/processo-ingresso-details.vue');
// prettier-ignore
const ProcessoIngressoList = () => import('@/entities/processo-ingresso/processo-ingresso-list.vue');
// prettier-ignore
const IngressoStartFormInit = () => import('@/entities/processo-ingresso/ingresso-start-form-init.vue');
// prettier-ignore
const ProcessoIngresso_EscolherEventoDetails = () => import('@/entities/processo-ingresso/escolher-evento/escolher-evento-details.vue');
// prettier-ignore
const ProcessoIngresso_EscolherEventoExecute = () => import('@/entities/processo-ingresso/escolher-evento/escolher-evento-execute.vue');
// prettier-ignore
const ProcessoIngresso_EscolherSetorDetails = () => import('@/entities/processo-ingresso/escolher-setor/escolher-setor-details.vue');
// prettier-ignore
const ProcessoIngresso_EscolherSetorExecute = () => import('@/entities/processo-ingresso/escolher-setor/escolher-setor-execute.vue');
// prettier-ignore
const ProcessoIngresso_PagamentoDetails = () => import('@/entities/processo-ingresso/pagamento/pagamento-details.vue');
// prettier-ignore
const ProcessoIngresso_PagamentoExecute = () => import('@/entities/processo-ingresso/pagamento/pagamento-execute.vue');
// prettier-ignore
const ProcessoIngresso_ConfirmacaoDetails = () => import('@/entities/processo-ingresso/confirmacao/confirmacao-details.vue');
// prettier-ignore
const ProcessoIngresso_ConfirmacaoExecute = () => import('@/entities/processo-ingresso/confirmacao/confirmacao-execute.vue');
// prettier-ignore
const Time = () => import('@/entities/time/time.vue');
// prettier-ignore
const TimeUpdate = () => import('@/entities/time/time-update.vue');
// prettier-ignore
const TimeDetails = () => import('@/entities/time/time-details.vue');
// prettier-ignore
const Estadio = () => import('@/entities/estadio/estadio.vue');
// prettier-ignore
const EstadioUpdate = () => import('@/entities/estadio/estadio-update.vue');
// prettier-ignore
const EstadioDetails = () => import('@/entities/estadio/estadio-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/ingresso',
    name: 'Ingresso',
    component: Ingresso,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/ingresso/:ingressoId/view',
    name: 'IngressoView',
    component: IngressoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/instance/:processInstanceId/view',
    name: 'ProcessoIngressoView',
    component: ProcessoIngressoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/instances',
    name: 'ProcessoIngressoList',
    component: ProcessoIngressoList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/init',
    name: 'IngressoStartFormInit',
    component: IngressoStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/EscolherEvento/:taskInstanceId/view',
    name: 'ProcessoIngresso_EscolherEventoDetails',
    component: ProcessoIngresso_EscolherEventoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/EscolherEvento/:taskInstanceId/execute',
    name: 'ProcessoIngresso_EscolherEventoExecute',
    component: ProcessoIngresso_EscolherEventoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/EscolherSetor/:taskInstanceId/view',
    name: 'ProcessoIngresso_EscolherSetorDetails',
    component: ProcessoIngresso_EscolherSetorDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/EscolherSetor/:taskInstanceId/execute',
    name: 'ProcessoIngresso_EscolherSetorExecute',
    component: ProcessoIngresso_EscolherSetorExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/Pagamento/:taskInstanceId/view',
    name: 'ProcessoIngresso_PagamentoDetails',
    component: ProcessoIngresso_PagamentoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/Pagamento/:taskInstanceId/execute',
    name: 'ProcessoIngresso_PagamentoExecute',
    component: ProcessoIngresso_PagamentoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/Confirmacao/:taskInstanceId/view',
    name: 'ProcessoIngresso_ConfirmacaoDetails',
    component: ProcessoIngresso_ConfirmacaoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ProcessoIngresso/task/Confirmacao/:taskInstanceId/execute',
    name: 'ProcessoIngresso_ConfirmacaoExecute',
    component: ProcessoIngresso_ConfirmacaoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/time',
    name: 'Time',
    component: Time,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/time/new',
    name: 'TimeCreate',
    component: TimeUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/time/:timeId/edit',
    name: 'TimeEdit',
    component: TimeUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/time/:timeId/view',
    name: 'TimeView',
    component: TimeDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/estadio',
    name: 'Estadio',
    component: Estadio,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/estadio/new',
    name: 'EstadioCreate',
    component: EstadioUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/estadio/:estadioId/edit',
    name: 'EstadioEdit',
    component: EstadioUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/estadio/:estadioId/view',
    name: 'EstadioView',
    component: EstadioDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
