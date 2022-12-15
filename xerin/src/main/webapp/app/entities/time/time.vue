<template>
  <div>
    <h2 id="page-heading" data-cy="TimeHeading">
      <span v-text="$t('xeremIngressosApp.time.home.title')" id="time-heading">Times</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('xeremIngressosApp.time.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TimeCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-time">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('xeremIngressosApp.time.home.createLabel')"> Create a new Time </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && times && times.length === 0">
      <span v-text="$t('xeremIngressosApp.time.home.notFound')">No times found</span>
    </div>
    <div class="table-responsive" v-if="times && times.length > 0">
      <table class="table table-striped" aria-describedby="times">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.time.nome')">Nome</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.time.nomeCasa')">Nome Casa</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.time.cidade')">Cidade</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="time in times" :key="time.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TimeView', params: { timeId: time.id } }">{{ time.id }}</router-link>
            </td>
            <td>{{ time.nome }}</td>
            <td>{{ time.nomeCasa }}</td>
            <td>{{ time.cidade }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TimeView', params: { timeId: time.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TimeEdit', params: { timeId: time.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(time)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="xeremIngressosApp.time.delete.question" data-cy="timeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-time-heading" v-text="$t('xeremIngressosApp.time.delete.question', { id: removeId })">
          Are you sure you want to delete this Time?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-time"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTime()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./time.component.ts"></script>
