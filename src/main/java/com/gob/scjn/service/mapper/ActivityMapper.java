package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.ActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Activity and its DTO ActivityDTO.
 */
@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface ActivityMapper extends EntityMapper<ActivityDTO, Activity> {

    @Mapping(source = "event.id", target = "eventId")
    ActivityDTO toDto(Activity activity);

    @Mapping(source = "eventId", target = "event")
    @Mapping(target = "languages", ignore = true)
    @Mapping(target = "cms", ignore = true)
    Activity toEntity(ActivityDTO activityDTO);

    default Activity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activity activity = new Activity();
        activity.setId(id);
        return activity;
    }
}
