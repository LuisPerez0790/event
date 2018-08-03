package com.gob.scjn.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gob.scjn.domain.Venue;
import com.gob.scjn.repository.EventRepository;
import com.gob.scjn.repository.VenueRepository;
import com.gob.scjn.service.VenueService;
import com.gob.scjn.service.dto.VenueDTO;
import com.gob.scjn.service.mapper.VenueMapper;
import com.gob.scjn.web.rest.errors.SiteNotFoundException;

/**
 * Service Implementation for managing Venue.
 */
@Service
@Transactional
public class VenueServiceImpl implements VenueService {

	private final Logger log = LoggerFactory.getLogger(VenueServiceImpl.class);

	private final VenueRepository venueRepository;

	private final VenueMapper venueMapper;

	private final EventRepository eventRepository;

	public VenueServiceImpl(VenueRepository venueRepository, VenueMapper venueMapper, EventRepository eventRepository) {
		this.venueRepository = venueRepository;
		this.venueMapper = venueMapper;
		this.eventRepository = eventRepository;
	}

	/**
	 * Save a venue.
	 *
	 * @param venueDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public VenueDTO save(Long siteid, VenueDTO venueDTO) {
		log.debug("Request to save Venue : {}", venueDTO);
		validateSite(siteid);
		venueDTO.setEventId(siteid);
		Venue venue = venueMapper.toEntity(venueDTO);
		venue = venueRepository.save(venue);
		return venueMapper.toDto(venue);
	}

	/**
	 * Get all the venues.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<VenueDTO> findAll(Long siteid, Pageable pageable) {
		log.debug("Request to get all Venues");
		validateSite(siteid);
		return venueRepository.findByEventId(siteid, pageable).map(venueMapper::toDto);
	}

	/**
	 * Get one venue by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<VenueDTO> findOne(Long siteid, Long id) {
		log.debug("Request to get Venue : {}", id);
		validateSite(siteid);
		return venueRepository.findById(id).map(venueMapper::toDto);
	}

	/**
	 * Delete the venue by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long siteid, Long id) {
		log.debug("Request to delete Venue : {}", id);
		validateSite(siteid);
		venueRepository.deleteById(id);
	}

	private void validateSite(Long id) {
		this.eventRepository.findById(id).orElseThrow(() -> new SiteNotFoundException());
	}
}
