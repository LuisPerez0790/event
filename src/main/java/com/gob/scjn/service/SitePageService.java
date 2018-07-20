package com.gob.scjn.service;

import com.gob.scjn.service.dto.SitePageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SitePage.
 */
public interface SitePageService {

    /**
     * Save a sitePage.
     *
     * @param sitePageDTO the entity to save
     * @return the persisted entity
     */
    SitePageDTO save(SitePageDTO sitePageDTO);

    /**
     * Get all the sitePages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SitePageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sitePage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SitePageDTO> findOne(Long id);

    /**
     * Delete the "id" sitePage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
