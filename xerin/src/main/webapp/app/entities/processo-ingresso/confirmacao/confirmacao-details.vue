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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.timeMandante')">timeMandante</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="timeMandante"
                id="ingresso-timeMandante"
                data-cy="timeMandante"
                v-model="taskContext.processoIngresso.ingresso.timeMandante"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.timeVisitante')">timeVisitante</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="timeVisitante"
                id="ingresso-timeVisitante"
                data-cy="timeVisitante"
                v-model="taskContext.processoIngresso.ingresso.timeVisitante"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.horarioJogo')">horarioJogo</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="horarioJogo"
                id="ingresso-horarioJogo"
                data-cy="horarioJogo"
                v-model="taskContext.processoIngresso.ingresso.horarioJogo"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.data')">data</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="data"
                id="ingresso-data"
                data-cy="data"
                v-model="taskContext.processoIngresso.ingresso.data"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.nomeEstadio')">nomeEstadio</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="nomeEstadio"
                id="ingresso-nomeEstadio"
                data-cy="nomeEstadio"
                v-model="taskContext.processoIngresso.ingresso.nomeEstadio"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.setorEstadio')">setorEstadio</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="setorEstadio"
                id="ingresso-setorEstadio"
                data-cy="setorEstadio"
                v-model="taskContext.processoIngresso.ingresso.setorEstadio"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.assentoEstadio')">assentoEstadio</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="assentoEstadio"
                id="ingresso-assentoEstadio"
                data-cy="assentoEstadio"
                v-model="taskContext.processoIngresso.ingresso.assentoEstadio"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.nomeComprador')">nomeComprador</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="nomeComprador"
                id="ingresso-nomeComprador"
                data-cy="nomeComprador"
                v-model="taskContext.processoIngresso.ingresso.nomeComprador"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.cpfComprador')">cpfComprador</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cpfComprador"
                id="ingresso-cpfComprador"
                data-cy="cpfComprador"
                v-model="taskContext.processoIngresso.ingresso.cpfComprador"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.nascimentoComprador')">nascimentoComprador</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="nascimentoComprador"
                id="ingresso-nascimentoComprador"
                data-cy="nascimentoComprador"
                v-model="taskContext.processoIngresso.ingresso.nascimentoComprador"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.emailComprador')">emailComprador</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="emailComprador"
                id="ingresso-emailComprador"
                data-cy="emailComprador"
                v-model="taskContext.processoIngresso.ingresso.emailComprador"
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
          :to="`/process-definition/ProcessoIngresso/task/Confirmacao/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./confirmacao-details.component.ts"></script>
