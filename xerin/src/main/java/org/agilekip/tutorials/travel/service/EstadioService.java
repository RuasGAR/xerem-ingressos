package org.agilekip.tutorials.travel.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.agilekip.tutorials.travel.domain.Estadio;
import org.agilekip.tutorials.travel.repository.EstadioRepository;
import org.agilekip.tutorials.travel.service.dto.EstadioDTO;
import org.agilekip.tutorials.travel.service.mapper.EstadioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Estadio}.
 */
@Service
@Transactional
public class EstadioService {

    private final Logger log = LoggerFactory.getLogger(EstadioService.class);

    private final EstadioRepository estadioRepository;

    private final EstadioMapper estadioMapper;

    public EstadioService(EstadioRepository estadioRepository, EstadioMapper estadioMapper) {
        this.estadioRepository = estadioRepository;
        this.estadioMapper = estadioMapper;
    }

    /**
     * Save a estadio.
     *
     * @param estadioDTO the entity to save.
     * @return the persisted entity.
     */
    public EstadioDTO save(EstadioDTO estadioDTO) {
        log.debug("Request to save Estadio : {}", estadioDTO);
        Estadio estadio = estadioMapper.toEntity(estadioDTO);
        estadio = estadioRepository.save(estadio);
        return estadioMapper.toDto(estadio);
    }

    /**
     * Partially update a estadio.
     *
     * @param estadioDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EstadioDTO> partialUpdate(EstadioDTO estadioDTO) {
        log.debug("Request to partially update Estadio : {}", estadioDTO);

        return estadioRepository
            .findById(estadioDTO.getId())
            .map(
                existingEstadio -> {
                    estadioMapper.partialUpdate(existingEstadio, estadioDTO);
                    return existingEstadio;
                }
            )
            .map(estadioRepository::save)
            .map(estadioMapper::toDto);
    }

    /**
     * Get all the estadios.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EstadioDTO> findAll() {
        log.debug("Request to get all Estadios");
        return estadioRepository.findAll().stream().map(estadioMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one estadio by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EstadioDTO> findOne(Long id) {
        log.debug("Request to get Estadio : {}", id);
        return estadioRepository.findById(id).map(estadioMapper::toDto);
    }

    /**
     * Delete the estadio by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Estadio : {}", id);
        estadioRepository.deleteById(id);
    }
}
