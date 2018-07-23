package com.gob.scjn.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gob.scjn.domain.EventLanguage;
import com.gob.scjn.repository.EventLanguageRepository;
import com.gob.scjn.service.EventLanguageService;
import com.gob.scjn.service.dto.EventLanguageDTO;
import com.gob.scjn.service.mapper.EventLanguageMapper;

/**
 * Service Implementation for managing EventLanguage.
 */
@Service
@Transactional
public class EventLanguageServiceImpl implements EventLanguageService {

	private final Logger log = LoggerFactory.getLogger(EventLanguageServiceImpl.class);

	private final EventLanguageRepository eventLanguageRepository;

	private final EventLanguageMapper eventLanguageMapper;

	public EventLanguageServiceImpl(EventLanguageRepository eventLanguageRepository,
			EventLanguageMapper eventLanguageMapper) {
		this.eventLanguageRepository = eventLanguageRepository;
		this.eventLanguageMapper = eventLanguageMapper;
	}

	/**
	 * Save a eventLanguage.
	 *
	 * @param eventLanguageDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public EventLanguageDTO save(EventLanguageDTO eventLanguageDTO) {
		log.debug("Request to save EventLanguage : {}", eventLanguageDTO);
		EventLanguage eventLanguage = eventLanguageMapper.toEntity(eventLanguageDTO);
		eventLanguage = eventLanguageRepository.save(eventLanguage);
		return eventLanguageMapper.toDto(eventLanguage);
	}

	/**
	 * Get all the eventLanguages.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<EventLanguageDTO> findAll(Pageable pageable) {
		log.debug("Request to get all EventLanguages");
		return eventLanguageRepository.findAll(pageable).map(eventLanguageMapper::toDto);
	}

	/**
	 * Get one eventLanguage by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<EventLanguageDTO> findOne(Long id) {
		log.debug("Request to get EventLanguage : {}", id);
		return eventLanguageRepository.findById(id).map(eventLanguageMapper::toDto);
	}

	/**
	 * Delete the eventLanguage by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete EventLanguage : {}", id);
		eventLanguageRepository.deleteById(id);
	}
}
