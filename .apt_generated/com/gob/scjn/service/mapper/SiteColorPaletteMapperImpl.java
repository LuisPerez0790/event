package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.SiteColorPalette;
import com.gob.scjn.service.dto.SiteColorPaletteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-21T14:51:12-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class SiteColorPaletteMapperImpl implements SiteColorPaletteMapper {

    @Override
    public SiteColorPalette toEntity(SiteColorPaletteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SiteColorPalette siteColorPalette = new SiteColorPalette();

        siteColorPalette.setId( dto.getId() );
        siteColorPalette.setPrimary( dto.getPrimary() );
        siteColorPalette.setSecondary( dto.getSecondary() );
        siteColorPalette.setInverse( dto.getInverse() );
        siteColorPalette.setComplementary( dto.getComplementary() );

        return siteColorPalette;
    }

    @Override
    public SiteColorPaletteDTO toDto(SiteColorPalette entity) {
        if ( entity == null ) {
            return null;
        }

        SiteColorPaletteDTO siteColorPaletteDTO = new SiteColorPaletteDTO();

        siteColorPaletteDTO.setId( entity.getId() );
        siteColorPaletteDTO.setPrimary( entity.getPrimary() );
        siteColorPaletteDTO.setSecondary( entity.getSecondary() );
        siteColorPaletteDTO.setInverse( entity.getInverse() );
        siteColorPaletteDTO.setComplementary( entity.getComplementary() );

        return siteColorPaletteDTO;
    }

    @Override
    public List<SiteColorPalette> toEntity(List<SiteColorPaletteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SiteColorPalette> list = new ArrayList<SiteColorPalette>( dtoList.size() );
        for ( SiteColorPaletteDTO siteColorPaletteDTO : dtoList ) {
            list.add( toEntity( siteColorPaletteDTO ) );
        }

        return list;
    }

    @Override
    public List<SiteColorPaletteDTO> toDto(List<SiteColorPalette> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SiteColorPaletteDTO> list = new ArrayList<SiteColorPaletteDTO>( entityList.size() );
        for ( SiteColorPalette siteColorPalette : entityList ) {
            list.add( toDto( siteColorPalette ) );
        }

        return list;
    }
}
