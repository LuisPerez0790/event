package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.CMSDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CMS and its DTO CMSDTO.
 */
@Mapper(componentModel = "spring", uses = {EventUserMapper.class, EventMapper.class, ActivityMapper.class, SitePageMapper.class})
public interface CMSMapper extends EntityMapper<CMSDTO, CMS> {

    @Mapping(source = "eventUser.id", target = "eventUserId")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "activity.id", target = "activityId")
    @Mapping(source = "sitePage.id", target = "sitePageId")
    CMSDTO toDto(CMS cMS);

    @Mapping(source = "eventUserId", target = "eventUser")
    @Mapping(source = "eventId", target = "event")
    @Mapping(source = "activityId", target = "activity")
    @Mapping(source = "sitePageId", target = "sitePage")
    CMS toEntity(CMSDTO cMSDTO);

    default CMS fromId(Long id) {
        if (id == null) {
            return null;
        }
        CMS cMS = new CMS();
        cMS.setId(id);
        return cMS;
    }
}
