package com.gob.scjn.service.impl;

import com.gob.scjn.service.CMSService;
import com.gob.scjn.domain.CMS;
import com.gob.scjn.repository.CMSRepository;
import com.gob.scjn.service.dto.CMSDTO;
import com.gob.scjn.service.mapper.CMSMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CMS.
 */
@Service
@Transactional
public class CMSServiceImpl implements CMSService {

    private final Logger log = LoggerFactory.getLogger(CMSServiceImpl.class);

    private final CMSRepository cMSRepository;

    private final CMSMapper cMSMapper;

    public CMSServiceImpl(CMSRepository cMSRepository, CMSMapper cMSMapper) {
        this.cMSRepository = cMSRepository;
        this.cMSMapper = cMSMapper;
    }

    /**
     * Save a cMS.
     *
     * @param cMSDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CMSDTO save(CMSDTO cMSDTO) {
        log.debug("Request to save CMS : {}", cMSDTO);
        CMS cMS = cMSMapper.toEntity(cMSDTO);
        cMS = cMSRepository.save(cMS);
        return cMSMapper.toDto(cMS);
    }

    /**
     * Get all the cMS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CMSDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CMS");
        return cMSRepository.findAll(pageable)
            .map(cMSMapper::toDto);
    }


    /**
     * Get one cMS by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CMSDTO> findOne(Long id) {
        log.debug("Request to get CMS : {}", id);
        return cMSRepository.findById(id)
            .map(cMSMapper::toDto);
    }

    /**
     * Delete the cMS by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CMS : {}", id);
        cMSRepository.deleteById(id);
    }
}
