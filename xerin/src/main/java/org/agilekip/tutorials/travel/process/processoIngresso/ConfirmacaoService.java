package org.agilekip.tutorials.travel.process.processoIngresso;

import org.agilekip.tutorials.travel.repository.ProcessoIngressoRepository;
import org.agilekip.tutorials.travel.service.IngressoService;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;
import org.agilekip.tutorials.travel.service.mapper.ProcessoIngressoMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class ConfirmacaoService {

    private final TaskInstanceService taskInstanceService;

    private final IngressoService ingressoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ProcessoIngressoRepository processoIngressoRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final ConfirmacaoMapper confirmacaoMapper;

    private final ProcessoIngressoMapper processoIngressoMapper;

    public ConfirmacaoService(
        TaskInstanceService taskInstanceService,
        IngressoService ingressoService,
        TaskInstanceRepository taskInstanceRepository,
        ProcessoIngressoRepository processoIngressoRepository,
        TaskInstanceMapper taskInstanceMapper,
        ConfirmacaoMapper confirmacaoMapper,
        ProcessoIngressoMapper processoIngressoMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.ingressoService = ingressoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.processoIngressoRepository = processoIngressoRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.confirmacaoMapper = confirmacaoMapper;
        this.processoIngressoMapper = processoIngressoMapper;
    }

    public ConfirmacaoContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(confirmacaoMapper::toProcessoIngressoDTO)
            .orElseThrow();

        ConfirmacaoContextDTO confirmacaoContext = new ConfirmacaoContextDTO();
        confirmacaoContext.setTaskInstance(taskInstanceDTO);
        confirmacaoContext.setProcessoIngresso(processoIngresso);

        return confirmacaoContext;
    }

    public ConfirmacaoContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(ConfirmacaoContextDTO confirmacaoContext) {
        IngressoDTO ingressoDTO = ingressoService.findOne(confirmacaoContext.getProcessoIngresso().getIngresso().getId()).orElseThrow();
        ingressoDTO.setTimeMandante(confirmacaoContext.getProcessoIngresso().getIngresso().getTimeMandante());
        ingressoDTO.setTimeVisitante(confirmacaoContext.getProcessoIngresso().getIngresso().getTimeVisitante());
        ingressoDTO.setHorarioJogo(confirmacaoContext.getProcessoIngresso().getIngresso().getHorarioJogo());
        ingressoDTO.setData(confirmacaoContext.getProcessoIngresso().getIngresso().getData());
        ingressoDTO.setNomeEstadio(confirmacaoContext.getProcessoIngresso().getIngresso().getNomeEstadio());
        ingressoDTO.setSetorEstadio(confirmacaoContext.getProcessoIngresso().getIngresso().getSetorEstadio());
        ingressoDTO.setAssentoEstadio(confirmacaoContext.getProcessoIngresso().getIngresso().getAssentoEstadio());
        ingressoDTO.setNomeComprador(confirmacaoContext.getProcessoIngresso().getIngresso().getNomeComprador());
        ingressoDTO.setCpfComprador(confirmacaoContext.getProcessoIngresso().getIngresso().getCpfComprador());
        ingressoDTO.setNascimentoComprador(confirmacaoContext.getProcessoIngresso().getIngresso().getNascimentoComprador());
        ingressoDTO.setEmailComprador(confirmacaoContext.getProcessoIngresso().getIngresso().getEmailComprador());
        ingressoService.save(ingressoDTO);
    }

    public void complete(ConfirmacaoContextDTO confirmacaoContext) {
        save(confirmacaoContext);
        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(confirmacaoContext.getProcessoIngresso().getProcessInstance().getId())
            .map(processoIngressoMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(confirmacaoContext.getTaskInstance(), processoIngresso);
    }
}
