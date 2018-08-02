package com.gob.scjn.service.impl;

import com.gob.scjn.service.VenueLanguageService;
import com.gob.scjn.domain.VenueLanguage;
import com.gob.scjn.repository.VenueLanguageRepository;
import com.gob.scjn.service.dto.VenueLanguageDTO;
import com.gob.scjn.service.mapper.VenueLanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing VenueLanguage.
 */
@Service
@Transactional
public class VenueLanguageServiceImpl implements VenueLanguageService {

    private final Logger log = LoggerFactory.getLogger(VenueLanguageServiceImpl.class);

    private final VenueLanguageRepository venueLanguageRepository;

    private final VenueLanguageMapper venueLanguageMapper;

    public VenueLanguageServiceImpl(VenueLanguageRepository venueLanguageRepository, VenueLanguageMapper venueLanguageMapper) {
        this.venueLanguageRepository = venueLanguageRepository;
        this.venueLanguageMapper = venueLanguageMapper;
    }

    /**
     * Save a venueLanguage.
     *
     * @param venueLanguageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VenueLanguageDTO save(VenueLanguageDTO venueLanguageDTO) {
        log.debug("Request to save VenueLanguage : {}", venueLanguageDTO);
        VenueLanguage venueLanguage = venueLanguageMapper.toEntity(venueLanguageDTO);
        venueLanguage = venueLanguageRepository.save(venueLanguage);
        return venueLanguageMapper.toDto(venueLanguage);
    }

    /**
     * Get all the venueLanguages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VenueLanguageDTO> findAll() {
        log.debug("Request to get all VenueLanguages");
        return venueLanguageRepository.findAll().stream()
            .map(venueLanguageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one venueLanguage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VenueLanguageDTO> findOne(Long id) {
        log.debug("Request to get VenueLanguage : {}", id);
        return venueLanguageRepository.findById(id)
            .map(venueLanguageMapper::toDto);
    }

    /**
     * Delete the venueLanguage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VenueLanguage : {}", id);
        venueLanguageRepository.deleteById(id);
    }
}
