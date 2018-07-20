package com.gob.scjn.service;

import com.gob.scjn.service.dto.CMSDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CMS.
 */
public interface CMSService {

    /**
     * Save a cMS.
     *
     * @param cMSDTO the entity to save
     * @return the persisted entity
     */
    CMSDTO save(CMSDTO cMSDTO);

    /**
     * Get all the cMS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CMSDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cMS.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CMSDTO> findOne(Long id);

    /**
     * Delete the "id" cMS.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
