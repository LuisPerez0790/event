package com.gob.scjn.service.impl;

import com.gob.scjn.service.SitePageLanguageService;
import com.gob.scjn.domain.SitePageLanguage;
import com.gob.scjn.repository.SitePageLanguageRepository;
import com.gob.scjn.service.dto.SitePageLanguageDTO;
import com.gob.scjn.service.mapper.SitePageLanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing SitePageLanguage.
 */
@Service
@Transactional
public class SitePageLanguageServiceImpl implements SitePageLanguageService {

    private final Logger log = LoggerFactory.getLogger(SitePageLanguageServiceImpl.class);

    private final SitePageLanguageRepository sitePageLanguageRepository;

    private final SitePageLanguageMapper sitePageLanguageMapper;

    public SitePageLanguageServiceImpl(SitePageLanguageRepository sitePageLanguageRepository, SitePageLanguageMapper sitePageLanguageMapper) {
        this.sitePageLanguageRepository = sitePageLanguageRepository;
        this.sitePageLanguageMapper = sitePageLanguageMapper;
    }

    /**
     * Save a sitePageLanguage.
     *
     * @param sitePageLanguageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SitePageLanguageDTO save(SitePageLanguageDTO sitePageLanguageDTO) {
        log.debug("Request to save SitePageLanguage : {}", sitePageLanguageDTO);
        SitePageLanguage sitePageLanguage = sitePageLanguageMapper.toEntity(sitePageLanguageDTO);
        sitePageLanguage = sitePageLanguageRepository.save(sitePageLanguage);
        return sitePageLanguageMapper.toDto(sitePageLanguage);
    }

    /**
     * Get all the sitePageLanguages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SitePageLanguageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SitePageLanguages");
        return sitePageLanguageRepository.findAll(pageable)
            .map(sitePageLanguageMapper::toDto);
    }


    /**
     * Get one sitePageLanguage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SitePageLanguageDTO> findOne(Long id) {
        log.debug("Request to get SitePageLanguage : {}", id);
        return sitePageLanguageRepository.findById(id)
            .map(sitePageLanguageMapper::toDto);
    }

    /**
     * Delete the sitePageLanguage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SitePageLanguage : {}", id);
        sitePageLanguageRepository.deleteById(id);
    }
}
