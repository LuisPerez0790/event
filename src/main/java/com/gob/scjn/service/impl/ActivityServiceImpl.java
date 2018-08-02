package com.gob.scjn.service.impl;

import com.gob.scjn.service.ActivityService;
import com.gob.scjn.domain.Activity;
import com.gob.scjn.repository.ActivityRepository;
import com.gob.scjn.repository.EventRepository;
import com.gob.scjn.service.dto.ActivityDTO;
import com.gob.scjn.service.mapper.ActivityMapper;
import com.gob.scjn.web.rest.errors.SiteNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Activity.
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	private final Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

	private final ActivityRepository activityRepository;

	private final ActivityMapper activityMapper;
	
	private final EventRepository eventRepository;

	public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper ,EventRepository eventRepository) {
		this.activityRepository = activityRepository;
		this.activityMapper = activityMapper;
		this.eventRepository = eventRepository;
	}

	/**
	 * Save a activity.
	 *
	 * @param activityDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ActivityDTO save(Long siteid, ActivityDTO activityDTO) {
		validateEvent(siteid);
		activityDTO.setEventId(siteid);
		Activity activity = activityMapper.toEntity(activityDTO);
		activity = activityRepository.save(activity);
		return activityMapper.toDto(activity);
	}

	/**
	 * Get all the activities.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ActivityDTO> findAll(Long siteid, Pageable pageable) {
		log.debug("Request to get all Activities");
		validateEvent(siteid);
		return activityRepository.findByEventId(siteid, pageable).map(activityMapper::toDto);
	}

	/**
	 * Get one activity by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ActivityDTO> findOne(Long siteid, Long id) {
		log.debug("Request to get Activity : {}", id);
		validateEvent(siteid);
		return activityRepository.findById(id).map(activityMapper::toDto);
	}

	/**
	 * Delete the activity by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long siteid, Long id) {
		log.debug("Request to delete Activity : {}", id);
		validateEvent(siteid);
		activityRepository.deleteById(id);
	}

	private void validateEvent(Long id) {
		this.eventRepository.findById(id).orElseThrow(() -> new SiteNotFoundException());
	}
}
