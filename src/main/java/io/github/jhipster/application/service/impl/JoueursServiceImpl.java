package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.JoueursService;
import io.github.jhipster.application.domain.Joueurs;
import io.github.jhipster.application.repository.JoueursRepository;
import io.github.jhipster.application.service.dto.JoueursDTO;
import io.github.jhipster.application.service.mapper.JoueursMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Joueurs.
 */
@Service
@Transactional
public class JoueursServiceImpl implements JoueursService {

    private final Logger log = LoggerFactory.getLogger(JoueursServiceImpl.class);

    private final JoueursRepository joueursRepository;

    private final JoueursMapper joueursMapper;

    public JoueursServiceImpl(JoueursRepository joueursRepository, JoueursMapper joueursMapper) {
        this.joueursRepository = joueursRepository;
        this.joueursMapper = joueursMapper;
    }

    /**
     * Save a joueurs.
     *
     * @param joueursDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JoueursDTO save(JoueursDTO joueursDTO) {
        log.debug("Request to save Joueurs : {}", joueursDTO);
        Joueurs joueurs = joueursMapper.toEntity(joueursDTO);
        joueurs = joueursRepository.save(joueurs);
        return joueursMapper.toDto(joueurs);
    }

    /**
     * Get all the joueurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JoueursDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Joueurs");
        return joueursRepository.findAll(pageable)
            .map(joueursMapper::toDto);
    }

    /**
     * Get one joueurs by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public JoueursDTO findOne(Long id) {
        log.debug("Request to get Joueurs : {}", id);
        Joueurs joueurs = joueursRepository.findOne(id);
        return joueursMapper.toDto(joueurs);
    }

    /**
     * Delete the joueurs by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Joueurs : {}", id);
        joueursRepository.delete(id);
    }
}
