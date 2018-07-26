package com.gob.scjn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gob.scjn.domain.Site;
import com.gob.scjn.repository.SiteRepository;
import com.gob.scjn.service.SiteFooterService;
import com.gob.scjn.service.dto.SiteFooterDTO;
import com.gob.scjn.service.mapper.SiteFooterMapper;

/**
 * Service Implementation for managing SiteFooter.
 */
@Service
@Transactional
public class SiteFooterServiceImpl implements SiteFooterService {

	private final Logger log = LoggerFactory.getLogger(SiteFooterServiceImpl.class);

	private final SiteFooterMapper siteFooterMapper;

	private final SiteRepository siteRepository;

	public SiteFooterServiceImpl(SiteFooterMapper siteFooterMapper, SiteRepository siteRepository) {
		this.siteFooterMapper = siteFooterMapper;
		this.siteRepository = siteRepository;
	}

	/**
	 * Save a siteFooter.
	 *
	 * @param siteFooterDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public SiteFooterDTO save(Long id, SiteFooterDTO siteFooterDTO) {
		log.debug("Request to save SiteFooter : {}", siteFooterDTO);
		Site site = findByEventId(id);
		site.setFooter(siteFooterMapper.toEntity(siteFooterDTO));
		site = siteRepository.save(site);
		return siteFooterMapper.toDto(site.getFooter());
	}

	/**
	 * Get one siteFooter by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public SiteFooterDTO findOne(Long id) {
		return siteFooterMapper.toDto(findByEventId(id).getFooter());
	}

	/**
	 * Delete the siteFooter by id.
	 *
	 * @param id
	 *            the id of the entity
	 */

	@Override
	public void delete(Long id) {
		log.debug("Request to delete SiteColorPalette : {}", id);
		Site site = findByEventId(id);
		site.setFooter(null);
		siteRepository.save(site);
	}

	private Site findByEventId(Long id) {
		return siteRepository.findById(id).get();
	}
}
