package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Event;
import com.gob.scjn.domain.EventLanguage;
import com.gob.scjn.domain.EventUser;
import com.gob.scjn.service.dto.EventDTO;
import com.gob.scjn.service.dto.EventUserDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-24T19:22:05-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Autowired
    private EventUserMapper eventUserMapper;

    @Override
    public List<EventDTO> toDto(List<Event> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>( arg0.size() );
        for ( Event event : arg0 ) {
            list.add( toDto( event ) );
        }

        return list;
    }

    @Override
    public List<Event> toEntity(List<EventDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( arg0.size() );
        for ( EventDTO eventDTO : arg0 ) {
            list.add( toEntity( eventDTO ) );
        }

        return list;
    }

    @Override
    public EventDTO toDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        eventDTO.setId( event.getId() );
        eventDTO.setStartDate( event.getStartDate() );
        eventDTO.setEndDate( event.getEndDate() );
        eventDTO.setUrl( event.getUrl() );
        eventDTO.setImageUrl( event.getImageUrl() );
        eventDTO.setEnabled( event.isEnabled() );
        eventDTO.setAcronym( event.getAcronym() );
        eventDTO.setEventUsers( eventUserSetToEventUserDTOSet( event.getEventUsers() ) );
        Set<EventLanguage> set1 = event.getLanguages();
        if ( set1 != null ) {
            eventDTO.setLanguages( new HashSet<EventLanguage>( set1 ) );
        }
        else {
            eventDTO.setLanguages( null );
        }

        return eventDTO;
    }

    @Override
    public Event toEntity(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        Event event = new Event();

        event.setId( eventDTO.getId() );
        event.setStartDate( eventDTO.getStartDate() );
        event.setEndDate( eventDTO.getEndDate() );
        event.setUrl( eventDTO.getUrl() );
        event.setImageUrl( eventDTO.getImageUrl() );
        event.setEnabled( eventDTO.isEnabled() );
        event.setAcronym( eventDTO.getAcronym() );
        Set<EventLanguage> set = eventDTO.getLanguages();
        if ( set != null ) {
            event.setLanguages( new HashSet<EventLanguage>( set ) );
        }
        else {
            event.setLanguages( null );
        }
        event.setEventUsers( eventUserDTOSetToEventUserSet( eventDTO.getEventUsers() ) );

        return event;
    }

    protected Set<EventUserDTO> eventUserSetToEventUserDTOSet(Set<EventUser> set) {
        if ( set == null ) {
            return null;
        }

        Set<EventUserDTO> set1 = new HashSet<EventUserDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EventUser eventUser : set ) {
            set1.add( eventUserMapper.toDto( eventUser ) );
        }

        return set1;
    }

    protected Set<EventUser> eventUserDTOSetToEventUserSet(Set<EventUserDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<EventUser> set1 = new HashSet<EventUser>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EventUserDTO eventUserDTO : set ) {
            set1.add( eventUserMapper.toEntity( eventUserDTO ) );
        }

        return set1;
    }
}
