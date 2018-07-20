package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.Venue;
import com.gob.scjn.repository.VenueRepository;
import com.gob.scjn.service.VenueService;
import com.gob.scjn.service.dto.VenueDTO;
import com.gob.scjn.service.mapper.VenueMapper;
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
 * Test class for the VenueResource REST controller.
 *
 * @see VenueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class VenueResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_GOOGLE_MAPS = "AAAAAAAAAA";
    private static final String UPDATED_GOOGLE_MAPS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private VenueRepository venueRepository;


    @Autowired
    private VenueMapper venueMapper;
    

    @Autowired
    private VenueService venueService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVenueMockMvc;

    private Venue venue;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VenueResource venueResource = new VenueResource(venueService);
        this.restVenueMockMvc = MockMvcBuilders.standaloneSetup(venueResource)
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
    public static Venue createEntity(EntityManager em) {
        Venue venue = new Venue()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .imageUrl(DEFAULT_IMAGE_URL)
            .phone(DEFAULT_PHONE)
            .url(DEFAULT_URL)
            .googleMaps(DEFAULT_GOOGLE_MAPS)
            .description(DEFAULT_DESCRIPTION);
        return venue;
    }

    @Before
    public void initTest() {
        venue = createEntity(em);
    }

    @Test
    @Transactional
    public void createVenue() throws Exception {
        int databaseSizeBeforeCreate = venueRepository.findAll().size();

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);
        restVenueMockMvc.perform(post("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isCreated());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeCreate + 1);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVenue.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testVenue.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testVenue.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testVenue.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testVenue.getGoogleMaps()).isEqualTo(DEFAULT_GOOGLE_MAPS);
        assertThat(testVenue.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createVenueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = venueRepository.findAll().size();

        // Create the Venue with an existing ID
        venue.setId(1L);
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenueMockMvc.perform(post("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVenues() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get all the venueList
        restVenueMockMvc.perform(get("/api/venues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venue.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].googleMaps").value(hasItem(DEFAULT_GOOGLE_MAPS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    

    @Test
    @Transactional
    public void getVenue() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get the venue
        restVenueMockMvc.perform(get("/api/venues/{id}", venue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(venue.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.googleMaps").value(DEFAULT_GOOGLE_MAPS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVenue() throws Exception {
        // Get the venue
        restVenueMockMvc.perform(get("/api/venues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVenue() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        int databaseSizeBeforeUpdate = venueRepository.findAll().size();

        // Update the venue
        Venue updatedVenue = venueRepository.findById(venue.getId()).get();
        // Disconnect from session so that the updates on updatedVenue are not directly saved in db
        em.detach(updatedVenue);
        updatedVenue
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .imageUrl(UPDATED_IMAGE_URL)
            .phone(UPDATED_PHONE)
            .url(UPDATED_URL)
            .googleMaps(UPDATED_GOOGLE_MAPS)
            .description(UPDATED_DESCRIPTION);
        VenueDTO venueDTO = venueMapper.toDto(updatedVenue);

        restVenueMockMvc.perform(put("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isOk());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVenue.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testVenue.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testVenue.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testVenue.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testVenue.getGoogleMaps()).isEqualTo(UPDATED_GOOGLE_MAPS);
        assertThat(testVenue.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVenueMockMvc.perform(put("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVenue() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        int databaseSizeBeforeDelete = venueRepository.findAll().size();

        // Get the venue
        restVenueMockMvc.perform(delete("/api/venues/{id}", venue.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Venue.class);
        Venue venue1 = new Venue();
        venue1.setId(1L);
        Venue venue2 = new Venue();
        venue2.setId(venue1.getId());
        assertThat(venue1).isEqualTo(venue2);
        venue2.setId(2L);
        assertThat(venue1).isNotEqualTo(venue2);
        venue1.setId(null);
        assertThat(venue1).isNotEqualTo(venue2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VenueDTO.class);
        VenueDTO venueDTO1 = new VenueDTO();
        venueDTO1.setId(1L);
        VenueDTO venueDTO2 = new VenueDTO();
        assertThat(venueDTO1).isNotEqualTo(venueDTO2);
        venueDTO2.setId(venueDTO1.getId());
        assertThat(venueDTO1).isEqualTo(venueDTO2);
        venueDTO2.setId(2L);
        assertThat(venueDTO1).isNotEqualTo(venueDTO2);
        venueDTO1.setId(null);
        assertThat(venueDTO1).isNotEqualTo(venueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(venueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(venueMapper.fromId(null)).isNull();
    }
}
