package com.gob.scjn.service.impl;

import com.gob.scjn.service.ActivityService;
import com.gob.scjn.domain.Activity;
import com.gob.scjn.repository.ActivityRepository;
import com.gob.scjn.service.dto.ActivityDTO;
import com.gob.scjn.service.mapper.ActivityMapper;
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

	public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper) {
		this.activityRepository = activityRepository;
		this.activityMapper = activityMapper;
	}

	/**
	 * Save a activity.
	 *
	 * @param activityDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ActivityDTO save(ActivityDTO activityDTO) {
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
	public Page<ActivityDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Activities");
		return activityRepository.findAll(pageable).map(activityMapper::toDto);
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
	public Optional<ActivityDTO> findOne(Long id) {
		log.debug("Request to get Activity : {}", id);
		return activityRepository.findById(id).map(activityMapper::toDto);
	}

	/**
	 * Delete the activity by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Activity : {}", id);
		activityRepository.deleteById(id);
	}

	@Override
	public Page<ActivityDTO> findAllByEventId(Long id, Pageable pageable) {
		return activityRepository.findByEventId(id, pageable).map(activityMapper::toDto);
	}
}
