package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.*;
import com.gob.scjn.service.dto.SiteFooterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SiteFooter and its DTO SiteFooterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SiteFooterMapper extends EntityMapper<SiteFooterDTO, SiteFooter> {



    default SiteFooter fromId(Long id) {
        if (id == null) {
            return null;
        }
        SiteFooter siteFooter = new SiteFooter();
        siteFooter.setId(id);
        return siteFooter;
    }
}
