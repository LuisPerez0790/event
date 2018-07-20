package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.ActivityLanguageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ActivityLanguage and its DTO ActivityLanguageDTO.
 */
@Mapper(componentModel = "spring", uses = {ActivityMapper.class})
public interface ActivityLanguageMapper extends EntityMapper<ActivityLanguageDTO, ActivityLanguage> {

    @Mapping(source = "activity.id", target = "activityId")
    ActivityLanguageDTO toDto(ActivityLanguage activityLanguage);

    @Mapping(source = "activityId", target = "activity")
    ActivityLanguage toEntity(ActivityLanguageDTO activityLanguageDTO);

    default ActivityLanguage fromId(Long id) {
        if (id == null) {
            return null;
        }
        ActivityLanguage activityLanguage = new ActivityLanguage();
        activityLanguage.setId(id);
        return activityLanguage;
    }
}
