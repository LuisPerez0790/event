package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.SitePageLanguageService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.SitePageLanguageDTO;
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
 * REST controller for managing SitePageLanguage.
 */
@RestController
@RequestMapping("/api")
public class SitePageLanguageResource {

    private final Logger log = LoggerFactory.getLogger(SitePageLanguageResource.class);

    private static final String ENTITY_NAME = "sitePageLanguage";

    private final SitePageLanguageService sitePageLanguageService;

    public SitePageLanguageResource(SitePageLanguageService sitePageLanguageService) {
        this.sitePageLanguageService = sitePageLanguageService;
    }

    /**
     * POST  /site-page-languages : Create a new sitePageLanguage.
     *
     * @param sitePageLanguageDTO the sitePageLanguageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sitePageLanguageDTO, or with status 400 (Bad Request) if the sitePageLanguage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/site-page-languages")
    @Timed
    public ResponseEntity<SitePageLanguageDTO> createSitePageLanguage(@RequestBody SitePageLanguageDTO sitePageLanguageDTO) throws URISyntaxException {
        log.debug("REST request to save SitePageLanguage : {}", sitePageLanguageDTO);
        if (sitePageLanguageDTO.getId() != null) {
            throw new BadRequestAlertException("A new sitePageLanguage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SitePageLanguageDTO result = sitePageLanguageService.save(sitePageLanguageDTO);
        return ResponseEntity.created(new URI("/api/site-page-languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /site-page-languages : Updates an existing sitePageLanguage.
     *
     * @param sitePageLanguageDTO the sitePageLanguageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sitePageLanguageDTO,
     * or with status 400 (Bad Request) if the sitePageLanguageDTO is not valid,
     * or with status 500 (Internal Server Error) if the sitePageLanguageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/site-page-languages")
    @Timed
    public ResponseEntity<SitePageLanguageDTO> updateSitePageLanguage(@RequestBody SitePageLanguageDTO sitePageLanguageDTO) throws URISyntaxException {
        log.debug("REST request to update SitePageLanguage : {}", sitePageLanguageDTO);
        if (sitePageLanguageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SitePageLanguageDTO result = sitePageLanguageService.save(sitePageLanguageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sitePageLanguageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /site-page-languages : get all the sitePageLanguages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sitePageLanguages in body
     */
    @GetMapping("/site-page-languages")
    @Timed
    public ResponseEntity<List<SitePageLanguageDTO>> getAllSitePageLanguages(Pageable pageable) {
        log.debug("REST request to get a page of SitePageLanguages");
        Page<SitePageLanguageDTO> page = sitePageLanguageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/site-page-languages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /site-page-languages/:id : get the "id" sitePageLanguage.
     *
     * @param id the id of the sitePageLanguageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sitePageLanguageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/site-page-languages/{id}")
    @Timed
    public ResponseEntity<SitePageLanguageDTO> getSitePageLanguage(@PathVariable Long id) {
        log.debug("REST request to get SitePageLanguage : {}", id);
        Optional<SitePageLanguageDTO> sitePageLanguageDTO = sitePageLanguageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sitePageLanguageDTO);
    }

    /**
     * DELETE  /site-page-languages/:id : delete the "id" sitePageLanguage.
     *
     * @param id the id of the sitePageLanguageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/site-page-languages/{id}")
    @Timed
    public ResponseEntity<Void> deleteSitePageLanguage(@PathVariable Long id) {
        log.debug("REST request to delete SitePageLanguage : {}", id);
        sitePageLanguageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
