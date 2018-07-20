package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.CMSService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.CMSDTO;
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
 * REST controller for managing CMS.
 */
@RestController
@RequestMapping("/api")
public class CMSResource {

    private final Logger log = LoggerFactory.getLogger(CMSResource.class);

    private static final String ENTITY_NAME = "cMS";

    private final CMSService cMSService;

    public CMSResource(CMSService cMSService) {
        this.cMSService = cMSService;
    }

    /**
     * POST  /cms : Create a new cMS.
     *
     * @param cMSDTO the cMSDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cMSDTO, or with status 400 (Bad Request) if the cMS has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cms")
    @Timed
    public ResponseEntity<CMSDTO> createCMS(@RequestBody CMSDTO cMSDTO) throws URISyntaxException {
        log.debug("REST request to save CMS : {}", cMSDTO);
        if (cMSDTO.getId() != null) {
            throw new BadRequestAlertException("A new cMS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CMSDTO result = cMSService.save(cMSDTO);
        return ResponseEntity.created(new URI("/api/cms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cms : Updates an existing cMS.
     *
     * @param cMSDTO the cMSDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cMSDTO,
     * or with status 400 (Bad Request) if the cMSDTO is not valid,
     * or with status 500 (Internal Server Error) if the cMSDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cms")
    @Timed
    public ResponseEntity<CMSDTO> updateCMS(@RequestBody CMSDTO cMSDTO) throws URISyntaxException {
        log.debug("REST request to update CMS : {}", cMSDTO);
        if (cMSDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CMSDTO result = cMSService.save(cMSDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cMSDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cms : get all the cMS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cMS in body
     */
    @GetMapping("/cms")
    @Timed
    public ResponseEntity<List<CMSDTO>> getAllCMS(Pageable pageable) {
        log.debug("REST request to get a page of CMS");
        Page<CMSDTO> page = cMSService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cms/:id : get the "id" cMS.
     *
     * @param id the id of the cMSDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cMSDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cms/{id}")
    @Timed
    public ResponseEntity<CMSDTO> getCMS(@PathVariable Long id) {
        log.debug("REST request to get CMS : {}", id);
        Optional<CMSDTO> cMSDTO = cMSService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cMSDTO);
    }

    /**
     * DELETE  /cms/:id : delete the "id" cMS.
     *
     * @param id the id of the cMSDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cms/{id}")
    @Timed
    public ResponseEntity<Void> deleteCMS(@PathVariable Long id) {
        log.debug("REST request to delete CMS : {}", id);
        cMSService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
