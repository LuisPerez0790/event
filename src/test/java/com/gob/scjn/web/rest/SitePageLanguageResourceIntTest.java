package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.SitePageLanguage;
import com.gob.scjn.repository.SitePageLanguageRepository;
import com.gob.scjn.service.SitePageLanguageService;
import com.gob.scjn.service.dto.SitePageLanguageDTO;
import com.gob.scjn.service.mapper.SitePageLanguageMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static com.gob.scjn.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gob.scjn.domain.enumeration.Language;
/**
 * Test class for the SitePageLanguageResource REST controller.
 *
 * @see SitePageLanguageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class SitePageLanguageResourceIntTest {

    private static final String DEFAULT_EXCEPT_PAGE = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPT_PAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final Language UPDATED_LANGUAGE = Language.FRENCH;

    @Autowired
    private SitePageLanguageRepository sitePageLanguageRepository;


    @Autowired
    private SitePageLanguageMapper sitePageLanguageMapper;
    

    @Autowired
    private SitePageLanguageService sitePageLanguageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSitePageLanguageMockMvc;

    private SitePageLanguage sitePageLanguage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SitePageLanguageResource sitePageLanguageResource = new SitePageLanguageResource(sitePageLanguageService);
        this.restSitePageLanguageMockMvc = MockMvcBuilders.standaloneSetup(sitePageLanguageResource)
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
    public static SitePageLanguage createEntity(EntityManager em) {
        SitePageLanguage sitePageLanguage = new SitePageLanguage()
            .exceptPage(DEFAULT_EXCEPT_PAGE)
            .content(DEFAULT_CONTENT)
            .title(DEFAULT_TITLE)
            .language(DEFAULT_LANGUAGE);
        return sitePageLanguage;
    }

    @Before
    public void initTest() {
        sitePageLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createSitePageLanguage() throws Exception {
        int databaseSizeBeforeCreate = sitePageLanguageRepository.findAll().size();

        // Create the SitePageLanguage
        SitePageLanguageDTO sitePageLanguageDTO = sitePageLanguageMapper.toDto(sitePageLanguage);
        restSitePageLanguageMockMvc.perform(post("/api/site-page-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageLanguageDTO)))
            .andExpect(status().isCreated());

        // Validate the SitePageLanguage in the database
        List<SitePageLanguage> sitePageLanguageList = sitePageLanguageRepository.findAll();
        assertThat(sitePageLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        SitePageLanguage testSitePageLanguage = sitePageLanguageList.get(sitePageLanguageList.size() - 1);
        assertThat(testSitePageLanguage.getExceptPage()).isEqualTo(DEFAULT_EXCEPT_PAGE);
        assertThat(testSitePageLanguage.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testSitePageLanguage.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSitePageLanguage.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    public void createSitePageLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sitePageLanguageRepository.findAll().size();

        // Create the SitePageLanguage with an existing ID
        sitePageLanguage.setId(1L);
        SitePageLanguageDTO sitePageLanguageDTO = sitePageLanguageMapper.toDto(sitePageLanguage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSitePageLanguageMockMvc.perform(post("/api/site-page-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SitePageLanguage in the database
        List<SitePageLanguage> sitePageLanguageList = sitePageLanguageRepository.findAll();
        assertThat(sitePageLanguageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSitePageLanguages() throws Exception {
        // Initialize the database
        sitePageLanguageRepository.saveAndFlush(sitePageLanguage);

        // Get all the sitePageLanguageList
        restSitePageLanguageMockMvc.perform(get("/api/site-page-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sitePageLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].exceptPage").value(hasItem(DEFAULT_EXCEPT_PAGE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }
    

    @Test
    @Transactional
    public void getSitePageLanguage() throws Exception {
        // Initialize the database
        sitePageLanguageRepository.saveAndFlush(sitePageLanguage);

        // Get the sitePageLanguage
        restSitePageLanguageMockMvc.perform(get("/api/site-page-languages/{id}", sitePageLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sitePageLanguage.getId().intValue()))
            .andExpect(jsonPath("$.exceptPage").value(DEFAULT_EXCEPT_PAGE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSitePageLanguage() throws Exception {
        // Get the sitePageLanguage
        restSitePageLanguageMockMvc.perform(get("/api/site-page-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSitePageLanguage() throws Exception {
        // Initialize the database
        sitePageLanguageRepository.saveAndFlush(sitePageLanguage);

        int databaseSizeBeforeUpdate = sitePageLanguageRepository.findAll().size();

        // Update the sitePageLanguage
        SitePageLanguage updatedSitePageLanguage = sitePageLanguageRepository.findById(sitePageLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedSitePageLanguage are not directly saved in db
        em.detach(updatedSitePageLanguage);
        updatedSitePageLanguage
            .exceptPage(UPDATED_EXCEPT_PAGE)
            .content(UPDATED_CONTENT)
            .title(UPDATED_TITLE)
            .language(UPDATED_LANGUAGE);
        SitePageLanguageDTO sitePageLanguageDTO = sitePageLanguageMapper.toDto(updatedSitePageLanguage);

        restSitePageLanguageMockMvc.perform(put("/api/site-page-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageLanguageDTO)))
            .andExpect(status().isOk());

        // Validate the SitePageLanguage in the database
        List<SitePageLanguage> sitePageLanguageList = sitePageLanguageRepository.findAll();
        assertThat(sitePageLanguageList).hasSize(databaseSizeBeforeUpdate);
        SitePageLanguage testSitePageLanguage = sitePageLanguageList.get(sitePageLanguageList.size() - 1);
        assertThat(testSitePageLanguage.getExceptPage()).isEqualTo(UPDATED_EXCEPT_PAGE);
        assertThat(testSitePageLanguage.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testSitePageLanguage.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSitePageLanguage.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingSitePageLanguage() throws Exception {
        int databaseSizeBeforeUpdate = sitePageLanguageRepository.findAll().size();

        // Create the SitePageLanguage
        SitePageLanguageDTO sitePageLanguageDTO = sitePageLanguageMapper.toDto(sitePageLanguage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSitePageLanguageMockMvc.perform(put("/api/site-page-languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SitePageLanguage in the database
        List<SitePageLanguage> sitePageLanguageList = sitePageLanguageRepository.findAll();
        assertThat(sitePageLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSitePageLanguage() throws Exception {
        // Initialize the database
        sitePageLanguageRepository.saveAndFlush(sitePageLanguage);

        int databaseSizeBeforeDelete = sitePageLanguageRepository.findAll().size();

        // Get the sitePageLanguage
        restSitePageLanguageMockMvc.perform(delete("/api/site-page-languages/{id}", sitePageLanguage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SitePageLanguage> sitePageLanguageList = sitePageLanguageRepository.findAll();
        assertThat(sitePageLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SitePageLanguage.class);
        SitePageLanguage sitePageLanguage1 = new SitePageLanguage();
        sitePageLanguage1.setId(1L);
        SitePageLanguage sitePageLanguage2 = new SitePageLanguage();
        sitePageLanguage2.setId(sitePageLanguage1.getId());
        assertThat(sitePageLanguage1).isEqualTo(sitePageLanguage2);
        sitePageLanguage2.setId(2L);
        assertThat(sitePageLanguage1).isNotEqualTo(sitePageLanguage2);
        sitePageLanguage1.setId(null);
        assertThat(sitePageLanguage1).isNotEqualTo(sitePageLanguage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SitePageLanguageDTO.class);
        SitePageLanguageDTO sitePageLanguageDTO1 = new SitePageLanguageDTO();
        sitePageLanguageDTO1.setId(1L);
        SitePageLanguageDTO sitePageLanguageDTO2 = new SitePageLanguageDTO();
        assertThat(sitePageLanguageDTO1).isNotEqualTo(sitePageLanguageDTO2);
        sitePageLanguageDTO2.setId(sitePageLanguageDTO1.getId());
        assertThat(sitePageLanguageDTO1).isEqualTo(sitePageLanguageDTO2);
        sitePageLanguageDTO2.setId(2L);
        assertThat(sitePageLanguageDTO1).isNotEqualTo(sitePageLanguageDTO2);
        sitePageLanguageDTO1.setId(null);
        assertThat(sitePageLanguageDTO1).isNotEqualTo(sitePageLanguageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sitePageLanguageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sitePageLanguageMapper.fromId(null)).isNull();
    }
}
