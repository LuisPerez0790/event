package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.VenueLanguage;
import com.gob.scjn.repository.VenueLanguageRepository;
import com.gob.scjn.service.VenueLanguageService;
import com.gob.scjn.service.dto.VenueLanguageDTO;
import com.gob.scjn.service.mapper.VenueLanguageMapper;
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
 * Test class for the VenueLanguageResource REST controller.
 *
 * @see VenueLanguageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class VenueLanguageResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final Language UPDATED_LANGUAGE = Language.FRENCH;

    @Autowired
    private VenueLanguageRepository venueLanguageRepository;


    @Autowired
    private VenueLanguageMapper venueLanguageMapper;
    

    @Autowired
    private VenueLanguageService venueLanguageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVenueLanguageMockMvc;

    private VenueLanguage venueLanguage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VenueLanguageResource venueLanguageResource = new VenueLanguageResource(venueLanguageService);
        this.restVenueLanguageMockMvc = MockMvcBuilders.standaloneSetup(venueLanguageResource)
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
    public static VenueLanguage createEntity(EntityManager em) {
        VenueLanguage venueLanguage = new VenueLanguage()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .description(DEFAULT_DESCRIPTION)
            .language(DEFAULT_LANGUAGE);
        return venueLanguage;
    }

    @Before
    public void initTest() {
        venueLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createVenueLanguage() throws Exception {
        int databaseSizeBeforeCreate = venueLanguageRepository.findAll().size();

        // Create the VenueLanguage
        VenueLanguageDTO venueLanguageDTO = venueLanguageMapper.toDto(venueLanguage);
        restVenueLanguageMockMvc.perform(post("/api/venue-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueLanguageDTO)))
            .andExpect(status().isCreated());

        // Validate the VenueLanguage in the database
        List<VenueLanguage> venueLanguageList = venueLanguageRepository.findAll();
        assertThat(venueLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        VenueLanguage testVenueLanguage = venueLanguageList.get(venueLanguageList.size() - 1);
        assertThat(testVenueLanguage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVenueLanguage.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testVenueLanguage.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVenueLanguage.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    public void createVenueLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = venueLanguageRepository.findAll().size();

        // Create the VenueLanguage with an existing ID
        venueLanguage.setId(1L);
        VenueLanguageDTO venueLanguageDTO = venueLanguageMapper.toDto(venueLanguage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenueLanguageMockMvc.perform(post("/api/venue-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VenueLanguage in the database
        List<VenueLanguage> venueLanguageList = venueLanguageRepository.findAll();
        assertThat(venueLanguageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVenueLanguages() throws Exception {
        // Initialize the database
        venueLanguageRepository.saveAndFlush(venueLanguage);

        // Get all the venueLanguageList
        restVenueLanguageMockMvc.perform(get("/api/venue-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venueLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }
    

    @Test
    @Transactional
    public void getVenueLanguage() throws Exception {
        // Initialize the database
        venueLanguageRepository.saveAndFlush(venueLanguage);

        // Get the venueLanguage
        restVenueLanguageMockMvc.perform(get("/api/venue-languages/{id}", venueLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(venueLanguage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVenueLanguage() throws Exception {
        // Get the venueLanguage
        restVenueLanguageMockMvc.perform(get("/api/venue-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVenueLanguage() throws Exception {
        // Initialize the database
        venueLanguageRepository.saveAndFlush(venueLanguage);

        int databaseSizeBeforeUpdate = venueLanguageRepository.findAll().size();

        // Update the venueLanguage
        VenueLanguage updatedVenueLanguage = venueLanguageRepository.findById(venueLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedVenueLanguage are not directly saved in db
        em.detach(updatedVenueLanguage);
        updatedVenueLanguage
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .description(UPDATED_DESCRIPTION)
            .language(UPDATED_LANGUAGE);
        VenueLanguageDTO venueLanguageDTO = venueLanguageMapper.toDto(updatedVenueLanguage);

        restVenueLanguageMockMvc.perform(put("/api/venue-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueLanguageDTO)))
            .andExpect(status().isOk());

        // Validate the VenueLanguage in the database
        List<VenueLanguage> venueLanguageList = venueLanguageRepository.findAll();
        assertThat(venueLanguageList).hasSize(databaseSizeBeforeUpdate);
        VenueLanguage testVenueLanguage = venueLanguageList.get(venueLanguageList.size() - 1);
        assertThat(testVenueLanguage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVenueLanguage.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testVenueLanguage.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVenueLanguage.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingVenueLanguage() throws Exception {
        int databaseSizeBeforeUpdate = venueLanguageRepository.findAll().size();

        // Create the VenueLanguage
        VenueLanguageDTO venueLanguageDTO = venueLanguageMapper.toDto(venueLanguage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVenueLanguageMockMvc.perform(put("/api/venue-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VenueLanguage in the database
        List<VenueLanguage> venueLanguageList = venueLanguageRepository.findAll();
        assertThat(venueLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVenueLanguage() throws Exception {
        // Initialize the database
        venueLanguageRepository.saveAndFlush(venueLanguage);

        int databaseSizeBeforeDelete = venueLanguageRepository.findAll().size();

        // Get the venueLanguage
        restVenueLanguageMockMvc.perform(delete("/api/venue-languages/{id}", venueLanguage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VenueLanguage> venueLanguageList = venueLanguageRepository.findAll();
        assertThat(venueLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VenueLanguage.class);
        VenueLanguage venueLanguage1 = new VenueLanguage();
        venueLanguage1.setId(1L);
        VenueLanguage venueLanguage2 = new VenueLanguage();
        venueLanguage2.setId(venueLanguage1.getId());
        assertThat(venueLanguage1).isEqualTo(venueLanguage2);
        venueLanguage2.setId(2L);
        assertThat(venueLanguage1).isNotEqualTo(venueLanguage2);
        venueLanguage1.setId(null);
        assertThat(venueLanguage1).isNotEqualTo(venueLanguage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VenueLanguageDTO.class);
        VenueLanguageDTO venueLanguageDTO1 = new VenueLanguageDTO();
        venueLanguageDTO1.setId(1L);
        VenueLanguageDTO venueLanguageDTO2 = new VenueLanguageDTO();
        assertThat(venueLanguageDTO1).isNotEqualTo(venueLanguageDTO2);
        venueLanguageDTO2.setId(venueLanguageDTO1.getId());
        assertThat(venueLanguageDTO1).isEqualTo(venueLanguageDTO2);
        venueLanguageDTO2.setId(2L);
        assertThat(venueLanguageDTO1).isNotEqualTo(venueLanguageDTO2);
        venueLanguageDTO1.setId(null);
        assertThat(venueLanguageDTO1).isNotEqualTo(venueLanguageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(venueLanguageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(venueLanguageMapper.fromId(null)).isNull();
    }
}
