package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.SiteColorPaletteService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.SiteColorPaletteDTO;
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
 * REST controller for managing SiteColorPalette.
 */
@RestController
@RequestMapping("/api")
public class SiteColorPaletteResource {

    private final Logger log = LoggerFactory.getLogger(SiteColorPaletteResource.class);

    private static final String ENTITY_NAME = "siteColorPalette";

    private final SiteColorPaletteService siteColorPaletteService;

    public SiteColorPaletteResource(SiteColorPaletteService siteColorPaletteService) {
        this.siteColorPaletteService = siteColorPaletteService;
    }

    /**
     * POST  /site-color-palettes : Create a new siteColorPalette.
     *
     * @param siteColorPaletteDTO the siteColorPaletteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new siteColorPaletteDTO, or with status 400 (Bad Request) if the siteColorPalette has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/site-color-palettes")
    @Timed
    public ResponseEntity<SiteColorPaletteDTO> createSiteColorPalette(@RequestBody SiteColorPaletteDTO siteColorPaletteDTO) throws URISyntaxException {
        log.debug("REST request to save SiteColorPalette : {}", siteColorPaletteDTO);
        if (siteColorPaletteDTO.getId() != null) {
            throw new BadRequestAlertException("A new siteColorPalette cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SiteColorPaletteDTO result = siteColorPaletteService.save(siteColorPaletteDTO);
        return ResponseEntity.created(new URI("/api/site-color-palettes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /site-color-palettes : Updates an existing siteColorPalette.
     *
     * @param siteColorPaletteDTO the siteColorPaletteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated siteColorPaletteDTO,
     * or with status 400 (Bad Request) if the siteColorPaletteDTO is not valid,
     * or with status 500 (Internal Server Error) if the siteColorPaletteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/site-color-palettes")
    @Timed
    public ResponseEntity<SiteColorPaletteDTO> updateSiteColorPalette(@RequestBody SiteColorPaletteDTO siteColorPaletteDTO) throws URISyntaxException {
        log.debug("REST request to update SiteColorPalette : {}", siteColorPaletteDTO);
        if (siteColorPaletteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SiteColorPaletteDTO result = siteColorPaletteService.save(siteColorPaletteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, siteColorPaletteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /site-color-palettes : get all the siteColorPalettes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of siteColorPalettes in body
     */
    @GetMapping("/site-color-palettes")
    @Timed
    public ResponseEntity<List<SiteColorPaletteDTO>> getAllSiteColorPalettes(Pageable pageable) {
        log.debug("REST request to get a page of SiteColorPalettes");
        Page<SiteColorPaletteDTO> page = siteColorPaletteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/site-color-palettes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /site-color-palettes/:id : get the "id" siteColorPalette.
     *
     * @param id the id of the siteColorPaletteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the siteColorPaletteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/site-color-palettes/{id}")
    @Timed
    public ResponseEntity<SiteColorPaletteDTO> getSiteColorPalette(@PathVariable Long id) {
        log.debug("REST request to get SiteColorPalette : {}", id);
        Optional<SiteColorPaletteDTO> siteColorPaletteDTO = siteColorPaletteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(siteColorPaletteDTO);
    }

    /**
     * DELETE  /site-color-palettes/:id : delete the "id" siteColorPalette.
     *
     * @param id the id of the siteColorPaletteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/site-color-palettes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSiteColorPalette(@PathVariable Long id) {
        log.debug("REST request to delete SiteColorPalette : {}", id);
        siteColorPaletteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
