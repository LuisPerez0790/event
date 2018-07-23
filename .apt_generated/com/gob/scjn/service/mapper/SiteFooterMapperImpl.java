package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.SiteFooter;
import com.gob.scjn.service.dto.SiteFooterDTO;
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
public class SiteFooterMapperImpl implements SiteFooterMapper {

    @Override
    public SiteFooter toEntity(SiteFooterDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SiteFooter siteFooter = new SiteFooter();

        siteFooter.setId( dto.getId() );
        siteFooter.setAddress( dto.getAddress() );
        siteFooter.setContact( dto.getContact() );
        siteFooter.setLinks( dto.getLinks() );
        siteFooter.setMoreContent( dto.getMoreContent() );
        siteFooter.setGoogleMaps( dto.getGoogleMaps() );

        return siteFooter;
    }

    @Override
    public SiteFooterDTO toDto(SiteFooter entity) {
        if ( entity == null ) {
            return null;
        }

        SiteFooterDTO siteFooterDTO = new SiteFooterDTO();

        siteFooterDTO.setId( entity.getId() );
        siteFooterDTO.setAddress( entity.getAddress() );
        siteFooterDTO.setContact( entity.getContact() );
        siteFooterDTO.setLinks( entity.getLinks() );
        siteFooterDTO.setMoreContent( entity.getMoreContent() );
        siteFooterDTO.setGoogleMaps( entity.getGoogleMaps() );

        return siteFooterDTO;
    }

    @Override
    public List<SiteFooter> toEntity(List<SiteFooterDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SiteFooter> list = new ArrayList<SiteFooter>( dtoList.size() );
        for ( SiteFooterDTO siteFooterDTO : dtoList ) {
            list.add( toEntity( siteFooterDTO ) );
        }

        return list;
    }

    @Override
    public List<SiteFooterDTO> toDto(List<SiteFooter> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SiteFooterDTO> list = new ArrayList<SiteFooterDTO>( entityList.size() );
        for ( SiteFooter siteFooter : entityList ) {
            list.add( toDto( siteFooter ) );
        }

        return list;
    }
}
