package com.gob.scjn.service;

import com.gob.scjn.service.dto.SitePageLanguageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SitePageLanguage.
 */
public interface SitePageLanguageService {

    /**
     * Save a sitePageLanguage.
     *
     * @param sitePageLanguageDTO the entity to save
     * @return the persisted entity
     */
    SitePageLanguageDTO save(SitePageLanguageDTO sitePageLanguageDTO);

    /**
     * Get all the sitePageLanguages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SitePageLanguageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sitePageLanguage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SitePageLanguageDTO> findOne(Long id);

    /**
     * Delete the "id" sitePageLanguage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
