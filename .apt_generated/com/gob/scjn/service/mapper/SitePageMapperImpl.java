package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Site;
import com.gob.scjn.domain.SitePage;
import com.gob.scjn.service.dto.SitePageDTO;
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
public class SitePageMapperImpl implements SitePageMapper {

    @Autowired
    private SiteMapper siteMapper;

    @Override
    public List<SitePage> toEntity(List<SitePageDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SitePage> list = new ArrayList<SitePage>( dtoList.size() );
        for ( SitePageDTO sitePageDTO : dtoList ) {
            list.add( toEntity( sitePageDTO ) );
        }

        return list;
    }

    @Override
    public List<SitePageDTO> toDto(List<SitePage> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SitePageDTO> list = new ArrayList<SitePageDTO>( entityList.size() );
        for ( SitePage sitePage : entityList ) {
            list.add( toDto( sitePage ) );
        }

        return list;
    }

    @Override
    public SitePageDTO toDto(SitePage sitePage) {
        if ( sitePage == null ) {
            return null;
        }

        SitePageDTO sitePageDTO = new SitePageDTO();

        Long id = sitePageSiteId( sitePage );
        if ( id != null ) {
            sitePageDTO.setSiteId( id );
        }
        sitePageDTO.setId( sitePage.getId() );
        sitePageDTO.setCreationDate( sitePage.getCreationDate() );
        sitePageDTO.setUpdatedDate( sitePage.getUpdatedDate() );
        sitePageDTO.setMenuEntry( sitePage.getMenuEntry() );
        sitePageDTO.setOrder( sitePage.getOrder() );
        sitePageDTO.setStatus( sitePage.isStatus() );
        sitePageDTO.setSlug( sitePage.getSlug() );

        return sitePageDTO;
    }

    @Override
    public SitePage toEntity(SitePageDTO sitePageDTO) {
        if ( sitePageDTO == null ) {
            return null;
        }

        SitePage sitePage = new SitePage();

        sitePage.setSite( siteMapper.fromId( sitePageDTO.getSiteId() ) );
        sitePage.setId( sitePageDTO.getId() );
        sitePage.setCreationDate( sitePageDTO.getCreationDate() );
        sitePage.setUpdatedDate( sitePageDTO.getUpdatedDate() );
        sitePage.setMenuEntry( sitePageDTO.getMenuEntry() );
        sitePage.setOrder( sitePageDTO.getOrder() );
        sitePage.setStatus( sitePageDTO.isStatus() );
        sitePage.setSlug( sitePageDTO.getSlug() );

        return sitePage;
    }

    private Long sitePageSiteId(SitePage sitePage) {
        if ( sitePage == null ) {
            return null;
        }
        Site site = sitePage.getSite();
        if ( site == null ) {
            return null;
        }
        Long id = site.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
