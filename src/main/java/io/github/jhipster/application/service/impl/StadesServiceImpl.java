package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.StadesService;
import io.github.jhipster.application.domain.Stades;
import io.github.jhipster.application.repository.StadesRepository;
import io.github.jhipster.application.service.dto.StadesDTO;
import io.github.jhipster.application.service.mapper.StadesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Stades.
 */
@Service
@Transactional
public class StadesServiceImpl implements StadesService {

    private final Logger log = LoggerFactory.getLogger(StadesServiceImpl.class);

    private final StadesRepository stadesRepository;

    private final StadesMapper stadesMapper;

    public StadesServiceImpl(StadesRepository stadesRepository, StadesMapper stadesMapper) {
        this.stadesRepository = stadesRepository;
        this.stadesMapper = stadesMapper;
    }

    /**
     * Save a stades.
     *
     * @param stadesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StadesDTO save(StadesDTO stadesDTO) {
        log.debug("Request to save Stades : {}", stadesDTO);
        Stades stades = stadesMapper.toEntity(stadesDTO);
        stades = stadesRepository.save(stades);
        return stadesMapper.toDto(stades);
    }

    /**
     * Get all the stades.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StadesDTO> findAll() {
        log.debug("Request to get all Stades");
        return stadesRepository.findAll().stream()
            .map(stadesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one stades by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StadesDTO findOne(Long id) {
        log.debug("Request to get Stades : {}", id);
        Stades stades = stadesRepository.findOne(id);
        return stadesMapper.toDto(stades);
    }

    /**
     * Delete the stades by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stades : {}", id);
        stadesRepository.delete(id);
    }
}
