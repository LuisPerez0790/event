package com.gob.scjn.service;

import com.gob.scjn.service.dto.ActivityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Activity.
 */
public interface ActivityService {

    /**
     * Save a activity.
     *
     * @param activityDTO the entity to save
     * @return the persisted entity
     */
    ActivityDTO save(ActivityDTO activityDTO);

    /**
     * Get all the activities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ActivityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" activity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ActivityDTO> findOne(Long id);

    /**
     * Delete the "id" activity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	Page<ActivityDTO> findAllByEventId(Long id, Pageable pageable);
}
