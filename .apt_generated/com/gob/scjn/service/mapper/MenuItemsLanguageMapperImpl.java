package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.MenuItemsLanguage;
import com.gob.scjn.service.dto.MenuItemsLanguageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-24T19:22:05-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class MenuItemsLanguageMapperImpl implements MenuItemsLanguageMapper {

    @Override
    public MenuItemsLanguageDTO toDto(MenuItemsLanguage arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MenuItemsLanguageDTO menuItemsLanguageDTO = new MenuItemsLanguageDTO();

        menuItemsLanguageDTO.setId( arg0.getId() );
        menuItemsLanguageDTO.setName( arg0.getName() );
        menuItemsLanguageDTO.setLanguage( arg0.getLanguage() );

        return menuItemsLanguageDTO;
    }

    @Override
    public List<MenuItemsLanguageDTO> toDto(List<MenuItemsLanguage> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MenuItemsLanguageDTO> list = new ArrayList<MenuItemsLanguageDTO>( arg0.size() );
        for ( MenuItemsLanguage menuItemsLanguage : arg0 ) {
            list.add( toDto( menuItemsLanguage ) );
        }

        return list;
    }

    @Override
    public MenuItemsLanguage toEntity(MenuItemsLanguageDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MenuItemsLanguage menuItemsLanguage = new MenuItemsLanguage();

        menuItemsLanguage.setId( arg0.getId() );
        menuItemsLanguage.setName( arg0.getName() );
        menuItemsLanguage.setLanguage( arg0.getLanguage() );

        return menuItemsLanguage;
    }

    @Override
    public List<MenuItemsLanguage> toEntity(List<MenuItemsLanguageDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MenuItemsLanguage> list = new ArrayList<MenuItemsLanguage>( arg0.size() );
        for ( MenuItemsLanguageDTO menuItemsLanguageDTO : arg0 ) {
            list.add( toEntity( menuItemsLanguageDTO ) );
        }

        return list;
    }
}
