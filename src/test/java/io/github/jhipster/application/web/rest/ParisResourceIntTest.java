package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterCdmApp;

import io.github.jhipster.application.domain.Paris;
import io.github.jhipster.application.repository.ParisRepository;
import io.github.jhipster.application.service.dto.ParisDTO;
import io.github.jhipster.application.service.mapper.ParisMapper;
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
 * Test class for the ParisResource REST controller.
 *
 * @see ParisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterCdmApp.class)
public class ParisResourceIntTest {

    private static final Integer DEFAULT_BUT = 1;
    private static final Integer UPDATED_BUT = 2;

    private static final Boolean DEFAULT_JOCKER = false;
    private static final Boolean UPDATED_JOCKER = true;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    @Autowired
    private ParisRepository parisRepository;

    @Autowired
    private ParisMapper parisMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restParisMockMvc;

    private Paris paris;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParisResource parisResource = new ParisResource(parisRepository, parisMapper);
        this.restParisMockMvc = MockMvcBuilders.standaloneSetup(parisResource)
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
    public static Paris createEntity(EntityManager em) {
        Paris paris = new Paris()
            .but(DEFAULT_BUT)
            .jocker(DEFAULT_JOCKER)
            .point(DEFAULT_POINT);
        return paris;
    }

    @Before
    public void initTest() {
        paris = createEntity(em);
    }

    @Test
    @Transactional
    public void createParis() throws Exception {
        int databaseSizeBeforeCreate = parisRepository.findAll().size();

        // Create the Paris
        ParisDTO parisDTO = parisMapper.toDto(paris);
        restParisMockMvc.perform(post("/api/parises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parisDTO)))
            .andExpect(status().isCreated());

        // Validate the Paris in the database
        List<Paris> parisList = parisRepository.findAll();
        assertThat(parisList).hasSize(databaseSizeBeforeCreate + 1);
        Paris testParis = parisList.get(parisList.size() - 1);
        assertThat(testParis.getBut()).isEqualTo(DEFAULT_BUT);
        assertThat(testParis.isJocker()).isEqualTo(DEFAULT_JOCKER);
        assertThat(testParis.getPoint()).isEqualTo(DEFAULT_POINT);
    }

    @Test
    @Transactional
    public void createParisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parisRepository.findAll().size();

        // Create the Paris with an existing ID
        paris.setId(1L);
        ParisDTO parisDTO = parisMapper.toDto(paris);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParisMockMvc.perform(post("/api/parises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paris in the database
        List<Paris> parisList = parisRepository.findAll();
        assertThat(parisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllParises() throws Exception {
        // Initialize the database
        parisRepository.saveAndFlush(paris);

        // Get all the parisList
        restParisMockMvc.perform(get("/api/parises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paris.getId().intValue())))
            .andExpect(jsonPath("$.[*].but").value(hasItem(DEFAULT_BUT)))
            .andExpect(jsonPath("$.[*].jocker").value(hasItem(DEFAULT_JOCKER.booleanValue())))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)));
    }

    @Test
    @Transactional
    public void getParis() throws Exception {
        // Initialize the database
        parisRepository.saveAndFlush(paris);

        // Get the paris
        restParisMockMvc.perform(get("/api/parises/{id}", paris.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paris.getId().intValue()))
            .andExpect(jsonPath("$.but").value(DEFAULT_BUT))
            .andExpect(jsonPath("$.jocker").value(DEFAULT_JOCKER.booleanValue()))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT));
    }

    @Test
    @Transactional
    public void getNonExistingParis() throws Exception {
        // Get the paris
        restParisMockMvc.perform(get("/api/parises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParis() throws Exception {
        // Initialize the database
        parisRepository.saveAndFlush(paris);
        int databaseSizeBeforeUpdate = parisRepository.findAll().size();

        // Update the paris
        Paris updatedParis = parisRepository.findOne(paris.getId());
        // Disconnect from session so that the updates on updatedParis are not directly saved in db
        em.detach(updatedParis);
        updatedParis
            .but(UPDATED_BUT)
            .jocker(UPDATED_JOCKER)
            .point(UPDATED_POINT);
        ParisDTO parisDTO = parisMapper.toDto(updatedParis);

        restParisMockMvc.perform(put("/api/parises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parisDTO)))
            .andExpect(status().isOk());

        // Validate the Paris in the database
        List<Paris> parisList = parisRepository.findAll();
        assertThat(parisList).hasSize(databaseSizeBeforeUpdate);
        Paris testParis = parisList.get(parisList.size() - 1);
        assertThat(testParis.getBut()).isEqualTo(UPDATED_BUT);
        assertThat(testParis.isJocker()).isEqualTo(UPDATED_JOCKER);
        assertThat(testParis.getPoint()).isEqualTo(UPDATED_POINT);
    }

    @Test
    @Transactional
    public void updateNonExistingParis() throws Exception {
        int databaseSizeBeforeUpdate = parisRepository.findAll().size();

        // Create the Paris
        ParisDTO parisDTO = parisMapper.toDto(paris);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restParisMockMvc.perform(put("/api/parises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parisDTO)))
            .andExpect(status().isCreated());

        // Validate the Paris in the database
        List<Paris> parisList = parisRepository.findAll();
        assertThat(parisList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteParis() throws Exception {
        // Initialize the database
        parisRepository.saveAndFlush(paris);
        int databaseSizeBeforeDelete = parisRepository.findAll().size();

        // Get the paris
        restParisMockMvc.perform(delete("/api/parises/{id}", paris.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Paris> parisList = parisRepository.findAll();
        assertThat(parisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paris.class);
        Paris paris1 = new Paris();
        paris1.setId(1L);
        Paris paris2 = new Paris();
        paris2.setId(paris1.getId());
        assertThat(paris1).isEqualTo(paris2);
        paris2.setId(2L);
        assertThat(paris1).isNotEqualTo(paris2);
        paris1.setId(null);
        assertThat(paris1).isNotEqualTo(paris2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParisDTO.class);
        ParisDTO parisDTO1 = new ParisDTO();
        parisDTO1.setId(1L);
        ParisDTO parisDTO2 = new ParisDTO();
        assertThat(parisDTO1).isNotEqualTo(parisDTO2);
        parisDTO2.setId(parisDTO1.getId());
        assertThat(parisDTO1).isEqualTo(parisDTO2);
        parisDTO2.setId(2L);
        assertThat(parisDTO1).isNotEqualTo(parisDTO2);
        parisDTO1.setId(null);
        assertThat(parisDTO1).isNotEqualTo(parisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(parisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(parisMapper.fromId(null)).isNull();
    }
}
