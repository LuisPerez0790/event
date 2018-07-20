package com.gob.scjn.web.rest;

import com.gob.scjn.EventApp;

import com.gob.scjn.domain.EventUser;
import com.gob.scjn.repository.EventUserRepository;
import com.gob.scjn.service.EventUserService;
import com.gob.scjn.service.dto.EventUserDTO;
import com.gob.scjn.service.mapper.EventUserMapper;
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

import com.gob.scjn.domain.enumeration.UserType;
/**
 * Test class for the EventUserResource REST controller.
 *
 * @see EventUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApp.class)
public class EventUserResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final UserType DEFAULT_USER_TYPE = UserType.EXPOSITOR;
    private static final UserType UPDATED_USER_TYPE = UserType.PARTICIPANT;

    @Autowired
    private EventUserRepository eventUserRepository;


    @Autowired
    private EventUserMapper eventUserMapper;
    

    @Autowired
    private EventUserService eventUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEventUserMockMvc;

    private EventUser eventUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventUserResource eventUserResource = new EventUserResource(eventUserService);
        this.restEventUserMockMvc = MockMvcBuilders.standaloneSetup(eventUserResource)
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
    public static EventUser createEntity(EntityManager em) {
        EventUser eventUser = new EventUser()
            .name(DEFAULT_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .userName(DEFAULT_USER_NAME)
            .institution(DEFAULT_INSTITUTION)
            .position(DEFAULT_POSITION)
            .email(DEFAULT_EMAIL)
            .description(DEFAULT_DESCRIPTION)
            .imageUrl(DEFAULT_IMAGE_URL)
            .userType(DEFAULT_USER_TYPE);
        return eventUser;
    }

    @Before
    public void initTest() {
        eventUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventUser() throws Exception {
        int databaseSizeBeforeCreate = eventUserRepository.findAll().size();

        // Create the EventUser
        EventUserDTO eventUserDTO = eventUserMapper.toDto(eventUser);
        restEventUserMockMvc.perform(post("/api/event-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventUserDTO)))
            .andExpect(status().isCreated());

        // Validate the EventUser in the database
        List<EventUser> eventUserList = eventUserRepository.findAll();
        assertThat(eventUserList).hasSize(databaseSizeBeforeCreate + 1);
        EventUser testEventUser = eventUserList.get(eventUserList.size() - 1);
        assertThat(testEventUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEventUser.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testEventUser.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testEventUser.getInstitution()).isEqualTo(DEFAULT_INSTITUTION);
        assertThat(testEventUser.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testEventUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEventUser.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEventUser.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testEventUser.getUserType()).isEqualTo(DEFAULT_USER_TYPE);
    }

    @Test
    @Transactional
    public void createEventUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventUserRepository.findAll().size();

        // Create the EventUser with an existing ID
        eventUser.setId(1L);
        EventUserDTO eventUserDTO = eventUserMapper.toDto(eventUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventUserMockMvc.perform(post("/api/event-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventUser in the database
        List<EventUser> eventUserList = eventUserRepository.findAll();
        assertThat(eventUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEventUsers() throws Exception {
        // Initialize the database
        eventUserRepository.saveAndFlush(eventUser);

        // Get all the eventUserList
        restEventUserMockMvc.perform(get("/api/event-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].institution").value(hasItem(DEFAULT_INSTITUTION.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].userType").value(hasItem(DEFAULT_USER_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getEventUser() throws Exception {
        // Initialize the database
        eventUserRepository.saveAndFlush(eventUser);

        // Get the eventUser
        restEventUserMockMvc.perform(get("/api/event-users/{id}", eventUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventUser.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.institution").value(DEFAULT_INSTITUTION.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.userType").value(DEFAULT_USER_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEventUser() throws Exception {
        // Get the eventUser
        restEventUserMockMvc.perform(get("/api/event-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventUser() throws Exception {
        // Initialize the database
        eventUserRepository.saveAndFlush(eventUser);

        int databaseSizeBeforeUpdate = eventUserRepository.findAll().size();

        // Update the eventUser
        EventUser updatedEventUser = eventUserRepository.findById(eventUser.getId()).get();
        // Disconnect from session so that the updates on updatedEventUser are not directly saved in db
        em.detach(updatedEventUser);
        updatedEventUser
            .name(UPDATED_NAME)
            .lastName(UPDATED_LAST_NAME)
            .userName(UPDATED_USER_NAME)
            .institution(UPDATED_INSTITUTION)
            .position(UPDATED_POSITION)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .imageUrl(UPDATED_IMAGE_URL)
            .userType(UPDATED_USER_TYPE);
        EventUserDTO eventUserDTO = eventUserMapper.toDto(updatedEventUser);

        restEventUserMockMvc.perform(put("/api/event-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventUserDTO)))
            .andExpect(status().isOk());

        // Validate the EventUser in the database
        List<EventUser> eventUserList = eventUserRepository.findAll();
        assertThat(eventUserList).hasSize(databaseSizeBeforeUpdate);
        EventUser testEventUser = eventUserList.get(eventUserList.size() - 1);
        assertThat(testEventUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEventUser.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testEventUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testEventUser.getInstitution()).isEqualTo(UPDATED_INSTITUTION);
        assertThat(testEventUser.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testEventUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEventUser.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEventUser.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testEventUser.getUserType()).isEqualTo(UPDATED_USER_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingEventUser() throws Exception {
        int databaseSizeBeforeUpdate = eventUserRepository.findAll().size();

        // Create the EventUser
        EventUserDTO eventUserDTO = eventUserMapper.toDto(eventUser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEventUserMockMvc.perform(put("/api/event-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventUser in the database
        List<EventUser> eventUserList = eventUserRepository.findAll();
        assertThat(eventUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventUser() throws Exception {
        // Initialize the database
        eventUserRepository.saveAndFlush(eventUser);

        int databaseSizeBeforeDelete = eventUserRepository.findAll().size();

        // Get the eventUser
        restEventUserMockMvc.perform(delete("/api/event-users/{id}", eventUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventUser> eventUserList = eventUserRepository.findAll();
        assertThat(eventUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventUser.class);
        EventUser eventUser1 = new EventUser();
        eventUser1.setId(1L);
        EventUser eventUser2 = new EventUser();
        eventUser2.setId(eventUser1.getId());
        assertThat(eventUser1).isEqualTo(eventUser2);
        eventUser2.setId(2L);
        assertThat(eventUser1).isNotEqualTo(eventUser2);
        eventUser1.setId(null);
        assertThat(eventUser1).isNotEqualTo(eventUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventUserDTO.class);
        EventUserDTO eventUserDTO1 = new EventUserDTO();
        eventUserDTO1.setId(1L);
        EventUserDTO eventUserDTO2 = new EventUserDTO();
        assertThat(eventUserDTO1).isNotEqualTo(eventUserDTO2);
        eventUserDTO2.setId(eventUserDTO1.getId());
        assertThat(eventUserDTO1).isEqualTo(eventUserDTO2);
        eventUserDTO2.setId(2L);
        assertThat(eventUserDTO1).isNotEqualTo(eventUserDTO2);
        eventUserDTO1.setId(null);
        assertThat(eventUserDTO1).isNotEqualTo(eventUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eventUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eventUserMapper.fromId(null)).isNull();
    }
}
