package com.gob.scjn.repository;

import com.gob.scjn.domain.Venue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Venue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

}
