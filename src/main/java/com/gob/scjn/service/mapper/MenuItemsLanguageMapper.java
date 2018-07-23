package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.MenuItemsLanguageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MenuItemsLanguage and its DTO MenuItemsLanguageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MenuItemsLanguageMapper extends EntityMapper<MenuItemsLanguageDTO, MenuItemsLanguage> {



    default MenuItemsLanguage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuItemsLanguage menuItemsLanguage = new MenuItemsLanguage();
        menuItemsLanguage.setId(id);
        return menuItemsLanguage;
    }
}
