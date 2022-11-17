<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="processoIngressoDetailsHeading">
      <span v-text="$t('xeremIngressosApp.processoIngresso.home.title')">ProcessoIngresso</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('xeremIngressosApp.processoIngresso.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/ProcessoIngresso/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('xeremIngressosApp.processoIngresso.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && processoIngressoList && processoIngressoList.length === 0">
      <span v-text="$t('xeremIngressosApp.processoIngresso.home.notFound')">No processoIngresso found</span>
    </div>
    <div class="table-responsive" v-if="processoIngressoList && processoIngressoList.length > 0">
      <table class="table table-striped" aria-describedby="processoIngressoList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span>Process Instance</span></th>
            <th scope="row"><span>Ingresso</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Start Date</span></th>
            <th scope="row"><span>End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="processoIngresso in processoIngressoList" :key="processoIngresso.id" data-cy="entityTable">
            <td>{{ processoIngresso.id }}</td>
            <td>
              <div v-if="processoIngresso.processInstance">
                <router-link :to="`/process-definition/ProcessoIngresso/instance/${processoIngresso.processInstance.id}/view`">
                  {{ processoIngresso.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="processoIngresso.ingresso">
                <router-link :to="{ name: 'IngressoView', params: { ingressoId: processoIngresso.ingresso.id } }">{{
                  processoIngresso.ingresso.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="processoIngresso.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>
              {{ processoIngresso.processInstance.startDate ? $d(Date.parse(processoIngresso.processInstance.startDate), 'short') : '' }}
            </td>
            <td>{{ processoIngresso.processInstance.endDate ? $d(Date.parse(processoIngresso.processInstance.endDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/ProcessoIngresso/instance/${processoIngresso.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./processo-ingresso-list.component.ts"></script>
