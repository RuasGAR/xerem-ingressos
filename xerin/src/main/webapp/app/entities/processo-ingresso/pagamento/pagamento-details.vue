<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('xeremIngressosApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.pagamento.numeroCartao')">numeroCartao</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="numeroCartao"
                id="ingresso-numeroCartao"
                data-cy="numeroCartao"
                v-model="taskContext.processoIngresso.ingresso.numeroCartao"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.pagamento.validadeCartao')">validadeCartao</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="validadeCartao"
                id="ingresso-validadeCartao"
                data-cy="validadeCartao"
                v-model="taskContext.processoIngresso.ingresso.validadeCartao"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.pagamento.codigoCartao')">codigoCartao</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="codigoCartao"
                id="ingresso-codigoCartao"
                data-cy="codigoCartao"
                v-model="taskContext.processoIngresso.ingresso.codigoCartao"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/ProcessoIngresso/task/Pagamento/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./pagamento-details.component.ts"></script>
