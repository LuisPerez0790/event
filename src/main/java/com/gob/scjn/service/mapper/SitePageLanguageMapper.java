package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.SitePageLanguageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SitePageLanguage and its DTO SitePageLanguageDTO.
 */
@Mapper(componentModel = "spring", uses = {SitePageMapper.class})
public interface SitePageLanguageMapper extends EntityMapper<SitePageLanguageDTO, SitePageLanguage> {

    @Mapping(source = "sitePage.id", target = "sitePageId")
    SitePageLanguageDTO toDto(SitePageLanguage sitePageLanguage);

    @Mapping(source = "sitePageId", target = "sitePage")
    SitePageLanguage toEntity(SitePageLanguageDTO sitePageLanguageDTO);

    default SitePageLanguage fromId(Long id) {
        if (id == null) {
            return null;
        }
        SitePageLanguage sitePageLanguage = new SitePageLanguage();
        sitePageLanguage.setId(id);
        return sitePageLanguage;
    }
}
