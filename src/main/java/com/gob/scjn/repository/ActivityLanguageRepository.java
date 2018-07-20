package com.gob.scjn.repository;

import com.gob.scjn.domain.ActivityLanguage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivityLanguage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityLanguageRepository extends JpaRepository<ActivityLanguage, Long> {

}
