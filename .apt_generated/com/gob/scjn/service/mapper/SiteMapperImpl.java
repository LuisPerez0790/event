package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Site;
import com.gob.scjn.domain.SiteColorPalette;
import com.gob.scjn.domain.SiteFooter;
import com.gob.scjn.service.dto.SiteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-21T14:51:12-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class SiteMapperImpl implements SiteMapper {

    @Autowired
    private SiteColorPaletteMapper siteColorPaletteMapper;
    @Autowired
    private SiteFooterMapper siteFooterMapper;

    @Override
    public List<Site> toEntity(List<SiteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Site> list = new ArrayList<Site>( dtoList.size() );
        for ( SiteDTO siteDTO : dtoList ) {
            list.add( toEntity( siteDTO ) );
        }

        return list;
    }

    @Override
    public List<SiteDTO> toDto(List<Site> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SiteDTO> list = new ArrayList<SiteDTO>( entityList.size() );
        for ( Site site : entityList ) {
            list.add( toDto( site ) );
        }

        return list;
    }

    @Override
    public SiteDTO toDto(Site site) {
        if ( site == null ) {
            return null;
        }

        SiteDTO siteDTO = new SiteDTO();

        Long id = siteFooterId( site );
        if ( id != null ) {
            siteDTO.setFooterId( id );
        }
        Long id1 = sitePaletteId( site );
        if ( id1 != null ) {
            siteDTO.setPaletteId( id1 );
        }
        siteDTO.setId( site.getId() );
        siteDTO.setAcronym( site.getAcronym() );
        siteDTO.setDate( site.getDate() );
        siteDTO.setStatus( site.getStatus() );
        siteDTO.setTitle( site.getTitle() );
        siteDTO.setSubtitle( site.getSubtitle() );

        return siteDTO;
    }

    @Override
    public Site toEntity(SiteDTO siteDTO) {
        if ( siteDTO == null ) {
            return null;
        }

        Site site = new Site();

        site.setPalette( siteColorPaletteMapper.fromId( siteDTO.getPaletteId() ) );
        site.setFooter( siteFooterMapper.fromId( siteDTO.getFooterId() ) );
        site.setId( siteDTO.getId() );
        site.setAcronym( siteDTO.getAcronym() );
        site.setDate( siteDTO.getDate() );
        site.setStatus( siteDTO.getStatus() );
        site.setTitle( siteDTO.getTitle() );
        site.setSubtitle( siteDTO.getSubtitle() );

        return site;
    }

    private Long siteFooterId(Site site) {
        if ( site == null ) {
            return null;
        }
        SiteFooter footer = site.getFooter();
        if ( footer == null ) {
            return null;
        }
        Long id = footer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long sitePaletteId(Site site) {
        if ( site == null ) {
            return null;
        }
        SiteColorPalette palette = site.getPalette();
        if ( palette == null ) {
            return null;
        }
        Long id = palette.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
