package com.gob.scjn.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.MenuItemsService;
import com.gob.scjn.web.rest.errors.BadRequestAlertException;
import com.gob.scjn.web.rest.util.HeaderUtil;
import com.gob.scjn.service.dto.MenuItemsDTO;
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
 * REST controller for managing MenuItems.
 */
@RestController
@RequestMapping("/api")
public class MenuItemsResource {

    private final Logger log = LoggerFactory.getLogger(MenuItemsResource.class);

    private static final String ENTITY_NAME = "menuItems";

    private final MenuItemsService menuItemsService;

    public MenuItemsResource(MenuItemsService menuItemsService) {
        this.menuItemsService = menuItemsService;
    }

    /**
     * POST  /menu-items : Create a new menuItems.
     *
     * @param menuItemsDTO the menuItemsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuItemsDTO, or with status 400 (Bad Request) if the menuItems has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-items")
    @Timed
    public ResponseEntity<MenuItemsDTO> createMenuItems(@RequestBody MenuItemsDTO menuItemsDTO) throws URISyntaxException {
        log.debug("REST request to save MenuItems : {}", menuItemsDTO);
        if (menuItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new menuItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuItemsDTO result = menuItemsService.save(menuItemsDTO);
        return ResponseEntity.created(new URI("/api/menu-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-items : Updates an existing menuItems.
     *
     * @param menuItemsDTO the menuItemsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuItemsDTO,
     * or with status 400 (Bad Request) if the menuItemsDTO is not valid,
     * or with status 500 (Internal Server Error) if the menuItemsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-items")
    @Timed
    public ResponseEntity<MenuItemsDTO> updateMenuItems(@RequestBody MenuItemsDTO menuItemsDTO) throws URISyntaxException {
        log.debug("REST request to update MenuItems : {}", menuItemsDTO);
        if (menuItemsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuItemsDTO result = menuItemsService.save(menuItemsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-items : get all the menuItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of menuItems in body
     */
    @GetMapping("/menu-items")
    @Timed
    public List<MenuItemsDTO> getAllMenuItems() {
        log.debug("REST request to get all MenuItems");
        return menuItemsService.findAll();
    }

    /**
     * GET  /menu-items/:id : get the "id" menuItems.
     *
     * @param id the id of the menuItemsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuItemsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/menu-items/{id}")
    @Timed
    public ResponseEntity<MenuItemsDTO> getMenuItems(@PathVariable Long id) {
        log.debug("REST request to get MenuItems : {}", id);
        Optional<MenuItemsDTO> menuItemsDTO = menuItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuItemsDTO);
    }

    /**
     * DELETE  /menu-items/:id : delete the "id" menuItems.
     *
     * @param id the id of the menuItemsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteMenuItems(@PathVariable Long id) {
        log.debug("REST request to delete MenuItems : {}", id);
        menuItemsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
