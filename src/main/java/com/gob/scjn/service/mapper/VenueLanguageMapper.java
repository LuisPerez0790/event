package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.VenueLanguageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VenueLanguage and its DTO VenueLanguageDTO.
 */
@Mapper(componentModel = "spring", uses = {VenueMapper.class})
public interface VenueLanguageMapper extends EntityMapper<VenueLanguageDTO, VenueLanguage> {

    @Mapping(source = "venue.id", target = "venueId")
    VenueLanguageDTO toDto(VenueLanguage venueLanguage);

    @Mapping(source = "venueId", target = "venue")
    VenueLanguage toEntity(VenueLanguageDTO venueLanguageDTO);

    default VenueLanguage fromId(Long id) {
        if (id == null) {
            return null;
        }
        VenueLanguage venueLanguage = new VenueLanguage();
        venueLanguage.setId(id);
        return venueLanguage;
    }
}
