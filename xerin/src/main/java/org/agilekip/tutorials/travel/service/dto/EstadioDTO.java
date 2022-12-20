package org.agilekip.tutorials.travel.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.agilekip.tutorials.travel.domain.Estadio} entity.
 */
public class EstadioDTO implements Serializable {

    private Long id;

    private String nome;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadioDTO)) {
            return false;
        }

        EstadioDTO estadioDTO = (EstadioDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, estadioDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstadioDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
