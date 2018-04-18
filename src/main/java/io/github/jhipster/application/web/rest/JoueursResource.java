package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.JoueursService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.JoueursDTO;
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
 * REST controller for managing Joueurs.
 */
@RestController
@RequestMapping("/api")
public class JoueursResource {

    private final Logger log = LoggerFactory.getLogger(JoueursResource.class);

    private static final String ENTITY_NAME = "joueurs";

    private final JoueursService joueursService;

    public JoueursResource(JoueursService joueursService) {
        this.joueursService = joueursService;
    }

    /**
     * POST  /joueurs : Create a new joueurs.
     *
     * @param joueursDTO the joueursDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new joueursDTO, or with status 400 (Bad Request) if the joueurs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/joueurs")
    @Timed
    public ResponseEntity<JoueursDTO> createJoueurs(@Valid @RequestBody JoueursDTO joueursDTO) throws URISyntaxException {
        log.debug("REST request to save Joueurs : {}", joueursDTO);
        if (joueursDTO.getId() != null) {
            throw new BadRequestAlertException("A new joueurs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JoueursDTO result = joueursService.save(joueursDTO);
        return ResponseEntity.created(new URI("/api/joueurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /joueurs : Updates an existing joueurs.
     *
     * @param joueursDTO the joueursDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated joueursDTO,
     * or with status 400 (Bad Request) if the joueursDTO is not valid,
     * or with status 500 (Internal Server Error) if the joueursDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/joueurs")
    @Timed
    public ResponseEntity<JoueursDTO> updateJoueurs(@Valid @RequestBody JoueursDTO joueursDTO) throws URISyntaxException {
        log.debug("REST request to update Joueurs : {}", joueursDTO);
        if (joueursDTO.getId() == null) {
            return createJoueurs(joueursDTO);
        }
        JoueursDTO result = joueursService.save(joueursDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, joueursDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /joueurs : get all the joueurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of joueurs in body
     */
    @GetMapping("/joueurs")
    @Timed
    public ResponseEntity<List<JoueursDTO>> getAllJoueurs(Pageable pageable) {
        log.debug("REST request to get a page of Joueurs");
        Page<JoueursDTO> page = joueursService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/joueurs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /joueurs/:id : get the "id" joueurs.
     *
     * @param id the id of the joueursDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the joueursDTO, or with status 404 (Not Found)
     */
    @GetMapping("/joueurs/{id}")
    @Timed
    public ResponseEntity<JoueursDTO> getJoueurs(@PathVariable Long id) {
        log.debug("REST request to get Joueurs : {}", id);
        JoueursDTO joueursDTO = joueursService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(joueursDTO));
    }

    /**
     * DELETE  /joueurs/:id : delete the "id" joueurs.
     *
     * @param id the id of the joueursDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/joueurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteJoueurs(@PathVariable Long id) {
        log.debug("REST request to delete Joueurs : {}", id);
        joueursService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
