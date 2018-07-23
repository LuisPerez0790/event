package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.MenuItemsLanguage;
import com.gob.scjn.repository.MenuItemsLanguageRepository;
import com.gob.scjn.service.MenuItemsLanguageService;
import com.gob.scjn.service.dto.MenuItemsLanguageDTO;
import com.gob.scjn.service.mapper.MenuItemsLanguageMapper;
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

import com.gob.scjn.domain.enumeration.Language;
/**
 * Test class for the MenuItemsLanguageResource REST controller.
 *
 * @see MenuItemsLanguageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class MenuItemsLanguageResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final Language UPDATED_LANGUAGE = Language.FRENCH;

    @Autowired
    private MenuItemsLanguageRepository menuItemsLanguageRepository;


    @Autowired
    private MenuItemsLanguageMapper menuItemsLanguageMapper;
    

    @Autowired
    private MenuItemsLanguageService menuItemsLanguageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMenuItemsLanguageMockMvc;

    private MenuItemsLanguage menuItemsLanguage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MenuItemsLanguageResource menuItemsLanguageResource = new MenuItemsLanguageResource(menuItemsLanguageService);
        this.restMenuItemsLanguageMockMvc = MockMvcBuilders.standaloneSetup(menuItemsLanguageResource)
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
    public static MenuItemsLanguage createEntity(EntityManager em) {
        MenuItemsLanguage menuItemsLanguage = new MenuItemsLanguage()
            .name(DEFAULT_NAME)
            .language(DEFAULT_LANGUAGE);
        return menuItemsLanguage;
    }

    @Before
    public void initTest() {
        menuItemsLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuItemsLanguage() throws Exception {
        int databaseSizeBeforeCreate = menuItemsLanguageRepository.findAll().size();

        // Create the MenuItemsLanguage
        MenuItemsLanguageDTO menuItemsLanguageDTO = menuItemsLanguageMapper.toDto(menuItemsLanguage);
        restMenuItemsLanguageMockMvc.perform(post("/api/menu-items-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsLanguageDTO)))
            .andExpect(status().isCreated());

        // Validate the MenuItemsLanguage in the database
        List<MenuItemsLanguage> menuItemsLanguageList = menuItemsLanguageRepository.findAll();
        assertThat(menuItemsLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        MenuItemsLanguage testMenuItemsLanguage = menuItemsLanguageList.get(menuItemsLanguageList.size() - 1);
        assertThat(testMenuItemsLanguage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMenuItemsLanguage.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    public void createMenuItemsLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuItemsLanguageRepository.findAll().size();

        // Create the MenuItemsLanguage with an existing ID
        menuItemsLanguage.setId(1L);
        MenuItemsLanguageDTO menuItemsLanguageDTO = menuItemsLanguageMapper.toDto(menuItemsLanguage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuItemsLanguageMockMvc.perform(post("/api/menu-items-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuItemsLanguage in the database
        List<MenuItemsLanguage> menuItemsLanguageList = menuItemsLanguageRepository.findAll();
        assertThat(menuItemsLanguageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMenuItemsLanguages() throws Exception {
        // Initialize the database
        menuItemsLanguageRepository.saveAndFlush(menuItemsLanguage);

        // Get all the menuItemsLanguageList
        restMenuItemsLanguageMockMvc.perform(get("/api/menu-items-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuItemsLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }
    

    @Test
    @Transactional
    public void getMenuItemsLanguage() throws Exception {
        // Initialize the database
        menuItemsLanguageRepository.saveAndFlush(menuItemsLanguage);

        // Get the menuItemsLanguage
        restMenuItemsLanguageMockMvc.perform(get("/api/menu-items-languages/{id}", menuItemsLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(menuItemsLanguage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMenuItemsLanguage() throws Exception {
        // Get the menuItemsLanguage
        restMenuItemsLanguageMockMvc.perform(get("/api/menu-items-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuItemsLanguage() throws Exception {
        // Initialize the database
        menuItemsLanguageRepository.saveAndFlush(menuItemsLanguage);

        int databaseSizeBeforeUpdate = menuItemsLanguageRepository.findAll().size();

        // Update the menuItemsLanguage
        MenuItemsLanguage updatedMenuItemsLanguage = menuItemsLanguageRepository.findById(menuItemsLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedMenuItemsLanguage are not directly saved in db
        em.detach(updatedMenuItemsLanguage);
        updatedMenuItemsLanguage
            .name(UPDATED_NAME)
            .language(UPDATED_LANGUAGE);
        MenuItemsLanguageDTO menuItemsLanguageDTO = menuItemsLanguageMapper.toDto(updatedMenuItemsLanguage);

        restMenuItemsLanguageMockMvc.perform(put("/api/menu-items-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsLanguageDTO)))
            .andExpect(status().isOk());

        // Validate the MenuItemsLanguage in the database
        List<MenuItemsLanguage> menuItemsLanguageList = menuItemsLanguageRepository.findAll();
        assertThat(menuItemsLanguageList).hasSize(databaseSizeBeforeUpdate);
        MenuItemsLanguage testMenuItemsLanguage = menuItemsLanguageList.get(menuItemsLanguageList.size() - 1);
        assertThat(testMenuItemsLanguage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMenuItemsLanguage.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuItemsLanguage() throws Exception {
        int databaseSizeBeforeUpdate = menuItemsLanguageRepository.findAll().size();

        // Create the MenuItemsLanguage
        MenuItemsLanguageDTO menuItemsLanguageDTO = menuItemsLanguageMapper.toDto(menuItemsLanguage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMenuItemsLanguageMockMvc.perform(put("/api/menu-items-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuItemsLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuItemsLanguage in the database
        List<MenuItemsLanguage> menuItemsLanguageList = menuItemsLanguageRepository.findAll();
        assertThat(menuItemsLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenuItemsLanguage() throws Exception {
        // Initialize the database
        menuItemsLanguageRepository.saveAndFlush(menuItemsLanguage);

        int databaseSizeBeforeDelete = menuItemsLanguageRepository.findAll().size();

        // Get the menuItemsLanguage
        restMenuItemsLanguageMockMvc.perform(delete("/api/menu-items-languages/{id}", menuItemsLanguage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MenuItemsLanguage> menuItemsLanguageList = menuItemsLanguageRepository.findAll();
        assertThat(menuItemsLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuItemsLanguage.class);
        MenuItemsLanguage menuItemsLanguage1 = new MenuItemsLanguage();
        menuItemsLanguage1.setId(1L);
        MenuItemsLanguage menuItemsLanguage2 = new MenuItemsLanguage();
        menuItemsLanguage2.setId(menuItemsLanguage1.getId());
        assertThat(menuItemsLanguage1).isEqualTo(menuItemsLanguage2);
        menuItemsLanguage2.setId(2L);
        assertThat(menuItemsLanguage1).isNotEqualTo(menuItemsLanguage2);
        menuItemsLanguage1.setId(null);
        assertThat(menuItemsLanguage1).isNotEqualTo(menuItemsLanguage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuItemsLanguageDTO.class);
        MenuItemsLanguageDTO menuItemsLanguageDTO1 = new MenuItemsLanguageDTO();
        menuItemsLanguageDTO1.setId(1L);
        MenuItemsLanguageDTO menuItemsLanguageDTO2 = new MenuItemsLanguageDTO();
        assertThat(menuItemsLanguageDTO1).isNotEqualTo(menuItemsLanguageDTO2);
        menuItemsLanguageDTO2.setId(menuItemsLanguageDTO1.getId());
        assertThat(menuItemsLanguageDTO1).isEqualTo(menuItemsLanguageDTO2);
        menuItemsLanguageDTO2.setId(2L);
        assertThat(menuItemsLanguageDTO1).isNotEqualTo(menuItemsLanguageDTO2);
        menuItemsLanguageDTO1.setId(null);
        assertThat(menuItemsLanguageDTO1).isNotEqualTo(menuItemsLanguageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(menuItemsLanguageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(menuItemsLanguageMapper.fromId(null)).isNull();
    }
}
