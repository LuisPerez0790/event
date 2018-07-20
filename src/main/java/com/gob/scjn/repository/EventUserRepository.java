package com.gob.scjn.repository;

import com.gob.scjn.domain.EventUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EventUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventUserRepository extends JpaRepository<EventUser, Long> {

}
