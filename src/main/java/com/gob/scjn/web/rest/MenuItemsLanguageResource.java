package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.MenuItemsLanguageService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.service.dto.MenuItemsLanguageDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MenuItemsLanguage.
 */
@RestController
@RequestMapping("/api")
public class MenuItemsLanguageResource {

    private final Logger log = LoggerFactory.getLogger(MenuItemsLanguageResource.class);

    private static final String ENTITY_NAME = "menuItemsLanguage";

    private final MenuItemsLanguageService menuItemsLanguageService;

    public MenuItemsLanguageResource(MenuItemsLanguageService menuItemsLanguageService) {
        this.menuItemsLanguageService = menuItemsLanguageService;
    }

    /**
     * POST  /menu-items-languages : Create a new menuItemsLanguage.
     *
     * @param menuItemsLanguageDTO the menuItemsLanguageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuItemsLanguageDTO, or with status 400 (Bad Request) if the menuItemsLanguage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-items-languages")
    @Timed
    public ResponseEntity<MenuItemsLanguageDTO> createMenuItemsLanguage(@RequestBody MenuItemsLanguageDTO menuItemsLanguageDTO) throws URISyntaxException {
        log.debug("REST request to save MenuItemsLanguage : {}", menuItemsLanguageDTO);
        if (menuItemsLanguageDTO.getId() != null) {
            throw new BadRequestAlertException("A new menuItemsLanguage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuItemsLanguageDTO result = menuItemsLanguageService.save(menuItemsLanguageDTO);
        return ResponseEntity.created(new URI("/api/menu-items-languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-items-languages : Updates an existing menuItemsLanguage.
     *
     * @param menuItemsLanguageDTO the menuItemsLanguageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuItemsLanguageDTO,
     * or with status 400 (Bad Request) if the menuItemsLanguageDTO is not valid,
     * or with status 500 (Internal Server Error) if the menuItemsLanguageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-items-languages")
    @Timed
    public ResponseEntity<MenuItemsLanguageDTO> updateMenuItemsLanguage(@RequestBody MenuItemsLanguageDTO menuItemsLanguageDTO) throws URISyntaxException {
        log.debug("REST request to update MenuItemsLanguage : {}", menuItemsLanguageDTO);
        if (menuItemsLanguageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuItemsLanguageDTO result = menuItemsLanguageService.save(menuItemsLanguageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuItemsLanguageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-items-languages : get all the menuItemsLanguages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of menuItemsLanguages in body
     */
    @GetMapping("/menu-items-languages")
    @Timed
    public List<MenuItemsLanguageDTO> getAllMenuItemsLanguages() {
        log.debug("REST request to get all MenuItemsLanguages");
        return menuItemsLanguageService.findAll();
    }

    /**
     * GET  /menu-items-languages/:id : get the "id" menuItemsLanguage.
     *
     * @param id the id of the menuItemsLanguageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuItemsLanguageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/menu-items-languages/{id}")
    @Timed
    public ResponseEntity<MenuItemsLanguageDTO> getMenuItemsLanguage(@PathVariable Long id) {
        log.debug("REST request to get MenuItemsLanguage : {}", id);
        Optional<MenuItemsLanguageDTO> menuItemsLanguageDTO = menuItemsLanguageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuItemsLanguageDTO);
    }

    /**
     * DELETE  /menu-items-languages/:id : delete the "id" menuItemsLanguage.
     *
     * @param id the id of the menuItemsLanguageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-items-languages/{id}")
    @Timed
    public ResponseEntity<Void> deleteMenuItemsLanguage(@PathVariable Long id) {
        log.debug("REST request to delete MenuItemsLanguage : {}", id);
        menuItemsLanguageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
