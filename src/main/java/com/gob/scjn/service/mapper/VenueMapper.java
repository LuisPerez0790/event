package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.VenueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Venue and its DTO VenueDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VenueMapper extends EntityMapper<VenueDTO, Venue> {



    default Venue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Venue venue = new Venue();
        venue.setId(id);
        return venue;
    }
}
