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
public class EscolherEventoService {

    private final TaskInstanceService taskInstanceService;

    private final IngressoService ingressoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ProcessoIngressoRepository processoIngressoRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final EscolherEventoMapper escolherEventoMapper;

    private final ProcessoIngressoMapper processoIngressoMapper;

    public EscolherEventoService(
        TaskInstanceService taskInstanceService,
        IngressoService ingressoService,
        TaskInstanceRepository taskInstanceRepository,
        ProcessoIngressoRepository processoIngressoRepository,
        TaskInstanceMapper taskInstanceMapper,
        EscolherEventoMapper escolherEventoMapper,
        ProcessoIngressoMapper processoIngressoMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.ingressoService = ingressoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.processoIngressoRepository = processoIngressoRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.escolherEventoMapper = escolherEventoMapper;
        this.processoIngressoMapper = processoIngressoMapper;
    }

    public EscolherEventoContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(escolherEventoMapper::toProcessoIngressoDTO)
            .orElseThrow();

        EscolherEventoContextDTO escolherEventoContext = new EscolherEventoContextDTO();
        escolherEventoContext.setTaskInstance(taskInstanceDTO);
        escolherEventoContext.setProcessoIngresso(processoIngresso);

        return escolherEventoContext;
    }

    public EscolherEventoContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(EscolherEventoContextDTO escolherEventoContext) {
        IngressoDTO ingressoDTO = ingressoService.findOne(escolherEventoContext.getProcessoIngresso().getIngresso().getId()).orElseThrow();
        ingressoDTO.setHorarioJogo(escolherEventoContext.getProcessoIngresso().getIngresso().getHorarioJogo());
        ingressoDTO.setTimeMandante(escolherEventoContext.getProcessoIngresso().getIngresso().getTimeMandante());
        ingressoDTO.setTimeVisitante(escolherEventoContext.getProcessoIngresso().getIngresso().getTimeVisitante());
        ingressoDTO.setData(escolherEventoContext.getProcessoIngresso().getIngresso().getData());
        ingressoService.save(ingressoDTO);
    }

    public void complete(EscolherEventoContextDTO escolherEventoContext) {
        save(escolherEventoContext);
        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(escolherEventoContext.getProcessoIngresso().getProcessInstance().getId())
            .map(processoIngressoMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(escolherEventoContext.getTaskInstance(), processoIngresso);
    }
}
