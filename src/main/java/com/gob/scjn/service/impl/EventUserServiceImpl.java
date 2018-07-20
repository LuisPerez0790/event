package com.gob.scjn.service.impl;

import com.gob.scjn.service.EventUserService;
import com.gob.scjn.domain.EventUser;
import com.gob.scjn.repository.EventUserRepository;
import com.gob.scjn.service.dto.EventUserDTO;
import com.gob.scjn.service.mapper.EventUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing EventUser.
 */
@Service
@Transactional
public class EventUserServiceImpl implements EventUserService {

    private final Logger log = LoggerFactory.getLogger(EventUserServiceImpl.class);

    private final EventUserRepository eventUserRepository;

    private final EventUserMapper eventUserMapper;

    public EventUserServiceImpl(EventUserRepository eventUserRepository, EventUserMapper eventUserMapper) {
        this.eventUserRepository = eventUserRepository;
        this.eventUserMapper = eventUserMapper;
    }

    /**
     * Save a eventUser.
     *
     * @param eventUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EventUserDTO save(EventUserDTO eventUserDTO) {
        log.debug("Request to save EventUser : {}", eventUserDTO);
        EventUser eventUser = eventUserMapper.toEntity(eventUserDTO);
        eventUser = eventUserRepository.save(eventUser);
        return eventUserMapper.toDto(eventUser);
    }

    /**
     * Get all the eventUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventUsers");
        return eventUserRepository.findAll(pageable)
            .map(eventUserMapper::toDto);
    }


    /**
     * Get one eventUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventUserDTO> findOne(Long id) {
        log.debug("Request to get EventUser : {}", id);
        return eventUserRepository.findById(id)
            .map(eventUserMapper::toDto);
    }

    /**
     * Delete the eventUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventUser : {}", id);
        eventUserRepository.deleteById(id);
    }
}
