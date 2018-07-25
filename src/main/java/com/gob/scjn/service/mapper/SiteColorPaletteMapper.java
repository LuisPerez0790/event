package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.SiteColorPaletteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SiteColorPalette and its DTO SiteColorPaletteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SiteColorPaletteMapper extends EntityMapper<SiteColorPaletteDTO, SiteColorPalette> {

	SiteColorPalette toEntity(SiteColorPaletteDTO siteColorPaletteDTO);

	default SiteColorPalette fromId(Long id) {
		if (id == null) {
			return null;
		}
		SiteColorPalette siteColorPalette = new SiteColorPalette();
		siteColorPalette.setId(id);
		return siteColorPalette;
	}
}
