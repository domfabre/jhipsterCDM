package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Resultats;

import io.github.jhipster.application.repository.ResultatsRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.ResultatsDTO;
import io.github.jhipster.application.service.mapper.ResultatsMapper;
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
 * REST controller for managing Resultats.
 */
@RestController
@RequestMapping("/api")
public class ResultatsResource {

    private final Logger log = LoggerFactory.getLogger(ResultatsResource.class);

    private static final String ENTITY_NAME = "resultats";

    private final ResultatsRepository resultatsRepository;

    private final ResultatsMapper resultatsMapper;

    public ResultatsResource(ResultatsRepository resultatsRepository, ResultatsMapper resultatsMapper) {
        this.resultatsRepository = resultatsRepository;
        this.resultatsMapper = resultatsMapper;
    }

    /**
     * POST  /resultats : Create a new resultats.
     *
     * @param resultatsDTO the resultatsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resultatsDTO, or with status 400 (Bad Request) if the resultats has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resultats")
    @Timed
    public ResponseEntity<ResultatsDTO> createResultats(@RequestBody ResultatsDTO resultatsDTO) throws URISyntaxException {
        log.debug("REST request to save Resultats : {}", resultatsDTO);
        if (resultatsDTO.getId() != null) {
            throw new BadRequestAlertException("A new resultats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Resultats resultats = resultatsMapper.toEntity(resultatsDTO);
        resultats = resultatsRepository.save(resultats);
        ResultatsDTO result = resultatsMapper.toDto(resultats);
        return ResponseEntity.created(new URI("/api/resultats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resultats : Updates an existing resultats.
     *
     * @param resultatsDTO the resultatsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resultatsDTO,
     * or with status 400 (Bad Request) if the resultatsDTO is not valid,
     * or with status 500 (Internal Server Error) if the resultatsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resultats")
    @Timed
    public ResponseEntity<ResultatsDTO> updateResultats(@RequestBody ResultatsDTO resultatsDTO) throws URISyntaxException {
        log.debug("REST request to update Resultats : {}", resultatsDTO);
        if (resultatsDTO.getId() == null) {
            return createResultats(resultatsDTO);
        }
        Resultats resultats = resultatsMapper.toEntity(resultatsDTO);
        resultats = resultatsRepository.save(resultats);
        ResultatsDTO result = resultatsMapper.toDto(resultats);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resultatsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resultats : get all the resultats.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resultats in body
     */
    @GetMapping("/resultats")
    @Timed
    public ResponseEntity<List<ResultatsDTO>> getAllResultats(Pageable pageable) {
        log.debug("REST request to get a page of Resultats");
        Page<Resultats> page = resultatsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resultats");
        return new ResponseEntity<>(resultatsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /resultats/:id : get the "id" resultats.
     *
     * @param id the id of the resultatsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resultatsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resultats/{id}")
    @Timed
    public ResponseEntity<ResultatsDTO> getResultats(@PathVariable Long id) {
        log.debug("REST request to get Resultats : {}", id);
        Resultats resultats = resultatsRepository.findOne(id);
        ResultatsDTO resultatsDTO = resultatsMapper.toDto(resultats);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(resultatsDTO));
    }

    /**
     * DELETE  /resultats/:id : delete the "id" resultats.
     *
     * @param id the id of the resultatsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resultats/{id}")
    @Timed
    public ResponseEntity<Void> deleteResultats(@PathVariable Long id) {
        log.debug("REST request to delete Resultats : {}", id);
        resultatsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
