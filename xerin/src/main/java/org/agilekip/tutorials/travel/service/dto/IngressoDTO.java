package org.agilekip.tutorials.travel.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link org.agilekip.tutorials.travel.domain.Ingresso} entity.
 */
public class IngressoDTO implements Serializable {

    private Long id;

    private String horarioJogo;

    private LocalDate data;

    private String setorEstadio;

    private String assentoEstadio;

    private String timeVisitante;

    private String nomeComprador;

    private String cpfComprador;

    private LocalDate nascimentoComprador;

    private Integer numeroCartao;

    private String validadeCartao;

    private Integer codigoCartao;

    private String emailComprador;

    private TimeDTO timeMandante;

    private EstadioDTO estadio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorarioJogo() {
        return horarioJogo;
    }

    public void setHorarioJogo(String horarioJogo) {
        this.horarioJogo = horarioJogo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getSetorEstadio() {
        return setorEstadio;
    }

    public void setSetorEstadio(String setorEstadio) {
        this.setorEstadio = setorEstadio;
    }

    public String getAssentoEstadio() {
        return assentoEstadio;
    }

    public void setAssentoEstadio(String assentoEstadio) {
        this.assentoEstadio = assentoEstadio;
    }

    public String getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(String timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public LocalDate getNascimentoComprador() {
        return nascimentoComprador;
    }

    public void setNascimentoComprador(LocalDate nascimentoComprador) {
        this.nascimentoComprador = nascimentoComprador;
    }

    public Integer getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Integer numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidadeCartao() {
        return validadeCartao;
    }

    public void setValidadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
    }

    public Integer getCodigoCartao() {
        return codigoCartao;
    }

    public void setCodigoCartao(Integer codigoCartao) {
        this.codigoCartao = codigoCartao;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

    public void setEmailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
    }

    public TimeDTO getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(TimeDTO timeMandante) {
        this.timeMandante = timeMandante;
    }

    public EstadioDTO getEstadio() {
        return estadio;
    }

    public void setEstadio(EstadioDTO estadio) {
        this.estadio = estadio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IngressoDTO)) {
            return false;
        }

        IngressoDTO ingressoDTO = (IngressoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ingressoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IngressoDTO{" +
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
            ", timeMandante=" + getTimeMandante() +
            ", estadio=" + getEstadio() +
            "}";
    }
}
