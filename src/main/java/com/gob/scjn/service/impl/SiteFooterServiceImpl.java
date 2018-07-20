package com.gob.scjn.service.impl;

import com.gob.scjn.service.SiteFooterService;
import com.gob.scjn.domain.SiteFooter;
import com.gob.scjn.repository.SiteFooterRepository;
import com.gob.scjn.service.dto.SiteFooterDTO;
import com.gob.scjn.service.mapper.SiteFooterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing SiteFooter.
 */
@Service
@Transactional
public class SiteFooterServiceImpl implements SiteFooterService {

    private final Logger log = LoggerFactory.getLogger(SiteFooterServiceImpl.class);

    private final SiteFooterRepository siteFooterRepository;

    private final SiteFooterMapper siteFooterMapper;

    public SiteFooterServiceImpl(SiteFooterRepository siteFooterRepository, SiteFooterMapper siteFooterMapper) {
        this.siteFooterRepository = siteFooterRepository;
        this.siteFooterMapper = siteFooterMapper;
    }

    /**
     * Save a siteFooter.
     *
     * @param siteFooterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SiteFooterDTO save(SiteFooterDTO siteFooterDTO) {
        log.debug("Request to save SiteFooter : {}", siteFooterDTO);
        SiteFooter siteFooter = siteFooterMapper.toEntity(siteFooterDTO);
        siteFooter = siteFooterRepository.save(siteFooter);
        return siteFooterMapper.toDto(siteFooter);
    }

    /**
     * Get all the siteFooters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SiteFooterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SiteFooters");
        return siteFooterRepository.findAll(pageable)
            .map(siteFooterMapper::toDto);
    }


    /**
     * Get one siteFooter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SiteFooterDTO> findOne(Long id) {
        log.debug("Request to get SiteFooter : {}", id);
        return siteFooterRepository.findById(id)
            .map(siteFooterMapper::toDto);
    }

    /**
     * Delete the siteFooter by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SiteFooter : {}", id);
        siteFooterRepository.deleteById(id);
    }
}
