package com.gob.scjn.service;

import com.gob.scjn.service.dto.SiteColorPaletteDTO;

/**
 * Service Interface for managing SiteColorPalette.
 */
public interface SiteColorPaletteService {

    /**
     * Save a siteColorPalette.
     *
     * @param site id & siteColorPaletteDTO
     * @return the persisted entity
     */
	SiteColorPaletteDTO save(Long id, SiteColorPaletteDTO siteColorPaletteDTO);


    /**
     * Get the "id" siteColorPalette.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SiteColorPaletteDTO findOne(Long id);

    /**
     * Delete the "id" siteColorPalette.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	
}
