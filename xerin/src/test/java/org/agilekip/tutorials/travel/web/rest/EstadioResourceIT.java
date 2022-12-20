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
import org.agilekip.tutorials.travel.domain.Estadio;
import org.agilekip.tutorials.travel.repository.EstadioRepository;
import org.agilekip.tutorials.travel.service.dto.EstadioDTO;
import org.agilekip.tutorials.travel.service.mapper.EstadioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EstadioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EstadioResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/estadios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EstadioRepository estadioRepository;

    @Autowired
    private EstadioMapper estadioMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEstadioMockMvc;

    private Estadio estadio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estadio createEntity(EntityManager em) {
        Estadio estadio = new Estadio().nome(DEFAULT_NOME);
        return estadio;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estadio createUpdatedEntity(EntityManager em) {
        Estadio estadio = new Estadio().nome(UPDATED_NOME);
        return estadio;
    }

    @BeforeEach
    public void initTest() {
        estadio = createEntity(em);
    }

    @Test
    @Transactional
    void createEstadio() throws Exception {
        int databaseSizeBeforeCreate = estadioRepository.findAll().size();
        // Create the Estadio
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);
        restEstadioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estadioDTO)))
            .andExpect(status().isCreated());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeCreate + 1);
        Estadio testEstadio = estadioList.get(estadioList.size() - 1);
        assertThat(testEstadio.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    void createEstadioWithExistingId() throws Exception {
        // Create the Estadio with an existing ID
        estadio.setId(1L);
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);

        int databaseSizeBeforeCreate = estadioRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estadioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEstadios() throws Exception {
        // Initialize the database
        estadioRepository.saveAndFlush(estadio);

        // Get all the estadioList
        restEstadioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadio.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }

    @Test
    @Transactional
    void getEstadio() throws Exception {
        // Initialize the database
        estadioRepository.saveAndFlush(estadio);

        // Get the estadio
        restEstadioMockMvc
            .perform(get(ENTITY_API_URL_ID, estadio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(estadio.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }

    @Test
    @Transactional
    void getNonExistingEstadio() throws Exception {
        // Get the estadio
        restEstadioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEstadio() throws Exception {
        // Initialize the database
        estadioRepository.saveAndFlush(estadio);

        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();

        // Update the estadio
        Estadio updatedEstadio = estadioRepository.findById(estadio.getId()).get();
        // Disconnect from session so that the updates on updatedEstadio are not directly saved in db
        em.detach(updatedEstadio);
        updatedEstadio.nome(UPDATED_NOME);
        EstadioDTO estadioDTO = estadioMapper.toDto(updatedEstadio);

        restEstadioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, estadioDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(estadioDTO))
            )
            .andExpect(status().isOk());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
        Estadio testEstadio = estadioList.get(estadioList.size() - 1);
        assertThat(testEstadio.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    void putNonExistingEstadio() throws Exception {
        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();
        estadio.setId(count.incrementAndGet());

        // Create the Estadio
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, estadioDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(estadioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEstadio() throws Exception {
        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();
        estadio.setId(count.incrementAndGet());

        // Create the Estadio
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstadioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(estadioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEstadio() throws Exception {
        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();
        estadio.setId(count.incrementAndGet());

        // Create the Estadio
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstadioMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(estadioDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEstadioWithPatch() throws Exception {
        // Initialize the database
        estadioRepository.saveAndFlush(estadio);

        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();

        // Update the estadio using partial update
        Estadio partialUpdatedEstadio = new Estadio();
        partialUpdatedEstadio.setId(estadio.getId());

        partialUpdatedEstadio.nome(UPDATED_NOME);

        restEstadioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEstadio.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEstadio))
            )
            .andExpect(status().isOk());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
        Estadio testEstadio = estadioList.get(estadioList.size() - 1);
        assertThat(testEstadio.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    void fullUpdateEstadioWithPatch() throws Exception {
        // Initialize the database
        estadioRepository.saveAndFlush(estadio);

        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();

        // Update the estadio using partial update
        Estadio partialUpdatedEstadio = new Estadio();
        partialUpdatedEstadio.setId(estadio.getId());

        partialUpdatedEstadio.nome(UPDATED_NOME);

        restEstadioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEstadio.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEstadio))
            )
            .andExpect(status().isOk());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
        Estadio testEstadio = estadioList.get(estadioList.size() - 1);
        assertThat(testEstadio.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    void patchNonExistingEstadio() throws Exception {
        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();
        estadio.setId(count.incrementAndGet());

        // Create the Estadio
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, estadioDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(estadioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEstadio() throws Exception {
        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();
        estadio.setId(count.incrementAndGet());

        // Create the Estadio
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstadioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(estadioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEstadio() throws Exception {
        int databaseSizeBeforeUpdate = estadioRepository.findAll().size();
        estadio.setId(count.incrementAndGet());

        // Create the Estadio
        EstadioDTO estadioDTO = estadioMapper.toDto(estadio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEstadioMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(estadioDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Estadio in the database
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEstadio() throws Exception {
        // Initialize the database
        estadioRepository.saveAndFlush(estadio);

        int databaseSizeBeforeDelete = estadioRepository.findAll().size();

        // Delete the estadio
        restEstadioMockMvc
            .perform(delete(ENTITY_API_URL_ID, estadio.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Estadio> estadioList = estadioRepository.findAll();
        assertThat(estadioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
