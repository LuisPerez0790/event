package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.EventLanguage;
import com.gob.scjn.service.dto.EventLanguageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-21T19:32:35-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class EventLanguageMapperImpl implements EventLanguageMapper {

    @Override
    public List<EventLanguageDTO> toDto(List<EventLanguage> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<EventLanguageDTO> list = new ArrayList<EventLanguageDTO>( arg0.size() );
        for ( EventLanguage eventLanguage : arg0 ) {
            list.add( toDto( eventLanguage ) );
        }

        return list;
    }

    @Override
    public List<EventLanguage> toEntity(List<EventLanguageDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<EventLanguage> list = new ArrayList<EventLanguage>( arg0.size() );
        for ( EventLanguageDTO eventLanguageDTO : arg0 ) {
            list.add( toEntity( eventLanguageDTO ) );
        }

        return list;
    }

    @Override
    public EventLanguageDTO toDto(EventLanguage eventLanguage) {
        if ( eventLanguage == null ) {
            return null;
        }

        EventLanguageDTO eventLanguageDTO = new EventLanguageDTO();

        eventLanguageDTO.setAddress( eventLanguage.getAddress() );
        eventLanguageDTO.setDescription( eventLanguage.getDescription() );
        eventLanguageDTO.setEvent( eventLanguage.getEvent() );
        eventLanguageDTO.setId( eventLanguage.getId() );
        eventLanguageDTO.setLanguage( eventLanguage.getLanguage() );
        eventLanguageDTO.setName( eventLanguage.getName() );

        return eventLanguageDTO;
    }

    @Override
    public EventLanguage toEntity(EventLanguageDTO eventLanguageDTO) {
        if ( eventLanguageDTO == null ) {
            return null;
        }

        EventLanguage eventLanguage = new EventLanguage();

        eventLanguage.setAddress( eventLanguageDTO.getAddress() );
        eventLanguage.setDescription( eventLanguageDTO.getDescription() );
        eventLanguage.setEvent( eventLanguageDTO.getEvent() );
        eventLanguage.setId( eventLanguageDTO.getId() );
        eventLanguage.setLanguage( eventLanguageDTO.getLanguage() );
        eventLanguage.setName( eventLanguageDTO.getName() );

        return eventLanguage;
    }
}
