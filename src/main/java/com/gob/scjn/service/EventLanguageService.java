package com.gob.scjn.service;

import com.gob.scjn.service.dto.EventLanguageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EventLanguage.
 */
public interface EventLanguageService {

    /**
     * Save a eventLanguage.
     *
     * @param eventLanguageDTO the entity to save
     * @return the persisted entity
     */
    EventLanguageDTO save(EventLanguageDTO eventLanguageDTO);

    /**
     * Get all the eventLanguages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EventLanguageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" eventLanguage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EventLanguageDTO> findOne(Long id);

    /**
     * Delete the "id" eventLanguage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

}
