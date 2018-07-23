package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.EventUser;
import com.gob.scjn.service.dto.EventUserDTO;
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
public class EventUserMapperImpl implements EventUserMapper {

    @Override
    public EventUserDTO toDto(EventUser entity) {
        if ( entity == null ) {
            return null;
        }

        EventUserDTO eventUserDTO = new EventUserDTO();

        eventUserDTO.setId( entity.getId() );
        eventUserDTO.setName( entity.getName() );
        eventUserDTO.setLastName( entity.getLastName() );
        eventUserDTO.setUserName( entity.getUserName() );
        eventUserDTO.setInstitution( entity.getInstitution() );
        eventUserDTO.setPosition( entity.getPosition() );
        eventUserDTO.setEmail( entity.getEmail() );
        eventUserDTO.setDescription( entity.getDescription() );
        eventUserDTO.setImageUrl( entity.getImageUrl() );
        eventUserDTO.setUserType( entity.getUserType() );

        return eventUserDTO;
    }

    @Override
    public List<EventUser> toEntity(List<EventUserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EventUser> list = new ArrayList<EventUser>( dtoList.size() );
        for ( EventUserDTO eventUserDTO : dtoList ) {
            list.add( toEntity( eventUserDTO ) );
        }

        return list;
    }

    @Override
    public List<EventUserDTO> toDto(List<EventUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EventUserDTO> list = new ArrayList<EventUserDTO>( entityList.size() );
        for ( EventUser eventUser : entityList ) {
            list.add( toDto( eventUser ) );
        }

        return list;
    }

    @Override
    public EventUser toEntity(EventUserDTO eventUserDTO) {
        if ( eventUserDTO == null ) {
            return null;
        }

        EventUser eventUser = new EventUser();

        eventUser.setId( eventUserDTO.getId() );
        eventUser.setName( eventUserDTO.getName() );
        eventUser.setLastName( eventUserDTO.getLastName() );
        eventUser.setUserName( eventUserDTO.getUserName() );
        eventUser.setInstitution( eventUserDTO.getInstitution() );
        eventUser.setPosition( eventUserDTO.getPosition() );
        eventUser.setEmail( eventUserDTO.getEmail() );
        eventUser.setDescription( eventUserDTO.getDescription() );
        eventUser.setImageUrl( eventUserDTO.getImageUrl() );
        eventUser.setUserType( eventUserDTO.getUserType() );

        return eventUser;
    }
}
