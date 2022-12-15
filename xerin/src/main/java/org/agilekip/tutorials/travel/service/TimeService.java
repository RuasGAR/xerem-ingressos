package org.agilekip.tutorials.travel.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.agilekip.tutorials.travel.domain.Time;
import org.agilekip.tutorials.travel.repository.TimeRepository;
import org.agilekip.tutorials.travel.service.dto.TimeDTO;
import org.agilekip.tutorials.travel.service.mapper.TimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Time}.
 */
@Service
@Transactional
public class TimeService {

    private final Logger log = LoggerFactory.getLogger(TimeService.class);

    private final TimeRepository timeRepository;

    private final TimeMapper timeMapper;

    public TimeService(TimeRepository timeRepository, TimeMapper timeMapper) {
        this.timeRepository = timeRepository;
        this.timeMapper = timeMapper;
    }

    /**
     * Save a time.
     *
     * @param timeDTO the entity to save.
     * @return the persisted entity.
     */
    public TimeDTO save(TimeDTO timeDTO) {
        log.debug("Request to save Time : {}", timeDTO);
        Time time = timeMapper.toEntity(timeDTO);
        time = timeRepository.save(time);
        return timeMapper.toDto(time);
    }

    /**
     * Partially update a time.
     *
     * @param timeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TimeDTO> partialUpdate(TimeDTO timeDTO) {
        log.debug("Request to partially update Time : {}", timeDTO);

        return timeRepository
            .findById(timeDTO.getId())
            .map(
                existingTime -> {
                    timeMapper.partialUpdate(existingTime, timeDTO);
                    return existingTime;
                }
            )
            .map(timeRepository::save)
            .map(timeMapper::toDto);
    }

    /**
     * Get all the times.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TimeDTO> findAll() {
        log.debug("Request to get all Times");
        return timeRepository.findAll().stream().map(timeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one time by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TimeDTO> findOne(Long id) {
        log.debug("Request to get Time : {}", id);
        return timeRepository.findById(id).map(timeMapper::toDto);
    }

    /**
     * Delete the time by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Time : {}", id);
        timeRepository.deleteById(id);
    }
}
