package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.SitePageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SitePage and its DTO SitePageDTO.
 */
@Mapper(componentModel = "spring", uses = {SiteMapper.class})
public interface SitePageMapper extends EntityMapper<SitePageDTO, SitePage> {

    @Mapping(source = "site.id", target = "siteId")
    SitePageDTO toDto(SitePage sitePage);

    @Mapping(source = "siteId", target = "site")
    @Mapping(target = "pages", ignore = true)
    @Mapping(target = "cms", ignore = true)
    SitePage toEntity(SitePageDTO sitePageDTO);

    default SitePage fromId(Long id) {
        if (id == null) {
            return null;
        }
        SitePage sitePage = new SitePage();
        sitePage.setId(id);
        return sitePage;
    }
}
