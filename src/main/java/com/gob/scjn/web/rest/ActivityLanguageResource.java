package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.ActivityLanguageService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.ActivityLanguageDTO;
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
 * REST controller for managing ActivityLanguage.
 */
@RestController
@RequestMapping("/api")
public class ActivityLanguageResource {

    private final Logger log = LoggerFactory.getLogger(ActivityLanguageResource.class);

    private static final String ENTITY_NAME = "activityLanguage";

    private final ActivityLanguageService activityLanguageService;

    public ActivityLanguageResource(ActivityLanguageService activityLanguageService) {
        this.activityLanguageService = activityLanguageService;
    }

    /**
     * POST  /activity-languages : Create a new activityLanguage.
     *
     * @param activityLanguageDTO the activityLanguageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activityLanguageDTO, or with status 400 (Bad Request) if the activityLanguage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/activity-languages")
    @Timed
    public ResponseEntity<ActivityLanguageDTO> createActivityLanguage(@RequestBody ActivityLanguageDTO activityLanguageDTO) throws URISyntaxException {
        log.debug("REST request to save ActivityLanguage : {}", activityLanguageDTO);
        if (activityLanguageDTO.getId() != null) {
            throw new BadRequestAlertException("A new activityLanguage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivityLanguageDTO result = activityLanguageService.save(activityLanguageDTO);
        return ResponseEntity.created(new URI("/api/activity-languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /activity-languages : Updates an existing activityLanguage.
     *
     * @param activityLanguageDTO the activityLanguageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activityLanguageDTO,
     * or with status 400 (Bad Request) if the activityLanguageDTO is not valid,
     * or with status 500 (Internal Server Error) if the activityLanguageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/activity-languages")
    @Timed
    public ResponseEntity<ActivityLanguageDTO> updateActivityLanguage(@RequestBody ActivityLanguageDTO activityLanguageDTO) throws URISyntaxException {
        log.debug("REST request to update ActivityLanguage : {}", activityLanguageDTO);
        if (activityLanguageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActivityLanguageDTO result = activityLanguageService.save(activityLanguageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, activityLanguageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /activity-languages : get all the activityLanguages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of activityLanguages in body
     */
    @GetMapping("/activity-languages")
    @Timed
    public ResponseEntity<List<ActivityLanguageDTO>> getAllActivityLanguages(Pageable pageable) {
        log.debug("REST request to get a page of ActivityLanguages");
        Page<ActivityLanguageDTO> page = activityLanguageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/activity-languages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /activity-languages/:id : get the "id" activityLanguage.
     *
     * @param id the id of the activityLanguageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activityLanguageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/activity-languages/{id}")
    @Timed
    public ResponseEntity<ActivityLanguageDTO> getActivityLanguage(@PathVariable Long id) {
        log.debug("REST request to get ActivityLanguage : {}", id);
        Optional<ActivityLanguageDTO> activityLanguageDTO = activityLanguageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activityLanguageDTO);
    }

    /**
     * DELETE  /activity-languages/:id : delete the "id" activityLanguage.
     *
     * @param id the id of the activityLanguageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/activity-languages/{id}")
    @Timed
    public ResponseEntity<Void> deleteActivityLanguage(@PathVariable Long id) {
        log.debug("REST request to delete ActivityLanguage : {}", id);
        activityLanguageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
