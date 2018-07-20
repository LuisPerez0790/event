package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.EventUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EventUser and its DTO EventUserDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventUserMapper extends EntityMapper<EventUserDTO, EventUser> {


    @Mapping(target = "cms", ignore = true)
    @Mapping(target = "sites", ignore = true)
    EventUser toEntity(EventUserDTO eventUserDTO);

    default EventUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventUser eventUser = new EventUser();
        eventUser.setId(id);
        return eventUser;
    }
}
