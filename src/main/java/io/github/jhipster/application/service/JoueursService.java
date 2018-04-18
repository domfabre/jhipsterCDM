package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.JoueursDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Joueurs.
 */
public interface JoueursService {

    /**
     * Save a joueurs.
     *
     * @param joueursDTO the entity to save
     * @return the persisted entity
     */
    JoueursDTO save(JoueursDTO joueursDTO);

    /**
     * Get all the joueurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JoueursDTO> findAll(Pageable pageable);

    /**
     * Get the "id" joueurs.
     *
     * @param id the id of the entity
     * @return the entity
     */
    JoueursDTO findOne(Long id);

    /**
     * Delete the "id" joueurs.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
