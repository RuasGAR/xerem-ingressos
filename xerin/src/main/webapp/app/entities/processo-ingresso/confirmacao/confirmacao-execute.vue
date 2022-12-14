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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.timeMandante')" for="confirmacao-timeMandante"
                >Time Mandante</label
              >
              <input
                type="text"
                class="form-control"
                name="timeMandante"
                id="confirmacao-timeMandante"
                readonly
                data-cy="timeMandante"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.timeMandante.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.timeMandante.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.timeMandante.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.timeVisitante')" for="confirmacao-timeVisitante"
                >Time Visitante</label
              >
              <input
                type="text"
                class="form-control"
                name="timeVisitante"
                id="confirmacao-timeVisitante"
                readonly
                data-cy="timeVisitante"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.timeVisitante.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.timeVisitante.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.timeVisitante.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.horarioJogo')" for="confirmacao-horarioJogo"
                >Horario Jogo</label
              >
              <input
                type="text"
                class="form-control"
                name="horarioJogo"
                id="confirmacao-horarioJogo"
                readonly
                data-cy="horarioJogo"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.horarioJogo.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.horarioJogo.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.horarioJogo.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.data')" for="confirmacao-data">Data</label>
              <b-input-group class="mb-3">
                <b-form-input
                  id="confirmacao-data"
                  readonly
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
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.nomeEstadio')" for="confirmacao-nomeEstadio"
                >Nome Estadio</label
              >
              <input
                type="text"
                class="form-control"
                name="nomeEstadio"
                id="confirmacao-nomeEstadio"
                readonly
                data-cy="nomeEstadio"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.nomeEstadio.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.nomeEstadio.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.nomeEstadio.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.setorEstadio')" for="confirmacao-setorEstadio"
                >Setor Estadio</label
              >
              <input
                type="text"
                class="form-control"
                name="setorEstadio"
                id="confirmacao-setorEstadio"
                readonly
                data-cy="setorEstadio"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.setorEstadio.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.setorEstadio.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.setorEstadio.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.assentoEstadio')" for="confirmacao-assentoEstadio"
                >Assento Estadio</label
              >
              <input
                type="text"
                class="form-control"
                name="assentoEstadio"
                id="confirmacao-assentoEstadio"
                readonly
                data-cy="assentoEstadio"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.assentoEstadio.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.assentoEstadio.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.assentoEstadio.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.nomeComprador')" for="confirmacao-nomeComprador"
                >Nome Comprador</label
              >
              <input
                type="text"
                class="form-control"
                name="nomeComprador"
                id="confirmacao-nomeComprador"
                readonly
                data-cy="nomeComprador"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.nomeComprador.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.nomeComprador.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.nomeComprador.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.cpfComprador')" for="confirmacao-cpfComprador"
                >Cpf Comprador</label
              >
              <input
                type="text"
                class="form-control"
                name="cpfComprador"
                id="confirmacao-cpfComprador"
                readonly
                data-cy="cpfComprador"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.cpfComprador.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.cpfComprador.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.cpfComprador.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('xeremIngressosApp.confirmacao.nascimentoComprador')"
                for="confirmacao-nascimentoComprador"
                >Nascimento Comprador</label
              >
              <b-input-group class="mb-3">
                <b-form-input
                  id="confirmacao-nascimentoComprador"
                  readonly
                  data-cy="nascimentoComprador"
                  type="text"
                  class="form-control"
                  name="nascimentoComprador"
                  :class="{
                    valid: !$v.taskContext.processoIngresso.ingresso.nascimentoComprador.$invalid,
                    invalid: $v.taskContext.processoIngresso.ingresso.nascimentoComprador.$invalid,
                  }"
                  v-model="$v.taskContext.processoIngresso.ingresso.nascimentoComprador.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.confirmacao.emailComprador')" for="confirmacao-emailComprador"
                >Email Comprador</label
              >
              <input
                type="text"
                class="form-control"
                name="emailComprador"
                id="confirmacao-emailComprador"
                readonly
                data-cy="emailComprador"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.emailComprador.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.emailComprador.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.emailComprador.$model"
              />
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

<script lang="ts" src="./confirmacao-execute.component.ts"></script>
