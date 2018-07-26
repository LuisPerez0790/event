package com.gob.scjn.service;

import com.gob.scjn.service.dto.MenuDTO;

/**
 * Service Interface for managing Menu.
 */
public interface MenuService {

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save
     * @return the persisted entity
     */
    MenuDTO save(Long id, MenuDTO menuDTO);

    /**
     * Get the "id" menu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MenuDTO findOne(Long id);

    /**
     * Delete the "id" menu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
