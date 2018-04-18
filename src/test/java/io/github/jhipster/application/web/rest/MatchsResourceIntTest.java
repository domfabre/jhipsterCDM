package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterCdmApp;

import io.github.jhipster.application.domain.Matchs;
import io.github.jhipster.application.repository.MatchsRepository;
import io.github.jhipster.application.service.dto.MatchsDTO;
import io.github.jhipster.application.service.mapper.MatchsMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Phases;
/**
 * Test class for the MatchsResource REST controller.
 *
 * @see MatchsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterCdmApp.class)
public class MatchsResourceIntTest {

    private static final String DEFAULT_MATCH = "AAAAAAAAAA";
    private static final String UPDATED_MATCH = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_HEURE = "AAAAAAAAAA";
    private static final String UPDATED_HEURE = "BBBBBBBBBB";

    private static final String DEFAULT_STADE = "AAAAAAAAAA";
    private static final String UPDATED_STADE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DOMICILE = false;
    private static final Boolean UPDATED_DOMICILE = true;

    private static final Phases DEFAULT_PHASE = Phases.J1;
    private static final Phases UPDATED_PHASE = Phases.J2;

    @Autowired
    private MatchsRepository matchsRepository;

    @Autowired
    private MatchsMapper matchsMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMatchsMockMvc;

    private Matchs matchs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MatchsResource matchsResource = new MatchsResource(matchsRepository, matchsMapper);
        this.restMatchsMockMvc = MockMvcBuilders.standaloneSetup(matchsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Matchs createEntity(EntityManager em) {
        Matchs matchs = new Matchs()
            .match(DEFAULT_MATCH)
            .date(DEFAULT_DATE)
            .heure(DEFAULT_HEURE)
            .stade(DEFAULT_STADE)
            .domicile(DEFAULT_DOMICILE)
            .phase(DEFAULT_PHASE);
        return matchs;
    }

    @Before
    public void initTest() {
        matchs = createEntity(em);
    }

    @Test
    @Transactional
    public void createMatchs() throws Exception {
        int databaseSizeBeforeCreate = matchsRepository.findAll().size();

        // Create the Matchs
        MatchsDTO matchsDTO = matchsMapper.toDto(matchs);
        restMatchsMockMvc.perform(post("/api/matchs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matchsDTO)))
            .andExpect(status().isCreated());

        // Validate the Matchs in the database
        List<Matchs> matchsList = matchsRepository.findAll();
        assertThat(matchsList).hasSize(databaseSizeBeforeCreate + 1);
        Matchs testMatchs = matchsList.get(matchsList.size() - 1);
        assertThat(testMatchs.getMatch()).isEqualTo(DEFAULT_MATCH);
        assertThat(testMatchs.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testMatchs.getHeure()).isEqualTo(DEFAULT_HEURE);
        assertThat(testMatchs.getStade()).isEqualTo(DEFAULT_STADE);
        assertThat(testMatchs.isDomicile()).isEqualTo(DEFAULT_DOMICILE);
        assertThat(testMatchs.getPhase()).isEqualTo(DEFAULT_PHASE);
    }

    @Test
    @Transactional
    public void createMatchsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = matchsRepository.findAll().size();

        // Create the Matchs with an existing ID
        matchs.setId(1L);
        MatchsDTO matchsDTO = matchsMapper.toDto(matchs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMatchsMockMvc.perform(post("/api/matchs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matchsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Matchs in the database
        List<Matchs> matchsList = matchsRepository.findAll();
        assertThat(matchsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMatchIsRequired() throws Exception {
        int databaseSizeBeforeTest = matchsRepository.findAll().size();
        // set the field null
        matchs.setMatch(null);

        // Create the Matchs, which fails.
        MatchsDTO matchsDTO = matchsMapper.toDto(matchs);

        restMatchsMockMvc.perform(post("/api/matchs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matchsDTO)))
            .andExpect(status().isBadRequest());

        List<Matchs> matchsList = matchsRepository.findAll();
        assertThat(matchsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMatchs() throws Exception {
        // Initialize the database
        matchsRepository.saveAndFlush(matchs);

        // Get all the matchsList
        restMatchsMockMvc.perform(get("/api/matchs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(matchs.getId().intValue())))
            .andExpect(jsonPath("$.[*].match").value(hasItem(DEFAULT_MATCH.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].heure").value(hasItem(DEFAULT_HEURE.toString())))
            .andExpect(jsonPath("$.[*].stade").value(hasItem(DEFAULT_STADE.toString())))
            .andExpect(jsonPath("$.[*].domicile").value(hasItem(DEFAULT_DOMICILE.booleanValue())))
            .andExpect(jsonPath("$.[*].phase").value(hasItem(DEFAULT_PHASE.toString())));
    }

    @Test
    @Transactional
    public void getMatchs() throws Exception {
        // Initialize the database
        matchsRepository.saveAndFlush(matchs);

        // Get the matchs
        restMatchsMockMvc.perform(get("/api/matchs/{id}", matchs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(matchs.getId().intValue()))
            .andExpect(jsonPath("$.match").value(DEFAULT_MATCH.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.heure").value(DEFAULT_HEURE.toString()))
            .andExpect(jsonPath("$.stade").value(DEFAULT_STADE.toString()))
            .andExpect(jsonPath("$.domicile").value(DEFAULT_DOMICILE.booleanValue()))
            .andExpect(jsonPath("$.phase").value(DEFAULT_PHASE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMatchs() throws Exception {
        // Get the matchs
        restMatchsMockMvc.perform(get("/api/matchs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMatchs() throws Exception {
        // Initialize the database
        matchsRepository.saveAndFlush(matchs);
        int databaseSizeBeforeUpdate = matchsRepository.findAll().size();

        // Update the matchs
        Matchs updatedMatchs = matchsRepository.findOne(matchs.getId());
        // Disconnect from session so that the updates on updatedMatchs are not directly saved in db
        em.detach(updatedMatchs);
        updatedMatchs
            .match(UPDATED_MATCH)
            .date(UPDATED_DATE)
            .heure(UPDATED_HEURE)
            .stade(UPDATED_STADE)
            .domicile(UPDATED_DOMICILE)
            .phase(UPDATED_PHASE);
        MatchsDTO matchsDTO = matchsMapper.toDto(updatedMatchs);

        restMatchsMockMvc.perform(put("/api/matchs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matchsDTO)))
            .andExpect(status().isOk());

        // Validate the Matchs in the database
        List<Matchs> matchsList = matchsRepository.findAll();
        assertThat(matchsList).hasSize(databaseSizeBeforeUpdate);
        Matchs testMatchs = matchsList.get(matchsList.size() - 1);
        assertThat(testMatchs.getMatch()).isEqualTo(UPDATED_MATCH);
        assertThat(testMatchs.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testMatchs.getHeure()).isEqualTo(UPDATED_HEURE);
        assertThat(testMatchs.getStade()).isEqualTo(UPDATED_STADE);
        assertThat(testMatchs.isDomicile()).isEqualTo(UPDATED_DOMICILE);
        assertThat(testMatchs.getPhase()).isEqualTo(UPDATED_PHASE);
    }

    @Test
    @Transactional
    public void updateNonExistingMatchs() throws Exception {
        int databaseSizeBeforeUpdate = matchsRepository.findAll().size();

        // Create the Matchs
        MatchsDTO matchsDTO = matchsMapper.toDto(matchs);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMatchsMockMvc.perform(put("/api/matchs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(matchsDTO)))
            .andExpect(status().isCreated());

        // Validate the Matchs in the database
        List<Matchs> matchsList = matchsRepository.findAll();
        assertThat(matchsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMatchs() throws Exception {
        // Initialize the database
        matchsRepository.saveAndFlush(matchs);
        int databaseSizeBeforeDelete = matchsRepository.findAll().size();

        // Get the matchs
        restMatchsMockMvc.perform(delete("/api/matchs/{id}", matchs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Matchs> matchsList = matchsRepository.findAll();
        assertThat(matchsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Matchs.class);
        Matchs matchs1 = new Matchs();
        matchs1.setId(1L);
        Matchs matchs2 = new Matchs();
        matchs2.setId(matchs1.getId());
        assertThat(matchs1).isEqualTo(matchs2);
        matchs2.setId(2L);
        assertThat(matchs1).isNotEqualTo(matchs2);
        matchs1.setId(null);
        assertThat(matchs1).isNotEqualTo(matchs2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MatchsDTO.class);
        MatchsDTO matchsDTO1 = new MatchsDTO();
        matchsDTO1.setId(1L);
        MatchsDTO matchsDTO2 = new MatchsDTO();
        assertThat(matchsDTO1).isNotEqualTo(matchsDTO2);
        matchsDTO2.setId(matchsDTO1.getId());
        assertThat(matchsDTO1).isEqualTo(matchsDTO2);
        matchsDTO2.setId(2L);
        assertThat(matchsDTO1).isNotEqualTo(matchsDTO2);
        matchsDTO1.setId(null);
        assertThat(matchsDTO1).isNotEqualTo(matchsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(matchsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(matchsMapper.fromId(null)).isNull();
    }
}
