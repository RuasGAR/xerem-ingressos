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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherEvento.horarioJogo')">horarioJogo</label>
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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherEvento.timeMandante')">timeMandante</label>
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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherEvento.timeVisitante')">timeVisitante</label>
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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherEvento.data')">data</label>
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
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/ProcessoIngresso/task/EscolherEvento/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./escolher-evento-details.component.ts"></script>
