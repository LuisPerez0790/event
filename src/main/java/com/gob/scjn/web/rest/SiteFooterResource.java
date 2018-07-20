package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.SiteFooterService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.SiteFooterDTO;
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
 * REST controller for managing SiteFooter.
 */
@RestController
@RequestMapping("/api")
public class SiteFooterResource {

    private final Logger log = LoggerFactory.getLogger(SiteFooterResource.class);

    private static final String ENTITY_NAME = "siteFooter";

    private final SiteFooterService siteFooterService;

    public SiteFooterResource(SiteFooterService siteFooterService) {
        this.siteFooterService = siteFooterService;
    }

    /**
     * POST  /site-footers : Create a new siteFooter.
     *
     * @param siteFooterDTO the siteFooterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new siteFooterDTO, or with status 400 (Bad Request) if the siteFooter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/site-footers")
    @Timed
    public ResponseEntity<SiteFooterDTO> createSiteFooter(@RequestBody SiteFooterDTO siteFooterDTO) throws URISyntaxException {
        log.debug("REST request to save SiteFooter : {}", siteFooterDTO);
        if (siteFooterDTO.getId() != null) {
            throw new BadRequestAlertException("A new siteFooter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SiteFooterDTO result = siteFooterService.save(siteFooterDTO);
        return ResponseEntity.created(new URI("/api/site-footers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /site-footers : Updates an existing siteFooter.
     *
     * @param siteFooterDTO the siteFooterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated siteFooterDTO,
     * or with status 400 (Bad Request) if the siteFooterDTO is not valid,
     * or with status 500 (Internal Server Error) if the siteFooterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/site-footers")
    @Timed
    public ResponseEntity<SiteFooterDTO> updateSiteFooter(@RequestBody SiteFooterDTO siteFooterDTO) throws URISyntaxException {
        log.debug("REST request to update SiteFooter : {}", siteFooterDTO);
        if (siteFooterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SiteFooterDTO result = siteFooterService.save(siteFooterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, siteFooterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /site-footers : get all the siteFooters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of siteFooters in body
     */
    @GetMapping("/site-footers")
    @Timed
    public ResponseEntity<List<SiteFooterDTO>> getAllSiteFooters(Pageable pageable) {
        log.debug("REST request to get a page of SiteFooters");
        Page<SiteFooterDTO> page = siteFooterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/site-footers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /site-footers/:id : get the "id" siteFooter.
     *
     * @param id the id of the siteFooterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the siteFooterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/site-footers/{id}")
    @Timed
    public ResponseEntity<SiteFooterDTO> getSiteFooter(@PathVariable Long id) {
        log.debug("REST request to get SiteFooter : {}", id);
        Optional<SiteFooterDTO> siteFooterDTO = siteFooterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(siteFooterDTO);
    }

    /**
     * DELETE  /site-footers/:id : delete the "id" siteFooter.
     *
     * @param id the id of the siteFooterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/site-footers/{id}")
    @Timed
    public ResponseEntity<Void> deleteSiteFooter(@PathVariable Long id) {
        log.debug("REST request to delete SiteFooter : {}", id);
        siteFooterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
