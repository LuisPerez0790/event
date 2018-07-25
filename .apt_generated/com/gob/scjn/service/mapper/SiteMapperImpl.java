package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Event;
import com.gob.scjn.domain.Site;
import com.gob.scjn.service.dto.SiteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-24T19:51:25-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class SiteMapperImpl implements SiteMapper {

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<SiteDTO> toDto(List<Site> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SiteDTO> list = new ArrayList<SiteDTO>( arg0.size() );
        for ( Site site : arg0 ) {
            list.add( toDto( site ) );
        }

        return list;
    }

    @Override
    public List<Site> toEntity(List<SiteDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Site> list = new ArrayList<Site>( arg0.size() );
        for ( SiteDTO siteDTO : arg0 ) {
            list.add( toEntity( siteDTO ) );
        }

        return list;
    }

    @Override
    public SiteDTO toDto(Site site) {
        if ( site == null ) {
            return null;
        }

        SiteDTO siteDTO = new SiteDTO();

        Long id = siteEventId( site );
        if ( id != null ) {
            siteDTO.setEventId( id );
        }
        siteDTO.setAcronym( site.getAcronym() );
        siteDTO.setDate( site.getDate() );
        siteDTO.setFooter( site.getFooter() );
        siteDTO.setId( site.getId() );
        siteDTO.setMenu( site.getMenu() );
        siteDTO.setPalette( site.getPalette() );
        siteDTO.setStatus( site.getStatus() );
        siteDTO.setSubtitle( site.getSubtitle() );
        siteDTO.setTitle( site.getTitle() );

        return siteDTO;
    }

    @Override
    public Site toEntity(SiteDTO siteDTO) {
        if ( siteDTO == null ) {
            return null;
        }

        Site site = new Site();

        site.setEvent( eventMapper.fromId( siteDTO.getEventId() ) );
        site.setAcronym( siteDTO.getAcronym() );
        site.setDate( siteDTO.getDate() );
        site.setFooter( siteDTO.getFooter() );
        site.setId( siteDTO.getId() );
        site.setMenu( siteDTO.getMenu() );
        site.setPalette( siteDTO.getPalette() );
        site.setStatus( siteDTO.getStatus() );
        site.setSubtitle( siteDTO.getSubtitle() );
        site.setTitle( siteDTO.getTitle() );

        return site;
    }

    private Long siteEventId(Site site) {
        if ( site == null ) {
            return null;
        }
        Event event = site.getEvent();
        if ( event == null ) {
            return null;
        }
        Long id = event.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
