package com.gob.scjn.service.impl;

import com.gob.scjn.service.SiteColorPaletteService;
import com.gob.scjn.domain.SiteColorPalette;
import com.gob.scjn.repository.SiteColorPaletteRepository;
import com.gob.scjn.service.dto.SiteColorPaletteDTO;
import com.gob.scjn.service.mapper.SiteColorPaletteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing SiteColorPalette.
 */
@Service
@Transactional
public class SiteColorPaletteServiceImpl implements SiteColorPaletteService {

    private final Logger log = LoggerFactory.getLogger(SiteColorPaletteServiceImpl.class);

    private final SiteColorPaletteRepository siteColorPaletteRepository;

    private final SiteColorPaletteMapper siteColorPaletteMapper;

    public SiteColorPaletteServiceImpl(SiteColorPaletteRepository siteColorPaletteRepository, SiteColorPaletteMapper siteColorPaletteMapper) {
        this.siteColorPaletteRepository = siteColorPaletteRepository;
        this.siteColorPaletteMapper = siteColorPaletteMapper;
    }

    /**
     * Save a siteColorPalette.
     *
     * @param siteColorPaletteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SiteColorPaletteDTO save(SiteColorPaletteDTO siteColorPaletteDTO) {
        log.debug("Request to save SiteColorPalette : {}", siteColorPaletteDTO);
        SiteColorPalette siteColorPalette = siteColorPaletteMapper.toEntity(siteColorPaletteDTO);
        siteColorPalette = siteColorPaletteRepository.save(siteColorPalette);
        return siteColorPaletteMapper.toDto(siteColorPalette);
    }

    /**
     * Get all the siteColorPalettes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SiteColorPaletteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SiteColorPalettes");
        return siteColorPaletteRepository.findAll(pageable)
            .map(siteColorPaletteMapper::toDto);
    }


    /**
     * Get one siteColorPalette by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SiteColorPaletteDTO> findOne(Long id) {
        log.debug("Request to get SiteColorPalette : {}", id);
        return siteColorPaletteRepository.findById(id)
            .map(siteColorPaletteMapper::toDto);
    }

    /**
     * Delete the siteColorPalette by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SiteColorPalette : {}", id);
        siteColorPaletteRepository.deleteById(id);
    }
}
