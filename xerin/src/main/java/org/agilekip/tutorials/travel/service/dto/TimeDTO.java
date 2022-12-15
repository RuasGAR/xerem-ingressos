package org.agilekip.tutorials.travel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.agilekip.tutorials.travel.domain.Time} entity.
 */
public class TimeDTO implements Serializable {

    private Long id;

    private String nome;

    private String nomeCasa;

    private String cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public void setNomeCasa(String nomeCasa) {
        this.nomeCasa = nomeCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimeDTO)) {
            return false;
        }

        TimeDTO timeDTO = (TimeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, timeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimeDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", nomeCasa='" + getNomeCasa() + "'" +
            ", cidade='" + getCidade() + "'" +
            "}";
    }
}
