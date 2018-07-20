package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.SitePage;
import com.gob.scjn.repository.SitePageRepository;
import com.gob.scjn.service.SitePageService;
import com.gob.scjn.service.dto.SitePageDTO;
import com.gob.scjn.service.mapper.SitePageMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.gob.scjn.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SitePageResource REST controller.
 *
 * @see SitePageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class SitePageResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MENU_ENTRY = "AAAAAAAAAA";
    private static final String UPDATED_MENU_ENTRY = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDER = 1L;
    private static final Long UPDATED_ORDER = 2L;

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    @Autowired
    private SitePageRepository sitePageRepository;


    @Autowired
    private SitePageMapper sitePageMapper;
    

    @Autowired
    private SitePageService sitePageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSitePageMockMvc;

    private SitePage sitePage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SitePageResource sitePageResource = new SitePageResource(sitePageService);
        this.restSitePageMockMvc = MockMvcBuilders.standaloneSetup(sitePageResource)
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
    public static SitePage createEntity(EntityManager em) {
        SitePage sitePage = new SitePage()
            .creationDate(DEFAULT_CREATION_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .menuEntry(DEFAULT_MENU_ENTRY)
            .order(DEFAULT_ORDER)
            .status(DEFAULT_STATUS)
            .slug(DEFAULT_SLUG);
        return sitePage;
    }

    @Before
    public void initTest() {
        sitePage = createEntity(em);
    }

    @Test
    @Transactional
    public void createSitePage() throws Exception {
        int databaseSizeBeforeCreate = sitePageRepository.findAll().size();

        // Create the SitePage
        SitePageDTO sitePageDTO = sitePageMapper.toDto(sitePage);
        restSitePageMockMvc.perform(post("/api/site-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageDTO)))
            .andExpect(status().isCreated());

        // Validate the SitePage in the database
        List<SitePage> sitePageList = sitePageRepository.findAll();
        assertThat(sitePageList).hasSize(databaseSizeBeforeCreate + 1);
        SitePage testSitePage = sitePageList.get(sitePageList.size() - 1);
        assertThat(testSitePage.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testSitePage.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSitePage.getMenuEntry()).isEqualTo(DEFAULT_MENU_ENTRY);
        assertThat(testSitePage.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testSitePage.isStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSitePage.getSlug()).isEqualTo(DEFAULT_SLUG);
    }

    @Test
    @Transactional
    public void createSitePageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sitePageRepository.findAll().size();

        // Create the SitePage with an existing ID
        sitePage.setId(1L);
        SitePageDTO sitePageDTO = sitePageMapper.toDto(sitePage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSitePageMockMvc.perform(post("/api/site-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SitePage in the database
        List<SitePage> sitePageList = sitePageRepository.findAll();
        assertThat(sitePageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSitePages() throws Exception {
        // Initialize the database
        sitePageRepository.saveAndFlush(sitePage);

        // Get all the sitePageList
        restSitePageMockMvc.perform(get("/api/site-pages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sitePage.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].menuEntry").value(hasItem(DEFAULT_MENU_ENTRY.toString())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG.toString())));
    }
    

    @Test
    @Transactional
    public void getSitePage() throws Exception {
        // Initialize the database
        sitePageRepository.saveAndFlush(sitePage);

        // Get the sitePage
        restSitePageMockMvc.perform(get("/api/site-pages/{id}", sitePage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sitePage.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.menuEntry").value(DEFAULT_MENU_ENTRY.toString()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSitePage() throws Exception {
        // Get the sitePage
        restSitePageMockMvc.perform(get("/api/site-pages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSitePage() throws Exception {
        // Initialize the database
        sitePageRepository.saveAndFlush(sitePage);

        int databaseSizeBeforeUpdate = sitePageRepository.findAll().size();

        // Update the sitePage
        SitePage updatedSitePage = sitePageRepository.findById(sitePage.getId()).get();
        // Disconnect from session so that the updates on updatedSitePage are not directly saved in db
        em.detach(updatedSitePage);
        updatedSitePage
            .creationDate(UPDATED_CREATION_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .menuEntry(UPDATED_MENU_ENTRY)
            .order(UPDATED_ORDER)
            .status(UPDATED_STATUS)
            .slug(UPDATED_SLUG);
        SitePageDTO sitePageDTO = sitePageMapper.toDto(updatedSitePage);

        restSitePageMockMvc.perform(put("/api/site-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageDTO)))
            .andExpect(status().isOk());

        // Validate the SitePage in the database
        List<SitePage> sitePageList = sitePageRepository.findAll();
        assertThat(sitePageList).hasSize(databaseSizeBeforeUpdate);
        SitePage testSitePage = sitePageList.get(sitePageList.size() - 1);
        assertThat(testSitePage.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testSitePage.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSitePage.getMenuEntry()).isEqualTo(UPDATED_MENU_ENTRY);
        assertThat(testSitePage.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testSitePage.isStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSitePage.getSlug()).isEqualTo(UPDATED_SLUG);
    }

    @Test
    @Transactional
    public void updateNonExistingSitePage() throws Exception {
        int databaseSizeBeforeUpdate = sitePageRepository.findAll().size();

        // Create the SitePage
        SitePageDTO sitePageDTO = sitePageMapper.toDto(sitePage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSitePageMockMvc.perform(put("/api/site-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sitePageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SitePage in the database
        List<SitePage> sitePageList = sitePageRepository.findAll();
        assertThat(sitePageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSitePage() throws Exception {
        // Initialize the database
        sitePageRepository.saveAndFlush(sitePage);

        int databaseSizeBeforeDelete = sitePageRepository.findAll().size();

        // Get the sitePage
        restSitePageMockMvc.perform(delete("/api/site-pages/{id}", sitePage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SitePage> sitePageList = sitePageRepository.findAll();
        assertThat(sitePageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SitePage.class);
        SitePage sitePage1 = new SitePage();
        sitePage1.setId(1L);
        SitePage sitePage2 = new SitePage();
        sitePage2.setId(sitePage1.getId());
        assertThat(sitePage1).isEqualTo(sitePage2);
        sitePage2.setId(2L);
        assertThat(sitePage1).isNotEqualTo(sitePage2);
        sitePage1.setId(null);
        assertThat(sitePage1).isNotEqualTo(sitePage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SitePageDTO.class);
        SitePageDTO sitePageDTO1 = new SitePageDTO();
        sitePageDTO1.setId(1L);
        SitePageDTO sitePageDTO2 = new SitePageDTO();
        assertThat(sitePageDTO1).isNotEqualTo(sitePageDTO2);
        sitePageDTO2.setId(sitePageDTO1.getId());
        assertThat(sitePageDTO1).isEqualTo(sitePageDTO2);
        sitePageDTO2.setId(2L);
        assertThat(sitePageDTO1).isNotEqualTo(sitePageDTO2);
        sitePageDTO1.setId(null);
        assertThat(sitePageDTO1).isNotEqualTo(sitePageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sitePageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sitePageMapper.fromId(null)).isNull();
    }
}
