package com.gob.scjn.service;

import com.gob.scjn.service.dto.SiteColorPaletteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SiteColorPalette.
 */
public interface SiteColorPaletteService {

    /**
     * Save a siteColorPalette.
     *
     * @param siteColorPaletteDTO the entity to save
     * @return the persisted entity
     */
    SiteColorPaletteDTO save(SiteColorPaletteDTO siteColorPaletteDTO);

    /**
     * Get all the siteColorPalettes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SiteColorPaletteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" siteColorPalette.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SiteColorPaletteDTO> findOne(Long id);

    /**
     * Delete the "id" siteColorPalette.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	SiteColorPaletteDTO findFooterBySiteId(Long id);
}
