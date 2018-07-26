package com.gob.scjn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gob.scjn.domain.Site;
import com.gob.scjn.repository.SiteRepository;
import com.gob.scjn.service.MenuService;
import com.gob.scjn.service.dto.MenuDTO;
import com.gob.scjn.service.mapper.MenuMapper;

/**
 * Service Implementation for managing Menu.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

	private final MenuMapper menuMapper;

	private final SiteRepository siteRepository;
	

	public MenuServiceImpl(MenuMapper menuMapper, SiteRepository siteRepository) {
		this.menuMapper = menuMapper;
		this.siteRepository = siteRepository;
	}

	/**
	 * Save a menu.
	 *
	 * @param menuDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public MenuDTO save(Long id, MenuDTO menuDTO) {
		log.debug("Request to save Menu : {}", menuDTO);
		Site site = findByEventId(id);
		site.setMenu(menuMapper.toEntity(menuDTO));
		site = siteRepository.save(site);
		return menuMapper.toDto(site.getMenu());
	}

	/**
	 * Get one menu by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public MenuDTO findOne(Long id) {
		Site site = findByEventId(id);
		System.out.println(site);
		return menuMapper.toDto(site.getMenu());
	}

	/**
	 * Delete the menu by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		Site site = findByEventId(id);
		site.setMenu(null);
		siteRepository.save(site);
	}

	private Site findByEventId(Long id) {
		return siteRepository.findById(id).get();
	}
}
