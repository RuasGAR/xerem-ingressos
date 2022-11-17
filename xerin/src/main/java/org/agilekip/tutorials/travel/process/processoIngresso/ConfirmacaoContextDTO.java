package org.agilekip.tutorials.travel.process.processoIngresso;

import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class ConfirmacaoContextDTO {

    private ProcessoIngressoDTO processoIngresso;
    private TaskInstanceDTO taskInstance;

    public ProcessoIngressoDTO getProcessoIngresso() {
        return processoIngresso;
    }

    public void setProcessoIngresso(ProcessoIngressoDTO processoIngresso) {
        this.processoIngresso = processoIngresso;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
