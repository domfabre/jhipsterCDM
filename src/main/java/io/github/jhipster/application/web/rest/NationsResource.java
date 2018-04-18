package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.NationsService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.NationsDTO;
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
 * REST controller for managing Nations.
 */
@RestController
@RequestMapping("/api")
public class NationsResource {

    private final Logger log = LoggerFactory.getLogger(NationsResource.class);

    private static final String ENTITY_NAME = "nations";

    private final NationsService nationsService;

    public NationsResource(NationsService nationsService) {
        this.nationsService = nationsService;
    }

    /**
     * POST  /nations : Create a new nations.
     *
     * @param nationsDTO the nationsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nationsDTO, or with status 400 (Bad Request) if the nations has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nations")
    @Timed
    public ResponseEntity<NationsDTO> createNations(@Valid @RequestBody NationsDTO nationsDTO) throws URISyntaxException {
        log.debug("REST request to save Nations : {}", nationsDTO);
        if (nationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new nations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NationsDTO result = nationsService.save(nationsDTO);
        return ResponseEntity.created(new URI("/api/nations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nations : Updates an existing nations.
     *
     * @param nationsDTO the nationsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nationsDTO,
     * or with status 400 (Bad Request) if the nationsDTO is not valid,
     * or with status 500 (Internal Server Error) if the nationsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nations")
    @Timed
    public ResponseEntity<NationsDTO> updateNations(@Valid @RequestBody NationsDTO nationsDTO) throws URISyntaxException {
        log.debug("REST request to update Nations : {}", nationsDTO);
        if (nationsDTO.getId() == null) {
            return createNations(nationsDTO);
        }
        NationsDTO result = nationsService.save(nationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nationsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nations : get all the nations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nations in body
     */
    @GetMapping("/nations")
    @Timed
    public ResponseEntity<List<NationsDTO>> getAllNations(Pageable pageable) {
        log.debug("REST request to get a page of Nations");
        Page<NationsDTO> page = nationsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nations/:id : get the "id" nations.
     *
     * @param id the id of the nationsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nationsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nations/{id}")
    @Timed
    public ResponseEntity<NationsDTO> getNations(@PathVariable Long id) {
        log.debug("REST request to get Nations : {}", id);
        NationsDTO nationsDTO = nationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(nationsDTO));
    }

    /**
     * DELETE  /nations/:id : delete the "id" nations.
     *
     * @param id the id of the nationsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nations/{id}")
    @Timed
    public ResponseEntity<Void> deleteNations(@PathVariable Long id) {
        log.debug("REST request to delete Nations : {}", id);
        nationsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
