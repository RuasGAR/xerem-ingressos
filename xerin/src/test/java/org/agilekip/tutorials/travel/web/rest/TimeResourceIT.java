package org.agilekip.tutorials.travel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.agilekip.tutorials.travel.IntegrationTest;
import org.agilekip.tutorials.travel.domain.Time;
import org.agilekip.tutorials.travel.repository.TimeRepository;
import org.agilekip.tutorials.travel.service.dto.TimeDTO;
import org.agilekip.tutorials.travel.service.mapper.TimeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TimeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TimeResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/times";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTimeMockMvc;

    private Time time;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Time createEntity(EntityManager em) {
        Time time = new Time().nome(DEFAULT_NOME).cidade(DEFAULT_CIDADE);
        return time;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Time createUpdatedEntity(EntityManager em) {
        Time time = new Time().nome(UPDATED_NOME).cidade(UPDATED_CIDADE);
        return time;
    }

    @BeforeEach
    public void initTest() {
        time = createEntity(em);
    }

    @Test
    @Transactional
    void createTime() throws Exception {
        int databaseSizeBeforeCreate = timeRepository.findAll().size();
        // Create the Time
        TimeDTO timeDTO = timeMapper.toDto(time);
        restTimeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(timeDTO)))
            .andExpect(status().isCreated());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeCreate + 1);
        Time testTime = timeList.get(timeList.size() - 1);
        assertThat(testTime.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTime.getCidade()).isEqualTo(DEFAULT_CIDADE);
    }

    @Test
    @Transactional
    void createTimeWithExistingId() throws Exception {
        // Create the Time with an existing ID
        time.setId(1L);
        TimeDTO timeDTO = timeMapper.toDto(time);

        int databaseSizeBeforeCreate = timeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTimeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(timeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTimes() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        // Get all the timeList
        restTimeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(time.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)));
    }

    @Test
    @Transactional
    void getTime() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        // Get the time
        restTimeMockMvc
            .perform(get(ENTITY_API_URL_ID, time.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(time.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE));
    }

    @Test
    @Transactional
    void getNonExistingTime() throws Exception {
        // Get the time
        restTimeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTime() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        int databaseSizeBeforeUpdate = timeRepository.findAll().size();

        // Update the time
        Time updatedTime = timeRepository.findById(time.getId()).get();
        // Disconnect from session so that the updates on updatedTime are not directly saved in db
        em.detach(updatedTime);
        updatedTime.nome(UPDATED_NOME).cidade(UPDATED_CIDADE);
        TimeDTO timeDTO = timeMapper.toDto(updatedTime);

        restTimeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, timeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(timeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
        Time testTime = timeList.get(timeList.size() - 1);
        assertThat(testTime.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTime.getCidade()).isEqualTo(UPDATED_CIDADE);
    }

    @Test
    @Transactional
    void putNonExistingTime() throws Exception {
        int databaseSizeBeforeUpdate = timeRepository.findAll().size();
        time.setId(count.incrementAndGet());

        // Create the Time
        TimeDTO timeDTO = timeMapper.toDto(time);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTimeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, timeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(timeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTime() throws Exception {
        int databaseSizeBeforeUpdate = timeRepository.findAll().size();
        time.setId(count.incrementAndGet());

        // Create the Time
        TimeDTO timeDTO = timeMapper.toDto(time);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTimeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(timeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTime() throws Exception {
        int databaseSizeBeforeUpdate = timeRepository.findAll().size();
        time.setId(count.incrementAndGet());

        // Create the Time
        TimeDTO timeDTO = timeMapper.toDto(time);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTimeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(timeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTimeWithPatch() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        int databaseSizeBeforeUpdate = timeRepository.findAll().size();

        // Update the time using partial update
        Time partialUpdatedTime = new Time();
        partialUpdatedTime.setId(time.getId());

        partialUpdatedTime.nome(UPDATED_NOME);

        restTimeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTime.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTime))
            )
            .andExpect(status().isOk());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
        Time testTime = timeList.get(timeList.size() - 1);
        assertThat(testTime.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTime.getCidade()).isEqualTo(DEFAULT_CIDADE);
    }

    @Test
    @Transactional
    void fullUpdateTimeWithPatch() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        int databaseSizeBeforeUpdate = timeRepository.findAll().size();

        // Update the time using partial update
        Time partialUpdatedTime = new Time();
        partialUpdatedTime.setId(time.getId());

        partialUpdatedTime.nome(UPDATED_NOME).cidade(UPDATED_CIDADE);

        restTimeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTime.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTime))
            )
            .andExpect(status().isOk());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
        Time testTime = timeList.get(timeList.size() - 1);
        assertThat(testTime.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTime.getCidade()).isEqualTo(UPDATED_CIDADE);
    }

    @Test
    @Transactional
    void patchNonExistingTime() throws Exception {
        int databaseSizeBeforeUpdate = timeRepository.findAll().size();
        time.setId(count.incrementAndGet());

        // Create the Time
        TimeDTO timeDTO = timeMapper.toDto(time);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTimeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, timeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(timeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTime() throws Exception {
        int databaseSizeBeforeUpdate = timeRepository.findAll().size();
        time.setId(count.incrementAndGet());

        // Create the Time
        TimeDTO timeDTO = timeMapper.toDto(time);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTimeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(timeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTime() throws Exception {
        int databaseSizeBeforeUpdate = timeRepository.findAll().size();
        time.setId(count.incrementAndGet());

        // Create the Time
        TimeDTO timeDTO = timeMapper.toDto(time);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTimeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(timeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Time in the database
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTime() throws Exception {
        // Initialize the database
        timeRepository.saveAndFlush(time);

        int databaseSizeBeforeDelete = timeRepository.findAll().size();

        // Delete the time
        restTimeMockMvc
            .perform(delete(ENTITY_API_URL_ID, time.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Time> timeList = timeRepository.findAll();
        assertThat(timeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
