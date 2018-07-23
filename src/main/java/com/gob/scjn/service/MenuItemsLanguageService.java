package com.gob.scjn.service;

import com.gob.scjn.service.dto.MenuItemsLanguageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MenuItemsLanguage.
 */
public interface MenuItemsLanguageService {

    /**
     * Save a menuItemsLanguage.
     *
     * @param menuItemsLanguageDTO the entity to save
     * @return the persisted entity
     */
    MenuItemsLanguageDTO save(MenuItemsLanguageDTO menuItemsLanguageDTO);

    /**
     * Get all the menuItemsLanguages.
     *
     * @return the list of entities
     */
    List<MenuItemsLanguageDTO> findAll();


    /**
     * Get the "id" menuItemsLanguage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MenuItemsLanguageDTO> findOne(Long id);

    /**
     * Delete the "id" menuItemsLanguage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
