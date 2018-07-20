package com.gob.scjn.service;

import com.gob.scjn.service.dto.ActivityLanguageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ActivityLanguage.
 */
public interface ActivityLanguageService {

    /**
     * Save a activityLanguage.
     *
     * @param activityLanguageDTO the entity to save
     * @return the persisted entity
     */
    ActivityLanguageDTO save(ActivityLanguageDTO activityLanguageDTO);

    /**
     * Get all the activityLanguages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ActivityLanguageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" activityLanguage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ActivityLanguageDTO> findOne(Long id);

    /**
     * Delete the "id" activityLanguage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
