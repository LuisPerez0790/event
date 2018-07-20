package com.gob.scjn.service.impl;

import com.gob.scjn.service.ActivityLanguageService;
import com.gob.scjn.domain.ActivityLanguage;
import com.gob.scjn.repository.ActivityLanguageRepository;
import com.gob.scjn.service.dto.ActivityLanguageDTO;
import com.gob.scjn.service.mapper.ActivityLanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ActivityLanguage.
 */
@Service
@Transactional
public class ActivityLanguageServiceImpl implements ActivityLanguageService {

    private final Logger log = LoggerFactory.getLogger(ActivityLanguageServiceImpl.class);

    private final ActivityLanguageRepository activityLanguageRepository;

    private final ActivityLanguageMapper activityLanguageMapper;

    public ActivityLanguageServiceImpl(ActivityLanguageRepository activityLanguageRepository, ActivityLanguageMapper activityLanguageMapper) {
        this.activityLanguageRepository = activityLanguageRepository;
        this.activityLanguageMapper = activityLanguageMapper;
    }

    /**
     * Save a activityLanguage.
     *
     * @param activityLanguageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActivityLanguageDTO save(ActivityLanguageDTO activityLanguageDTO) {
        log.debug("Request to save ActivityLanguage : {}", activityLanguageDTO);
        ActivityLanguage activityLanguage = activityLanguageMapper.toEntity(activityLanguageDTO);
        activityLanguage = activityLanguageRepository.save(activityLanguage);
        return activityLanguageMapper.toDto(activityLanguage);
    }

    /**
     * Get all the activityLanguages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActivityLanguageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ActivityLanguages");
        return activityLanguageRepository.findAll(pageable)
            .map(activityLanguageMapper::toDto);
    }


    /**
     * Get one activityLanguage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActivityLanguageDTO> findOne(Long id) {
        log.debug("Request to get ActivityLanguage : {}", id);
        return activityLanguageRepository.findById(id)
            .map(activityLanguageMapper::toDto);
    }

    /**
     * Delete the activityLanguage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActivityLanguage : {}", id);
        activityLanguageRepository.deleteById(id);
    }
}
