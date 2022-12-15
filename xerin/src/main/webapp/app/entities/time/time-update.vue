<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="xeremIngressosApp.time.home.createOrEditLabel"
          data-cy="TimeCreateUpdateHeading"
          v-text="$t('xeremIngressosApp.time.home.createOrEditLabel')"
        >
          Create or edit a Time
        </h2>
        <div>
          <div class="form-group" v-if="time.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="time.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('xeremIngressosApp.time.nome')" for="time-nome">Nome</label>
            <input
              type="text"
              class="form-control"
              name="nome"
              id="time-nome"
              data-cy="nome"
              :class="{ valid: !$v.time.nome.$invalid, invalid: $v.time.nome.$invalid }"
              v-model="$v.time.nome.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('xeremIngressosApp.time.nomeCasa')" for="time-nomeCasa">Nome Casa</label>
            <input
              type="text"
              class="form-control"
              name="nomeCasa"
              id="time-nomeCasa"
              data-cy="nomeCasa"
              :class="{ valid: !$v.time.nomeCasa.$invalid, invalid: $v.time.nomeCasa.$invalid }"
              v-model="$v.time.nomeCasa.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('xeremIngressosApp.time.cidade')" for="time-cidade">Cidade</label>
            <input
              type="text"
              class="form-control"
              name="cidade"
              id="time-cidade"
              data-cy="cidade"
              :class="{ valid: !$v.time.cidade.$invalid, invalid: $v.time.cidade.$invalid }"
              v-model="$v.time.cidade.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.time.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./time-update.component.ts"></script>
