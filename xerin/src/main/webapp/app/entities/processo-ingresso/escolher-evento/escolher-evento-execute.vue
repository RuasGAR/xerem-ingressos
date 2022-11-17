<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('xeremIngressosApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('xeremIngressosApp.escolherEvento.horarioJogo')"
                for="escolher-evento-horarioJogo"
                >Horario Jogo</label
              >
              <input
                type="text"
                class="form-control"
                name="horarioJogo"
                id="escolher-evento-horarioJogo"
                data-cy="horarioJogo"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.horarioJogo.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.horarioJogo.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.horarioJogo.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('xeremIngressosApp.escolherEvento.timeMandante')"
                for="escolher-evento-timeMandante"
                >Time Mandante</label
              >
              <input
                type="text"
                class="form-control"
                name="timeMandante"
                id="escolher-evento-timeMandante"
                data-cy="timeMandante"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.timeMandante.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.timeMandante.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.timeMandante.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('xeremIngressosApp.escolherEvento.timeVisitante')"
                for="escolher-evento-timeVisitante"
                >Time Visitante</label
              >
              <input
                type="text"
                class="form-control"
                name="timeVisitante"
                id="escolher-evento-timeVisitante"
                data-cy="timeVisitante"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.timeVisitante.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.timeVisitante.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.timeVisitante.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherEvento.data')" for="escolher-evento-data">Data</label>
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="escolher-evento-data"
                    v-model="$v.taskContext.processoIngresso.ingresso.data.$model"
                    name="data"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="escolher-evento-data"
                  data-cy="data"
                  type="text"
                  class="form-control"
                  name="data"
                  :class="{
                    valid: !$v.taskContext.processoIngresso.ingresso.data.$invalid,
                    invalid: $v.taskContext.processoIngresso.ingresso.data.$invalid,
                  }"
                  v-model="$v.taskContext.processoIngresso.ingresso.data.$model"
                />
              </b-input-group>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./escolher-evento-execute.component.ts"></script>
