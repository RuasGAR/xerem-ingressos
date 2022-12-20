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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherSetor.setorEstadio')">setorEstadio</label>
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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherSetor.assentoEstadio')">assentoEstadio</label>
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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherSetor.nomeEstadio')" for="escolher-setor-nomeEstadio"
                >Nome Estadio</label
              >
              <input
                v-if="taskContext.processoIngresso.ingresso.nomeEstadio"
                readonly
                type="text"
                class="form-control"
                name="nomeEstadio"
                id="ingresso-nomeEstadio"
                data-cy="nomeEstadio"
                :value="taskContext.processoIngresso.ingresso.nomeEstadio.nome"
              />
              <input
                v-else
                readonly
                type="text"
                class="form-control"
                name="nomeEstadio"
                id="ingresso-nomeEstadio"
                data-cy="nomeEstadio"
                value=""
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
          :to="`/process-definition/ProcessoIngresso/task/EscolherSetor/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./escolher-setor-details.component.ts"></script>
