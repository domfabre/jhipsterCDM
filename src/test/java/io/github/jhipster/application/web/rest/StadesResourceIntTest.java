package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterCdmApp;

import io.github.jhipster.application.domain.Stades;
import io.github.jhipster.application.repository.StadesRepository;
import io.github.jhipster.application.service.StadesService;
import io.github.jhipster.application.service.dto.StadesDTO;
import io.github.jhipster.application.service.mapper.StadesMapper;
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

/**
 * Test class for the StadesResource REST controller.
 *
 * @see StadesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterCdmApp.class)
public class StadesResourceIntTest {

    private static final String DEFAULT_STADE = "AAAAAAAAAA";
    private static final String UPDATED_STADE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAPACITE = 1;
    private static final Integer UPDATED_CAPACITE = 2;

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    @Autowired
    private StadesRepository stadesRepository;

    @Autowired
    private StadesMapper stadesMapper;

    @Autowired
    private StadesService stadesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStadesMockMvc;

    private Stades stades;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StadesResource stadesResource = new StadesResource(stadesService);
        this.restStadesMockMvc = MockMvcBuilders.standaloneSetup(stadesResource)
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
    public static Stades createEntity(EntityManager em) {
        Stades stades = new Stades()
            .stade(DEFAULT_STADE)
            .ville(DEFAULT_VILLE)
            .capacite(DEFAULT_CAPACITE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return stades;
    }

    @Before
    public void initTest() {
        stades = createEntity(em);
    }

    @Test
    @Transactional
    public void createStades() throws Exception {
        int databaseSizeBeforeCreate = stadesRepository.findAll().size();

        // Create the Stades
        StadesDTO stadesDTO = stadesMapper.toDto(stades);
        restStadesMockMvc.perform(post("/api/stades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadesDTO)))
            .andExpect(status().isCreated());

        // Validate the Stades in the database
        List<Stades> stadesList = stadesRepository.findAll();
        assertThat(stadesList).hasSize(databaseSizeBeforeCreate + 1);
        Stades testStades = stadesList.get(stadesList.size() - 1);
        assertThat(testStades.getStade()).isEqualTo(DEFAULT_STADE);
        assertThat(testStades.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testStades.getCapacite()).isEqualTo(DEFAULT_CAPACITE);
        assertThat(testStades.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testStades.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    public void createStadesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stadesRepository.findAll().size();

        // Create the Stades with an existing ID
        stades.setId(1L);
        StadesDTO stadesDTO = stadesMapper.toDto(stades);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStadesMockMvc.perform(post("/api/stades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Stades in the database
        List<Stades> stadesList = stadesRepository.findAll();
        assertThat(stadesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stadesRepository.findAll().size();
        // set the field null
        stades.setStade(null);

        // Create the Stades, which fails.
        StadesDTO stadesDTO = stadesMapper.toDto(stades);

        restStadesMockMvc.perform(post("/api/stades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadesDTO)))
            .andExpect(status().isBadRequest());

        List<Stades> stadesList = stadesRepository.findAll();
        assertThat(stadesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVilleIsRequired() throws Exception {
        int databaseSizeBeforeTest = stadesRepository.findAll().size();
        // set the field null
        stades.setVille(null);

        // Create the Stades, which fails.
        StadesDTO stadesDTO = stadesMapper.toDto(stades);

        restStadesMockMvc.perform(post("/api/stades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadesDTO)))
            .andExpect(status().isBadRequest());

        List<Stades> stadesList = stadesRepository.findAll();
        assertThat(stadesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStades() throws Exception {
        // Initialize the database
        stadesRepository.saveAndFlush(stades);

        // Get all the stadesList
        restStadesMockMvc.perform(get("/api/stades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stades.getId().intValue())))
            .andExpect(jsonPath("$.[*].stade").value(hasItem(DEFAULT_STADE.toString())))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE.toString())))
            .andExpect(jsonPath("$.[*].capacite").value(hasItem(DEFAULT_CAPACITE)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())));
    }

    @Test
    @Transactional
    public void getStades() throws Exception {
        // Initialize the database
        stadesRepository.saveAndFlush(stades);

        // Get the stades
        restStadesMockMvc.perform(get("/api/stades/{id}", stades.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stades.getId().intValue()))
            .andExpect(jsonPath("$.stade").value(DEFAULT_STADE.toString()))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE.toString()))
            .andExpect(jsonPath("$.capacite").value(DEFAULT_CAPACITE))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStades() throws Exception {
        // Get the stades
        restStadesMockMvc.perform(get("/api/stades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStades() throws Exception {
        // Initialize the database
        stadesRepository.saveAndFlush(stades);
        int databaseSizeBeforeUpdate = stadesRepository.findAll().size();

        // Update the stades
        Stades updatedStades = stadesRepository.findOne(stades.getId());
        // Disconnect from session so that the updates on updatedStades are not directly saved in db
        em.detach(updatedStades);
        updatedStades
            .stade(UPDATED_STADE)
            .ville(UPDATED_VILLE)
            .capacite(UPDATED_CAPACITE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        StadesDTO stadesDTO = stadesMapper.toDto(updatedStades);

        restStadesMockMvc.perform(put("/api/stades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadesDTO)))
            .andExpect(status().isOk());

        // Validate the Stades in the database
        List<Stades> stadesList = stadesRepository.findAll();
        assertThat(stadesList).hasSize(databaseSizeBeforeUpdate);
        Stades testStades = stadesList.get(stadesList.size() - 1);
        assertThat(testStades.getStade()).isEqualTo(UPDATED_STADE);
        assertThat(testStades.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testStades.getCapacite()).isEqualTo(UPDATED_CAPACITE);
        assertThat(testStades.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testStades.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingStades() throws Exception {
        int databaseSizeBeforeUpdate = stadesRepository.findAll().size();

        // Create the Stades
        StadesDTO stadesDTO = stadesMapper.toDto(stades);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStadesMockMvc.perform(put("/api/stades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadesDTO)))
            .andExpect(status().isCreated());

        // Validate the Stades in the database
        List<Stades> stadesList = stadesRepository.findAll();
        assertThat(stadesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStades() throws Exception {
        // Initialize the database
        stadesRepository.saveAndFlush(stades);
        int databaseSizeBeforeDelete = stadesRepository.findAll().size();

        // Get the stades
        restStadesMockMvc.perform(delete("/api/stades/{id}", stades.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Stades> stadesList = stadesRepository.findAll();
        assertThat(stadesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stades.class);
        Stades stades1 = new Stades();
        stades1.setId(1L);
        Stades stades2 = new Stades();
        stades2.setId(stades1.getId());
        assertThat(stades1).isEqualTo(stades2);
        stades2.setId(2L);
        assertThat(stades1).isNotEqualTo(stades2);
        stades1.setId(null);
        assertThat(stades1).isNotEqualTo(stades2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StadesDTO.class);
        StadesDTO stadesDTO1 = new StadesDTO();
        stadesDTO1.setId(1L);
        StadesDTO stadesDTO2 = new StadesDTO();
        assertThat(stadesDTO1).isNotEqualTo(stadesDTO2);
        stadesDTO2.setId(stadesDTO1.getId());
        assertThat(stadesDTO1).isEqualTo(stadesDTO2);
        stadesDTO2.setId(2L);
        assertThat(stadesDTO1).isNotEqualTo(stadesDTO2);
        stadesDTO1.setId(null);
        assertThat(stadesDTO1).isNotEqualTo(stadesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(stadesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(stadesMapper.fromId(null)).isNull();
    }
}
