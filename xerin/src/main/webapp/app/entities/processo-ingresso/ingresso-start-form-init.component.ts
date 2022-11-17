import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProcessoIngresso, ProcessoIngresso } from '@/shared/model/processo-ingresso.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Ingresso } from '@/shared/model/ingresso.model';
import ProcessoIngressoService from './processo-ingresso.service';

const validations: any = {
  processoIngresso: {
    ingresso: {
      nomeComprador: {},
      cpfComprador: {},
      nascimentoComprador: {},
    },
  },
};

@Component({
  validations,
})
export default class IngressoStartFormInitComponent extends Vue {
  @Inject('processoIngressoService') private processoIngressoService: () => ProcessoIngressoService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ProcessoIngresso';
  public processoIngresso: IProcessoIngresso = new ProcessoIngresso();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initIngressoStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.processoIngressoService()
      .create(this.processoIngresso)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('xeremIngressosApp.ingressoStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initIngressoStartForm(): void {
    this.processoIngresso.ingresso = new Ingresso();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.processoIngresso.processInstance = new ProcessInstance();
      this.processoIngresso.processInstance.processDefinition = res;
    });
  }
}
