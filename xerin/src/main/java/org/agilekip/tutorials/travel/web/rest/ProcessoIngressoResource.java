package org.agilekip.tutorials.travel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.agilekip.tutorials.travel.service.ProcessoIngressoService;
import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;
import org.agilekip.tutorials.travel.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.agilekip.tutorials.travel.domain.ProcessoIngresso}.
 */
@RestController
@RequestMapping("/api")
public class ProcessoIngressoResource {

    private final Logger log = LoggerFactory.getLogger(ProcessoIngressoResource.class);

    private static final String ENTITY_NAME = "processoIngresso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProcessoIngressoService processoIngressoService;

    public ProcessoIngressoResource(ProcessoIngressoService processoIngressoService) {
        this.processoIngressoService = processoIngressoService;
    }

    /**
     * {@code POST  /processo-ingressos} : Create a new processoIngresso.
     *
     * @param processoIngressoDTO the processoIngressoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new processoIngressoDTO, or with status {@code 400 (Bad Request)} if the processoIngresso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/processo-ingressos")
    public ResponseEntity<ProcessoIngressoDTO> create(@RequestBody ProcessoIngressoDTO processoIngressoDTO) throws URISyntaxException {
        log.debug("REST request to save ProcessoIngresso : {}", processoIngressoDTO);
        if (processoIngressoDTO.getId() != null) {
            throw new BadRequestAlertException("A new processoIngresso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProcessoIngressoDTO result = processoIngressoService.create(processoIngressoDTO);
        return ResponseEntity
            .created(new URI("/api/processo-ingressos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /processo-ingressos} : get all the processoIngressos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of processoIngressos in body.
     */
    @GetMapping("/processo-ingressos")
    public List<ProcessoIngressoDTO> getAllProcessoIngressos() {
        log.debug("REST request to get all ProcessoIngressos");
        return processoIngressoService.findAll();
    }

    /**
     * {@code GET  /processo-ingressos/:id} : get the "id" processoIngresso.
     *
     * @param processInstanceId the id of the processoIngressoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the processoIngressoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/processo-ingressos/{processInstanceId}")
    public ResponseEntity<ProcessoIngressoDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ProcessoIngresso by processInstanceId : {}", processInstanceId);
        Optional<ProcessoIngressoDTO> processoIngressoDTO = processoIngressoService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(processoIngressoDTO);
    }
}
