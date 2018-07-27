package com.gob.scjn.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gob.scjn.domain.SitePage;
import com.gob.scjn.service.dto.SitePageDTO;

/**
 * Mapper for the entity SitePage and its DTO SitePageDTO.
 */
@Mapper(componentModel = "spring", uses = {SiteMapper.class, SitePageLanguageMapper.class})
public interface SitePageMapper extends EntityMapper<SitePageDTO, SitePage> {

    @Mapping(source = "site.id", target = "siteId")
    SitePageDTO toDto(SitePage sitePage);

    @Mapping(source = "siteId", target = "site")
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
