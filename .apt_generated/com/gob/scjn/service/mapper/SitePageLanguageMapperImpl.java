package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.SitePage;
import com.gob.scjn.domain.SitePageLanguage;
import com.gob.scjn.service.dto.SitePageLanguageDTO;
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
public class SitePageLanguageMapperImpl implements SitePageLanguageMapper {

    @Autowired
    private SitePageMapper sitePageMapper;

    @Override
    public List<SitePageLanguage> toEntity(List<SitePageLanguageDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SitePageLanguage> list = new ArrayList<SitePageLanguage>( dtoList.size() );
        for ( SitePageLanguageDTO sitePageLanguageDTO : dtoList ) {
            list.add( toEntity( sitePageLanguageDTO ) );
        }

        return list;
    }

    @Override
    public List<SitePageLanguageDTO> toDto(List<SitePageLanguage> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SitePageLanguageDTO> list = new ArrayList<SitePageLanguageDTO>( entityList.size() );
        for ( SitePageLanguage sitePageLanguage : entityList ) {
            list.add( toDto( sitePageLanguage ) );
        }

        return list;
    }

    @Override
    public SitePageLanguageDTO toDto(SitePageLanguage sitePageLanguage) {
        if ( sitePageLanguage == null ) {
            return null;
        }

        SitePageLanguageDTO sitePageLanguageDTO = new SitePageLanguageDTO();

        Long id = sitePageLanguageSitePageId( sitePageLanguage );
        if ( id != null ) {
            sitePageLanguageDTO.setSitePageId( id );
        }
        sitePageLanguageDTO.setId( sitePageLanguage.getId() );
        sitePageLanguageDTO.setExceptPage( sitePageLanguage.getExceptPage() );
        sitePageLanguageDTO.setContent( sitePageLanguage.getContent() );
        sitePageLanguageDTO.setTitle( sitePageLanguage.getTitle() );
        sitePageLanguageDTO.setLanguage( sitePageLanguage.getLanguage() );

        return sitePageLanguageDTO;
    }

    @Override
    public SitePageLanguage toEntity(SitePageLanguageDTO sitePageLanguageDTO) {
        if ( sitePageLanguageDTO == null ) {
            return null;
        }

        SitePageLanguage sitePageLanguage = new SitePageLanguage();

        sitePageLanguage.setSitePage( sitePageMapper.fromId( sitePageLanguageDTO.getSitePageId() ) );
        sitePageLanguage.setId( sitePageLanguageDTO.getId() );
        sitePageLanguage.setExceptPage( sitePageLanguageDTO.getExceptPage() );
        sitePageLanguage.setContent( sitePageLanguageDTO.getContent() );
        sitePageLanguage.setTitle( sitePageLanguageDTO.getTitle() );
        sitePageLanguage.setLanguage( sitePageLanguageDTO.getLanguage() );

        return sitePageLanguage;
    }

    private Long sitePageLanguageSitePageId(SitePageLanguage sitePageLanguage) {
        if ( sitePageLanguage == null ) {
            return null;
        }
        SitePage sitePage = sitePageLanguage.getSitePage();
        if ( sitePage == null ) {
            return null;
        }
        Long id = sitePage.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
