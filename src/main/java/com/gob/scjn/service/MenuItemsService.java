package com.gob.scjn.service;

import com.gob.scjn.service.dto.MenuItemsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MenuItems.
 */
public interface MenuItemsService {

    /**
     * Save a menuItems.
     *
     * @param menuItemsDTO the entity to save
     * @return the persisted entity
     */
    MenuItemsDTO save(MenuItemsDTO menuItemsDTO);

    /**
     * Get all the menuItems.
     *
     * @return the list of entities
     */
    List<MenuItemsDTO> findAll();


    /**
     * Get the "id" menuItems.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MenuItemsDTO> findOne(Long id);

    /**
     * Delete the "id" menuItems.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
