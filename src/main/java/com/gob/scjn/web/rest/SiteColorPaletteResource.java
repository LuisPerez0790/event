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
import com.gob.scjn.service.SiteColorPaletteService;
import com.gob.scjn.service.dto.SiteColorPaletteDTO;
import com.gob.scjn.web.rest.util.HeaderUtil;

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
	 * POST /site-color-palettes : Create a new siteColorPalette.
	 *
	 * @param siteColorPaletteDTO
	 *            the siteColorPaletteDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         siteColorPaletteDTO, or with status 400 (Bad Request) if the
	 *         siteColorPalette has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/site/{id}/color-palette")
	public ResponseEntity<SiteColorPaletteDTO> createFooterEntity(@PathVariable Long id, @RequestBody SiteColorPaletteDTO siteColorPaletteDTO) {
		SiteColorPaletteDTO result = siteColorPaletteService.save(id,siteColorPaletteDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	/**
	 * PUT /site-color-palettes : Updates an existing siteColorPalette.
	 *
	 * @param siteColorPaletteDTO
	 *            the siteColorPaletteDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         siteColorPaletteDTO, or with status 400 (Bad Request) if the
	 *         siteColorPaletteDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the siteColorPaletteDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/site/{id}/color-palette")
	@Timed
	public ResponseEntity<SiteColorPaletteDTO> updateSiteColorPalette(@PathVariable Long id, @RequestBody SiteColorPaletteDTO siteColorPaletteDTO) throws URISyntaxException {
		SiteColorPaletteDTO result = siteColorPaletteService.save(id, siteColorPaletteDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * GET /site-color-palettes : get all the siteColorPalettes.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         siteColorPalettes in body
	 */
	@GetMapping("/site/{id}/color-palette")
	@Timed
	public ResponseEntity<SiteColorPaletteDTO> getAllSiteColorPalettes(@PathVariable Long id) {
		log.debug("REST request to get a page of SiteColorPalettes");
		SiteColorPaletteDTO sitePalette = siteColorPaletteService.findOne(id);
		return new ResponseEntity<>(sitePalette, HttpStatus.OK);
	}

	
	/**
	 * DELETE /site-color-palettes/:id : delete the "id" siteColorPalette.
	 *
	 * @param id
	 *            the id of the siteColorPaletteDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/site/{id}/color-palette")
	@Timed
	public ResponseEntity<Void> deleteSiteColorPalette(@PathVariable Long id) {
		log.debug("REST request to delete SiteColorPalette : {}", id);
		siteColorPaletteService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
