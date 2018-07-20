package com.gob.scjn.service;

import com.gob.scjn.service.dto.VenueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Venue.
 */
public interface VenueService {

    /**
     * Save a venue.
     *
     * @param venueDTO the entity to save
     * @return the persisted entity
     */
    VenueDTO save(VenueDTO venueDTO);

    /**
     * Get all the venues.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VenueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" venue.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VenueDTO> findOne(Long id);

    /**
     * Delete the "id" venue.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
