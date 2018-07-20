package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.CMS;
import com.gob.scjn.repository.CMSRepository;
import com.gob.scjn.service.CMSService;
import com.gob.scjn.service.dto.CMSDTO;
import com.gob.scjn.service.mapper.CMSMapper;
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
 * Test class for the CMSResource REST controller.
 *
 * @see CMSResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class CMSResourceIntTest {

    private static final Long DEFAULT_FILE_ID = 1L;
    private static final Long UPDATED_FILE_ID = 2L;

    @Autowired
    private CMSRepository cMSRepository;


    @Autowired
    private CMSMapper cMSMapper;
    

    @Autowired
    private CMSService cMSService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCMSMockMvc;

    private CMS cMS;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CMSResource cMSResource = new CMSResource(cMSService);
        this.restCMSMockMvc = MockMvcBuilders.standaloneSetup(cMSResource)
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
    public static CMS createEntity(EntityManager em) {
        CMS cMS = new CMS()
            .fileId(DEFAULT_FILE_ID);
        return cMS;
    }

    @Before
    public void initTest() {
        cMS = createEntity(em);
    }

    @Test
    @Transactional
    public void createCMS() throws Exception {
        int databaseSizeBeforeCreate = cMSRepository.findAll().size();

        // Create the CMS
        CMSDTO cMSDTO = cMSMapper.toDto(cMS);
        restCMSMockMvc.perform(post("/api/cms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cMSDTO)))
            .andExpect(status().isCreated());

        // Validate the CMS in the database
        List<CMS> cMSList = cMSRepository.findAll();
        assertThat(cMSList).hasSize(databaseSizeBeforeCreate + 1);
        CMS testCMS = cMSList.get(cMSList.size() - 1);
        assertThat(testCMS.getFileId()).isEqualTo(DEFAULT_FILE_ID);
    }

    @Test
    @Transactional
    public void createCMSWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cMSRepository.findAll().size();

        // Create the CMS with an existing ID
        cMS.setId(1L);
        CMSDTO cMSDTO = cMSMapper.toDto(cMS);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCMSMockMvc.perform(post("/api/cms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cMSDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CMS in the database
        List<CMS> cMSList = cMSRepository.findAll();
        assertThat(cMSList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCMS() throws Exception {
        // Initialize the database
        cMSRepository.saveAndFlush(cMS);

        // Get all the cMSList
        restCMSMockMvc.perform(get("/api/cms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cMS.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileId").value(hasItem(DEFAULT_FILE_ID.intValue())));
    }
    

    @Test
    @Transactional
    public void getCMS() throws Exception {
        // Initialize the database
        cMSRepository.saveAndFlush(cMS);

        // Get the cMS
        restCMSMockMvc.perform(get("/api/cms/{id}", cMS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cMS.getId().intValue()))
            .andExpect(jsonPath("$.fileId").value(DEFAULT_FILE_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCMS() throws Exception {
        // Get the cMS
        restCMSMockMvc.perform(get("/api/cms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCMS() throws Exception {
        // Initialize the database
        cMSRepository.saveAndFlush(cMS);

        int databaseSizeBeforeUpdate = cMSRepository.findAll().size();

        // Update the cMS
        CMS updatedCMS = cMSRepository.findById(cMS.getId()).get();
        // Disconnect from session so that the updates on updatedCMS are not directly saved in db
        em.detach(updatedCMS);
        updatedCMS
            .fileId(UPDATED_FILE_ID);
        CMSDTO cMSDTO = cMSMapper.toDto(updatedCMS);

        restCMSMockMvc.perform(put("/api/cms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cMSDTO)))
            .andExpect(status().isOk());

        // Validate the CMS in the database
        List<CMS> cMSList = cMSRepository.findAll();
        assertThat(cMSList).hasSize(databaseSizeBeforeUpdate);
        CMS testCMS = cMSList.get(cMSList.size() - 1);
        assertThat(testCMS.getFileId()).isEqualTo(UPDATED_FILE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCMS() throws Exception {
        int databaseSizeBeforeUpdate = cMSRepository.findAll().size();

        // Create the CMS
        CMSDTO cMSDTO = cMSMapper.toDto(cMS);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCMSMockMvc.perform(put("/api/cms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cMSDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CMS in the database
        List<CMS> cMSList = cMSRepository.findAll();
        assertThat(cMSList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCMS() throws Exception {
        // Initialize the database
        cMSRepository.saveAndFlush(cMS);

        int databaseSizeBeforeDelete = cMSRepository.findAll().size();

        // Get the cMS
        restCMSMockMvc.perform(delete("/api/cms/{id}", cMS.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CMS> cMSList = cMSRepository.findAll();
        assertThat(cMSList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CMS.class);
        CMS cMS1 = new CMS();
        cMS1.setId(1L);
        CMS cMS2 = new CMS();
        cMS2.setId(cMS1.getId());
        assertThat(cMS1).isEqualTo(cMS2);
        cMS2.setId(2L);
        assertThat(cMS1).isNotEqualTo(cMS2);
        cMS1.setId(null);
        assertThat(cMS1).isNotEqualTo(cMS2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CMSDTO.class);
        CMSDTO cMSDTO1 = new CMSDTO();
        cMSDTO1.setId(1L);
        CMSDTO cMSDTO2 = new CMSDTO();
        assertThat(cMSDTO1).isNotEqualTo(cMSDTO2);
        cMSDTO2.setId(cMSDTO1.getId());
        assertThat(cMSDTO1).isEqualTo(cMSDTO2);
        cMSDTO2.setId(2L);
        assertThat(cMSDTO1).isNotEqualTo(cMSDTO2);
        cMSDTO1.setId(null);
        assertThat(cMSDTO1).isNotEqualTo(cMSDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cMSMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cMSMapper.fromId(null)).isNull();
    }
}
