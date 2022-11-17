package org.agilekip.tutorials.travel.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link org.agilekip.tutorials.travel.domain.ProcessoIngresso} entity.
 */
public class ProcessoIngressoDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private IngressoDTO ingresso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public IngressoDTO getIngresso() {
        return ingresso;
    }

    public void setIngresso(IngressoDTO ingresso) {
        this.ingresso = ingresso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcessoIngressoDTO)) {
            return false;
        }

        ProcessoIngressoDTO processoIngressoDTO = (ProcessoIngressoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, processoIngressoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProcessoIngressoDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", ingresso=" + getIngresso() +
            "}";
    }
}
