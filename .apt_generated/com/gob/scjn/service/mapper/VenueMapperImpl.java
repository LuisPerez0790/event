package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Venue;
import com.gob.scjn.service.dto.VenueDTO;
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
public class VenueMapperImpl implements VenueMapper {

    @Override
    public Venue toEntity(VenueDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setId( dto.getId() );
        venue.setName( dto.getName() );
        venue.setAddress( dto.getAddress() );
        venue.setImageUrl( dto.getImageUrl() );
        venue.setPhone( dto.getPhone() );
        venue.setUrl( dto.getUrl() );
        venue.setGoogleMaps( dto.getGoogleMaps() );
        venue.setDescription( dto.getDescription() );

        return venue;
    }

    @Override
    public VenueDTO toDto(Venue entity) {
        if ( entity == null ) {
            return null;
        }

        VenueDTO venueDTO = new VenueDTO();

        venueDTO.setId( entity.getId() );
        venueDTO.setName( entity.getName() );
        venueDTO.setAddress( entity.getAddress() );
        venueDTO.setImageUrl( entity.getImageUrl() );
        venueDTO.setPhone( entity.getPhone() );
        venueDTO.setUrl( entity.getUrl() );
        venueDTO.setGoogleMaps( entity.getGoogleMaps() );
        venueDTO.setDescription( entity.getDescription() );

        return venueDTO;
    }

    @Override
    public List<Venue> toEntity(List<VenueDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Venue> list = new ArrayList<Venue>( dtoList.size() );
        for ( VenueDTO venueDTO : dtoList ) {
            list.add( toEntity( venueDTO ) );
        }

        return list;
    }

    @Override
    public List<VenueDTO> toDto(List<Venue> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VenueDTO> list = new ArrayList<VenueDTO>( entityList.size() );
        for ( Venue venue : entityList ) {
            list.add( toDto( venue ) );
        }

        return list;
    }
}
