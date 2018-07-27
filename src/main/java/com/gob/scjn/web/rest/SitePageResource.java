package com.gob.scjn.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.gob.scjn.service.SitePageService;
import com.gob.scjn.service.dto.SitePageDTO;
import com.gob.scjn.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing SitePage.
 */
@RestController
@RequestMapping("/api/site/{id}")
public class SitePageResource {

	private final Logger log = LoggerFactory.getLogger(SitePageResource.class);

	private static final String ENTITY_NAME = "sitePage";

	private final SitePageService sitePageService;

	public SitePageResource(SitePageService sitePageService) {
		this.sitePageService = sitePageService;
	}

	/**
	 * POST /site-pages : Create a new sitePage.
	 *
	 * @param sitePageDTO
	 *            the sitePageDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         sitePageDTO, or with status 400 (Bad Request) if the sitePage has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/page")
	@Timed
	public ResponseEntity<SitePageDTO> createSitePage(@PathVariable Long id, @RequestBody SitePageDTO sitePageDTO)
			throws URISyntaxException {
		log.debug("REST request to save SitePage : {}", sitePageDTO);
		SitePageDTO result = sitePageService.save(id, sitePageDTO);
		return ResponseEntity.created(new URI("/site/" + id + "/page/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /site-pages : Updates an existing sitePage.
	 *
	 * @param sitePageDTO
	 *            the sitePageDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         sitePageDTO, or with status 400 (Bad Request) if the sitePageDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         sitePageDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/page/{pageId}")
	@Timed
	public ResponseEntity<SitePageDTO> updateSitePage(@PathVariable Long id, @PathVariable Long pageId,
			@RequestBody SitePageDTO sitePageDTO) throws URISyntaxException {
		log.debug("REST request to update SitePage : {}", sitePageDTO);
		SitePageDTO result = sitePageService.save(id, pageId, sitePageDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * GET /site-pages/:id : get the "id" sitePage.
	 *
	 * @param id
	 *            the id of the sitePageDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         sitePageDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/page/{pageId}")
	@Timed
	public ResponseEntity<SitePageDTO> getSitePage(@PathVariable Long id, @PathVariable Long pageId) {
		log.debug("REST request to get SitePage : {}", id);
		Optional<SitePageDTO> sitePageDTO = sitePageService.findOne(id, pageId);
		return ResponseUtil.wrapOrNotFound(sitePageDTO);
	}

	/**
	 * DELETE /site-pages/:id : delete the "id" sitePage.
	 *
	 * @param id
	 *            the id of the sitePageDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/page/{pageId}")
	@Timed
	public ResponseEntity<Void> deleteSitePage(@PathVariable Long id, @PathVariable Long pageId) {
		log.debug("REST request to delete SitePage : {}", id);
		sitePageService.delete(id, pageId);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
