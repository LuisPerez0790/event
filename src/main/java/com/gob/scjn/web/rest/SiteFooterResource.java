package com.gob.scjn.web.rest;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.SiteFooterService;
import com.gob.scjn.service.dto.SiteFooterDTO;
import com.gob.scjn.web.rest.util.HeaderUtil;

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
	 * POST /site-footers : Create a new siteFooter.
	 *
	 * @param siteFooterDTO
	 *            the siteFooterDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         siteFooterDTO, or with status 400 (Bad Request) if the siteFooter has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/site/{id}/site-footer")
	@Timed
	public ResponseEntity<SiteFooterDTO> createSiteFooter(@PathVariable Long id,
			@RequestBody SiteFooterDTO siteFooterDTO) throws URISyntaxException {
		SiteFooterDTO result = siteFooterService.save(id, siteFooterDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	/**
	 * PUT /site-footers : Updates an existing siteFooter.
	 *
	 * @param siteFooterDTO
	 *            the siteFooterDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         siteFooterDTO, or with status 400 (Bad Request) if the siteFooterDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         siteFooterDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */

	@PutMapping("/site/{id}/site-footer")
	@Timed
	public ResponseEntity<SiteFooterDTO> updateSiteColorPalette(@PathVariable Long id,
			@RequestBody SiteFooterDTO siteFooterDTO) throws URISyntaxException {
		SiteFooterDTO result = siteFooterService.save(id, siteFooterDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * GET /site-footers/:id : get the "id" siteFooter.
	 *
	 * @param id
	 *            the id of the siteFooterDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         siteFooterDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/site/{id}/site-footer")
	@Timed
	public ResponseEntity<SiteFooterDTO> getSiteFooter(@PathVariable Long id) {
		log.debug("REST request to get SiteFooter : {}", id);
		SiteFooterDTO footer = siteFooterService.findOne(id);
		return new ResponseEntity<>(footer, HttpStatus.OK);
	}

	/**
	 * DELETE /site-footers/:id : delete the "id" siteFooter.
	 *
	 * @param id
	 *            the id of the siteFooterDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/site/{id}/site-footer")
	@Timed
	public ResponseEntity<Void> deleteSiteFooter(@PathVariable Long id) {
		log.debug("REST request to delete SiteFooter : {}", id);
		siteFooterService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
