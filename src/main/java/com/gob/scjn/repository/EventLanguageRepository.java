package com.gob.scjn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gob.scjn.domain.EventLanguage;


/**
 * Spring Data  repository for the EventLanguage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventLanguageRepository extends JpaRepository<EventLanguage, Long> {
	
}
