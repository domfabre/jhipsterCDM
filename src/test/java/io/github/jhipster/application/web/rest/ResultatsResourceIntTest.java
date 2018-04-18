package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterCdmApp;

import io.github.jhipster.application.domain.Resultats;
import io.github.jhipster.application.repository.ResultatsRepository;
import io.github.jhipster.application.service.dto.ResultatsDTO;
import io.github.jhipster.application.service.mapper.ResultatsMapper;
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
 * Test class for the ResultatsResource REST controller.
 *
 * @see ResultatsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterCdmApp.class)
public class ResultatsResourceIntTest {

    private static final Integer DEFAULT_BUT = 1;
    private static final Integer UPDATED_BUT = 2;

    private static final Integer DEFAULT_TAB = 1;
    private static final Integer UPDATED_TAB = 2;

    private static final Integer DEFAULT_JAUNE = 1;
    private static final Integer UPDATED_JAUNE = 2;

    private static final Integer DEFAULT_ROUGE = 1;
    private static final Integer UPDATED_ROUGE = 2;

    private static final Boolean DEFAULT_DOMICILE = false;
    private static final Boolean UPDATED_DOMICILE = true;

    @Autowired
    private ResultatsRepository resultatsRepository;

    @Autowired
    private ResultatsMapper resultatsMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResultatsMockMvc;

    private Resultats resultats;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResultatsResource resultatsResource = new ResultatsResource(resultatsRepository, resultatsMapper);
        this.restResultatsMockMvc = MockMvcBuilders.standaloneSetup(resultatsResource)
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
    public static Resultats createEntity(EntityManager em) {
        Resultats resultats = new Resultats()
            .but(DEFAULT_BUT)
            .tab(DEFAULT_TAB)
            .jaune(DEFAULT_JAUNE)
            .rouge(DEFAULT_ROUGE)
            .domicile(DEFAULT_DOMICILE);
        return resultats;
    }

    @Before
    public void initTest() {
        resultats = createEntity(em);
    }

    @Test
    @Transactional
    public void createResultats() throws Exception {
        int databaseSizeBeforeCreate = resultatsRepository.findAll().size();

        // Create the Resultats
        ResultatsDTO resultatsDTO = resultatsMapper.toDto(resultats);
        restResultatsMockMvc.perform(post("/api/resultats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultatsDTO)))
            .andExpect(status().isCreated());

        // Validate the Resultats in the database
        List<Resultats> resultatsList = resultatsRepository.findAll();
        assertThat(resultatsList).hasSize(databaseSizeBeforeCreate + 1);
        Resultats testResultats = resultatsList.get(resultatsList.size() - 1);
        assertThat(testResultats.getBut()).isEqualTo(DEFAULT_BUT);
        assertThat(testResultats.getTab()).isEqualTo(DEFAULT_TAB);
        assertThat(testResultats.getJaune()).isEqualTo(DEFAULT_JAUNE);
        assertThat(testResultats.getRouge()).isEqualTo(DEFAULT_ROUGE);
        assertThat(testResultats.isDomicile()).isEqualTo(DEFAULT_DOMICILE);
    }

    @Test
    @Transactional
    public void createResultatsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resultatsRepository.findAll().size();

        // Create the Resultats with an existing ID
        resultats.setId(1L);
        ResultatsDTO resultatsDTO = resultatsMapper.toDto(resultats);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResultatsMockMvc.perform(post("/api/resultats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultatsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Resultats in the database
        List<Resultats> resultatsList = resultatsRepository.findAll();
        assertThat(resultatsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResultats() throws Exception {
        // Initialize the database
        resultatsRepository.saveAndFlush(resultats);

        // Get all the resultatsList
        restResultatsMockMvc.perform(get("/api/resultats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resultats.getId().intValue())))
            .andExpect(jsonPath("$.[*].but").value(hasItem(DEFAULT_BUT)))
            .andExpect(jsonPath("$.[*].tab").value(hasItem(DEFAULT_TAB)))
            .andExpect(jsonPath("$.[*].jaune").value(hasItem(DEFAULT_JAUNE)))
            .andExpect(jsonPath("$.[*].rouge").value(hasItem(DEFAULT_ROUGE)))
            .andExpect(jsonPath("$.[*].domicile").value(hasItem(DEFAULT_DOMICILE.booleanValue())));
    }

    @Test
    @Transactional
    public void getResultats() throws Exception {
        // Initialize the database
        resultatsRepository.saveAndFlush(resultats);

        // Get the resultats
        restResultatsMockMvc.perform(get("/api/resultats/{id}", resultats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resultats.getId().intValue()))
            .andExpect(jsonPath("$.but").value(DEFAULT_BUT))
            .andExpect(jsonPath("$.tab").value(DEFAULT_TAB))
            .andExpect(jsonPath("$.jaune").value(DEFAULT_JAUNE))
            .andExpect(jsonPath("$.rouge").value(DEFAULT_ROUGE))
            .andExpect(jsonPath("$.domicile").value(DEFAULT_DOMICILE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingResultats() throws Exception {
        // Get the resultats
        restResultatsMockMvc.perform(get("/api/resultats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultats() throws Exception {
        // Initialize the database
        resultatsRepository.saveAndFlush(resultats);
        int databaseSizeBeforeUpdate = resultatsRepository.findAll().size();

        // Update the resultats
        Resultats updatedResultats = resultatsRepository.findOne(resultats.getId());
        // Disconnect from session so that the updates on updatedResultats are not directly saved in db
        em.detach(updatedResultats);
        updatedResultats
            .but(UPDATED_BUT)
            .tab(UPDATED_TAB)
            .jaune(UPDATED_JAUNE)
            .rouge(UPDATED_ROUGE)
            .domicile(UPDATED_DOMICILE);
        ResultatsDTO resultatsDTO = resultatsMapper.toDto(updatedResultats);

        restResultatsMockMvc.perform(put("/api/resultats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultatsDTO)))
            .andExpect(status().isOk());

        // Validate the Resultats in the database
        List<Resultats> resultatsList = resultatsRepository.findAll();
        assertThat(resultatsList).hasSize(databaseSizeBeforeUpdate);
        Resultats testResultats = resultatsList.get(resultatsList.size() - 1);
        assertThat(testResultats.getBut()).isEqualTo(UPDATED_BUT);
        assertThat(testResultats.getTab()).isEqualTo(UPDATED_TAB);
        assertThat(testResultats.getJaune()).isEqualTo(UPDATED_JAUNE);
        assertThat(testResultats.getRouge()).isEqualTo(UPDATED_ROUGE);
        assertThat(testResultats.isDomicile()).isEqualTo(UPDATED_DOMICILE);
    }

    @Test
    @Transactional
    public void updateNonExistingResultats() throws Exception {
        int databaseSizeBeforeUpdate = resultatsRepository.findAll().size();

        // Create the Resultats
        ResultatsDTO resultatsDTO = resultatsMapper.toDto(resultats);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResultatsMockMvc.perform(put("/api/resultats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultatsDTO)))
            .andExpect(status().isCreated());

        // Validate the Resultats in the database
        List<Resultats> resultatsList = resultatsRepository.findAll();
        assertThat(resultatsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResultats() throws Exception {
        // Initialize the database
        resultatsRepository.saveAndFlush(resultats);
        int databaseSizeBeforeDelete = resultatsRepository.findAll().size();

        // Get the resultats
        restResultatsMockMvc.perform(delete("/api/resultats/{id}", resultats.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Resultats> resultatsList = resultatsRepository.findAll();
        assertThat(resultatsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resultats.class);
        Resultats resultats1 = new Resultats();
        resultats1.setId(1L);
        Resultats resultats2 = new Resultats();
        resultats2.setId(resultats1.getId());
        assertThat(resultats1).isEqualTo(resultats2);
        resultats2.setId(2L);
        assertThat(resultats1).isNotEqualTo(resultats2);
        resultats1.setId(null);
        assertThat(resultats1).isNotEqualTo(resultats2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResultatsDTO.class);
        ResultatsDTO resultatsDTO1 = new ResultatsDTO();
        resultatsDTO1.setId(1L);
        ResultatsDTO resultatsDTO2 = new ResultatsDTO();
        assertThat(resultatsDTO1).isNotEqualTo(resultatsDTO2);
        resultatsDTO2.setId(resultatsDTO1.getId());
        assertThat(resultatsDTO1).isEqualTo(resultatsDTO2);
        resultatsDTO2.setId(2L);
        assertThat(resultatsDTO1).isNotEqualTo(resultatsDTO2);
        resultatsDTO1.setId(null);
        assertThat(resultatsDTO1).isNotEqualTo(resultatsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(resultatsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(resultatsMapper.fromId(null)).isNull();
    }
}
