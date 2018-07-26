package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.MenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Menu and its DTO MenuDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuItemsMapper.class})
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

	
    Menu toEntity(MenuDTO menuDTO);

    default Menu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(id);
        return menu;
    }
}
