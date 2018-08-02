package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.VenueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Venue and its DTO VenueDTO.
 */
@Mapper(componentModel = "spring", uses = { EventMapper.class, VenueLanguageMapper.class })
public interface VenueMapper extends EntityMapper<VenueDTO, Venue> {

	@Mapping(source = "event.id", target = "eventId")
	VenueDTO toDto(Venue venue);

	@Mapping(source = "eventId", target = "event")
	Venue toEntity(VenueDTO venueDTO);

	default Venue fromId(Long id) {
		if (id == null) {
			return null;
		}
		Venue venue = new Venue();
		venue.setId(id);
		return venue;
	}
}
