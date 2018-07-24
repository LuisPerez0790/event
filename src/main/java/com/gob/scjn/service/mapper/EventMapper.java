package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.EventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Event and its DTO EventDTO.
 */
@Mapper(componentModel = "spring", uses = {VenueMapper.class, SiteMapper.class, EventUserMapper.class})
public interface EventMapper extends EntityMapper<EventDTO, Event> {

    EventDTO toDto(Event event);

    @Mapping(target = "activities", ignore = true)
    @Mapping(target = "cms", ignore = true)
    Event toEntity(EventDTO eventDTO);

    default Event fromId(Long id) {
        if (id == null) {
            return null;
        }
        Event event = new Event();
        event.setId(id);
        return event;
    }
}
