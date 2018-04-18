package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.NationsService;
import io.github.jhipster.application.domain.Nations;
import io.github.jhipster.application.repository.NationsRepository;
import io.github.jhipster.application.service.dto.NationsDTO;
import io.github.jhipster.application.service.mapper.NationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Nations.
 */
@Service
@Transactional
public class NationsServiceImpl implements NationsService {

    private final Logger log = LoggerFactory.getLogger(NationsServiceImpl.class);

    private final NationsRepository nationsRepository;

    private final NationsMapper nationsMapper;

    public NationsServiceImpl(NationsRepository nationsRepository, NationsMapper nationsMapper) {
        this.nationsRepository = nationsRepository;
        this.nationsMapper = nationsMapper;
    }

    /**
     * Save a nations.
     *
     * @param nationsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NationsDTO save(NationsDTO nationsDTO) {
        log.debug("Request to save Nations : {}", nationsDTO);
        Nations nations = nationsMapper.toEntity(nationsDTO);
        nations = nationsRepository.save(nations);
        return nationsMapper.toDto(nations);
    }

    /**
     * Get all the nations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Nations");
        return nationsRepository.findAll(pageable)
            .map(nationsMapper::toDto);
    }

    /**
     * Get one nations by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public NationsDTO findOne(Long id) {
        log.debug("Request to get Nations : {}", id);
        Nations nations = nationsRepository.findOne(id);
        return nationsMapper.toDto(nations);
    }

    /**
     * Delete the nations by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nations : {}", id);
        nationsRepository.delete(id);
    }
}
