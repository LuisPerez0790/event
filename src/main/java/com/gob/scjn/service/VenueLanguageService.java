package com.gob.scjn.service;

import com.gob.scjn.service.dto.VenueLanguageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing VenueLanguage.
 */
public interface VenueLanguageService {

    /**
     * Save a venueLanguage.
     *
     * @param venueLanguageDTO the entity to save
     * @return the persisted entity
     */
    VenueLanguageDTO save(VenueLanguageDTO venueLanguageDTO);

    /**
     * Get all the venueLanguages.
     *
     * @return the list of entities
     */
    List<VenueLanguageDTO> findAll();


    /**
     * Get the "id" venueLanguage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VenueLanguageDTO> findOne(Long id);

    /**
     * Delete the "id" venueLanguage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
