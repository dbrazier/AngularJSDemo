package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.repository.InquiryInfoRepository;
import com.bss.mosaic.service.InquiryInfoService;
import com.bss.mosaic.domain.InquiryInfo;

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
 * Test class for the InquiryInfoResource REST controller.
 *
 * @see InquiryInfoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class InquiryInfoResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final String DEFAULT_CASE_ID = "AAAAA";
    private static final String UPDATED_CASE_ID = "BBBBB";

    private static final Long DEFAULT_GFC_ID = 1L;
    private static final Long UPDATED_GFC_ID = 2L;
    private static final String DEFAULT_INQUIRY_TYPE_ID = "AAAAA";
    private static final String UPDATED_INQUIRY_TYPE_ID = "BBBBB";
    private static final String DEFAULT_CASE_OWNER = "AAAAA";
    private static final String UPDATED_CASE_OWNER = "BBBBB";
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    private static final ZonedDateTime DEFAULT_LAST_INTERACTION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_INTERACTION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_INTERACTION_STR = dateTimeFormatter.format(DEFAULT_LAST_INTERACTION);

    private static final Integer DEFAULT_SLA = 1;
    private static final Integer UPDATED_SLA = 2;

    private static final ZonedDateTime DEFAULT_CASE_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CASE_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CASE_START_DATE_STR = dateTimeFormatter.format(DEFAULT_CASE_START_DATE);
    private static final String DEFAULT_URGENCY = "AAAAA";
    private static final String UPDATED_URGENCY = "BBBBB";
    private static final String DEFAULT_NOTES = "AAAAA";
    private static final String UPDATED_NOTES = "BBBBB";

    private static final Long DEFAULT_ACCOUNT_NO = 1L;
    private static final Long UPDATED_ACCOUNT_NO = 2L;

    @Inject
    private InquiryInfoRepository inquiryInfoRepository;

    @Inject
    private InquiryInfoService inquiryInfoService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInquiryInfoMockMvc;

    private InquiryInfo inquiryInfo;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InquiryInfoResource inquiryInfoResource = new InquiryInfoResource();
        ReflectionTestUtils.setField(inquiryInfoResource, "inquiryInfoService", inquiryInfoService);
        this.restInquiryInfoMockMvc = MockMvcBuilders.standaloneSetup(inquiryInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        inquiryInfo = new InquiryInfo();
        inquiryInfo.setCaseId(DEFAULT_CASE_ID);
        inquiryInfo.setGfcId(DEFAULT_GFC_ID);
        inquiryInfo.setInquiryTypeId(DEFAULT_INQUIRY_TYPE_ID);
        inquiryInfo.setCaseOwner(DEFAULT_CASE_OWNER);
        inquiryInfo.setStatus(DEFAULT_STATUS);
        inquiryInfo.setLastInteraction(DEFAULT_LAST_INTERACTION);
        inquiryInfo.setSla(DEFAULT_SLA);
        inquiryInfo.setCaseStartDate(DEFAULT_CASE_START_DATE);
        inquiryInfo.setUrgency(DEFAULT_URGENCY);
        inquiryInfo.setNotes(DEFAULT_NOTES);
        inquiryInfo.setAccountNo(DEFAULT_ACCOUNT_NO);
    }

    @Test
    @Transactional
    public void createInquiryInfo() throws Exception {
        int databaseSizeBeforeCreate = inquiryInfoRepository.findAll().size();

        // Create the InquiryInfo

        restInquiryInfoMockMvc.perform(post("/api/inquiry-infos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(inquiryInfo)))
                .andExpect(status().isCreated());

        // Validate the InquiryInfo in the database
        List<InquiryInfo> inquiryInfos = inquiryInfoRepository.findAll();
        assertThat(inquiryInfos).hasSize(databaseSizeBeforeCreate + 1);
        InquiryInfo testInquiryInfo = inquiryInfos.get(inquiryInfos.size() - 1);
        assertThat(testInquiryInfo.getCaseId()).isEqualTo(DEFAULT_CASE_ID);
        assertThat(testInquiryInfo.getGfcId()).isEqualTo(DEFAULT_GFC_ID);
        assertThat(testInquiryInfo.getInquiryTypeId()).isEqualTo(DEFAULT_INQUIRY_TYPE_ID);
        assertThat(testInquiryInfo.getCaseOwner()).isEqualTo(DEFAULT_CASE_OWNER);
        assertThat(testInquiryInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInquiryInfo.getLastInteraction()).isEqualTo(DEFAULT_LAST_INTERACTION);
        assertThat(testInquiryInfo.getSla()).isEqualTo(DEFAULT_SLA);
        assertThat(testInquiryInfo.getCaseStartDate()).isEqualTo(DEFAULT_CASE_START_DATE);
        assertThat(testInquiryInfo.getUrgency()).isEqualTo(DEFAULT_URGENCY);
        assertThat(testInquiryInfo.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testInquiryInfo.getAccountNo()).isEqualTo(DEFAULT_ACCOUNT_NO);
    }

    @Test
    @Transactional
    public void getAllInquiryInfos() throws Exception {
        // Initialize the database
        inquiryInfoRepository.saveAndFlush(inquiryInfo);

        // Get all the inquiryInfos
        restInquiryInfoMockMvc.perform(get("/api/inquiry-infos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(inquiryInfo.getId().intValue())))
                .andExpect(jsonPath("$.[*].caseId").value(hasItem(DEFAULT_CASE_ID.toString())))
                .andExpect(jsonPath("$.[*].gfcId").value(hasItem(DEFAULT_GFC_ID.intValue())))
                .andExpect(jsonPath("$.[*].inquiryTypeId").value(hasItem(DEFAULT_INQUIRY_TYPE_ID.toString())))
                .andExpect(jsonPath("$.[*].caseOwner").value(hasItem(DEFAULT_CASE_OWNER.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].lastInteraction").value(hasItem(DEFAULT_LAST_INTERACTION_STR)))
                .andExpect(jsonPath("$.[*].sla").value(hasItem(DEFAULT_SLA)))
                .andExpect(jsonPath("$.[*].caseStartDate").value(hasItem(DEFAULT_CASE_START_DATE_STR)))
                .andExpect(jsonPath("$.[*].urgency").value(hasItem(DEFAULT_URGENCY.toString())))
                .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
                .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO.intValue())));
    }

    @Test
    @Transactional
    public void getInquiryInfo() throws Exception {
        // Initialize the database
        inquiryInfoRepository.saveAndFlush(inquiryInfo);

        // Get the inquiryInfo
        restInquiryInfoMockMvc.perform(get("/api/inquiry-infos/{id}", inquiryInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(inquiryInfo.getId().intValue()))
            .andExpect(jsonPath("$.caseId").value(DEFAULT_CASE_ID.toString()))
            .andExpect(jsonPath("$.gfcId").value(DEFAULT_GFC_ID.intValue()))
            .andExpect(jsonPath("$.inquiryTypeId").value(DEFAULT_INQUIRY_TYPE_ID.toString()))
            .andExpect(jsonPath("$.caseOwner").value(DEFAULT_CASE_OWNER.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.lastInteraction").value(DEFAULT_LAST_INTERACTION_STR))
            .andExpect(jsonPath("$.sla").value(DEFAULT_SLA))
            .andExpect(jsonPath("$.caseStartDate").value(DEFAULT_CASE_START_DATE_STR))
            .andExpect(jsonPath("$.urgency").value(DEFAULT_URGENCY.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.accountNo").value(DEFAULT_ACCOUNT_NO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInquiryInfo() throws Exception {
        // Get the inquiryInfo
        restInquiryInfoMockMvc.perform(get("/api/inquiry-infos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInquiryInfo() throws Exception {
        // Initialize the database
        inquiryInfoService.save(inquiryInfo);

        int databaseSizeBeforeUpdate = inquiryInfoRepository.findAll().size();

        // Update the inquiryInfo
        InquiryInfo updatedInquiryInfo = new InquiryInfo();
        updatedInquiryInfo.setId(inquiryInfo.getId());
        updatedInquiryInfo.setCaseId(UPDATED_CASE_ID);
        updatedInquiryInfo.setGfcId(UPDATED_GFC_ID);
        updatedInquiryInfo.setInquiryTypeId(UPDATED_INQUIRY_TYPE_ID);
        updatedInquiryInfo.setCaseOwner(UPDATED_CASE_OWNER);
        updatedInquiryInfo.setStatus(UPDATED_STATUS);
        updatedInquiryInfo.setLastInteraction(UPDATED_LAST_INTERACTION);
        updatedInquiryInfo.setSla(UPDATED_SLA);
        updatedInquiryInfo.setCaseStartDate(UPDATED_CASE_START_DATE);
        updatedInquiryInfo.setUrgency(UPDATED_URGENCY);
        updatedInquiryInfo.setNotes(UPDATED_NOTES);
        updatedInquiryInfo.setAccountNo(UPDATED_ACCOUNT_NO);

        restInquiryInfoMockMvc.perform(put("/api/inquiry-infos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedInquiryInfo)))
                .andExpect(status().isOk());

        // Validate the InquiryInfo in the database
        List<InquiryInfo> inquiryInfos = inquiryInfoRepository.findAll();
        assertThat(inquiryInfos).hasSize(databaseSizeBeforeUpdate);
        InquiryInfo testInquiryInfo = inquiryInfos.get(inquiryInfos.size() - 1);
        assertThat(testInquiryInfo.getCaseId()).isEqualTo(UPDATED_CASE_ID);
        assertThat(testInquiryInfo.getGfcId()).isEqualTo(UPDATED_GFC_ID);
        assertThat(testInquiryInfo.getInquiryTypeId()).isEqualTo(UPDATED_INQUIRY_TYPE_ID);
        assertThat(testInquiryInfo.getCaseOwner()).isEqualTo(UPDATED_CASE_OWNER);
        assertThat(testInquiryInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInquiryInfo.getLastInteraction()).isEqualTo(UPDATED_LAST_INTERACTION);
        assertThat(testInquiryInfo.getSla()).isEqualTo(UPDATED_SLA);
        assertThat(testInquiryInfo.getCaseStartDate()).isEqualTo(UPDATED_CASE_START_DATE);
        assertThat(testInquiryInfo.getUrgency()).isEqualTo(UPDATED_URGENCY);
        assertThat(testInquiryInfo.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testInquiryInfo.getAccountNo()).isEqualTo(UPDATED_ACCOUNT_NO);
    }

    @Test
    @Transactional
    public void deleteInquiryInfo() throws Exception {
        // Initialize the database
        inquiryInfoService.save(inquiryInfo);

        int databaseSizeBeforeDelete = inquiryInfoRepository.findAll().size();

        // Get the inquiryInfo
        restInquiryInfoMockMvc.perform(delete("/api/inquiry-infos/{id}", inquiryInfo.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<InquiryInfo> inquiryInfos = inquiryInfoRepository.findAll();
        assertThat(inquiryInfos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
