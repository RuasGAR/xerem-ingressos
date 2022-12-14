<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="xeremIngressosApp.ingressoStartForm.home.createOrEditLabel"
          data-cy="IngressoStartFormCreateUpdateHeading"
          v-text="$t('xeremIngressosApp.ingressoStartForm.home.createOrEditLabel')"
        >
          Create or edit a IngressoStartForm
        </h2>
        <div v-if="processoIngresso.processInstance">
          <akip-show-process-definition :processDefinition="processoIngresso.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="processoIngresso.ingresso">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('xeremIngressosApp.ingressoStartForm.nomeComprador')"
                    for="ingresso-start-form-nomeComprador"
                    >Nome Comprador</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="nomeComprador"
                    id="ingresso-start-form-nomeComprador"
                    data-cy="nomeComprador"
                    :class="{
                      valid: !$v.processoIngresso.ingresso.nomeComprador.$invalid,
                      invalid: $v.processoIngresso.ingresso.nomeComprador.$invalid,
                    }"
                    v-model="$v.processoIngresso.ingresso.nomeComprador.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('xeremIngressosApp.ingressoStartForm.cpfComprador')"
                    for="ingresso-start-form-cpfComprador"
                    >Cpf Comprador</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="cpfComprador"
                    id="ingresso-start-form-cpfComprador"
                    data-cy="cpfComprador"
                    :class="{
                      valid: !$v.processoIngresso.ingresso.cpfComprador.$invalid,
                      invalid: $v.processoIngresso.ingresso.cpfComprador.$invalid,
                    }"
                    v-model="$v.processoIngresso.ingresso.cpfComprador.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('xeremIngressosApp.ingressoStartForm.nascimentoComprador')"
                    for="ingresso-start-form-nascimentoComprador"
                    >Nascimento Comprador</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="ingresso-start-form-nascimentoComprador"
                        v-model="$v.processoIngresso.ingresso.nascimentoComprador.$model"
                        name="nascimentoComprador"
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
                      id="ingresso-start-form-nascimentoComprador"
                      data-cy="nascimentoComprador"
                      type="text"
                      class="form-control"
                      name="nascimentoComprador"
                      :class="{
                        valid: !$v.processoIngresso.ingresso.nascimentoComprador.$invalid,
                        invalid: $v.processoIngresso.ingresso.nascimentoComprador.$invalid,
                      }"
                      v-model="$v.processoIngresso.ingresso.nascimentoComprador.$model"
                    />
                  </b-input-group>
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('xeremIngressosApp.ingressoStartForm.emailComprador')"
                    for="ingresso-start-form-emailComprador"
                    >Email Comprador</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="emailComprador"
                    id="ingresso-start-form-emailComprador"
                    data-cy="emailComprador"
                    :class="{
                      valid: !$v.processoIngresso.ingresso.emailComprador.$invalid,
                      invalid: $v.processoIngresso.ingresso.emailComprador.$invalid,
                    }"
                    v-model="$v.processoIngresso.ingresso.emailComprador.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.processoIngresso.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ingresso-start-form-init.component.ts"></script>
