package com.gob.scjn.service.impl;

import com.gob.scjn.service.MenuItemsLanguageService;
import com.gob.scjn.domain.MenuItemsLanguage;
import com.gob.scjn.repository.MenuItemsLanguageRepository;
import com.gob.scjn.service.dto.MenuItemsLanguageDTO;
import com.gob.scjn.service.mapper.MenuItemsLanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing MenuItemsLanguage.
 */
@Service
@Transactional
public class MenuItemsLanguageServiceImpl implements MenuItemsLanguageService {

    private final Logger log = LoggerFactory.getLogger(MenuItemsLanguageServiceImpl.class);

    private final MenuItemsLanguageRepository menuItemsLanguageRepository;

    private final MenuItemsLanguageMapper menuItemsLanguageMapper;

    public MenuItemsLanguageServiceImpl(MenuItemsLanguageRepository menuItemsLanguageRepository, MenuItemsLanguageMapper menuItemsLanguageMapper) {
        this.menuItemsLanguageRepository = menuItemsLanguageRepository;
        this.menuItemsLanguageMapper = menuItemsLanguageMapper;
    }

    /**
     * Save a menuItemsLanguage.
     *
     * @param menuItemsLanguageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuItemsLanguageDTO save(MenuItemsLanguageDTO menuItemsLanguageDTO) {
        log.debug("Request to save MenuItemsLanguage : {}", menuItemsLanguageDTO);
        MenuItemsLanguage menuItemsLanguage = menuItemsLanguageMapper.toEntity(menuItemsLanguageDTO);
        menuItemsLanguage = menuItemsLanguageRepository.save(menuItemsLanguage);
        return menuItemsLanguageMapper.toDto(menuItemsLanguage);
    }

    /**
     * Get all the menuItemsLanguages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuItemsLanguageDTO> findAll() {
        log.debug("Request to get all MenuItemsLanguages");
        return menuItemsLanguageRepository.findAll().stream()
            .map(menuItemsLanguageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one menuItemsLanguage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuItemsLanguageDTO> findOne(Long id) {
        log.debug("Request to get MenuItemsLanguage : {}", id);
        return menuItemsLanguageRepository.findById(id)
            .map(menuItemsLanguageMapper::toDto);
    }

    /**
     * Delete the menuItemsLanguage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuItemsLanguage : {}", id);
        menuItemsLanguageRepository.deleteById(id);
    }
}
