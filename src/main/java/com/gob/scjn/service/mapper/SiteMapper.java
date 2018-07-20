package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.SiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Site and its DTO SiteDTO.
 */
@Mapper(componentModel = "spring", uses = {SiteColorPaletteMapper.class, SiteFooterMapper.class})
public interface SiteMapper extends EntityMapper<SiteDTO, Site> {

    @Mapping(source = "palette.id", target = "paletteId")
    @Mapping(source = "footer.id", target = "footerId")
    SiteDTO toDto(Site site);

    @Mapping(source = "paletteId", target = "palette")
    @Mapping(source = "footerId", target = "footer")
    @Mapping(target = "sitePages", ignore = true)
    Site toEntity(SiteDTO siteDTO);

    default Site fromId(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }
}
