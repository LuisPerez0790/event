package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.MenuItems;
import com.gob.scjn.repository.MenuItemsRepository;
import com.gob.scjn.service.MenuItemsService;
import com.gob.scjn.service.dto.MenuItemsDTO;
import com.gob.scjn.service.mapper.MenuItemsMapper;
import com.gob.scjn.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.gob.scjn.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MenuItemsResource REST controller.
 *
 * @see MenuItemsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class MenuItemsResourceIntTest {

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private MenuItemsRepository menuItemsRepository;


    @Autowired
    private MenuItemsMapper menuItemsMapper;
    

    @Autowired
    private MenuItemsService menuItemsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMenuItemsMockMvc;

    private MenuItems menuItems;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MenuItemsResource menuItemsResource = new MenuItemsResource(menuItemsService);
        this.restMenuItemsMockMvc = MockMvcBuilders.standaloneSetup(menuItemsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuItems createEntity(EntityManager em) {
        MenuItems menuItems = new MenuItems()
            .weight(DEFAULT_WEIGHT)
            .url(DEFAULT_URL);
        return menuItems;
    }

    @Before
    public void initTest() {
        menuItems = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuItems() throws Exception {
        int databaseSizeBeforeCreate = menuItemsRepository.findAll().size();

        // Create the MenuItems
        MenuItemsDTO menuItemsDTO = menuItemsMapper.toDto(menuItems);
        restMenuItemsMockMvc.perform(post("/api/menu-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsDTO)))
            .andExpect(status().isCreated());

        // Validate the MenuItems in the database
        List<MenuItems> menuItemsList = menuItemsRepository.findAll();
        assertThat(menuItemsList).hasSize(databaseSizeBeforeCreate + 1);
        MenuItems testMenuItems = menuItemsList.get(menuItemsList.size() - 1);
        assertThat(testMenuItems.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMenuItems.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createMenuItemsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuItemsRepository.findAll().size();

        // Create the MenuItems with an existing ID
        menuItems.setId(1L);
        MenuItemsDTO menuItemsDTO = menuItemsMapper.toDto(menuItems);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuItemsMockMvc.perform(post("/api/menu-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuItems in the database
        List<MenuItems> menuItemsList = menuItemsRepository.findAll();
        assertThat(menuItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMenuItems() throws Exception {
        // Initialize the database
        menuItemsRepository.saveAndFlush(menuItems);

        // Get all the menuItemsList
        restMenuItemsMockMvc.perform(get("/api/menu-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuItems.getId().intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())));
    }
    

    @Test
    @Transactional
    public void getMenuItems() throws Exception {
        // Initialize the database
        menuItemsRepository.saveAndFlush(menuItems);

        // Get the menuItems
        restMenuItemsMockMvc.perform(get("/api/menu-items/{id}", menuItems.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(menuItems.getId().intValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMenuItems() throws Exception {
        // Get the menuItems
        restMenuItemsMockMvc.perform(get("/api/menu-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuItems() throws Exception {
        // Initialize the database
        menuItemsRepository.saveAndFlush(menuItems);

        int databaseSizeBeforeUpdate = menuItemsRepository.findAll().size();

        // Update the menuItems
        MenuItems updatedMenuItems = menuItemsRepository.findById(menuItems.getId()).get();
        // Disconnect from session so that the updates on updatedMenuItems are not directly saved in db
        em.detach(updatedMenuItems);
        updatedMenuItems
            .weight(UPDATED_WEIGHT)
            .url(UPDATED_URL);
        MenuItemsDTO menuItemsDTO = menuItemsMapper.toDto(updatedMenuItems);

        restMenuItemsMockMvc.perform(put("/api/menu-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsDTO)))
            .andExpect(status().isOk());

        // Validate the MenuItems in the database
        List<MenuItems> menuItemsList = menuItemsRepository.findAll();
        assertThat(menuItemsList).hasSize(databaseSizeBeforeUpdate);
        MenuItems testMenuItems = menuItemsList.get(menuItemsList.size() - 1);
        assertThat(testMenuItems.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMenuItems.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuItems() throws Exception {
        int databaseSizeBeforeUpdate = menuItemsRepository.findAll().size();

        // Create the MenuItems
        MenuItemsDTO menuItemsDTO = menuItemsMapper.toDto(menuItems);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMenuItemsMockMvc.perform(put("/api/menu-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuItems in the database
        List<MenuItems> menuItemsList = menuItemsRepository.findAll();
        assertThat(menuItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenuItems() throws Exception {
        // Initialize the database
        menuItemsRepository.saveAndFlush(menuItems);

        int databaseSizeBeforeDelete = menuItemsRepository.findAll().size();

        // Get the menuItems
        restMenuItemsMockMvc.perform(delete("/api/menu-items/{id}", menuItems.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MenuItems> menuItemsList = menuItemsRepository.findAll();
        assertThat(menuItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuItems.class);
        MenuItems menuItems1 = new MenuItems();
        menuItems1.setId(1L);
        MenuItems menuItems2 = new MenuItems();
        menuItems2.setId(menuItems1.getId());
        assertThat(menuItems1).isEqualTo(menuItems2);
        menuItems2.setId(2L);
        assertThat(menuItems1).isNotEqualTo(menuItems2);
        menuItems1.setId(null);
        assertThat(menuItems1).isNotEqualTo(menuItems2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuItemsDTO.class);
        MenuItemsDTO menuItemsDTO1 = new MenuItemsDTO();
        menuItemsDTO1.setId(1L);
        MenuItemsDTO menuItemsDTO2 = new MenuItemsDTO();
        assertThat(menuItemsDTO1).isNotEqualTo(menuItemsDTO2);
        menuItemsDTO2.setId(menuItemsDTO1.getId());
        assertThat(menuItemsDTO1).isEqualTo(menuItemsDTO2);
        menuItemsDTO2.setId(2L);
        assertThat(menuItemsDTO1).isNotEqualTo(menuItemsDTO2);
        menuItemsDTO1.setId(null);
        assertThat(menuItemsDTO1).isNotEqualTo(menuItemsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(menuItemsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(menuItemsMapper.fromId(null)).isNull();
    }
}
