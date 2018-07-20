package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.SitePageService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.SitePageDTO;
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
 * REST controller for managing SitePage.
 */
@RestController
@RequestMapping("/api")
public class SitePageResource {

    private final Logger log = LoggerFactory.getLogger(SitePageResource.class);

    private static final String ENTITY_NAME = "sitePage";

    private final SitePageService sitePageService;

    public SitePageResource(SitePageService sitePageService) {
        this.sitePageService = sitePageService;
    }

    /**
     * POST  /site-pages : Create a new sitePage.
     *
     * @param sitePageDTO the sitePageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sitePageDTO, or with status 400 (Bad Request) if the sitePage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/site-pages")
    @Timed
    public ResponseEntity<SitePageDTO> createSitePage(@RequestBody SitePageDTO sitePageDTO) throws URISyntaxException {
        log.debug("REST request to save SitePage : {}", sitePageDTO);
        if (sitePageDTO.getId() != null) {
            throw new BadRequestAlertException("A new sitePage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SitePageDTO result = sitePageService.save(sitePageDTO);
        return ResponseEntity.created(new URI("/api/site-pages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /site-pages : Updates an existing sitePage.
     *
     * @param sitePageDTO the sitePageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sitePageDTO,
     * or with status 400 (Bad Request) if the sitePageDTO is not valid,
     * or with status 500 (Internal Server Error) if the sitePageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/site-pages")
    @Timed
    public ResponseEntity<SitePageDTO> updateSitePage(@RequestBody SitePageDTO sitePageDTO) throws URISyntaxException {
        log.debug("REST request to update SitePage : {}", sitePageDTO);
        if (sitePageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SitePageDTO result = sitePageService.save(sitePageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sitePageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /site-pages : get all the sitePages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sitePages in body
     */
    @GetMapping("/site-pages")
    @Timed
    public ResponseEntity<List<SitePageDTO>> getAllSitePages(Pageable pageable) {
        log.debug("REST request to get a page of SitePages");
        Page<SitePageDTO> page = sitePageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/site-pages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /site-pages/:id : get the "id" sitePage.
     *
     * @param id the id of the sitePageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sitePageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/site-pages/{id}")
    @Timed
    public ResponseEntity<SitePageDTO> getSitePage(@PathVariable Long id) {
        log.debug("REST request to get SitePage : {}", id);
        Optional<SitePageDTO> sitePageDTO = sitePageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sitePageDTO);
    }

    /**
     * DELETE  /site-pages/:id : delete the "id" sitePage.
     *
     * @param id the id of the sitePageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/site-pages/{id}")
    @Timed
    public ResponseEntity<Void> deleteSitePage(@PathVariable Long id) {
        log.debug("REST request to delete SitePage : {}", id);
        sitePageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
