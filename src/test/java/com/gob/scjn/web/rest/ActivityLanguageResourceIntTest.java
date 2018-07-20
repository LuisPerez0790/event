package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.ActivityLanguage;
import com.gob.scjn.repository.ActivityLanguageRepository;
import com.gob.scjn.service.ActivityLanguageService;
import com.gob.scjn.service.dto.ActivityLanguageDTO;
import com.gob.scjn.service.mapper.ActivityLanguageMapper;
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
 * Test class for the ActivityLanguageResource REST controller.
 *
 * @see ActivityLanguageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class ActivityLanguageResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final Language UPDATED_LANGUAGE = Language.FRENCH;

    @Autowired
    private ActivityLanguageRepository activityLanguageRepository;


    @Autowired
    private ActivityLanguageMapper activityLanguageMapper;
    

    @Autowired
    private ActivityLanguageService activityLanguageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restActivityLanguageMockMvc;

    private ActivityLanguage activityLanguage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivityLanguageResource activityLanguageResource = new ActivityLanguageResource(activityLanguageService);
        this.restActivityLanguageMockMvc = MockMvcBuilders.standaloneSetup(activityLanguageResource)
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
    public static ActivityLanguage createEntity(EntityManager em) {
        ActivityLanguage activityLanguage = new ActivityLanguage()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .address(DEFAULT_ADDRESS)
            .language(DEFAULT_LANGUAGE);
        return activityLanguage;
    }

    @Before
    public void initTest() {
        activityLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivityLanguage() throws Exception {
        int databaseSizeBeforeCreate = activityLanguageRepository.findAll().size();

        // Create the ActivityLanguage
        ActivityLanguageDTO activityLanguageDTO = activityLanguageMapper.toDto(activityLanguage);
        restActivityLanguageMockMvc.perform(post("/api/activity-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityLanguageDTO)))
            .andExpect(status().isCreated());

        // Validate the ActivityLanguage in the database
        List<ActivityLanguage> activityLanguageList = activityLanguageRepository.findAll();
        assertThat(activityLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        ActivityLanguage testActivityLanguage = activityLanguageList.get(activityLanguageList.size() - 1);
        assertThat(testActivityLanguage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testActivityLanguage.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testActivityLanguage.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testActivityLanguage.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    public void createActivityLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityLanguageRepository.findAll().size();

        // Create the ActivityLanguage with an existing ID
        activityLanguage.setId(1L);
        ActivityLanguageDTO activityLanguageDTO = activityLanguageMapper.toDto(activityLanguage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityLanguageMockMvc.perform(post("/api/activity-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityLanguage in the database
        List<ActivityLanguage> activityLanguageList = activityLanguageRepository.findAll();
        assertThat(activityLanguageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllActivityLanguages() throws Exception {
        // Initialize the database
        activityLanguageRepository.saveAndFlush(activityLanguage);

        // Get all the activityLanguageList
        restActivityLanguageMockMvc.perform(get("/api/activity-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activityLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }
    

    @Test
    @Transactional
    public void getActivityLanguage() throws Exception {
        // Initialize the database
        activityLanguageRepository.saveAndFlush(activityLanguage);

        // Get the activityLanguage
        restActivityLanguageMockMvc.perform(get("/api/activity-languages/{id}", activityLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activityLanguage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingActivityLanguage() throws Exception {
        // Get the activityLanguage
        restActivityLanguageMockMvc.perform(get("/api/activity-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivityLanguage() throws Exception {
        // Initialize the database
        activityLanguageRepository.saveAndFlush(activityLanguage);

        int databaseSizeBeforeUpdate = activityLanguageRepository.findAll().size();

        // Update the activityLanguage
        ActivityLanguage updatedActivityLanguage = activityLanguageRepository.findById(activityLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedActivityLanguage are not directly saved in db
        em.detach(updatedActivityLanguage);
        updatedActivityLanguage
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS)
            .language(UPDATED_LANGUAGE);
        ActivityLanguageDTO activityLanguageDTO = activityLanguageMapper.toDto(updatedActivityLanguage);

        restActivityLanguageMockMvc.perform(put("/api/activity-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityLanguageDTO)))
            .andExpect(status().isOk());

        // Validate the ActivityLanguage in the database
        List<ActivityLanguage> activityLanguageList = activityLanguageRepository.findAll();
        assertThat(activityLanguageList).hasSize(databaseSizeBeforeUpdate);
        ActivityLanguage testActivityLanguage = activityLanguageList.get(activityLanguageList.size() - 1);
        assertThat(testActivityLanguage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testActivityLanguage.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testActivityLanguage.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testActivityLanguage.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingActivityLanguage() throws Exception {
        int databaseSizeBeforeUpdate = activityLanguageRepository.findAll().size();

        // Create the ActivityLanguage
        ActivityLanguageDTO activityLanguageDTO = activityLanguageMapper.toDto(activityLanguage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restActivityLanguageMockMvc.perform(put("/api/activity-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityLanguage in the database
        List<ActivityLanguage> activityLanguageList = activityLanguageRepository.findAll();
        assertThat(activityLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActivityLanguage() throws Exception {
        // Initialize the database
        activityLanguageRepository.saveAndFlush(activityLanguage);

        int databaseSizeBeforeDelete = activityLanguageRepository.findAll().size();

        // Get the activityLanguage
        restActivityLanguageMockMvc.perform(delete("/api/activity-languages/{id}", activityLanguage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ActivityLanguage> activityLanguageList = activityLanguageRepository.findAll();
        assertThat(activityLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityLanguage.class);
        ActivityLanguage activityLanguage1 = new ActivityLanguage();
        activityLanguage1.setId(1L);
        ActivityLanguage activityLanguage2 = new ActivityLanguage();
        activityLanguage2.setId(activityLanguage1.getId());
        assertThat(activityLanguage1).isEqualTo(activityLanguage2);
        activityLanguage2.setId(2L);
        assertThat(activityLanguage1).isNotEqualTo(activityLanguage2);
        activityLanguage1.setId(null);
        assertThat(activityLanguage1).isNotEqualTo(activityLanguage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityLanguageDTO.class);
        ActivityLanguageDTO activityLanguageDTO1 = new ActivityLanguageDTO();
        activityLanguageDTO1.setId(1L);
        ActivityLanguageDTO activityLanguageDTO2 = new ActivityLanguageDTO();
        assertThat(activityLanguageDTO1).isNotEqualTo(activityLanguageDTO2);
        activityLanguageDTO2.setId(activityLanguageDTO1.getId());
        assertThat(activityLanguageDTO1).isEqualTo(activityLanguageDTO2);
        activityLanguageDTO2.setId(2L);
        assertThat(activityLanguageDTO1).isNotEqualTo(activityLanguageDTO2);
        activityLanguageDTO1.setId(null);
        assertThat(activityLanguageDTO1).isNotEqualTo(activityLanguageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(activityLanguageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(activityLanguageMapper.fromId(null)).isNull();
    }
}
