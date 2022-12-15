<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="time">
        <h2 class="jh-entity-heading" data-cy="timeDetailsHeading">
          <span v-text="$t('xeremIngressosApp.time.detail.title')">Time</span> {{ time.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('xeremIngressosApp.time.nome')">Nome</span>
          </dt>
          <dd>
            <span>{{ time.nome }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.time.nomeCasa')">Nome Casa</span>
          </dt>
          <dd>
            <span>{{ time.nomeCasa }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.time.cidade')">Cidade</span>
          </dt>
          <dd>
            <span>{{ time.cidade }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="time.id" :to="{ name: 'TimeEdit', params: { timeId: time.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./time-details.component.ts"></script>
