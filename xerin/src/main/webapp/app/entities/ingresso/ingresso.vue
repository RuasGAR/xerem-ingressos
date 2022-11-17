<template>
  <div>
    <h2 id="page-heading" data-cy="IngressoHeading">
      <span v-text="$t('xeremIngressosApp.ingresso.home.title')" id="ingresso-heading">Ingressos</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('xeremIngressosApp.ingresso.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && ingressos && ingressos.length === 0">
      <span v-text="$t('xeremIngressosApp.ingresso.home.notFound')">No ingressos found</span>
    </div>
    <div class="table-responsive" v-if="ingressos && ingressos.length > 0">
      <table class="table table-striped" aria-describedby="ingressos">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.horarioJogo')">Horario Jogo</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.timeMandante')">Time Mandante</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.timeVisitante')">Time Visitante</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.data')">Data</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.nomeEstadio')">Nome Estadio</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.setorEstadio')">Setor Estadio</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.assentoEstadio')">Assento Estadio</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.nomeComprador')">Nome Comprador</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.cpfComprador')">Cpf Comprador</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.nascimentoComprador')">Nascimento Comprador</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.numeroCartao')">Numero Cartao</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.validadeCartao')">Validade Cartao</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.codigoCartao')">Codigo Cartao</span></th>
            <th scope="row"><span v-text="$t('xeremIngressosApp.ingresso.quantidadeDeIngressos')">Quantidade De Ingressos</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="ingresso in ingressos" :key="ingresso.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'IngressoView', params: { ingressoId: ingresso.id } }">{{ ingresso.id }}</router-link>
            </td>
            <td>{{ ingresso.horarioJogo }}</td>
            <td>{{ ingresso.timeMandante }}</td>
            <td>{{ ingresso.timeVisitante }}</td>
            <td>{{ ingresso.data }}</td>
            <td>{{ ingresso.nomeEstadio }}</td>
            <td>{{ ingresso.setorEstadio }}</td>
            <td>{{ ingresso.assentoEstadio }}</td>
            <td>{{ ingresso.nomeComprador }}</td>
            <td>{{ ingresso.cpfComprador }}</td>
            <td>{{ ingresso.nascimentoComprador }}</td>
            <td>{{ ingresso.numeroCartao }}</td>
            <td>{{ ingresso.validadeCartao }}</td>
            <td>{{ ingresso.codigoCartao }}</td>
            <td>{{ ingresso.quantidadeDeIngressos }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'IngressoView', params: { ingressoId: ingresso.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="xeremIngressosApp.ingresso.delete.question" data-cy="ingressoDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-ingresso-heading" v-text="$t('xeremIngressosApp.ingresso.delete.question', { id: removeId })">
          Are you sure you want to delete this Ingresso?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-ingresso"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeIngresso()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./ingresso.component.ts"></script>
