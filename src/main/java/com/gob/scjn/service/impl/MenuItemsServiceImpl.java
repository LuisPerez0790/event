package com.gob.scjn.service.impl;

import com.gob.scjn.service.MenuItemsService;
import com.gob.scjn.domain.MenuItems;
import com.gob.scjn.repository.MenuItemsRepository;
import com.gob.scjn.service.dto.MenuItemsDTO;
import com.gob.scjn.service.mapper.MenuItemsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing MenuItems.
 */
@Service
@Transactional
public class MenuItemsServiceImpl implements MenuItemsService {

    private final Logger log = LoggerFactory.getLogger(MenuItemsServiceImpl.class);

    private final MenuItemsRepository menuItemsRepository;

    private final MenuItemsMapper menuItemsMapper;

    public MenuItemsServiceImpl(MenuItemsRepository menuItemsRepository, MenuItemsMapper menuItemsMapper) {
        this.menuItemsRepository = menuItemsRepository;
        this.menuItemsMapper = menuItemsMapper;
    }

    /**
     * Save a menuItems.
     *
     * @param menuItemsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuItemsDTO save(MenuItemsDTO menuItemsDTO) {
        log.debug("Request to save MenuItems : {}", menuItemsDTO);
        MenuItems menuItems = menuItemsMapper.toEntity(menuItemsDTO);
        menuItems = menuItemsRepository.save(menuItems);
        return menuItemsMapper.toDto(menuItems);
    }

    /**
     * Get all the menuItems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuItemsDTO> findAll() {
        log.debug("Request to get all MenuItems");
        return menuItemsRepository.findAll().stream()
            .map(menuItemsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one menuItems by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuItemsDTO> findOne(Long id) {
        log.debug("Request to get MenuItems : {}", id);
        return menuItemsRepository.findById(id)
            .map(menuItemsMapper::toDto);
    }

    /**
     * Delete the menuItems by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuItems : {}", id);
        menuItemsRepository.deleteById(id);
    }
}
