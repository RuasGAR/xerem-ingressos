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
                v-text="$t('xeremIngressosApp.escolherSetor.setorEstadio')"
                for="escolher-setor-setorEstadio"
                >Setor Estadio</label
              >
              <input
                type="text"
                class="form-control"
                name="setorEstadio"
                id="escolher-setor-setorEstadio"
                data-cy="setorEstadio"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.setorEstadio.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.setorEstadio.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.setorEstadio.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('xeremIngressosApp.escolherSetor.assentoEstadio')"
                for="escolher-setor-assentoEstadio"
                >Assento Estadio</label
              >
              <input
                type="text"
                class="form-control"
                name="assentoEstadio"
                id="escolher-setor-assentoEstadio"
                data-cy="assentoEstadio"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.assentoEstadio.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.assentoEstadio.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.assentoEstadio.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.escolherSetor.nomeEstadio')" for="escolher-setor-nomeEstadio"
                >Nome Estadio</label
              >
              <select
                class="form-control"
                id="escolher-setor-nomeEstadio"
                data-cy="nomeEstadio"
                name="nomeEstadio"
                v-model="taskContext.processoIngresso.ingresso.nomeEstadio"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.processoIngresso.ingresso.nomeEstadio &&
                    EstadioOption.id === taskContext.processoIngresso.ingresso.nomeEstadio.id
                      ? taskContext.processoIngresso.ingresso.nomeEstadio
                      : EstadioOption
                  "
                  v-for="EstadioOption in Estadios"
                  :key="EstadioOption.id"
                >
                  {{ EstadioOption.nome }}
                </option>
              </select>
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

<script lang="ts" src="./escolher-setor-execute.component.ts"></script>
