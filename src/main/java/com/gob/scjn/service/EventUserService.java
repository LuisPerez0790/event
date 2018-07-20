package com.gob.scjn.service;

import com.gob.scjn.service.dto.EventUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EventUser.
 */
public interface EventUserService {

    /**
     * Save a eventUser.
     *
     * @param eventUserDTO the entity to save
     * @return the persisted entity
     */
    EventUserDTO save(EventUserDTO eventUserDTO);

    /**
     * Get all the eventUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EventUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" eventUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EventUserDTO> findOne(Long id);

    /**
     * Delete the "id" eventUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
