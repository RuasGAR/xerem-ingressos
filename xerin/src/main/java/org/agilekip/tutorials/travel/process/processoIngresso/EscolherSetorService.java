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
public class EscolherSetorService {

    private final TaskInstanceService taskInstanceService;

    private final IngressoService ingressoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ProcessoIngressoRepository processoIngressoRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final EscolherSetorMapper escolherSetorMapper;

    private final ProcessoIngressoMapper processoIngressoMapper;

    public EscolherSetorService(
        TaskInstanceService taskInstanceService,
        IngressoService ingressoService,
        TaskInstanceRepository taskInstanceRepository,
        ProcessoIngressoRepository processoIngressoRepository,
        TaskInstanceMapper taskInstanceMapper,
        EscolherSetorMapper escolherSetorMapper,
        ProcessoIngressoMapper processoIngressoMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.ingressoService = ingressoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.processoIngressoRepository = processoIngressoRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.escolherSetorMapper = escolherSetorMapper;
        this.processoIngressoMapper = processoIngressoMapper;
    }

    public EscolherSetorContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(escolherSetorMapper::toProcessoIngressoDTO)
            .orElseThrow();

        EscolherSetorContextDTO escolherSetorContext = new EscolherSetorContextDTO();
        escolherSetorContext.setTaskInstance(taskInstanceDTO);
        escolherSetorContext.setProcessoIngresso(processoIngresso);

        return escolherSetorContext;
    }

    public EscolherSetorContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(EscolherSetorContextDTO escolherSetorContext) {
        IngressoDTO ingressoDTO = ingressoService.findOne(escolherSetorContext.getProcessoIngresso().getIngresso().getId()).orElseThrow();
        ingressoDTO.setNomeEstadio(escolherSetorContext.getProcessoIngresso().getIngresso().getNomeEstadio());
        ingressoDTO.setSetorEstadio(escolherSetorContext.getProcessoIngresso().getIngresso().getSetorEstadio());
        ingressoDTO.setAssentoEstadio(escolherSetorContext.getProcessoIngresso().getIngresso().getAssentoEstadio());
        ingressoService.save(ingressoDTO);
    }

    public void complete(EscolherSetorContextDTO escolherSetorContext) {
        save(escolherSetorContext);
        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(escolherSetorContext.getProcessoIngresso().getProcessInstance().getId())
            .map(processoIngressoMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(escolherSetorContext.getTaskInstance(), processoIngresso);
    }
}
