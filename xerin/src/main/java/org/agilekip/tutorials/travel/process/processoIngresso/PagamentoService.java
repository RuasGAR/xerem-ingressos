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
public class PagamentoService {

    private final TaskInstanceService taskInstanceService;

    private final IngressoService ingressoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ProcessoIngressoRepository processoIngressoRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final PagamentoMapper pagamentoMapper;

    private final ProcessoIngressoMapper processoIngressoMapper;

    public PagamentoService(
        TaskInstanceService taskInstanceService,
        IngressoService ingressoService,
        TaskInstanceRepository taskInstanceRepository,
        ProcessoIngressoRepository processoIngressoRepository,
        TaskInstanceMapper taskInstanceMapper,
        PagamentoMapper pagamentoMapper,
        ProcessoIngressoMapper processoIngressoMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.ingressoService = ingressoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.processoIngressoRepository = processoIngressoRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.pagamentoMapper = pagamentoMapper;
        this.processoIngressoMapper = processoIngressoMapper;
    }

    public PagamentoContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(pagamentoMapper::toProcessoIngressoDTO)
            .orElseThrow();

        PagamentoContextDTO pagamentoContext = new PagamentoContextDTO();
        pagamentoContext.setTaskInstance(taskInstanceDTO);
        pagamentoContext.setProcessoIngresso(processoIngresso);

        return pagamentoContext;
    }

    public PagamentoContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(PagamentoContextDTO pagamentoContext) {
        IngressoDTO ingressoDTO = ingressoService.findOne(pagamentoContext.getProcessoIngresso().getIngresso().getId()).orElseThrow();
        ingressoDTO.setNumeroCartao(pagamentoContext.getProcessoIngresso().getIngresso().getNumeroCartao());
        ingressoDTO.setValidadeCartao(pagamentoContext.getProcessoIngresso().getIngresso().getValidadeCartao());
        ingressoDTO.setCodigoCartao(pagamentoContext.getProcessoIngresso().getIngresso().getCodigoCartao());
        ingressoDTO.setQuantidadeDeIngressos(pagamentoContext.getProcessoIngresso().getIngresso().getQuantidadeDeIngressos());
        ingressoService.save(ingressoDTO);
    }

    public void complete(PagamentoContextDTO pagamentoContext) {
        save(pagamentoContext);
        ProcessoIngressoDTO processoIngresso = processoIngressoRepository
            .findByProcessInstanceId(pagamentoContext.getProcessoIngresso().getProcessInstance().getId())
            .map(processoIngressoMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(pagamentoContext.getTaskInstance(), processoIngresso);
    }
}
