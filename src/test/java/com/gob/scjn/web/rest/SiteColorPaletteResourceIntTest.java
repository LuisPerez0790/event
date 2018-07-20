package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.SiteColorPalette;
import com.gob.scjn.repository.SiteColorPaletteRepository;
import com.gob.scjn.service.SiteColorPaletteService;
import com.gob.scjn.service.dto.SiteColorPaletteDTO;
import com.gob.scjn.service.mapper.SiteColorPaletteMapper;
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
 * Test class for the SiteColorPaletteResource REST controller.
 *
 * @see SiteColorPaletteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class SiteColorPaletteResourceIntTest {

    private static final String DEFAULT_PRIMARY = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY = "BBBBBBBBBB";

    private static final String DEFAULT_INVERSE = "AAAAAAAAAA";
    private static final String UPDATED_INVERSE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLEMENTARY = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENTARY = "BBBBBBBBBB";

    @Autowired
    private SiteColorPaletteRepository siteColorPaletteRepository;


    @Autowired
    private SiteColorPaletteMapper siteColorPaletteMapper;
    

    @Autowired
    private SiteColorPaletteService siteColorPaletteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSiteColorPaletteMockMvc;

    private SiteColorPalette siteColorPalette;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SiteColorPaletteResource siteColorPaletteResource = new SiteColorPaletteResource(siteColorPaletteService);
        this.restSiteColorPaletteMockMvc = MockMvcBuilders.standaloneSetup(siteColorPaletteResource)
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
    public static SiteColorPalette createEntity(EntityManager em) {
        SiteColorPalette siteColorPalette = new SiteColorPalette()
            .primary(DEFAULT_PRIMARY)
            .secondary(DEFAULT_SECONDARY)
            .inverse(DEFAULT_INVERSE)
            .complementary(DEFAULT_COMPLEMENTARY);
        return siteColorPalette;
    }

    @Before
    public void initTest() {
        siteColorPalette = createEntity(em);
    }

    @Test
    @Transactional
    public void createSiteColorPalette() throws Exception {
        int databaseSizeBeforeCreate = siteColorPaletteRepository.findAll().size();

        // Create the SiteColorPalette
        SiteColorPaletteDTO siteColorPaletteDTO = siteColorPaletteMapper.toDto(siteColorPalette);
        restSiteColorPaletteMockMvc.perform(post("/api/site-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteColorPaletteDTO)))
            .andExpect(status().isCreated());

        // Validate the SiteColorPalette in the database
        List<SiteColorPalette> siteColorPaletteList = siteColorPaletteRepository.findAll();
        assertThat(siteColorPaletteList).hasSize(databaseSizeBeforeCreate + 1);
        SiteColorPalette testSiteColorPalette = siteColorPaletteList.get(siteColorPaletteList.size() - 1);
        assertThat(testSiteColorPalette.getPrimary()).isEqualTo(DEFAULT_PRIMARY);
        assertThat(testSiteColorPalette.getSecondary()).isEqualTo(DEFAULT_SECONDARY);
        assertThat(testSiteColorPalette.getInverse()).isEqualTo(DEFAULT_INVERSE);
        assertThat(testSiteColorPalette.getComplementary()).isEqualTo(DEFAULT_COMPLEMENTARY);
    }

    @Test
    @Transactional
    public void createSiteColorPaletteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = siteColorPaletteRepository.findAll().size();

        // Create the SiteColorPalette with an existing ID
        siteColorPalette.setId(1L);
        SiteColorPaletteDTO siteColorPaletteDTO = siteColorPaletteMapper.toDto(siteColorPalette);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSiteColorPaletteMockMvc.perform(post("/api/site-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteColorPaletteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SiteColorPalette in the database
        List<SiteColorPalette> siteColorPaletteList = siteColorPaletteRepository.findAll();
        assertThat(siteColorPaletteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSiteColorPalettes() throws Exception {
        // Initialize the database
        siteColorPaletteRepository.saveAndFlush(siteColorPalette);

        // Get all the siteColorPaletteList
        restSiteColorPaletteMockMvc.perform(get("/api/site-color-palettes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(siteColorPalette.getId().intValue())))
            .andExpect(jsonPath("$.[*].primary").value(hasItem(DEFAULT_PRIMARY.toString())))
            .andExpect(jsonPath("$.[*].secondary").value(hasItem(DEFAULT_SECONDARY.toString())))
            .andExpect(jsonPath("$.[*].inverse").value(hasItem(DEFAULT_INVERSE.toString())))
            .andExpect(jsonPath("$.[*].complementary").value(hasItem(DEFAULT_COMPLEMENTARY.toString())));
    }
    

    @Test
    @Transactional
    public void getSiteColorPalette() throws Exception {
        // Initialize the database
        siteColorPaletteRepository.saveAndFlush(siteColorPalette);

        // Get the siteColorPalette
        restSiteColorPaletteMockMvc.perform(get("/api/site-color-palettes/{id}", siteColorPalette.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(siteColorPalette.getId().intValue()))
            .andExpect(jsonPath("$.primary").value(DEFAULT_PRIMARY.toString()))
            .andExpect(jsonPath("$.secondary").value(DEFAULT_SECONDARY.toString()))
            .andExpect(jsonPath("$.inverse").value(DEFAULT_INVERSE.toString()))
            .andExpect(jsonPath("$.complementary").value(DEFAULT_COMPLEMENTARY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSiteColorPalette() throws Exception {
        // Get the siteColorPalette
        restSiteColorPaletteMockMvc.perform(get("/api/site-color-palettes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSiteColorPalette() throws Exception {
        // Initialize the database
        siteColorPaletteRepository.saveAndFlush(siteColorPalette);

        int databaseSizeBeforeUpdate = siteColorPaletteRepository.findAll().size();

        // Update the siteColorPalette
        SiteColorPalette updatedSiteColorPalette = siteColorPaletteRepository.findById(siteColorPalette.getId()).get();
        // Disconnect from session so that the updates on updatedSiteColorPalette are not directly saved in db
        em.detach(updatedSiteColorPalette);
        updatedSiteColorPalette
            .primary(UPDATED_PRIMARY)
            .secondary(UPDATED_SECONDARY)
            .inverse(UPDATED_INVERSE)
            .complementary(UPDATED_COMPLEMENTARY);
        SiteColorPaletteDTO siteColorPaletteDTO = siteColorPaletteMapper.toDto(updatedSiteColorPalette);

        restSiteColorPaletteMockMvc.perform(put("/api/site-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteColorPaletteDTO)))
            .andExpect(status().isOk());

        // Validate the SiteColorPalette in the database
        List<SiteColorPalette> siteColorPaletteList = siteColorPaletteRepository.findAll();
        assertThat(siteColorPaletteList).hasSize(databaseSizeBeforeUpdate);
        SiteColorPalette testSiteColorPalette = siteColorPaletteList.get(siteColorPaletteList.size() - 1);
        assertThat(testSiteColorPalette.getPrimary()).isEqualTo(UPDATED_PRIMARY);
        assertThat(testSiteColorPalette.getSecondary()).isEqualTo(UPDATED_SECONDARY);
        assertThat(testSiteColorPalette.getInverse()).isEqualTo(UPDATED_INVERSE);
        assertThat(testSiteColorPalette.getComplementary()).isEqualTo(UPDATED_COMPLEMENTARY);
    }

    @Test
    @Transactional
    public void updateNonExistingSiteColorPalette() throws Exception {
        int databaseSizeBeforeUpdate = siteColorPaletteRepository.findAll().size();

        // Create the SiteColorPalette
        SiteColorPaletteDTO siteColorPaletteDTO = siteColorPaletteMapper.toDto(siteColorPalette);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSiteColorPaletteMockMvc.perform(put("/api/site-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteColorPaletteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SiteColorPalette in the database
        List<SiteColorPalette> siteColorPaletteList = siteColorPaletteRepository.findAll();
        assertThat(siteColorPaletteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSiteColorPalette() throws Exception {
        // Initialize the database
        siteColorPaletteRepository.saveAndFlush(siteColorPalette);

        int databaseSizeBeforeDelete = siteColorPaletteRepository.findAll().size();

        // Get the siteColorPalette
        restSiteColorPaletteMockMvc.perform(delete("/api/site-color-palettes/{id}", siteColorPalette.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SiteColorPalette> siteColorPaletteList = siteColorPaletteRepository.findAll();
        assertThat(siteColorPaletteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteColorPalette.class);
        SiteColorPalette siteColorPalette1 = new SiteColorPalette();
        siteColorPalette1.setId(1L);
        SiteColorPalette siteColorPalette2 = new SiteColorPalette();
        siteColorPalette2.setId(siteColorPalette1.getId());
        assertThat(siteColorPalette1).isEqualTo(siteColorPalette2);
        siteColorPalette2.setId(2L);
        assertThat(siteColorPalette1).isNotEqualTo(siteColorPalette2);
        siteColorPalette1.setId(null);
        assertThat(siteColorPalette1).isNotEqualTo(siteColorPalette2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteColorPaletteDTO.class);
        SiteColorPaletteDTO siteColorPaletteDTO1 = new SiteColorPaletteDTO();
        siteColorPaletteDTO1.setId(1L);
        SiteColorPaletteDTO siteColorPaletteDTO2 = new SiteColorPaletteDTO();
        assertThat(siteColorPaletteDTO1).isNotEqualTo(siteColorPaletteDTO2);
        siteColorPaletteDTO2.setId(siteColorPaletteDTO1.getId());
        assertThat(siteColorPaletteDTO1).isEqualTo(siteColorPaletteDTO2);
        siteColorPaletteDTO2.setId(2L);
        assertThat(siteColorPaletteDTO1).isNotEqualTo(siteColorPaletteDTO2);
        siteColorPaletteDTO1.setId(null);
        assertThat(siteColorPaletteDTO1).isNotEqualTo(siteColorPaletteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(siteColorPaletteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(siteColorPaletteMapper.fromId(null)).isNull();
    }
}
