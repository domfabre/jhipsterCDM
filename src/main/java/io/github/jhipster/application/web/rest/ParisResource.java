package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Paris;

import io.github.jhipster.application.repository.ParisRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.ParisDTO;
import io.github.jhipster.application.service.mapper.ParisMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Paris.
 */
@RestController
@RequestMapping("/api")
public class ParisResource {

    private final Logger log = LoggerFactory.getLogger(ParisResource.class);

    private static final String ENTITY_NAME = "paris";

    private final ParisRepository parisRepository;

    private final ParisMapper parisMapper;

    public ParisResource(ParisRepository parisRepository, ParisMapper parisMapper) {
        this.parisRepository = parisRepository;
        this.parisMapper = parisMapper;
    }

    /**
     * POST  /parises : Create a new paris.
     *
     * @param parisDTO the parisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new parisDTO, or with status 400 (Bad Request) if the paris has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parises")
    @Timed
    public ResponseEntity<ParisDTO> createParis(@RequestBody ParisDTO parisDTO) throws URISyntaxException {
        log.debug("REST request to save Paris : {}", parisDTO);
        if (parisDTO.getId() != null) {
            throw new BadRequestAlertException("A new paris cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Paris paris = parisMapper.toEntity(parisDTO);
        paris = parisRepository.save(paris);
        ParisDTO result = parisMapper.toDto(paris);
        return ResponseEntity.created(new URI("/api/parises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parises : Updates an existing paris.
     *
     * @param parisDTO the parisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated parisDTO,
     * or with status 400 (Bad Request) if the parisDTO is not valid,
     * or with status 500 (Internal Server Error) if the parisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parises")
    @Timed
    public ResponseEntity<ParisDTO> updateParis(@RequestBody ParisDTO parisDTO) throws URISyntaxException {
        log.debug("REST request to update Paris : {}", parisDTO);
        if (parisDTO.getId() == null) {
            return createParis(parisDTO);
        }
        Paris paris = parisMapper.toEntity(parisDTO);
        paris = parisRepository.save(paris);
        ParisDTO result = parisMapper.toDto(paris);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, parisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parises : get all the parises.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of parises in body
     */
    @GetMapping("/parises")
    @Timed
    public ResponseEntity<List<ParisDTO>> getAllParises(Pageable pageable) {
        log.debug("REST request to get a page of Parises");
        Page<Paris> page = parisRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/parises");
        return new ResponseEntity<>(parisMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /parises/:id : get the "id" paris.
     *
     * @param id the id of the parisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the parisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/parises/{id}")
    @Timed
    public ResponseEntity<ParisDTO> getParis(@PathVariable Long id) {
        log.debug("REST request to get Paris : {}", id);
        Paris paris = parisRepository.findOne(id);
        ParisDTO parisDTO = parisMapper.toDto(paris);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(parisDTO));
    }

    /**
     * DELETE  /parises/:id : delete the "id" paris.
     *
     * @param id the id of the parisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parises/{id}")
    @Timed
    public ResponseEntity<Void> deleteParis(@PathVariable Long id) {
        log.debug("REST request to delete Paris : {}", id);
        parisRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
