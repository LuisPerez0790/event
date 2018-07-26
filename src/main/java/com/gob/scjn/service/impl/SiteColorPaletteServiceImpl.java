package com.gob.scjn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gob.scjn.domain.Site;
import com.gob.scjn.repository.SiteRepository;
import com.gob.scjn.service.SiteColorPaletteService;
import com.gob.scjn.service.dto.SiteColorPaletteDTO;
import com.gob.scjn.service.mapper.SiteColorPaletteMapper;

/**
 * Service Implementation for managing SiteColorPalette.
 */
@Service
@Transactional
public class SiteColorPaletteServiceImpl implements SiteColorPaletteService {

	private final Logger log = LoggerFactory.getLogger(SiteColorPaletteServiceImpl.class);

	private final SiteColorPaletteMapper siteColorPaletteMapper;

	private final SiteRepository siteRepository;

	public SiteColorPaletteServiceImpl(SiteColorPaletteMapper siteColorPaletteMapper, SiteRepository siteRepository) {
		this.siteColorPaletteMapper = siteColorPaletteMapper;
		this.siteRepository = siteRepository;
	}

	/**
	 * Save a siteColorPalette.
	 *
	 * @param siteColorPaletteDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public SiteColorPaletteDTO save(Long id, SiteColorPaletteDTO siteColorPaletteDTO) {
		Site site = findByEventId(id);
		site.setPalette(siteColorPaletteMapper.toEntity(siteColorPaletteDTO));
		site = siteRepository.save(site);
		return siteColorPaletteMapper.toDto(site.getPalette());
	}

	/**
	 * Get site color palette.
	 *
	 * @param site
	 *            id
	 * @return site color palette
	 */
	@Override
	@Transactional(readOnly = true)
	public SiteColorPaletteDTO findOne(Long id) {
		return siteColorPaletteMapper.toDto(findByEventId(id).getPalette());
	}

	/**
	 * Delete the siteColorPalette by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete SiteColorPalette : {}", id);
		Site site = findByEventId(id);
		site.setPalette(null);
		siteRepository.save(site);
	}
	
	private Site findByEventId(Long id) {
		return siteRepository.findById(id).get();
	}
}
