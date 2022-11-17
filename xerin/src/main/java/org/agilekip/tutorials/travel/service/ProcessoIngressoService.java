package org.agilekip.tutorials.travel.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.agilekip.tutorials.travel.domain.ProcessoIngresso;
import org.agilekip.tutorials.travel.repository.IngressoRepository;
import org.agilekip.tutorials.travel.repository.ProcessoIngressoRepository;
import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;
import org.agilekip.tutorials.travel.service.mapper.ProcessoIngressoMapper;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProcessoIngresso}.
 */
@Service
@Transactional
public class ProcessoIngressoService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "ProcessoIngresso";

    private final Logger log = LoggerFactory.getLogger(ProcessoIngressoService.class);

    private final ProcessInstanceService processInstanceService;

    private final IngressoRepository ingressoRepository;

    private final ProcessoIngressoRepository processoIngressoRepository;

    private final ProcessoIngressoMapper processoIngressoMapper;

    public ProcessoIngressoService(
        ProcessInstanceService processInstanceService,
        IngressoRepository ingressoRepository,
        ProcessoIngressoRepository processoIngressoRepository,
        ProcessoIngressoMapper processoIngressoMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.ingressoRepository = ingressoRepository;
        this.processoIngressoRepository = processoIngressoRepository;
        this.processoIngressoMapper = processoIngressoMapper;
    }

    /**
     * Save a processoIngresso.
     *
     * @param processoIngressoDTO the entity to save.
     * @return the persisted entity.
     */
    public ProcessoIngressoDTO create(ProcessoIngressoDTO processoIngressoDTO) {
        log.debug("Request to save ProcessoIngresso : {}", processoIngressoDTO);

        ProcessoIngresso processoIngresso = processoIngressoMapper.toEntity(processoIngressoDTO);

        //Saving the domainEntity
        ingressoRepository.save(processoIngresso.getIngresso());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Ingresso#" + processoIngresso.getIngresso().getId(),
            processoIngresso
        );
        processoIngresso.setProcessInstance(processInstance);

        //Saving the process entity
        processoIngresso = processoIngressoRepository.save(processoIngresso);
        return processoIngressoMapper.toDto(processoIngresso);
    }

    /**
     * Get all the processoIngressos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProcessoIngressoDTO> findAll() {
        log.debug("Request to get all ProcessoIngressos");
        return processoIngressoRepository
            .findAll()
            .stream()
            .map(processoIngressoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one processoIngresso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProcessoIngressoDTO> findOne(Long id) {
        log.debug("Request to get ProcessoIngresso : {}", id);
        return processoIngressoRepository.findById(id).map(processoIngressoMapper::toDto);
    }

    /**
     * Get one processoIngresso by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProcessoIngressoDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ProcessoIngresso by  processInstanceId: {}", processInstanceId);
        return processoIngressoRepository.findByProcessInstanceId(processInstanceId).map(processoIngressoMapper::toDto);
    }
}
