package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.StadesService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.StadesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Stades.
 */
@RestController
@RequestMapping("/api")
public class StadesResource {

    private final Logger log = LoggerFactory.getLogger(StadesResource.class);

    private static final String ENTITY_NAME = "stades";

    private final StadesService stadesService;

    public StadesResource(StadesService stadesService) {
        this.stadesService = stadesService;
    }

    /**
     * POST  /stades : Create a new stades.
     *
     * @param stadesDTO the stadesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stadesDTO, or with status 400 (Bad Request) if the stades has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stades")
    @Timed
    public ResponseEntity<StadesDTO> createStades(@Valid @RequestBody StadesDTO stadesDTO) throws URISyntaxException {
        log.debug("REST request to save Stades : {}", stadesDTO);
        if (stadesDTO.getId() != null) {
            throw new BadRequestAlertException("A new stades cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StadesDTO result = stadesService.save(stadesDTO);
        return ResponseEntity.created(new URI("/api/stades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stades : Updates an existing stades.
     *
     * @param stadesDTO the stadesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stadesDTO,
     * or with status 400 (Bad Request) if the stadesDTO is not valid,
     * or with status 500 (Internal Server Error) if the stadesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stades")
    @Timed
    public ResponseEntity<StadesDTO> updateStades(@Valid @RequestBody StadesDTO stadesDTO) throws URISyntaxException {
        log.debug("REST request to update Stades : {}", stadesDTO);
        if (stadesDTO.getId() == null) {
            return createStades(stadesDTO);
        }
        StadesDTO result = stadesService.save(stadesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stadesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stades : get all the stades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of stades in body
     */
    @GetMapping("/stades")
    @Timed
    public List<StadesDTO> getAllStades() {
        log.debug("REST request to get all Stades");
        return stadesService.findAll();
        }

    /**
     * GET  /stades/:id : get the "id" stades.
     *
     * @param id the id of the stadesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stadesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stades/{id}")
    @Timed
    public ResponseEntity<StadesDTO> getStades(@PathVariable Long id) {
        log.debug("REST request to get Stades : {}", id);
        StadesDTO stadesDTO = stadesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stadesDTO));
    }

    /**
     * DELETE  /stades/:id : delete the "id" stades.
     *
     * @param id the id of the stadesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stades/{id}")
    @Timed
    public ResponseEntity<Void> deleteStades(@PathVariable Long id) {
        log.debug("REST request to delete Stades : {}", id);
        stadesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
