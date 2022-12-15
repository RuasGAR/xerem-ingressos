<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="estadio">
        <h2 class="jh-entity-heading" data-cy="estadioDetailsHeading">
          <span v-text="$t('xeremIngressosApp.estadio.detail.title')">Estadio</span> {{ estadio.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('xeremIngressosApp.estadio.nome')">Nome</span>
          </dt>
          <dd>
            <span>{{ estadio.nome }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="estadio.id" :to="{ name: 'EstadioEdit', params: { estadioId: estadio.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./estadio-details.component.ts"></script>
