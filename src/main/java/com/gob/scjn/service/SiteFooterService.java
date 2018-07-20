package com.gob.scjn.service;

import com.gob.scjn.service.dto.SiteFooterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SiteFooter.
 */
public interface SiteFooterService {

    /**
     * Save a siteFooter.
     *
     * @param siteFooterDTO the entity to save
     * @return the persisted entity
     */
    SiteFooterDTO save(SiteFooterDTO siteFooterDTO);

    /**
     * Get all the siteFooters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SiteFooterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" siteFooter.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SiteFooterDTO> findOne(Long id);

    /**
     * Delete the "id" siteFooter.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
