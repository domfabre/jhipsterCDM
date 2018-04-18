package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Matchs;

import io.github.jhipster.application.repository.MatchsRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.MatchsDTO;
import io.github.jhipster.application.service.mapper.MatchsMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Matchs.
 */
@RestController
@RequestMapping("/api")
public class MatchsResource {

    private final Logger log = LoggerFactory.getLogger(MatchsResource.class);

    private static final String ENTITY_NAME = "matchs";

    private final MatchsRepository matchsRepository;

    private final MatchsMapper matchsMapper;

    public MatchsResource(MatchsRepository matchsRepository, MatchsMapper matchsMapper) {
        this.matchsRepository = matchsRepository;
        this.matchsMapper = matchsMapper;
    }

    /**
     * POST  /matchs : Create a new matchs.
     *
     * @param matchsDTO the matchsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new matchsDTO, or with status 400 (Bad Request) if the matchs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/matchs")
    @Timed
    public ResponseEntity<MatchsDTO> createMatchs(@Valid @RequestBody MatchsDTO matchsDTO) throws URISyntaxException {
        log.debug("REST request to save Matchs : {}", matchsDTO);
        if (matchsDTO.getId() != null) {
            throw new BadRequestAlertException("A new matchs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Matchs matchs = matchsMapper.toEntity(matchsDTO);
        matchs = matchsRepository.save(matchs);
        MatchsDTO result = matchsMapper.toDto(matchs);
        return ResponseEntity.created(new URI("/api/matchs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /matchs : Updates an existing matchs.
     *
     * @param matchsDTO the matchsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated matchsDTO,
     * or with status 400 (Bad Request) if the matchsDTO is not valid,
     * or with status 500 (Internal Server Error) if the matchsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/matchs")
    @Timed
    public ResponseEntity<MatchsDTO> updateMatchs(@Valid @RequestBody MatchsDTO matchsDTO) throws URISyntaxException {
        log.debug("REST request to update Matchs : {}", matchsDTO);
        if (matchsDTO.getId() == null) {
            return createMatchs(matchsDTO);
        }
        Matchs matchs = matchsMapper.toEntity(matchsDTO);
        matchs = matchsRepository.save(matchs);
        MatchsDTO result = matchsMapper.toDto(matchs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, matchsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /matchs : get all the matchs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of matchs in body
     */
    @GetMapping("/matchs")
    @Timed
    public ResponseEntity<List<MatchsDTO>> getAllMatchs(Pageable pageable) {
        log.debug("REST request to get a page of Matchs");
        Page<Matchs> page = matchsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/matchs");
        return new ResponseEntity<>(matchsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /matchs/:id : get the "id" matchs.
     *
     * @param id the id of the matchsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the matchsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/matchs/{id}")
    @Timed
    public ResponseEntity<MatchsDTO> getMatchs(@PathVariable Long id) {
        log.debug("REST request to get Matchs : {}", id);
        Matchs matchs = matchsRepository.findOne(id);
        MatchsDTO matchsDTO = matchsMapper.toDto(matchs);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(matchsDTO));
    }

    /**
     * DELETE  /matchs/:id : delete the "id" matchs.
     *
     * @param id the id of the matchsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/matchs/{id}")
    @Timed
    public ResponseEntity<Void> deleteMatchs(@PathVariable Long id) {
        log.debug("REST request to delete Matchs : {}", id);
        matchsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
