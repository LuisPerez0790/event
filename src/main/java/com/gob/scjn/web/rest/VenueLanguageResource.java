package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.VenueLanguageService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.service.dto.VenueLanguageDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VenueLanguage.
 */
@RestController
@RequestMapping("/api")
public class VenueLanguageResource {

    private final Logger log = LoggerFactory.getLogger(VenueLanguageResource.class);

    private static final String ENTITY_NAME = "venueLanguage";

    private final VenueLanguageService venueLanguageService;

    public VenueLanguageResource(VenueLanguageService venueLanguageService) {
        this.venueLanguageService = venueLanguageService;
    }

    /**
     * POST  /venue-languages : Create a new venueLanguage.
     *
     * @param venueLanguageDTO the venueLanguageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new venueLanguageDTO, or with status 400 (Bad Request) if the venueLanguage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/venue-languages")
    @Timed
    public ResponseEntity<VenueLanguageDTO> createVenueLanguage(@RequestBody VenueLanguageDTO venueLanguageDTO) throws URISyntaxException {
        log.debug("REST request to save VenueLanguage : {}", venueLanguageDTO);
        if (venueLanguageDTO.getId() != null) {
            throw new BadRequestAlertException("A new venueLanguage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VenueLanguageDTO result = venueLanguageService.save(venueLanguageDTO);
        return ResponseEntity.created(new URI("/api/venue-languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /venue-languages : Updates an existing venueLanguage.
     *
     * @param venueLanguageDTO the venueLanguageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated venueLanguageDTO,
     * or with status 400 (Bad Request) if the venueLanguageDTO is not valid,
     * or with status 500 (Internal Server Error) if the venueLanguageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/venue-languages/{id}")
    @Timed
    public ResponseEntity<VenueLanguageDTO> updateVenueLanguage(@PathVariable Long id, @RequestBody VenueLanguageDTO venueLanguageDTO) throws URISyntaxException {
        log.debug("REST request to update VenueLanguage : {}", venueLanguageDTO);
        venueLanguageDTO.setId(id);
        VenueLanguageDTO result = venueLanguageService.save(venueLanguageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, venueLanguageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /venue-languages : get all the venueLanguages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of venueLanguages in body
     */
    @GetMapping("/venue-languages")
    @Timed
    public List<VenueLanguageDTO> getAllVenueLanguages() {
        log.debug("REST request to get all VenueLanguages");
        return venueLanguageService.findAll();
    }

    /**
     * GET  /venue-languages/:id : get the "id" venueLanguage.
     *
     * @param id the id of the venueLanguageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the venueLanguageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/venue-languages/{id}")
    @Timed
    public ResponseEntity<VenueLanguageDTO> getVenueLanguage(@PathVariable Long id) {
        log.debug("REST request to get VenueLanguage : {}", id);
        Optional<VenueLanguageDTO> venueLanguageDTO = venueLanguageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(venueLanguageDTO);
    }

    /**
     * DELETE  /venue-languages/:id : delete the "id" venueLanguage.
     *
     * @param id the id of the venueLanguageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/venue-languages/{id}")
    @Timed
    public ResponseEntity<Void> deleteVenueLanguage(@PathVariable Long id) {
        log.debug("REST request to delete VenueLanguage : {}", id);
        venueLanguageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
