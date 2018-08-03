package com.gob.scjn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gob.scjn.domain.Venue;


/**
 * Spring Data  repository for the Venue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
	
	Page<Venue> findByEventId(Long id, Pageable pageable);

}
