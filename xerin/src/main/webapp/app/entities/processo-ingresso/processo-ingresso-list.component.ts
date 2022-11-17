import { Component, Vue, Inject } from 'vue-property-decorator';
import { IProcessoIngresso } from '@/shared/model/processo-ingresso.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ProcessoIngressoService from './processo-ingresso.service';

@Component
export default class ProcessoIngressoListComponent extends Vue {
  @Inject('processoIngressoService') private processoIngressoService: () => ProcessoIngressoService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ProcessoIngresso';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public processoIngressoList: IProcessoIngresso[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.processoIngressoService()
      .retrieve()
      .then(
        res => {
          this.processoIngressoList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
