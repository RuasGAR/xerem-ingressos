package org.agilekip.tutorials.travel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.agilekip.tutorials.travel.repository.TimeRepository;
import org.agilekip.tutorials.travel.service.TimeService;
import org.agilekip.tutorials.travel.service.dto.TimeDTO;
import org.agilekip.tutorials.travel.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.agilekip.tutorials.travel.domain.Time}.
 */
@RestController
@RequestMapping("/api")
public class TimeResource {

    private final Logger log = LoggerFactory.getLogger(TimeResource.class);

    private static final String ENTITY_NAME = "time";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TimeService timeService;

    private final TimeRepository timeRepository;

    public TimeResource(TimeService timeService, TimeRepository timeRepository) {
        this.timeService = timeService;
        this.timeRepository = timeRepository;
    }

    /**
     * {@code POST  /times} : Create a new time.
     *
     * @param timeDTO the timeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new timeDTO, or with status {@code 400 (Bad Request)} if the time has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/times")
    public ResponseEntity<TimeDTO> createTime(@RequestBody TimeDTO timeDTO) throws URISyntaxException {
        log.debug("REST request to save Time : {}", timeDTO);
        if (timeDTO.getId() != null) {
            throw new BadRequestAlertException("A new time cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TimeDTO result = timeService.save(timeDTO);
        return ResponseEntity
            .created(new URI("/api/times/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /times/:id} : Updates an existing time.
     *
     * @param id the id of the timeDTO to save.
     * @param timeDTO the timeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timeDTO,
     * or with status {@code 400 (Bad Request)} if the timeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the timeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/times/{id}")
    public ResponseEntity<TimeDTO> updateTime(@PathVariable(value = "id", required = false) final Long id, @RequestBody TimeDTO timeDTO)
        throws URISyntaxException {
        log.debug("REST request to update Time : {}, {}", id, timeDTO);
        if (timeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, timeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!timeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TimeDTO result = timeService.save(timeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, timeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /times/:id} : Partial updates given fields of an existing time, field will ignore if it is null
     *
     * @param id the id of the timeDTO to save.
     * @param timeDTO the timeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timeDTO,
     * or with status {@code 400 (Bad Request)} if the timeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the timeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the timeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/times/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TimeDTO> partialUpdateTime(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TimeDTO timeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Time partially : {}, {}", id, timeDTO);
        if (timeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, timeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!timeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TimeDTO> result = timeService.partialUpdate(timeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, timeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /times} : get all the times.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of times in body.
     */
    @GetMapping("/times")
    public List<TimeDTO> getAllTimes() {
        log.debug("REST request to get all Times");
        return timeService.findAll();
    }

    /**
     * {@code GET  /times/:id} : get the "id" time.
     *
     * @param id the id of the timeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the timeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/times/{id}")
    public ResponseEntity<TimeDTO> getTime(@PathVariable Long id) {
        log.debug("REST request to get Time : {}", id);
        Optional<TimeDTO> timeDTO = timeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timeDTO);
    }

    /**
     * {@code DELETE  /times/:id} : delete the "id" time.
     *
     * @param id the id of the timeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        log.debug("REST request to delete Time : {}", id);
        timeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
