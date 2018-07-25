package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Menu;
import com.gob.scjn.domain.MenuItems;
import com.gob.scjn.service.dto.MenuDTO;
import com.gob.scjn.service.dto.MenuItemsDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-24T20:29:31-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public MenuDTO toDto(Menu arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId( arg0.getId() );
        menuDTO.setItems( menuItemsSetToMenuItemsDTOList( arg0.getItems() ) );

        return menuDTO;
    }

    @Override
    public List<MenuDTO> toDto(List<Menu> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MenuDTO> list = new ArrayList<MenuDTO>( arg0.size() );
        for ( Menu menu : arg0 ) {
            list.add( toDto( menu ) );
        }

        return list;
    }

    @Override
    public List<Menu> toEntity(List<MenuDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>( arg0.size() );
        for ( MenuDTO menuDTO : arg0 ) {
            list.add( toEntity( menuDTO ) );
        }

        return list;
    }

    @Override
    public Menu toEntity(MenuDTO menuDTO) {
        if ( menuDTO == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setId( menuDTO.getId() );

        return menu;
    }

    protected MenuItemsDTO menuItemsToMenuItemsDTO(MenuItems menuItems) {
        if ( menuItems == null ) {
            return null;
        }

        MenuItemsDTO menuItemsDTO = new MenuItemsDTO();

        menuItemsDTO.setId( menuItems.getId() );
        menuItemsDTO.setUrl( menuItems.getUrl() );
        menuItemsDTO.setWeight( menuItems.getWeight() );

        return menuItemsDTO;
    }

    protected List<MenuItemsDTO> menuItemsSetToMenuItemsDTOList(Set<MenuItems> set) {
        if ( set == null ) {
            return null;
        }

        List<MenuItemsDTO> list = new ArrayList<MenuItemsDTO>( set.size() );
        for ( MenuItems menuItems : set ) {
            list.add( menuItemsToMenuItemsDTO( menuItems ) );
        }

        return list;
    }
}
