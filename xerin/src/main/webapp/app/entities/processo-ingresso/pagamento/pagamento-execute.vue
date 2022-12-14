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
              <label class="form-control-label" v-text="$t('xeremIngressosApp.pagamento.numeroCartao')" for="pagamento-numeroCartao"
                >Numero Cartao</label
              >
              <input
                type="number"
                class="form-control"
                name="numeroCartao"
                id="pagamento-numeroCartao"
                data-cy="numeroCartao"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.numeroCartao.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.numeroCartao.$invalid,
                }"
                v-model.number="$v.taskContext.processoIngresso.ingresso.numeroCartao.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.pagamento.validadeCartao')" for="pagamento-validadeCartao"
                >Validade Cartao</label
              >
              <input
                type="text"
                class="form-control"
                name="validadeCartao"
                id="pagamento-validadeCartao"
                data-cy="validadeCartao"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.validadeCartao.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.validadeCartao.$invalid,
                }"
                v-model="$v.taskContext.processoIngresso.ingresso.validadeCartao.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('xeremIngressosApp.pagamento.codigoCartao')" for="pagamento-codigoCartao"
                >Codigo Cartao</label
              >
              <input
                type="number"
                class="form-control"
                name="codigoCartao"
                id="pagamento-codigoCartao"
                data-cy="codigoCartao"
                :class="{
                  valid: !$v.taskContext.processoIngresso.ingresso.codigoCartao.$invalid,
                  invalid: $v.taskContext.processoIngresso.ingresso.codigoCartao.$invalid,
                }"
                v-model.number="$v.taskContext.processoIngresso.ingresso.codigoCartao.$model"
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

<script lang="ts" src="./pagamento-execute.component.ts"></script>
