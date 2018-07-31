package com.gob.scjn.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import com.gob.scjn.service.EventLanguageService;
import com.gob.scjn.service.dto.EventLanguageDTO;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing EventLanguage.
 */
@RestController
@RequestMapping("/api")
public class EventLanguageResource {

	private final Logger log = LoggerFactory.getLogger(EventLanguageResource.class);

	private static final String ENTITY_NAME = "eventLanguage";

	private final EventLanguageService eventLanguageService;

	public EventLanguageResource(EventLanguageService eventLanguageService) {
		this.eventLanguageService = eventLanguageService;
	}

	/**
	 * POST /event-languages : Create a new eventLanguage.
	 *
	 * @param eventLanguageDTO
	 *            the eventLanguageDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         eventLanguageDTO, or with status 400 (Bad Request) if the
	 *         eventLanguage has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/event-languages")
	@Timed
	public ResponseEntity<EventLanguageDTO> createEventLanguage(@RequestBody EventLanguageDTO eventLanguageDTO)
			throws URISyntaxException {
		log.debug("REST request to save EventLanguage : {}", eventLanguageDTO);
		if (eventLanguageDTO.getId() != null) {
			throw new BadRequestAlertException("A new eventLanguage cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		EventLanguageDTO result = eventLanguageService.save(eventLanguageDTO);
		return ResponseEntity.created(new URI("/api/event-languages/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /event-languages : Updates an existing eventLanguage.
	 *
	 * @param eventLanguageDTO
	 *            the eventLanguageDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         eventLanguageDTO, or with status 400 (Bad Request) if the
	 *         eventLanguageDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the eventLanguageDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/event-languages/{id}")
	@Timed
	public ResponseEntity<EventLanguageDTO> updateEventLanguage(@PathVariable Long id,
			@RequestBody EventLanguageDTO eventLanguageDTO) throws URISyntaxException {
		log.debug("REST request to update EventLanguage : {}", eventLanguageDTO);
		eventLanguageDTO.setId(id);
		EventLanguageDTO result = eventLanguageService.save(eventLanguageDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventLanguageDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /event-languages : get all the eventLanguages.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         eventLanguages in body
	 */
	@GetMapping("/event-languages") // Muestra todos los idiomas de un evento especifico
	@Timed
	public ResponseEntity<List<EventLanguageDTO>> getAllEventLanguages(Pageable pageable) {
		log.debug("REST request to get a page of ActivityLanguages");
		Page<EventLanguageDTO> page = eventLanguageService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/activity-languages");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /event-languages/:id : get the "id" eventLanguage.
	 *
	 * @param id
	 *            the id of the eventLanguageDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         eventLanguageDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/event-languages/{id}")
	@Timed
	public ResponseEntity<EventLanguageDTO> getEventLanguage(@PathVariable Long id) {
		log.debug("REST request to get EventLanguage : {}", id);
		Optional<EventLanguageDTO> eventLanguageDTO = eventLanguageService.findOne(id);
		return ResponseUtil.wrapOrNotFound(eventLanguageDTO);
	}

	/**
	 * DELETE /event-languages/:id : delete the "id" eventLanguage.
	 *
	 * @param id
	 *            the id of the eventLanguageDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/event-languages/{id}")
	@Timed
	public ResponseEntity<Void> deleteEventLanguage(@PathVariable Long id) {
		log.debug("REST request to delete EventLanguage : {}", id);
		eventLanguageService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
