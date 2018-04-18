package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterCdmApp;

import io.github.jhipster.application.domain.Nations;
import io.github.jhipster.application.repository.NationsRepository;
import io.github.jhipster.application.service.NationsService;
import io.github.jhipster.application.service.dto.NationsDTO;
import io.github.jhipster.application.service.mapper.NationsMapper;
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

import io.github.jhipster.application.domain.enumeration.Groupes;
/**
 * Test class for the NationsResource REST controller.
 *
 * @see NationsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterCdmApp.class)
public class NationsResourceIntTest {

    private static final String DEFAULT_NATION = "AAAAAAAAAA";
    private static final String UPDATED_NATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONFEDERATION = "AAAAAAAAAA";
    private static final String UPDATED_CONFEDERATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_FIFA = 1;
    private static final Integer UPDATED_FIFA = 2;

    private static final Integer DEFAULT_CDM = 1;
    private static final Integer UPDATED_CDM = 2;

    private static final Groupes DEFAULT_GROUPE = Groupes.A;
    private static final Groupes UPDATED_GROUPE = Groupes.B;

    @Autowired
    private NationsRepository nationsRepository;

    @Autowired
    private NationsMapper nationsMapper;

    @Autowired
    private NationsService nationsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNationsMockMvc;

    private Nations nations;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NationsResource nationsResource = new NationsResource(nationsService);
        this.restNationsMockMvc = MockMvcBuilders.standaloneSetup(nationsResource)
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
    public static Nations createEntity(EntityManager em) {
        Nations nations = new Nations()
            .nation(DEFAULT_NATION)
            .confederation(DEFAULT_CONFEDERATION)
            .fifa(DEFAULT_FIFA)
            .cdm(DEFAULT_CDM)
            .groupe(DEFAULT_GROUPE);
        return nations;
    }

    @Before
    public void initTest() {
        nations = createEntity(em);
    }

    @Test
    @Transactional
    public void createNations() throws Exception {
        int databaseSizeBeforeCreate = nationsRepository.findAll().size();

        // Create the Nations
        NationsDTO nationsDTO = nationsMapper.toDto(nations);
        restNationsMockMvc.perform(post("/api/nations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nationsDTO)))
            .andExpect(status().isCreated());

        // Validate the Nations in the database
        List<Nations> nationsList = nationsRepository.findAll();
        assertThat(nationsList).hasSize(databaseSizeBeforeCreate + 1);
        Nations testNations = nationsList.get(nationsList.size() - 1);
        assertThat(testNations.getNation()).isEqualTo(DEFAULT_NATION);
        assertThat(testNations.getConfederation()).isEqualTo(DEFAULT_CONFEDERATION);
        assertThat(testNations.getFifa()).isEqualTo(DEFAULT_FIFA);
        assertThat(testNations.getCdm()).isEqualTo(DEFAULT_CDM);
        assertThat(testNations.getGroupe()).isEqualTo(DEFAULT_GROUPE);
    }

    @Test
    @Transactional
    public void createNationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nationsRepository.findAll().size();

        // Create the Nations with an existing ID
        nations.setId(1L);
        NationsDTO nationsDTO = nationsMapper.toDto(nations);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNationsMockMvc.perform(post("/api/nations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nations in the database
        List<Nations> nationsList = nationsRepository.findAll();
        assertThat(nationsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNationIsRequired() throws Exception {
        int databaseSizeBeforeTest = nationsRepository.findAll().size();
        // set the field null
        nations.setNation(null);

        // Create the Nations, which fails.
        NationsDTO nationsDTO = nationsMapper.toDto(nations);

        restNationsMockMvc.perform(post("/api/nations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nationsDTO)))
            .andExpect(status().isBadRequest());

        List<Nations> nationsList = nationsRepository.findAll();
        assertThat(nationsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNations() throws Exception {
        // Initialize the database
        nationsRepository.saveAndFlush(nations);

        // Get all the nationsList
        restNationsMockMvc.perform(get("/api/nations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nations.getId().intValue())))
            .andExpect(jsonPath("$.[*].nation").value(hasItem(DEFAULT_NATION.toString())))
            .andExpect(jsonPath("$.[*].confederation").value(hasItem(DEFAULT_CONFEDERATION.toString())))
            .andExpect(jsonPath("$.[*].fifa").value(hasItem(DEFAULT_FIFA)))
            .andExpect(jsonPath("$.[*].cdm").value(hasItem(DEFAULT_CDM)))
            .andExpect(jsonPath("$.[*].groupe").value(hasItem(DEFAULT_GROUPE.toString())));
    }

    @Test
    @Transactional
    public void getNations() throws Exception {
        // Initialize the database
        nationsRepository.saveAndFlush(nations);

        // Get the nations
        restNationsMockMvc.perform(get("/api/nations/{id}", nations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nations.getId().intValue()))
            .andExpect(jsonPath("$.nation").value(DEFAULT_NATION.toString()))
            .andExpect(jsonPath("$.confederation").value(DEFAULT_CONFEDERATION.toString()))
            .andExpect(jsonPath("$.fifa").value(DEFAULT_FIFA))
            .andExpect(jsonPath("$.cdm").value(DEFAULT_CDM))
            .andExpect(jsonPath("$.groupe").value(DEFAULT_GROUPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNations() throws Exception {
        // Get the nations
        restNationsMockMvc.perform(get("/api/nations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNations() throws Exception {
        // Initialize the database
        nationsRepository.saveAndFlush(nations);
        int databaseSizeBeforeUpdate = nationsRepository.findAll().size();

        // Update the nations
        Nations updatedNations = nationsRepository.findOne(nations.getId());
        // Disconnect from session so that the updates on updatedNations are not directly saved in db
        em.detach(updatedNations);
        updatedNations
            .nation(UPDATED_NATION)
            .confederation(UPDATED_CONFEDERATION)
            .fifa(UPDATED_FIFA)
            .cdm(UPDATED_CDM)
            .groupe(UPDATED_GROUPE);
        NationsDTO nationsDTO = nationsMapper.toDto(updatedNations);

        restNationsMockMvc.perform(put("/api/nations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nationsDTO)))
            .andExpect(status().isOk());

        // Validate the Nations in the database
        List<Nations> nationsList = nationsRepository.findAll();
        assertThat(nationsList).hasSize(databaseSizeBeforeUpdate);
        Nations testNations = nationsList.get(nationsList.size() - 1);
        assertThat(testNations.getNation()).isEqualTo(UPDATED_NATION);
        assertThat(testNations.getConfederation()).isEqualTo(UPDATED_CONFEDERATION);
        assertThat(testNations.getFifa()).isEqualTo(UPDATED_FIFA);
        assertThat(testNations.getCdm()).isEqualTo(UPDATED_CDM);
        assertThat(testNations.getGroupe()).isEqualTo(UPDATED_GROUPE);
    }

    @Test
    @Transactional
    public void updateNonExistingNations() throws Exception {
        int databaseSizeBeforeUpdate = nationsRepository.findAll().size();

        // Create the Nations
        NationsDTO nationsDTO = nationsMapper.toDto(nations);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNationsMockMvc.perform(put("/api/nations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nationsDTO)))
            .andExpect(status().isCreated());

        // Validate the Nations in the database
        List<Nations> nationsList = nationsRepository.findAll();
        assertThat(nationsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNations() throws Exception {
        // Initialize the database
        nationsRepository.saveAndFlush(nations);
        int databaseSizeBeforeDelete = nationsRepository.findAll().size();

        // Get the nations
        restNationsMockMvc.perform(delete("/api/nations/{id}", nations.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nations> nationsList = nationsRepository.findAll();
        assertThat(nationsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nations.class);
        Nations nations1 = new Nations();
        nations1.setId(1L);
        Nations nations2 = new Nations();
        nations2.setId(nations1.getId());
        assertThat(nations1).isEqualTo(nations2);
        nations2.setId(2L);
        assertThat(nations1).isNotEqualTo(nations2);
        nations1.setId(null);
        assertThat(nations1).isNotEqualTo(nations2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NationsDTO.class);
        NationsDTO nationsDTO1 = new NationsDTO();
        nationsDTO1.setId(1L);
        NationsDTO nationsDTO2 = new NationsDTO();
        assertThat(nationsDTO1).isNotEqualTo(nationsDTO2);
        nationsDTO2.setId(nationsDTO1.getId());
        assertThat(nationsDTO1).isEqualTo(nationsDTO2);
        nationsDTO2.setId(2L);
        assertThat(nationsDTO1).isNotEqualTo(nationsDTO2);
        nationsDTO1.setId(null);
        assertThat(nationsDTO1).isNotEqualTo(nationsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nationsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nationsMapper.fromId(null)).isNull();
    }
}
