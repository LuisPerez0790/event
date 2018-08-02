package com.gob.scjn.repository;

import com.gob.scjn.domain.VenueLanguage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VenueLanguage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VenueLanguageRepository extends JpaRepository<VenueLanguage, Long> {

}
