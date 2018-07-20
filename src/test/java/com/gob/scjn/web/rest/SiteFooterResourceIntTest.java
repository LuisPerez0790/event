package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.SiteFooter;
import com.gob.scjn.repository.SiteFooterRepository;
import com.gob.scjn.service.SiteFooterService;
import com.gob.scjn.service.dto.SiteFooterDTO;
import com.gob.scjn.service.mapper.SiteFooterMapper;
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

/**
 * Test class for the SiteFooterResource REST controller.
 *
 * @see SiteFooterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class SiteFooterResourceIntTest {

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_LINKS = "AAAAAAAAAA";
    private static final String UPDATED_LINKS = "BBBBBBBBBB";

    private static final String DEFAULT_MORE_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_MORE_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_GOOGLE_MAPS = "AAAAAAAAAA";
    private static final String UPDATED_GOOGLE_MAPS = "BBBBBBBBBB";

    @Autowired
    private SiteFooterRepository siteFooterRepository;


    @Autowired
    private SiteFooterMapper siteFooterMapper;
    

    @Autowired
    private SiteFooterService siteFooterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSiteFooterMockMvc;

    private SiteFooter siteFooter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SiteFooterResource siteFooterResource = new SiteFooterResource(siteFooterService);
        this.restSiteFooterMockMvc = MockMvcBuilders.standaloneSetup(siteFooterResource)
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
    public static SiteFooter createEntity(EntityManager em) {
        SiteFooter siteFooter = new SiteFooter()
            .address(DEFAULT_ADDRESS)
            .contact(DEFAULT_CONTACT)
            .links(DEFAULT_LINKS)
            .moreContent(DEFAULT_MORE_CONTENT)
            .googleMaps(DEFAULT_GOOGLE_MAPS);
        return siteFooter;
    }

    @Before
    public void initTest() {
        siteFooter = createEntity(em);
    }

    @Test
    @Transactional
    public void createSiteFooter() throws Exception {
        int databaseSizeBeforeCreate = siteFooterRepository.findAll().size();

        // Create the SiteFooter
        SiteFooterDTO siteFooterDTO = siteFooterMapper.toDto(siteFooter);
        restSiteFooterMockMvc.perform(post("/api/site-footers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteFooterDTO)))
            .andExpect(status().isCreated());

        // Validate the SiteFooter in the database
        List<SiteFooter> siteFooterList = siteFooterRepository.findAll();
        assertThat(siteFooterList).hasSize(databaseSizeBeforeCreate + 1);
        SiteFooter testSiteFooter = siteFooterList.get(siteFooterList.size() - 1);
        assertThat(testSiteFooter.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSiteFooter.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testSiteFooter.getLinks()).isEqualTo(DEFAULT_LINKS);
        assertThat(testSiteFooter.getMoreContent()).isEqualTo(DEFAULT_MORE_CONTENT);
        assertThat(testSiteFooter.getGoogleMaps()).isEqualTo(DEFAULT_GOOGLE_MAPS);
    }

    @Test
    @Transactional
    public void createSiteFooterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = siteFooterRepository.findAll().size();

        // Create the SiteFooter with an existing ID
        siteFooter.setId(1L);
        SiteFooterDTO siteFooterDTO = siteFooterMapper.toDto(siteFooter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSiteFooterMockMvc.perform(post("/api/site-footers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteFooterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SiteFooter in the database
        List<SiteFooter> siteFooterList = siteFooterRepository.findAll();
        assertThat(siteFooterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSiteFooters() throws Exception {
        // Initialize the database
        siteFooterRepository.saveAndFlush(siteFooter);

        // Get all the siteFooterList
        restSiteFooterMockMvc.perform(get("/api/site-footers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(siteFooter.getId().intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].links").value(hasItem(DEFAULT_LINKS.toString())))
            .andExpect(jsonPath("$.[*].moreContent").value(hasItem(DEFAULT_MORE_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].googleMaps").value(hasItem(DEFAULT_GOOGLE_MAPS.toString())));
    }
    

    @Test
    @Transactional
    public void getSiteFooter() throws Exception {
        // Initialize the database
        siteFooterRepository.saveAndFlush(siteFooter);

        // Get the siteFooter
        restSiteFooterMockMvc.perform(get("/api/site-footers/{id}", siteFooter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(siteFooter.getId().intValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.toString()))
            .andExpect(jsonPath("$.links").value(DEFAULT_LINKS.toString()))
            .andExpect(jsonPath("$.moreContent").value(DEFAULT_MORE_CONTENT.toString()))
            .andExpect(jsonPath("$.googleMaps").value(DEFAULT_GOOGLE_MAPS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSiteFooter() throws Exception {
        // Get the siteFooter
        restSiteFooterMockMvc.perform(get("/api/site-footers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSiteFooter() throws Exception {
        // Initialize the database
        siteFooterRepository.saveAndFlush(siteFooter);

        int databaseSizeBeforeUpdate = siteFooterRepository.findAll().size();

        // Update the siteFooter
        SiteFooter updatedSiteFooter = siteFooterRepository.findById(siteFooter.getId()).get();
        // Disconnect from session so that the updates on updatedSiteFooter are not directly saved in db
        em.detach(updatedSiteFooter);
        updatedSiteFooter
            .address(UPDATED_ADDRESS)
            .contact(UPDATED_CONTACT)
            .links(UPDATED_LINKS)
            .moreContent(UPDATED_MORE_CONTENT)
            .googleMaps(UPDATED_GOOGLE_MAPS);
        SiteFooterDTO siteFooterDTO = siteFooterMapper.toDto(updatedSiteFooter);

        restSiteFooterMockMvc.perform(put("/api/site-footers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteFooterDTO)))
            .andExpect(status().isOk());

        // Validate the SiteFooter in the database
        List<SiteFooter> siteFooterList = siteFooterRepository.findAll();
        assertThat(siteFooterList).hasSize(databaseSizeBeforeUpdate);
        SiteFooter testSiteFooter = siteFooterList.get(siteFooterList.size() - 1);
        assertThat(testSiteFooter.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSiteFooter.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testSiteFooter.getLinks()).isEqualTo(UPDATED_LINKS);
        assertThat(testSiteFooter.getMoreContent()).isEqualTo(UPDATED_MORE_CONTENT);
        assertThat(testSiteFooter.getGoogleMaps()).isEqualTo(UPDATED_GOOGLE_MAPS);
    }

    @Test
    @Transactional
    public void updateNonExistingSiteFooter() throws Exception {
        int databaseSizeBeforeUpdate = siteFooterRepository.findAll().size();

        // Create the SiteFooter
        SiteFooterDTO siteFooterDTO = siteFooterMapper.toDto(siteFooter);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSiteFooterMockMvc.perform(put("/api/site-footers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteFooterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SiteFooter in the database
        List<SiteFooter> siteFooterList = siteFooterRepository.findAll();
        assertThat(siteFooterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSiteFooter() throws Exception {
        // Initialize the database
        siteFooterRepository.saveAndFlush(siteFooter);

        int databaseSizeBeforeDelete = siteFooterRepository.findAll().size();

        // Get the siteFooter
        restSiteFooterMockMvc.perform(delete("/api/site-footers/{id}", siteFooter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SiteFooter> siteFooterList = siteFooterRepository.findAll();
        assertThat(siteFooterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteFooter.class);
        SiteFooter siteFooter1 = new SiteFooter();
        siteFooter1.setId(1L);
        SiteFooter siteFooter2 = new SiteFooter();
        siteFooter2.setId(siteFooter1.getId());
        assertThat(siteFooter1).isEqualTo(siteFooter2);
        siteFooter2.setId(2L);
        assertThat(siteFooter1).isNotEqualTo(siteFooter2);
        siteFooter1.setId(null);
        assertThat(siteFooter1).isNotEqualTo(siteFooter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteFooterDTO.class);
        SiteFooterDTO siteFooterDTO1 = new SiteFooterDTO();
        siteFooterDTO1.setId(1L);
        SiteFooterDTO siteFooterDTO2 = new SiteFooterDTO();
        assertThat(siteFooterDTO1).isNotEqualTo(siteFooterDTO2);
        siteFooterDTO2.setId(siteFooterDTO1.getId());
        assertThat(siteFooterDTO1).isEqualTo(siteFooterDTO2);
        siteFooterDTO2.setId(2L);
        assertThat(siteFooterDTO1).isNotEqualTo(siteFooterDTO2);
        siteFooterDTO1.setId(null);
        assertThat(siteFooterDTO1).isNotEqualTo(siteFooterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(siteFooterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(siteFooterMapper.fromId(null)).isNull();
    }
}
