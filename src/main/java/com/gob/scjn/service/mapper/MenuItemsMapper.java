package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.MenuItemsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MenuItems and its DTO MenuItemsDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuItemsLanguageMapper.class})
public interface MenuItemsMapper extends EntityMapper<MenuItemsDTO, MenuItems> {

    MenuItems toEntity(MenuItemsDTO menuItemsDTO);

    default MenuItems fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuItems menuItems = new MenuItems();
        menuItems.setId(id);
        return menuItems;
    }
}
