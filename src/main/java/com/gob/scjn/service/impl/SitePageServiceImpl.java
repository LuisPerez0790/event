package com.gob.scjn.service.impl;

import com.gob.scjn.service.SitePageService;
import com.gob.scjn.domain.SitePage;
import com.gob.scjn.repository.SitePageRepository;
import com.gob.scjn.service.dto.SitePageDTO;
import com.gob.scjn.service.mapper.SitePageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing SitePage.
 */
@Service
@Transactional
public class SitePageServiceImpl implements SitePageService {

    private final Logger log = LoggerFactory.getLogger(SitePageServiceImpl.class);

    private final SitePageRepository sitePageRepository;

    private final SitePageMapper sitePageMapper;

    public SitePageServiceImpl(SitePageRepository sitePageRepository, SitePageMapper sitePageMapper) {
        this.sitePageRepository = sitePageRepository;
        this.sitePageMapper = sitePageMapper;
    }

    /**
     * Save a sitePage.
     *
     * @param sitePageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SitePageDTO save(SitePageDTO sitePageDTO) {
        log.debug("Request to save SitePage : {}", sitePageDTO);
        SitePage sitePage = sitePageMapper.toEntity(sitePageDTO);
        sitePage = sitePageRepository.save(sitePage);
        return sitePageMapper.toDto(sitePage);
    }

    /**
     * Get all the sitePages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SitePageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SitePages");
        return sitePageRepository.findAll(pageable)
            .map(sitePageMapper::toDto);
    }


    /**
     * Get one sitePage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SitePageDTO> findOne(Long id) {
        log.debug("Request to get SitePage : {}", id);
        return sitePageRepository.findById(id)
            .map(sitePageMapper::toDto);
    }

    /**
     * Delete the sitePage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SitePage : {}", id);
        sitePageRepository.deleteById(id);
    }
}
