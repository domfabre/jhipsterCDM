package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.NationsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Nations.
 */
public interface NationsService {

    /**
     * Save a nations.
     *
     * @param nationsDTO the entity to save
     * @return the persisted entity
     */
    NationsDTO save(NationsDTO nationsDTO);

    /**
     * Get all the nations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NationsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" nations.
     *
     * @param id the id of the entity
     * @return the entity
     */
    NationsDTO findOne(Long id);

    /**
     * Delete the "id" nations.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
