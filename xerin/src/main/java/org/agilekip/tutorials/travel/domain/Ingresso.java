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

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "setor_estadio")
    private String setorEstadio;

    @Column(name = "assento_estadio")
    private String assentoEstadio;

    @Column(name = "time_visitante")
    private String timeVisitante;

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

    @Column(name = "email_comprador")
    private String emailComprador;

    @ManyToOne
    private Time timeMandante;

    @ManyToOne
    private Estadio estadio;

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

    public String getEmailComprador() {
        return this.emailComprador;
    }

    public Ingresso emailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
        return this;
    }

    public void setEmailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
    }

    public Time getTimeMandante() {
        return this.timeMandante;
    }

    public Ingresso timeMandante(Time Time) {
        this.setTimeMandante(Time);
        return this;
    }

    public void setTimeMandante(Time Time) {
        this.timeMandante = Time;
    }

    public Estadio getEstadio() {
        return this.estadio;
    }

    public Ingresso estadio(Estadio Estadio) {
        this.setEstadio(Estadio);
        return this;
    }

    public void setEstadio(Estadio Estadio) {
        this.estadio = Estadio;
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
            ", data='" + getData() + "'" +
            ", setorEstadio='" + getSetorEstadio() + "'" +
            ", assentoEstadio='" + getAssentoEstadio() + "'" +
            ", timeVisitante='" + getTimeVisitante() + "'" +
            ", nomeComprador='" + getNomeComprador() + "'" +
            ", cpfComprador='" + getCpfComprador() + "'" +
            ", nascimentoComprador='" + getNascimentoComprador() + "'" +
            ", numeroCartao=" + getNumeroCartao() +
            ", validadeCartao='" + getValidadeCartao() + "'" +
            ", codigoCartao=" + getCodigoCartao() +
            ", emailComprador='" + getEmailComprador() + "'" +
            "}";
    }
}
