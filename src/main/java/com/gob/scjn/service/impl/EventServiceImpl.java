package com.gob.scjn.service.impl;

import com.gob.scjn.service.EventService;
import com.gob.scjn.domain.Event;
import com.gob.scjn.domain.EventLanguage;
import com.gob.scjn.repository.EventRepository;
import com.gob.scjn.service.dto.EventDTO;
import com.gob.scjn.service.mapper.EventMapper;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;
/**
 * Service Implementation for managing Event.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * Save a event.
     *
     * @param eventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EventDTO save(EventDTO eventDTO) {
        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    /**
     * Get all the events.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Events");
        return eventRepository.findAll(pageable)
            .map(eventMapper::toDto);
    }

    /**
     * Get all the Event with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EventDTO> findAllWithEagerRelationships(Pageable pageable) {
        return eventRepository.findAllWithEagerRelationships(pageable).map(eventMapper::toDto);
    }
    

    /**
     * Get one event by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventDTO> findOne(Long id) {
        log.debug("Request to get Event : {}", id);
        return eventRepository.findOneWithEagerRelationships(id)
            .map(eventMapper::toDto);
    }

    /**
     * Delete the event by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Event : {}", id);
        eventRepository.deleteById(id);
    }
    
    
    @Override
	public EventDTO editEvent(Long id, EventDTO eventDTO) {

		EventDTO event = findOne(id).orElseThrow(() -> new BadRequestAlertException("Invalid id", "event", "idnull"));

		if (!StringUtils.isEmpty(eventDTO.getStartDate())) {
			event.setStartDate(eventDTO.getStartDate());
		}

		if (!StringUtils.isEmpty(eventDTO.getEndDate())) {
			event.setEndDate(eventDTO.getEndDate());
		}

		if (!StringUtils.isEmpty(eventDTO.getImageUrl())) {
			event.setImageUrl(eventDTO.getImageUrl());
		}

		if (!StringUtils.isEmpty(eventDTO.getUrl())) {
			event.setUrl(eventDTO.getUrl());
		}

		if (!StringUtils.isEmpty(eventDTO.getAcronym())) {
			event.setAcronym(eventDTO.getAcronym());
		}
		
		if (eventDTO.getLanguages().size() > 0) {
			eventDTO.getLanguages().forEach(language -> event.getLanguages().add(language));
		}
		
		return save(event);
	}
    
  
}
