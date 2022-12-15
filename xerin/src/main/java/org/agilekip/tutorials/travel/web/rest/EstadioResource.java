package org.agilekip.tutorials.travel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.agilekip.tutorials.travel.repository.EstadioRepository;
import org.agilekip.tutorials.travel.service.EstadioService;
import org.agilekip.tutorials.travel.service.dto.EstadioDTO;
import org.agilekip.tutorials.travel.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.agilekip.tutorials.travel.domain.Estadio}.
 */
@RestController
@RequestMapping("/api")
public class EstadioResource {

    private final Logger log = LoggerFactory.getLogger(EstadioResource.class);

    private static final String ENTITY_NAME = "estadio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadioService estadioService;

    private final EstadioRepository estadioRepository;

    public EstadioResource(EstadioService estadioService, EstadioRepository estadioRepository) {
        this.estadioService = estadioService;
        this.estadioRepository = estadioRepository;
    }

    /**
     * {@code POST  /estadios} : Create a new estadio.
     *
     * @param estadioDTO the estadioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadioDTO, or with status {@code 400 (Bad Request)} if the estadio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estadios")
    public ResponseEntity<EstadioDTO> createEstadio(@RequestBody EstadioDTO estadioDTO) throws URISyntaxException {
        log.debug("REST request to save Estadio : {}", estadioDTO);
        if (estadioDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadioDTO result = estadioService.save(estadioDTO);
        return ResponseEntity
            .created(new URI("/api/estadios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estadios/:id} : Updates an existing estadio.
     *
     * @param id the id of the estadioDTO to save.
     * @param estadioDTO the estadioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadioDTO,
     * or with status {@code 400 (Bad Request)} if the estadioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estadios/{id}")
    public ResponseEntity<EstadioDTO> updateEstadio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EstadioDTO estadioDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Estadio : {}, {}", id, estadioDTO);
        if (estadioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, estadioDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!estadioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EstadioDTO result = estadioService.save(estadioDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /estadios/:id} : Partial updates given fields of an existing estadio, field will ignore if it is null
     *
     * @param id the id of the estadioDTO to save.
     * @param estadioDTO the estadioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadioDTO,
     * or with status {@code 400 (Bad Request)} if the estadioDTO is not valid,
     * or with status {@code 404 (Not Found)} if the estadioDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the estadioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/estadios/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<EstadioDTO> partialUpdateEstadio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EstadioDTO estadioDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Estadio partially : {}, {}", id, estadioDTO);
        if (estadioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, estadioDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!estadioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EstadioDTO> result = estadioService.partialUpdate(estadioDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadioDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /estadios} : get all the estadios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadios in body.
     */
    @GetMapping("/estadios")
    public List<EstadioDTO> getAllEstadios() {
        log.debug("REST request to get all Estadios");
        return estadioService.findAll();
    }

    /**
     * {@code GET  /estadios/:id} : get the "id" estadio.
     *
     * @param id the id of the estadioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estadios/{id}")
    public ResponseEntity<EstadioDTO> getEstadio(@PathVariable Long id) {
        log.debug("REST request to get Estadio : {}", id);
        Optional<EstadioDTO> estadioDTO = estadioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadioDTO);
    }

    /**
     * {@code DELETE  /estadios/:id} : delete the "id" estadio.
     *
     * @param id the id of the estadioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estadios/{id}")
    public ResponseEntity<Void> deleteEstadio(@PathVariable Long id) {
        log.debug("REST request to delete Estadio : {}", id);
        estadioService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
