package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.EventUserService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.web.rest.util.PaginationUtil;
import com.gob.scjn.service.dto.EventUserDTO;
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
 * REST controller for managing EventUser.
 */
@RestController
@RequestMapping("/api")
public class EventUserResource {

    private final Logger log = LoggerFactory.getLogger(EventUserResource.class);

    private static final String ENTITY_NAME = "eventUser";

    private final EventUserService eventUserService;

    public EventUserResource(EventUserService eventUserService) {
        this.eventUserService = eventUserService;
    }

    /**
     * POST  /event-users : Create a new eventUser.
     *
     * @param eventUserDTO the eventUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventUserDTO, or with status 400 (Bad Request) if the eventUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-users")
    @Timed
    public ResponseEntity<EventUserDTO> createEventUser(@RequestBody EventUserDTO eventUserDTO) throws URISyntaxException {
        log.debug("REST request to save EventUser : {}", eventUserDTO);
        if (eventUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventUserDTO result = eventUserService.save(eventUserDTO);
        return ResponseEntity.created(new URI("/api/event-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-users : Updates an existing eventUser.
     *
     * @param eventUserDTO the eventUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventUserDTO,
     * or with status 400 (Bad Request) if the eventUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the eventUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-users")
    @Timed
    public ResponseEntity<EventUserDTO> updateEventUser(@RequestBody EventUserDTO eventUserDTO) throws URISyntaxException {
        log.debug("REST request to update EventUser : {}", eventUserDTO);
        if (eventUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventUserDTO result = eventUserService.save(eventUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-users : get all the eventUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eventUsers in body
     */
    @GetMapping("/event-users")
    @Timed
    public ResponseEntity<List<EventUserDTO>> getAllEventUsers(Pageable pageable) {
        log.debug("REST request to get a page of EventUsers");
        Page<EventUserDTO> page = eventUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/event-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /event-users/:id : get the "id" eventUser.
     *
     * @param id the id of the eventUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/event-users/{id}")
    @Timed
    public ResponseEntity<EventUserDTO> getEventUser(@PathVariable Long id) {
        log.debug("REST request to get EventUser : {}", id);
        Optional<EventUserDTO> eventUserDTO = eventUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventUserDTO);
    }

    /**
     * DELETE  /event-users/:id : delete the "id" eventUser.
     *
     * @param id the id of the eventUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventUser(@PathVariable Long id) {
        log.debug("REST request to delete EventUser : {}", id);
        eventUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
