package com.gob.scjn.repository;

import com.gob.scjn.domain.SitePageLanguage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SitePageLanguage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SitePageLanguageRepository extends JpaRepository<SitePageLanguage, Long> {

}
