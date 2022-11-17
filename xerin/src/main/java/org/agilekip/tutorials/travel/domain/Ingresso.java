package org.agilekip.tutorials.travel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ingresso.
 */
@Entity
@Table(name = "ingresso")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ingresso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "horario_jogo")
    private String horarioJogo;

    @Column(name = "time_mandante")
    private String timeMandante;

    @Column(name = "time_visitante")
    private String timeVisitante;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "nome_estadio")
    private String nomeEstadio;

    @Column(name = "setor_estadio")
    private String setorEstadio;

    @Column(name = "assento_estadio")
    private String assentoEstadio;

    @Column(name = "nome_comprador")
    private String nomeComprador;

    @Column(name = "cpf_comprador")
    private String cpfComprador;

    @Column(name = "nascimento_comprador")
    private LocalDate nascimentoComprador;

    @Column(name = "numero_cartao")
    private Integer numeroCartao;

    @Column(name = "validade_cartao")
    private String validadeCartao;

    @Column(name = "codigo_cartao")
    private Integer codigoCartao;

    @Column(name = "quantidade_de_ingressos")
    private String quantidadeDeIngressos;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingresso id(Long id) {
        this.id = id;
        return this;
    }

    public String getHorarioJogo() {
        return this.horarioJogo;
    }

    public Ingresso horarioJogo(String horarioJogo) {
        this.horarioJogo = horarioJogo;
        return this;
    }

    public void setHorarioJogo(String horarioJogo) {
        this.horarioJogo = horarioJogo;
    }

    public String getTimeMandante() {
        return this.timeMandante;
    }

    public Ingresso timeMandante(String timeMandante) {
        this.timeMandante = timeMandante;
        return this;
    }

    public void setTimeMandante(String timeMandante) {
        this.timeMandante = timeMandante;
    }

    public String getTimeVisitante() {
        return this.timeVisitante;
    }

    public Ingresso timeVisitante(String timeVisitante) {
        this.timeVisitante = timeVisitante;
        return this;
    }

    public void setTimeVisitante(String timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public LocalDate getData() {
        return this.data;
    }

    public Ingresso data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeEstadio() {
        return this.nomeEstadio;
    }

    public Ingresso nomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
        return this;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public String getSetorEstadio() {
        return this.setorEstadio;
    }

    public Ingresso setorEstadio(String setorEstadio) {
        this.setorEstadio = setorEstadio;
        return this;
    }

    public void setSetorEstadio(String setorEstadio) {
        this.setorEstadio = setorEstadio;
    }

    public String getAssentoEstadio() {
        return this.assentoEstadio;
    }

    public Ingresso assentoEstadio(String assentoEstadio) {
        this.assentoEstadio = assentoEstadio;
        return this;
    }

    public void setAssentoEstadio(String assentoEstadio) {
        this.assentoEstadio = assentoEstadio;
    }

    public String getNomeComprador() {
        return this.nomeComprador;
    }

    public Ingresso nomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
        return this;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getCpfComprador() {
        return this.cpfComprador;
    }

    public Ingresso cpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
        return this;
    }

    public void setCpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public LocalDate getNascimentoComprador() {
        return this.nascimentoComprador;
    }

    public Ingresso nascimentoComprador(LocalDate nascimentoComprador) {
        this.nascimentoComprador = nascimentoComprador;
        return this;
    }

    public void setNascimentoComprador(LocalDate nascimentoComprador) {
        this.nascimentoComprador = nascimentoComprador;
    }

    public Integer getNumeroCartao() {
        return this.numeroCartao;
    }

    public Ingresso numeroCartao(Integer numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public void setNumeroCartao(Integer numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidadeCartao() {
        return this.validadeCartao;
    }

    public Ingresso validadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
        return this;
    }

    public void setValidadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
    }

    public Integer getCodigoCartao() {
        return this.codigoCartao;
    }

    public Ingresso codigoCartao(Integer codigoCartao) {
        this.codigoCartao = codigoCartao;
        return this;
    }

    public void setCodigoCartao(Integer codigoCartao) {
        this.codigoCartao = codigoCartao;
    }

    public String getQuantidadeDeIngressos() {
        return this.quantidadeDeIngressos;
    }

    public Ingresso quantidadeDeIngressos(String quantidadeDeIngressos) {
        this.quantidadeDeIngressos = quantidadeDeIngressos;
        return this;
    }

    public void setQuantidadeDeIngressos(String quantidadeDeIngressos) {
        this.quantidadeDeIngressos = quantidadeDeIngressos;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ingresso)) {
            return false;
        }
        return id != null && id.equals(((Ingresso) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ingresso{" +
            "id=" + getId() +
            ", horarioJogo='" + getHorarioJogo() + "'" +
            ", timeMandante='" + getTimeMandante() + "'" +
            ", timeVisitante='" + getTimeVisitante() + "'" +
            ", data='" + getData() + "'" +
            ", nomeEstadio='" + getNomeEstadio() + "'" +
            ", setorEstadio='" + getSetorEstadio() + "'" +
            ", assentoEstadio='" + getAssentoEstadio() + "'" +
            ", nomeComprador='" + getNomeComprador() + "'" +
            ", cpfComprador='" + getCpfComprador() + "'" +
            ", nascimentoComprador='" + getNascimentoComprador() + "'" +
            ", numeroCartao=" + getNumeroCartao() +
            ", validadeCartao='" + getValidadeCartao() + "'" +
            ", codigoCartao=" + getCodigoCartao() +
            ", quantidadeDeIngressos='" + getQuantidadeDeIngressos() + "'" +
            "}";
    }
}
