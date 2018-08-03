package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.VenueService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.VenueDTO;
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
 * REST controller for managing Venue.
 */
@RestController
@RequestMapping("/api/event/{eventid}")
public class VenueResource {

	private final Logger log = LoggerFactory.getLogger(VenueResource.class);

	private static final String ENTITY_NAME = "venue";

	private final VenueService venueService;

	public VenueResource(VenueService venueService) {
		this.venueService = venueService;
	}

	/**
	 * POST /venues : Create a new venue.
	 *
	 * @param venueDTO
	 *            the venueDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         venueDTO, or with status 400 (Bad Request) if the venue has already
	 *         an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/venues")
	@Timed
	public ResponseEntity<VenueDTO> createVenue(@PathVariable Long eventid, @RequestBody VenueDTO venueDTO)
			throws URISyntaxException {
		log.debug("REST request to save Venue : {}", venueDTO);
		if (venueDTO.getId() != null) {
			throw new BadRequestAlertException("A new venue cannot already have an ID", ENTITY_NAME, "idexists");
		}
		VenueDTO result = venueService.save(eventid, venueDTO);
		return ResponseEntity.created(new URI("/api/venues/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /venues : Updates an existing venue.
	 *
	 * @param venueDTO
	 *            the venueDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         venueDTO, or with status 400 (Bad Request) if the venueDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the venueDTO
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/venues/{id}")
	@Timed
	public ResponseEntity<VenueDTO> updateVenue(@PathVariable Long eventid, @PathVariable Long id,
			@RequestBody VenueDTO venueDTO) throws URISyntaxException {
		log.debug("REST request to update Venue : {}", venueDTO);
		venueDTO.setId(id);
		VenueDTO result = venueService.save(eventid, venueDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, venueDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /venues : get all the venues.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of venues in
	 *         body
	 */
	@GetMapping("/venues")
	@Timed
	public ResponseEntity<List<VenueDTO>> getAllVenues(@PathVariable Long eventid, Pageable pageable) {
		log.debug("REST request to get a page of Venues");
		Page<VenueDTO> page = venueService.findAll(eventid, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/venues");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /venues/:id : get the "id" venue.
	 *
	 * @param id
	 *            the id of the venueDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the venueDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/venues/{id}")
	@Timed
	public ResponseEntity<VenueDTO> getVenue(@PathVariable Long eventid, @PathVariable Long id) {
		log.debug("REST request to get Venue : {}", id);
		Optional<VenueDTO> venueDTO = venueService.findOne(eventid, id);
		return ResponseUtil.wrapOrNotFound(venueDTO);
	}

	/**
	 * DELETE /venues/:id : delete the "id" venue.
	 *
	 * @param id
	 *            the id of the venueDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/venues/{id}")
	@Timed
	public ResponseEntity<Void> deleteVenue(@PathVariable Long eventid, @PathVariable Long id) {
		log.debug("REST request to delete Venue : {}", id);
		venueService.delete(eventid, id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
