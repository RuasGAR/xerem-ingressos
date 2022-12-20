<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="xeremIngressosApp.estadio.home.createOrEditLabel"
          data-cy="EstadioCreateUpdateHeading"
          v-text="$t('xeremIngressosApp.estadio.home.createOrEditLabel')"
        >
          Create or edit a Estadio
        </h2>
        <div>
          <div class="form-group" v-if="estadio.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="estadio.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('xeremIngressosApp.estadio.nome')" for="estadio-nome">Nome</label>
            <input
              type="text"
              class="form-control"
              name="nome"
              id="estadio-nome"
              data-cy="nome"
              :class="{ valid: !$v.estadio.nome.$invalid, invalid: $v.estadio.nome.$invalid }"
              v-model="$v.estadio.nome.$model"
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
            :disabled="$v.estadio.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./estadio-update.component.ts"></script>
