package com.gob.scjn.web.rest;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.gob.scjn.service.MenuService;
import com.gob.scjn.service.dto.MenuDTO;
import com.gob.scjn.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Menu.
 */
@RestController
@RequestMapping("/api")
public class MenuResource {

	private final Logger log = LoggerFactory.getLogger(MenuResource.class);

	private static final String ENTITY_NAME = "menu";

	private final MenuService menuService;

	public MenuResource(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * POST /menus : Create a new menu.
	 *
	 * @param menuDTO
	 *            the menuDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         menuDTO, or with status 400 (Bad Request) if the menu has already an
	 *         ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/site/{id}/menu")
	@Timed
	public ResponseEntity<MenuDTO> createMenu(@PathVariable Long id, @RequestBody MenuDTO menuDTO) throws URISyntaxException {
		MenuDTO result = menuService.save(id, menuDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	/**
	 * PUT /menus : Updates an existing menu.
	 *
	 * @param menuDTO
	 *            the menuDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         menuDTO, or with status 400 (Bad Request) if the menuDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the menuDTO
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/site/{id}/menu")
	@Timed
	public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long id, @RequestBody MenuDTO menuDTO) throws URISyntaxException {
		log.debug("REST request to update Menu : {}", menuDTO);
		MenuDTO result = menuService.save(id, menuDTO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * GET /menus/:id : get the "id" menu.
	 *
	 * @param id
	 *            the id of the menuDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the menuDTO, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/site/{id}/menu")
	@Timed
	public ResponseEntity<MenuDTO> getMenu(@PathVariable Long id) {
		log.debug("REST request to get Menu : {}", id);
		MenuDTO menuDTO = menuService.findOne(id);
		return new ResponseEntity<>(menuDTO, HttpStatus.OK);
	}

	/**
	 * DELETE /menus/:id : delete the "id" menu.
	 *
	 * @param id
	 *            the id of the menuDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/site/{id}/menu")
	@Timed
	public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
		log.debug("REST request to delete Menu : {}", id);
		menuService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
