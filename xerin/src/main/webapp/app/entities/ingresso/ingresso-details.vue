<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="ingresso">
        <h2 class="jh-entity-heading" data-cy="ingressoDetailsHeading">
          <span v-text="$t('xeremIngressosApp.ingresso.detail.title')">Ingresso</span> {{ ingresso.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.horarioJogo')">Horario Jogo</span>
          </dt>
          <dd>
            <span>{{ ingresso.horarioJogo }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.timeMandante')">Time Mandante</span>
          </dt>
          <dd>
            <span>{{ ingresso.timeMandante }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.timeVisitante')">Time Visitante</span>
          </dt>
          <dd>
            <span>{{ ingresso.timeVisitante }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.data')">Data</span>
          </dt>
          <dd>
            <span>{{ ingresso.data }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.nomeEstadio')">Nome Estadio</span>
          </dt>
          <dd>
            <span>{{ ingresso.nomeEstadio }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.setorEstadio')">Setor Estadio</span>
          </dt>
          <dd>
            <span>{{ ingresso.setorEstadio }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.assentoEstadio')">Assento Estadio</span>
          </dt>
          <dd>
            <span>{{ ingresso.assentoEstadio }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.nomeComprador')">Nome Comprador</span>
          </dt>
          <dd>
            <span>{{ ingresso.nomeComprador }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.cpfComprador')">Cpf Comprador</span>
          </dt>
          <dd>
            <span>{{ ingresso.cpfComprador }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.nascimentoComprador')">Nascimento Comprador</span>
          </dt>
          <dd>
            <span>{{ ingresso.nascimentoComprador }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.numeroCartao')">Numero Cartao</span>
          </dt>
          <dd>
            <span>{{ ingresso.numeroCartao }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.validadeCartao')">Validade Cartao</span>
          </dt>
          <dd>
            <span>{{ ingresso.validadeCartao }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.codigoCartao')">Codigo Cartao</span>
          </dt>
          <dd>
            <span>{{ ingresso.codigoCartao }}</span>
          </dd>
          <dt>
            <span v-text="$t('xeremIngressosApp.ingresso.emailComprador')">Email Comprador</span>
          </dt>
          <dd>
            <span>{{ ingresso.emailComprador }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ingresso-details.component.ts"></script>
