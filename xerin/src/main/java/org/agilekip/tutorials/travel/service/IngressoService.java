package org.agilekip.tutorials.travel.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.agilekip.tutorials.travel.domain.Ingresso;
import org.agilekip.tutorials.travel.repository.IngressoRepository;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.agilekip.tutorials.travel.service.mapper.IngressoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ingresso}.
 */
@Service
@Transactional
public class IngressoService {

    private final Logger log = LoggerFactory.getLogger(IngressoService.class);

    private final IngressoRepository ingressoRepository;

    private final IngressoMapper ingressoMapper;

    public IngressoService(IngressoRepository ingressoRepository, IngressoMapper ingressoMapper) {
        this.ingressoRepository = ingressoRepository;
        this.ingressoMapper = ingressoMapper;
    }

    /**
     * Save a ingresso.
     *
     * @param ingressoDTO the entity to save.
     * @return the persisted entity.
     */
    public IngressoDTO save(IngressoDTO ingressoDTO) {
        log.debug("Request to save Ingresso : {}", ingressoDTO);
        Ingresso ingresso = ingressoMapper.toEntity(ingressoDTO);
        ingresso = ingressoRepository.save(ingresso);
        return ingressoMapper.toDto(ingresso);
    }

    /**
     * Partially update a ingresso.
     *
     * @param ingressoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<IngressoDTO> partialUpdate(IngressoDTO ingressoDTO) {
        log.debug("Request to partially update Ingresso : {}", ingressoDTO);

        return ingressoRepository
            .findById(ingressoDTO.getId())
            .map(
                existingIngresso -> {
                    ingressoMapper.partialUpdate(existingIngresso, ingressoDTO);
                    return existingIngresso;
                }
            )
            .map(ingressoRepository::save)
            .map(ingressoMapper::toDto);
    }

    /**
     * Get all the ingressos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IngressoDTO> findAll() {
        log.debug("Request to get all Ingressos");
        return ingressoRepository.findAll().stream().map(ingressoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ingresso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IngressoDTO> findOne(Long id) {
        log.debug("Request to get Ingresso : {}", id);
        return ingressoRepository.findById(id).map(ingressoMapper::toDto);
    }

    /**
     * Delete the ingresso by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ingresso : {}", id);
        ingressoRepository.deleteById(id);
    }
}
