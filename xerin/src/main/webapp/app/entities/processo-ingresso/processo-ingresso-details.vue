<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="processoIngresso.processInstance">
        <h2 class="jh-entity-heading" data-cy="processoIngressoDetailsHeading">
          <span v-text="$t('xeremIngressosApp.processoIngresso.detail.title')">ProcessoIngresso</span> {{ processoIngresso.id }}
        </h2>
        <akip-show-process-instance :processInstance="processoIngresso.processInstance">
          <template v-slot:title>
            <span v-text="$t('xeremIngressosApp.processoIngresso.detail.title')">ProcessoIngresso</span> #{{ processoIngresso.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="processoIngresso.ingresso">
              <h5 class="card-header">
                <span class="title"> Ingresso #{{ processoIngresso.ingresso.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.horarioJogo')">horarioJogo</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="horarioJogo"
                    id="ingresso-horarioJogo"
                    data-cy="horarioJogo"
                    v-model="processoIngresso.ingresso.horarioJogo"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.timeMandante')">timeMandante</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="timeMandante"
                    id="ingresso-timeMandante"
                    data-cy="timeMandante"
                    v-model="processoIngresso.ingresso.timeMandante"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.timeVisitante')">timeVisitante</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="timeVisitante"
                    id="ingresso-timeVisitante"
                    data-cy="timeVisitante"
                    v-model="processoIngresso.ingresso.timeVisitante"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.data')">data</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="data"
                    id="ingresso-data"
                    data-cy="data"
                    v-model="processoIngresso.ingresso.data"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.setorEstadio')">setorEstadio</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="setorEstadio"
                    id="ingresso-setorEstadio"
                    data-cy="setorEstadio"
                    v-model="processoIngresso.ingresso.setorEstadio"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.assentoEstadio')">assentoEstadio</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="assentoEstadio"
                    id="ingresso-assentoEstadio"
                    data-cy="assentoEstadio"
                    v-model="processoIngresso.ingresso.assentoEstadio"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.nomeComprador')">nomeComprador</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="nomeComprador"
                    id="ingresso-nomeComprador"
                    data-cy="nomeComprador"
                    v-model="processoIngresso.ingresso.nomeComprador"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.cpfComprador')">cpfComprador</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cpfComprador"
                    id="ingresso-cpfComprador"
                    data-cy="cpfComprador"
                    v-model="processoIngresso.ingresso.cpfComprador"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.nascimentoComprador')"
                    >nascimentoComprador</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="nascimentoComprador"
                    id="ingresso-nascimentoComprador"
                    data-cy="nascimentoComprador"
                    v-model="processoIngresso.ingresso.nascimentoComprador"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.numeroCartao')">numeroCartao</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="numeroCartao"
                    id="ingresso-numeroCartao"
                    data-cy="numeroCartao"
                    v-model="processoIngresso.ingresso.numeroCartao"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.validadeCartao')">validadeCartao</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="validadeCartao"
                    id="ingresso-validadeCartao"
                    data-cy="validadeCartao"
                    v-model="processoIngresso.ingresso.validadeCartao"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.codigoCartao')">codigoCartao</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="codigoCartao"
                    id="ingresso-codigoCartao"
                    data-cy="codigoCartao"
                    v-model="processoIngresso.ingresso.codigoCartao"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('xeremIngressosApp.processoIngresso.emailComprador')">emailComprador</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="emailComprador"
                    id="ingresso-emailComprador"
                    data-cy="emailComprador"
                    v-model="processoIngresso.ingresso.emailComprador"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('xeremIngressosApp.processoIngresso.nomeEstadio')"
                    for="processo-ingresso-nomeEstadio"
                    >Nome Estadio</label
                  >
                  <input
                    v-if="processoIngresso.ingresso.nomeEstadio"
                    readonly
                    type="text"
                    class="form-control"
                    name="nomeEstadio"
                    id="ingresso-nomeEstadio"
                    data-cy="nomeEstadio"
                    :value="processoIngresso.ingresso.nomeEstadio.nome"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="nomeEstadio"
                    id="ingresso-nomeEstadio"
                    data-cy="nomeEstadio"
                    value=""
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./processo-ingresso-details.component.ts"></script>
