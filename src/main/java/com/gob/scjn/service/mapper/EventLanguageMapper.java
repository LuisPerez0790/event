package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.EventLanguageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EventLanguage and its DTO EventLanguageDTO.
 */
@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface EventLanguageMapper extends EntityMapper<EventLanguageDTO, EventLanguage> {

    //@Mapping(source = "event.id", target = "eventId")
    EventLanguageDTO toDto(EventLanguage eventLanguage);

    //@Mapping(source = "eventId", target = "event")
    EventLanguage toEntity(EventLanguageDTO eventLanguageDTO);

    default EventLanguage fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventLanguage eventLanguage = new EventLanguage();
        eventLanguage.setId(id);
        return eventLanguage;
    }
}
