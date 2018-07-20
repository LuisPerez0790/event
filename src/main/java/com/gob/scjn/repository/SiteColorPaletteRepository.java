package com.gob.scjn.repository;

import com.gob.scjn.domain.SiteColorPalette;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SiteColorPalette entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteColorPaletteRepository extends JpaRepository<SiteColorPalette, Long> {

}
