package com.gob.scjn.repository;

import com.gob.scjn.domain.SiteFooter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SiteFooter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteFooterRepository extends JpaRepository<SiteFooter, Long> {

}
