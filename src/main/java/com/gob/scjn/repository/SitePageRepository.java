package com.gob.scjn.repository;

import com.gob.scjn.domain.SitePage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SitePage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SitePageRepository extends JpaRepository<SitePage, Long> {

}
