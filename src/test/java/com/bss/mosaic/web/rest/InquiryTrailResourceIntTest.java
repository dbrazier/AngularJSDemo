package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.InquiryTrail;
import com.bss.mosaic.service.InquiryTrailService;
import com.bss.mosaic.repository.InquiryTrailRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the InquiryTrailResource REST controller.
 *
 * @see InquiryTrailResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class InquiryTrailResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final String DEFAULT_CASE_ID = "AAAAA";
    private static final String UPDATED_CASE_ID = "BBBBB";
    private static final String DEFAULT_CSO_NAME = "AAAAA";
    private static final String UPDATED_CSO_NAME = "BBBBB";
    private static final String DEFAULT_CSO_EMAIL = "AAAAA";
    private static final String UPDATED_CSO_EMAIL = "BBBBB";
    private static final String DEFAULT_CLIENT_NAME = "AAAAA";
    private static final String UPDATED_CLIENT_NAME = "BBBBB";
    private static final String DEFAULT_CLIENT_EMAIL = "AAAAA";
    private static final String UPDATED_CLIENT_EMAIL = "BBBBB";
    private static final String DEFAULT_CLIENT_CONTACT_NO = "AAAAA";
    private static final String UPDATED_CLIENT_CONTACT_NO = "BBBBB";
    private static final String DEFAULT_CLIENT_CONTACT_TYPE = "AAAAA";
    private static final String UPDATED_CLIENT_CONTACT_TYPE = "BBBBB";

    private static final ZonedDateTime DEFAULT_INQUIRY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_INQUIRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_INQUIRY_DATE_STR = dateTimeFormatter.format(DEFAULT_INQUIRY_DATE);

    private static final ZonedDateTime DEFAULT_RESPONSE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_RESPONSE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_RESPONSE_DATE_STR = dateTimeFormatter.format(DEFAULT_RESPONSE_DATE);
    private static final String DEFAULT_NOTES = "AAAAA";
    private static final String UPDATED_NOTES = "BBBBB";
    private static final String DEFAULT_CSO_CONTACT_TYPE = "AAAAA";
    private static final String UPDATED_CSO_CONTACT_TYPE = "BBBBB";
    private static final String DEFAULT_ATTACHMENT = "AAAAA";
    private static final String UPDATED_ATTACHMENT = "BBBBB";

    @Inject
    private InquiryTrailRepository inquiryTrailRepository;

    @Inject
    private InquiryTrailService inquiryTrailService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInquiryTrailMockMvc;

    private InquiryTrail inquiryTrail;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InquiryTrailResource inquiryTrailResource = new InquiryTrailResource();
        ReflectionTestUtils.setField(inquiryTrailResource, "inquiryTrailService", inquiryTrailService);
        this.restInquiryTrailMockMvc = MockMvcBuilders.standaloneSetup(inquiryTrailResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        inquiryTrail = new InquiryTrail();
        inquiryTrail.setCaseId(DEFAULT_CASE_ID);
        inquiryTrail.setCsoName(DEFAULT_CSO_NAME);
        inquiryTrail.setCsoEmail(DEFAULT_CSO_EMAIL);
        inquiryTrail.setClientName(DEFAULT_CLIENT_NAME);
        inquiryTrail.setClientEmail(DEFAULT_CLIENT_EMAIL);
        inquiryTrail.setClientContactNo(DEFAULT_CLIENT_CONTACT_NO);
        inquiryTrail.setClientContactType(DEFAULT_CLIENT_CONTACT_TYPE);
        inquiryTrail.setInquiryDate(DEFAULT_INQUIRY_DATE);
        inquiryTrail.setResponseDate(DEFAULT_RESPONSE_DATE);
        inquiryTrail.setNotes(DEFAULT_NOTES);
        inquiryTrail.setCsoContactType(DEFAULT_CSO_CONTACT_TYPE);
        inquiryTrail.setAttachment(DEFAULT_ATTACHMENT);
    }

    @Test
    @Transactional
    public void createInquiryTrail() throws Exception {
        int databaseSizeBeforeCreate = inquiryTrailRepository.findAll().size();

        // Create the InquiryTrail

        restInquiryTrailMockMvc.perform(post("/api/inquiry-trails")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(inquiryTrail)))
                .andExpect(status().isCreated());

        // Validate the InquiryTrail in the database
        List<InquiryTrail> inquiryTrails = inquiryTrailRepository.findAll();
        assertThat(inquiryTrails).hasSize(databaseSizeBeforeCreate + 1);
        InquiryTrail testInquiryTrail = inquiryTrails.get(inquiryTrails.size() - 1);
        assertThat(testInquiryTrail.getCaseId()).isEqualTo(DEFAULT_CASE_ID);
        assertThat(testInquiryTrail.getCsoName()).isEqualTo(DEFAULT_CSO_NAME);
        assertThat(testInquiryTrail.getCsoEmail()).isEqualTo(DEFAULT_CSO_EMAIL);
        assertThat(testInquiryTrail.getClientName()).isEqualTo(DEFAULT_CLIENT_NAME);
        assertThat(testInquiryTrail.getClientEmail()).isEqualTo(DEFAULT_CLIENT_EMAIL);
        assertThat(testInquiryTrail.getClientContactNo()).isEqualTo(DEFAULT_CLIENT_CONTACT_NO);
        assertThat(testInquiryTrail.getClientContactType()).isEqualTo(DEFAULT_CLIENT_CONTACT_TYPE);
        assertThat(testInquiryTrail.getInquiryDate()).isEqualTo(DEFAULT_INQUIRY_DATE);
        assertThat(testInquiryTrail.getResponseDate()).isEqualTo(DEFAULT_RESPONSE_DATE);
        assertThat(testInquiryTrail.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testInquiryTrail.getCsoContactType()).isEqualTo(DEFAULT_CSO_CONTACT_TYPE);
        assertThat(testInquiryTrail.getAttachment()).isEqualTo(DEFAULT_ATTACHMENT);
    }

    @Test
    @Transactional
    public void getAllInquiryTrails() throws Exception {
        // Initialize the database
        inquiryTrailRepository.saveAndFlush(inquiryTrail);

        // Get all the inquiryTrails
        restInquiryTrailMockMvc.perform(get("/api/inquiry-trails?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(inquiryTrail.getId().intValue())))
                .andExpect(jsonPath("$.[*].caseId").value(hasItem(DEFAULT_CASE_ID.toString())))
                .andExpect(jsonPath("$.[*].csoName").value(hasItem(DEFAULT_CSO_NAME.toString())))
                .andExpect(jsonPath("$.[*].csoEmail").value(hasItem(DEFAULT_CSO_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].clientName").value(hasItem(DEFAULT_CLIENT_NAME.toString())))
                .andExpect(jsonPath("$.[*].clientEmail").value(hasItem(DEFAULT_CLIENT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].clientContactNo").value(hasItem(DEFAULT_CLIENT_CONTACT_NO.toString())))
                .andExpect(jsonPath("$.[*].clientContactType").value(hasItem(DEFAULT_CLIENT_CONTACT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].inquiryDate").value(hasItem(DEFAULT_INQUIRY_DATE_STR)))
                .andExpect(jsonPath("$.[*].responseDate").value(hasItem(DEFAULT_RESPONSE_DATE_STR)))
                .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
                .andExpect(jsonPath("$.[*].csoContactType").value(hasItem(DEFAULT_CSO_CONTACT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].attachment").value(hasItem(DEFAULT_ATTACHMENT.toString())));
    }

    @Test
    @Transactional
    public void getInquiryTrail() throws Exception {
        // Initialize the database
        inquiryTrailRepository.saveAndFlush(inquiryTrail);

        // Get the inquiryTrail
        restInquiryTrailMockMvc.perform(get("/api/inquiry-trails/{id}", inquiryTrail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(inquiryTrail.getId().intValue()))
            .andExpect(jsonPath("$.caseId").value(DEFAULT_CASE_ID.toString()))
            .andExpect(jsonPath("$.csoName").value(DEFAULT_CSO_NAME.toString()))
            .andExpect(jsonPath("$.csoEmail").value(DEFAULT_CSO_EMAIL.toString()))
            .andExpect(jsonPath("$.clientName").value(DEFAULT_CLIENT_NAME.toString()))
            .andExpect(jsonPath("$.clientEmail").value(DEFAULT_CLIENT_EMAIL.toString()))
            .andExpect(jsonPath("$.clientContactNo").value(DEFAULT_CLIENT_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.clientContactType").value(DEFAULT_CLIENT_CONTACT_TYPE.toString()))
            .andExpect(jsonPath("$.inquiryDate").value(DEFAULT_INQUIRY_DATE_STR))
            .andExpect(jsonPath("$.responseDate").value(DEFAULT_RESPONSE_DATE_STR))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.csoContactType").value(DEFAULT_CSO_CONTACT_TYPE.toString()))
            .andExpect(jsonPath("$.attachment").value(DEFAULT_ATTACHMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInquiryTrail() throws Exception {
        // Get the inquiryTrail
        restInquiryTrailMockMvc.perform(get("/api/inquiry-trails/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInquiryTrail() throws Exception {
        // Initialize the database
        inquiryTrailService.save(inquiryTrail);

        int databaseSizeBeforeUpdate = inquiryTrailRepository.findAll().size();

        // Update the inquiryTrail
        InquiryTrail updatedInquiryTrail = new InquiryTrail();
        updatedInquiryTrail.setId(inquiryTrail.getId());
        updatedInquiryTrail.setCaseId(UPDATED_CASE_ID);
        updatedInquiryTrail.setCsoName(UPDATED_CSO_NAME);
        updatedInquiryTrail.setCsoEmail(UPDATED_CSO_EMAIL);
        updatedInquiryTrail.setClientName(UPDATED_CLIENT_NAME);
        updatedInquiryTrail.setClientEmail(UPDATED_CLIENT_EMAIL);
        updatedInquiryTrail.setClientContactNo(UPDATED_CLIENT_CONTACT_NO);
        updatedInquiryTrail.setClientContactType(UPDATED_CLIENT_CONTACT_TYPE);
        updatedInquiryTrail.setInquiryDate(UPDATED_INQUIRY_DATE);
        updatedInquiryTrail.setResponseDate(UPDATED_RESPONSE_DATE);
        updatedInquiryTrail.setNotes(UPDATED_NOTES);
        updatedInquiryTrail.setCsoContactType(UPDATED_CSO_CONTACT_TYPE);
        updatedInquiryTrail.setAttachment(UPDATED_ATTACHMENT);

        restInquiryTrailMockMvc.perform(put("/api/inquiry-trails")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedInquiryTrail)))
                .andExpect(status().isOk());

        // Validate the InquiryTrail in the database
        List<InquiryTrail> inquiryTrails = inquiryTrailRepository.findAll();
        assertThat(inquiryTrails).hasSize(databaseSizeBeforeUpdate);
        InquiryTrail testInquiryTrail = inquiryTrails.get(inquiryTrails.size() - 1);
        assertThat(testInquiryTrail.getCaseId()).isEqualTo(UPDATED_CASE_ID);
        assertThat(testInquiryTrail.getCsoName()).isEqualTo(UPDATED_CSO_NAME);
        assertThat(testInquiryTrail.getCsoEmail()).isEqualTo(UPDATED_CSO_EMAIL);
        assertThat(testInquiryTrail.getClientName()).isEqualTo(UPDATED_CLIENT_NAME);
        assertThat(testInquiryTrail.getClientEmail()).isEqualTo(UPDATED_CLIENT_EMAIL);
        assertThat(testInquiryTrail.getClientContactNo()).isEqualTo(UPDATED_CLIENT_CONTACT_NO);
        assertThat(testInquiryTrail.getClientContactType()).isEqualTo(UPDATED_CLIENT_CONTACT_TYPE);
        assertThat(testInquiryTrail.getInquiryDate()).isEqualTo(UPDATED_INQUIRY_DATE);
        assertThat(testInquiryTrail.getResponseDate()).isEqualTo(UPDATED_RESPONSE_DATE);
        assertThat(testInquiryTrail.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testInquiryTrail.getCsoContactType()).isEqualTo(UPDATED_CSO_CONTACT_TYPE);
        assertThat(testInquiryTrail.getAttachment()).isEqualTo(UPDATED_ATTACHMENT);
    }

    @Test
    @Transactional
    public void deleteInquiryTrail() throws Exception {
        // Initialize the database
        inquiryTrailService.save(inquiryTrail);

        int databaseSizeBeforeDelete = inquiryTrailRepository.findAll().size();

        // Get the inquiryTrail
        restInquiryTrailMockMvc.perform(delete("/api/inquiry-trails/{id}", inquiryTrail.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<InquiryTrail> inquiryTrails = inquiryTrailRepository.findAll();
        assertThat(inquiryTrails).hasSize(databaseSizeBeforeDelete - 1);
    }
}
