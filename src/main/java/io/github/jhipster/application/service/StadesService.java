package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.StadesDTO;
import java.util.List;

/**
 * Service Interface for managing Stades.
 */
public interface StadesService {

    /**
     * Save a stades.
     *
     * @param stadesDTO the entity to save
     * @return the persisted entity
     */
    StadesDTO save(StadesDTO stadesDTO);

    /**
     * Get all the stades.
     *
     * @return the list of entities
     */
    List<StadesDTO> findAll();

    /**
     * Get the "id" stades.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StadesDTO findOne(Long id);

    /**
     * Delete the "id" stades.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
