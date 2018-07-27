package com.gob.scjn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gob.scjn.domain.SitePage;


/**
 * Spring Data  repository for the SitePage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SitePageRepository extends JpaRepository<SitePage, Long> {
	
	Optional<SitePage> findBySiteId(Long id);

}
