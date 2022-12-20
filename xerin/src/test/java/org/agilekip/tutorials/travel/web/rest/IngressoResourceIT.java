package org.agilekip.tutorials.travel.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.agilekip.tutorials.travel.IntegrationTest;
import org.agilekip.tutorials.travel.domain.Ingresso;
import org.agilekip.tutorials.travel.repository.IngressoRepository;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.agilekip.tutorials.travel.service.mapper.IngressoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link IngressoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IngressoResourceIT {

    private static final String DEFAULT_HORARIO_JOGO = "AAAAAAAAAA";
    private static final String UPDATED_HORARIO_JOGO = "BBBBBBBBBB";

    private static final String DEFAULT_TIME_MANDANTE = "AAAAAAAAAA";
    private static final String UPDATED_TIME_MANDANTE = "BBBBBBBBBB";

    private static final String DEFAULT_TIME_VISITANTE = "AAAAAAAAAA";
    private static final String UPDATED_TIME_VISITANTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SETOR_ESTADIO = "AAAAAAAAAA";
    private static final String UPDATED_SETOR_ESTADIO = "BBBBBBBBBB";

    private static final String DEFAULT_ASSENTO_ESTADIO = "AAAAAAAAAA";
    private static final String UPDATED_ASSENTO_ESTADIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_COMPRADOR = "AAAAAAAAAA";
    private static final String UPDATED_NOME_COMPRADOR = "BBBBBBBBBB";

    private static final String DEFAULT_CPF_COMPRADOR = "AAAAAAAAAA";
    private static final String UPDATED_CPF_COMPRADOR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NASCIMENTO_COMPRADOR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NASCIMENTO_COMPRADOR = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUMERO_CARTAO = 1;
    private static final Integer UPDATED_NUMERO_CARTAO = 2;

    private static final String DEFAULT_VALIDADE_CARTAO = "AAAAAAAAAA";
    private static final String UPDATED_VALIDADE_CARTAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_CODIGO_CARTAO = 1;
    private static final Integer UPDATED_CODIGO_CARTAO = 2;

    private static final String DEFAULT_EMAIL_COMPRADOR = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_COMPRADOR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ingressos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private IngressoMapper ingressoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIngressoMockMvc;

    private Ingresso ingresso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ingresso createEntity(EntityManager em) {
        Ingresso ingresso = new Ingresso()
            .horarioJogo(DEFAULT_HORARIO_JOGO)
            .timeMandante(DEFAULT_TIME_MANDANTE)
            .timeVisitante(DEFAULT_TIME_VISITANTE)
            .data(DEFAULT_DATA)
            .setorEstadio(DEFAULT_SETOR_ESTADIO)
            .assentoEstadio(DEFAULT_ASSENTO_ESTADIO)
            .nomeComprador(DEFAULT_NOME_COMPRADOR)
            .cpfComprador(DEFAULT_CPF_COMPRADOR)
            .nascimentoComprador(DEFAULT_NASCIMENTO_COMPRADOR)
            .numeroCartao(DEFAULT_NUMERO_CARTAO)
            .validadeCartao(DEFAULT_VALIDADE_CARTAO)
            .codigoCartao(DEFAULT_CODIGO_CARTAO)
            .emailComprador(DEFAULT_EMAIL_COMPRADOR);
        return ingresso;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ingresso createUpdatedEntity(EntityManager em) {
        Ingresso ingresso = new Ingresso()
            .horarioJogo(UPDATED_HORARIO_JOGO)
            .timeMandante(UPDATED_TIME_MANDANTE)
            .timeVisitante(UPDATED_TIME_VISITANTE)
            .data(UPDATED_DATA)
            .setorEstadio(UPDATED_SETOR_ESTADIO)
            .assentoEstadio(UPDATED_ASSENTO_ESTADIO)
            .nomeComprador(UPDATED_NOME_COMPRADOR)
            .cpfComprador(UPDATED_CPF_COMPRADOR)
            .nascimentoComprador(UPDATED_NASCIMENTO_COMPRADOR)
            .numeroCartao(UPDATED_NUMERO_CARTAO)
            .validadeCartao(UPDATED_VALIDADE_CARTAO)
            .codigoCartao(UPDATED_CODIGO_CARTAO)
            .emailComprador(UPDATED_EMAIL_COMPRADOR);
        return ingresso;
    }

    @BeforeEach
    public void initTest() {
        ingresso = createEntity(em);
    }

    @Test
    @Transactional
    void getAllIngressos() throws Exception {
        // Initialize the database
        ingressoRepository.saveAndFlush(ingresso);

        // Get all the ingressoList
        restIngressoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ingresso.getId().intValue())))
            .andExpect(jsonPath("$.[*].horarioJogo").value(hasItem(DEFAULT_HORARIO_JOGO)))
            .andExpect(jsonPath("$.[*].timeMandante").value(hasItem(DEFAULT_TIME_MANDANTE)))
            .andExpect(jsonPath("$.[*].timeVisitante").value(hasItem(DEFAULT_TIME_VISITANTE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].setorEstadio").value(hasItem(DEFAULT_SETOR_ESTADIO)))
            .andExpect(jsonPath("$.[*].assentoEstadio").value(hasItem(DEFAULT_ASSENTO_ESTADIO)))
            .andExpect(jsonPath("$.[*].nomeComprador").value(hasItem(DEFAULT_NOME_COMPRADOR)))
            .andExpect(jsonPath("$.[*].cpfComprador").value(hasItem(DEFAULT_CPF_COMPRADOR)))
            .andExpect(jsonPath("$.[*].nascimentoComprador").value(hasItem(DEFAULT_NASCIMENTO_COMPRADOR.toString())))
            .andExpect(jsonPath("$.[*].numeroCartao").value(hasItem(DEFAULT_NUMERO_CARTAO)))
            .andExpect(jsonPath("$.[*].validadeCartao").value(hasItem(DEFAULT_VALIDADE_CARTAO)))
            .andExpect(jsonPath("$.[*].codigoCartao").value(hasItem(DEFAULT_CODIGO_CARTAO)))
            .andExpect(jsonPath("$.[*].emailComprador").value(hasItem(DEFAULT_EMAIL_COMPRADOR)));
    }

    @Test
    @Transactional
    void getIngresso() throws Exception {
        // Initialize the database
        ingressoRepository.saveAndFlush(ingresso);

        // Get the ingresso
        restIngressoMockMvc
            .perform(get(ENTITY_API_URL_ID, ingresso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ingresso.getId().intValue()))
            .andExpect(jsonPath("$.horarioJogo").value(DEFAULT_HORARIO_JOGO))
            .andExpect(jsonPath("$.timeMandante").value(DEFAULT_TIME_MANDANTE))
            .andExpect(jsonPath("$.timeVisitante").value(DEFAULT_TIME_VISITANTE))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.setorEstadio").value(DEFAULT_SETOR_ESTADIO))
            .andExpect(jsonPath("$.assentoEstadio").value(DEFAULT_ASSENTO_ESTADIO))
            .andExpect(jsonPath("$.nomeComprador").value(DEFAULT_NOME_COMPRADOR))
            .andExpect(jsonPath("$.cpfComprador").value(DEFAULT_CPF_COMPRADOR))
            .andExpect(jsonPath("$.nascimentoComprador").value(DEFAULT_NASCIMENTO_COMPRADOR.toString()))
            .andExpect(jsonPath("$.numeroCartao").value(DEFAULT_NUMERO_CARTAO))
            .andExpect(jsonPath("$.validadeCartao").value(DEFAULT_VALIDADE_CARTAO))
            .andExpect(jsonPath("$.codigoCartao").value(DEFAULT_CODIGO_CARTAO))
            .andExpect(jsonPath("$.emailComprador").value(DEFAULT_EMAIL_COMPRADOR));
    }

    @Test
    @Transactional
    void getNonExistingIngresso() throws Exception {
        // Get the ingresso
        restIngressoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
