package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterCdmApp;

import io.github.jhipster.application.domain.Joueurs;
import io.github.jhipster.application.repository.JoueursRepository;
import io.github.jhipster.application.service.JoueursService;
import io.github.jhipster.application.service.dto.JoueursDTO;
import io.github.jhipster.application.service.mapper.JoueursMapper;
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
 * Test class for the JoueursResource REST controller.
 *
 * @see JoueursResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterCdmApp.class)
public class JoueursResourceIntTest {

    private static final String DEFAULT_JOUEUR = "AAAAAAAAAA";
    private static final String UPDATED_JOUEUR = "BBBBBBBBBB";

    private static final String DEFAULT_COURRIEL = "AAAAAAAAAA";
    private static final String UPDATED_COURRIEL = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    @Autowired
    private JoueursRepository joueursRepository;

    @Autowired
    private JoueursMapper joueursMapper;

    @Autowired
    private JoueursService joueursService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJoueursMockMvc;

    private Joueurs joueurs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JoueursResource joueursResource = new JoueursResource(joueursService);
        this.restJoueursMockMvc = MockMvcBuilders.standaloneSetup(joueursResource)
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
    public static Joueurs createEntity(EntityManager em) {
        Joueurs joueurs = new Joueurs()
            .joueur(DEFAULT_JOUEUR)
            .courriel(DEFAULT_COURRIEL)
            .avatar(DEFAULT_AVATAR);
        return joueurs;
    }

    @Before
    public void initTest() {
        joueurs = createEntity(em);
    }

    @Test
    @Transactional
    public void createJoueurs() throws Exception {
        int databaseSizeBeforeCreate = joueursRepository.findAll().size();

        // Create the Joueurs
        JoueursDTO joueursDTO = joueursMapper.toDto(joueurs);
        restJoueursMockMvc.perform(post("/api/joueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(joueursDTO)))
            .andExpect(status().isCreated());

        // Validate the Joueurs in the database
        List<Joueurs> joueursList = joueursRepository.findAll();
        assertThat(joueursList).hasSize(databaseSizeBeforeCreate + 1);
        Joueurs testJoueurs = joueursList.get(joueursList.size() - 1);
        assertThat(testJoueurs.getJoueur()).isEqualTo(DEFAULT_JOUEUR);
        assertThat(testJoueurs.getCourriel()).isEqualTo(DEFAULT_COURRIEL);
        assertThat(testJoueurs.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    }

    @Test
    @Transactional
    public void createJoueursWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = joueursRepository.findAll().size();

        // Create the Joueurs with an existing ID
        joueurs.setId(1L);
        JoueursDTO joueursDTO = joueursMapper.toDto(joueurs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJoueursMockMvc.perform(post("/api/joueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(joueursDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Joueurs in the database
        List<Joueurs> joueursList = joueursRepository.findAll();
        assertThat(joueursList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkJoueurIsRequired() throws Exception {
        int databaseSizeBeforeTest = joueursRepository.findAll().size();
        // set the field null
        joueurs.setJoueur(null);

        // Create the Joueurs, which fails.
        JoueursDTO joueursDTO = joueursMapper.toDto(joueurs);

        restJoueursMockMvc.perform(post("/api/joueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(joueursDTO)))
            .andExpect(status().isBadRequest());

        List<Joueurs> joueursList = joueursRepository.findAll();
        assertThat(joueursList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJoueurs() throws Exception {
        // Initialize the database
        joueursRepository.saveAndFlush(joueurs);

        // Get all the joueursList
        restJoueursMockMvc.perform(get("/api/joueurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(joueurs.getId().intValue())))
            .andExpect(jsonPath("$.[*].joueur").value(hasItem(DEFAULT_JOUEUR.toString())))
            .andExpect(jsonPath("$.[*].courriel").value(hasItem(DEFAULT_COURRIEL.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())));
    }

    @Test
    @Transactional
    public void getJoueurs() throws Exception {
        // Initialize the database
        joueursRepository.saveAndFlush(joueurs);

        // Get the joueurs
        restJoueursMockMvc.perform(get("/api/joueurs/{id}", joueurs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(joueurs.getId().intValue()))
            .andExpect(jsonPath("$.joueur").value(DEFAULT_JOUEUR.toString()))
            .andExpect(jsonPath("$.courriel").value(DEFAULT_COURRIEL.toString()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJoueurs() throws Exception {
        // Get the joueurs
        restJoueursMockMvc.perform(get("/api/joueurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJoueurs() throws Exception {
        // Initialize the database
        joueursRepository.saveAndFlush(joueurs);
        int databaseSizeBeforeUpdate = joueursRepository.findAll().size();

        // Update the joueurs
        Joueurs updatedJoueurs = joueursRepository.findOne(joueurs.getId());
        // Disconnect from session so that the updates on updatedJoueurs are not directly saved in db
        em.detach(updatedJoueurs);
        updatedJoueurs
            .joueur(UPDATED_JOUEUR)
            .courriel(UPDATED_COURRIEL)
            .avatar(UPDATED_AVATAR);
        JoueursDTO joueursDTO = joueursMapper.toDto(updatedJoueurs);

        restJoueursMockMvc.perform(put("/api/joueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(joueursDTO)))
            .andExpect(status().isOk());

        // Validate the Joueurs in the database
        List<Joueurs> joueursList = joueursRepository.findAll();
        assertThat(joueursList).hasSize(databaseSizeBeforeUpdate);
        Joueurs testJoueurs = joueursList.get(joueursList.size() - 1);
        assertThat(testJoueurs.getJoueur()).isEqualTo(UPDATED_JOUEUR);
        assertThat(testJoueurs.getCourriel()).isEqualTo(UPDATED_COURRIEL);
        assertThat(testJoueurs.getAvatar()).isEqualTo(UPDATED_AVATAR);
    }

    @Test
    @Transactional
    public void updateNonExistingJoueurs() throws Exception {
        int databaseSizeBeforeUpdate = joueursRepository.findAll().size();

        // Create the Joueurs
        JoueursDTO joueursDTO = joueursMapper.toDto(joueurs);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJoueursMockMvc.perform(put("/api/joueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(joueursDTO)))
            .andExpect(status().isCreated());

        // Validate the Joueurs in the database
        List<Joueurs> joueursList = joueursRepository.findAll();
        assertThat(joueursList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJoueurs() throws Exception {
        // Initialize the database
        joueursRepository.saveAndFlush(joueurs);
        int databaseSizeBeforeDelete = joueursRepository.findAll().size();

        // Get the joueurs
        restJoueursMockMvc.perform(delete("/api/joueurs/{id}", joueurs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Joueurs> joueursList = joueursRepository.findAll();
        assertThat(joueursList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Joueurs.class);
        Joueurs joueurs1 = new Joueurs();
        joueurs1.setId(1L);
        Joueurs joueurs2 = new Joueurs();
        joueurs2.setId(joueurs1.getId());
        assertThat(joueurs1).isEqualTo(joueurs2);
        joueurs2.setId(2L);
        assertThat(joueurs1).isNotEqualTo(joueurs2);
        joueurs1.setId(null);
        assertThat(joueurs1).isNotEqualTo(joueurs2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JoueursDTO.class);
        JoueursDTO joueursDTO1 = new JoueursDTO();
        joueursDTO1.setId(1L);
        JoueursDTO joueursDTO2 = new JoueursDTO();
        assertThat(joueursDTO1).isNotEqualTo(joueursDTO2);
        joueursDTO2.setId(joueursDTO1.getId());
        assertThat(joueursDTO1).isEqualTo(joueursDTO2);
        joueursDTO2.setId(2L);
        assertThat(joueursDTO1).isNotEqualTo(joueursDTO2);
        joueursDTO1.setId(null);
        assertThat(joueursDTO1).isNotEqualTo(joueursDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(joueursMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(joueursMapper.fromId(null)).isNull();
    }
}
