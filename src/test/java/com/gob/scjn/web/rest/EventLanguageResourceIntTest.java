package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.EventLanguage;
import com.gob.scjn.repository.EventLanguageRepository;
import com.gob.scjn.service.EventLanguageService;
import com.gob.scjn.service.dto.EventLanguageDTO;
import com.gob.scjn.service.mapper.EventLanguageMapper;
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
 * Test class for the EventLanguageResource REST controller.
 *
 * @see EventLanguageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class EventLanguageResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final Language UPDATED_LANGUAGE = Language.FRENCH;

    @Autowired
    private EventLanguageRepository eventLanguageRepository;


    @Autowired
    private EventLanguageMapper eventLanguageMapper;
    

    @Autowired
    private EventLanguageService eventLanguageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEventLanguageMockMvc;

    private EventLanguage eventLanguage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventLanguageResource eventLanguageResource = new EventLanguageResource(eventLanguageService);
        this.restEventLanguageMockMvc = MockMvcBuilders.standaloneSetup(eventLanguageResource)
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
    public static EventLanguage createEntity(EntityManager em) {
        EventLanguage eventLanguage = new EventLanguage()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .address(DEFAULT_ADDRESS)
            .language(DEFAULT_LANGUAGE);
        return eventLanguage;
    }

    @Before
    public void initTest() {
        eventLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventLanguage() throws Exception {
        int databaseSizeBeforeCreate = eventLanguageRepository.findAll().size();

        // Create the EventLanguage
        EventLanguageDTO eventLanguageDTO = eventLanguageMapper.toDto(eventLanguage);
        restEventLanguageMockMvc.perform(post("/api/event-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLanguageDTO)))
            .andExpect(status().isCreated());

        // Validate the EventLanguage in the database
        List<EventLanguage> eventLanguageList = eventLanguageRepository.findAll();
        assertThat(eventLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        EventLanguage testEventLanguage = eventLanguageList.get(eventLanguageList.size() - 1);
        assertThat(testEventLanguage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEventLanguage.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEventLanguage.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testEventLanguage.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    public void createEventLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventLanguageRepository.findAll().size();

        // Create the EventLanguage with an existing ID
        eventLanguage.setId(1L);
        EventLanguageDTO eventLanguageDTO = eventLanguageMapper.toDto(eventLanguage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventLanguageMockMvc.perform(post("/api/event-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventLanguage in the database
        List<EventLanguage> eventLanguageList = eventLanguageRepository.findAll();
        assertThat(eventLanguageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEventLanguages() throws Exception {
        // Initialize the database
        eventLanguageRepository.saveAndFlush(eventLanguage);

        // Get all the eventLanguageList
        restEventLanguageMockMvc.perform(get("/api/event-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }
    

    @Test
    @Transactional
    public void getEventLanguage() throws Exception {
        // Initialize the database
        eventLanguageRepository.saveAndFlush(eventLanguage);

        // Get the eventLanguage
        restEventLanguageMockMvc.perform(get("/api/event-languages/{id}", eventLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventLanguage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEventLanguage() throws Exception {
        // Get the eventLanguage
        restEventLanguageMockMvc.perform(get("/api/event-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventLanguage() throws Exception {
        // Initialize the database
        eventLanguageRepository.saveAndFlush(eventLanguage);

        int databaseSizeBeforeUpdate = eventLanguageRepository.findAll().size();

        // Update the eventLanguage
        EventLanguage updatedEventLanguage = eventLanguageRepository.findById(eventLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedEventLanguage are not directly saved in db
        em.detach(updatedEventLanguage);
        updatedEventLanguage
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS)
            .language(UPDATED_LANGUAGE);
        EventLanguageDTO eventLanguageDTO = eventLanguageMapper.toDto(updatedEventLanguage);

        restEventLanguageMockMvc.perform(put("/api/event-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLanguageDTO)))
            .andExpect(status().isOk());

        // Validate the EventLanguage in the database
        List<EventLanguage> eventLanguageList = eventLanguageRepository.findAll();
        assertThat(eventLanguageList).hasSize(databaseSizeBeforeUpdate);
        EventLanguage testEventLanguage = eventLanguageList.get(eventLanguageList.size() - 1);
        assertThat(testEventLanguage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEventLanguage.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEventLanguage.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testEventLanguage.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingEventLanguage() throws Exception {
        int databaseSizeBeforeUpdate = eventLanguageRepository.findAll().size();

        // Create the EventLanguage
        EventLanguageDTO eventLanguageDTO = eventLanguageMapper.toDto(eventLanguage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEventLanguageMockMvc.perform(put("/api/event-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventLanguage in the database
        List<EventLanguage> eventLanguageList = eventLanguageRepository.findAll();
        assertThat(eventLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventLanguage() throws Exception {
        // Initialize the database
        eventLanguageRepository.saveAndFlush(eventLanguage);

        int databaseSizeBeforeDelete = eventLanguageRepository.findAll().size();

        // Get the eventLanguage
        restEventLanguageMockMvc.perform(delete("/api/event-languages/{id}", eventLanguage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventLanguage> eventLanguageList = eventLanguageRepository.findAll();
        assertThat(eventLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLanguage.class);
        EventLanguage eventLanguage1 = new EventLanguage();
        eventLanguage1.setId(1L);
        EventLanguage eventLanguage2 = new EventLanguage();
        eventLanguage2.setId(eventLanguage1.getId());
        assertThat(eventLanguage1).isEqualTo(eventLanguage2);
        eventLanguage2.setId(2L);
        assertThat(eventLanguage1).isNotEqualTo(eventLanguage2);
        eventLanguage1.setId(null);
        assertThat(eventLanguage1).isNotEqualTo(eventLanguage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLanguageDTO.class);
        EventLanguageDTO eventLanguageDTO1 = new EventLanguageDTO();
        eventLanguageDTO1.setId(1L);
        EventLanguageDTO eventLanguageDTO2 = new EventLanguageDTO();
        assertThat(eventLanguageDTO1).isNotEqualTo(eventLanguageDTO2);
        eventLanguageDTO2.setId(eventLanguageDTO1.getId());
        assertThat(eventLanguageDTO1).isEqualTo(eventLanguageDTO2);
        eventLanguageDTO2.setId(2L);
        assertThat(eventLanguageDTO1).isNotEqualTo(eventLanguageDTO2);
        eventLanguageDTO1.setId(null);
        assertThat(eventLanguageDTO1).isNotEqualTo(eventLanguageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eventLanguageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eventLanguageMapper.fromId(null)).isNull();
    }
}
