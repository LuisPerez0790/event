package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.MenuItems;
import com.gob.scjn.service.dto.MenuItemsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-24T20:56:20-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class MenuItemsMapperImpl implements MenuItemsMapper {

    @Override
    public MenuItemsDTO toDto(MenuItems arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MenuItemsDTO menuItemsDTO = new MenuItemsDTO();

        menuItemsDTO.setId( arg0.getId() );
        menuItemsDTO.setUrl( arg0.getUrl() );
        menuItemsDTO.setWeight( arg0.getWeight() );

        return menuItemsDTO;
    }

    @Override
    public List<MenuItemsDTO> toDto(List<MenuItems> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MenuItemsDTO> list = new ArrayList<MenuItemsDTO>( arg0.size() );
        for ( MenuItems menuItems : arg0 ) {
            list.add( toDto( menuItems ) );
        }

        return list;
    }

    @Override
    public List<MenuItems> toEntity(List<MenuItemsDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MenuItems> list = new ArrayList<MenuItems>( arg0.size() );
        for ( MenuItemsDTO menuItemsDTO : arg0 ) {
            list.add( toEntity( menuItemsDTO ) );
        }

        return list;
    }

    @Override
    public MenuItems toEntity(MenuItemsDTO menuItemsDTO) {
        if ( menuItemsDTO == null ) {
            return null;
        }

        MenuItems menuItems = new MenuItems();

        menuItems.setId( menuItemsDTO.getId() );
        menuItems.setUrl( menuItemsDTO.getUrl() );
        menuItems.setWeight( menuItemsDTO.getWeight() );

        return menuItems;
    }
}
