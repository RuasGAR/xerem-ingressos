package org.agilekip.tutorials.travel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.agilekip.tutorials.travel.repository.IngressoRepository;
import org.agilekip.tutorials.travel.service.IngressoService;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.agilekip.tutorials.travel.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.agilekip.tutorials.travel.domain.Ingresso}.
 */
@RestController
@RequestMapping("/api")
public class IngressoResource {

    private final Logger log = LoggerFactory.getLogger(IngressoResource.class);

    private final IngressoService ingressoService;

    private final IngressoRepository ingressoRepository;

    public IngressoResource(IngressoService ingressoService, IngressoRepository ingressoRepository) {
        this.ingressoService = ingressoService;
        this.ingressoRepository = ingressoRepository;
    }

    /**
     * {@code GET  /ingressos} : get all the ingressos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ingressos in body.
     */
    @GetMapping("/ingressos")
    public List<IngressoDTO> getAllIngressos() {
        log.debug("REST request to get all Ingressos");
        return ingressoService.findAll();
    }

    /**
     * {@code GET  /ingressos/:id} : get the "id" ingresso.
     *
     * @param id the id of the ingressoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ingressoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ingressos/{id}")
    public ResponseEntity<IngressoDTO> getIngresso(@PathVariable Long id) {
        log.debug("REST request to get Ingresso : {}", id);
        Optional<IngressoDTO> ingressoDTO = ingressoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ingressoDTO);
    }
}
