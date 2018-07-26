package com.gob.scjn.service;

import com.gob.scjn.service.dto.SiteFooterDTO;

/**
 * Service Interface for managing SiteFooter.
 */
public interface SiteFooterService {

    /**
     * Save a siteFooter.
     *
     * @param site id & siteFooterDTO the entity to save
     * @return the persisted entity
     */
    SiteFooterDTO save(Long id, SiteFooterDTO siteFooterDTO);

    /**
     * Get footer related to site id.
     *
     * @param id the id of the site
     * @return the entity
     */
    SiteFooterDTO findOne(Long id);

    /**
     * Delete the site footer.
     *
     * @param id the id of the site
     */
    void delete(Long id);
}
