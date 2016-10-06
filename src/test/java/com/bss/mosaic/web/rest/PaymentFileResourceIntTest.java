package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.PaymentFile;
import com.bss.mosaic.repository.PaymentFileRepository;
import com.bss.mosaic.service.PaymentFileService;

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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PaymentFileResource REST controller.
 *
 * @see PaymentFileResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class PaymentFileResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final Long DEFAULT_GFC_ID = 1L;
    private static final Long UPDATED_GFC_ID = 2L;

    private static final Long DEFAULT_FILE_ID = 1L;
    private static final Long UPDATED_FILE_ID = 2L;

    private static final Long DEFAULT_PROFILE_ID = 1L;
    private static final Long UPDATED_PROFILE_ID = 2L;
    private static final String DEFAULT_FILE_NAME = "AAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBB";

    private static final ZonedDateTime DEFAULT_FILE_START = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_FILE_START = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_FILE_START_STR = dateTimeFormatter.format(DEFAULT_FILE_START);

    private static final String DEFAULT_GKS_END = "AAAAA";
    private static final String UPDATED_GKS_END = "BBBBB";
    private static final String DEFAULT_LAST_STEP = "AAAAA";
    private static final String UPDATED_LAST_STEP = "BBBBB";

    private static final Long DEFAULT_NO_TXN = 1L;
    private static final Long UPDATED_NO_TXN = 2L;
    private static final String DEFAULT_FAILED = "AAAAA";
    private static final String UPDATED_FAILED = "BBBBB";
    private static final String DEFAULT_FILE_STATUS = "AAAAA";
    private static final String UPDATED_FILE_STATUS = "BBBBB";
    private static final String DEFAULT_FILE_PROFILE = "AAAAA";
    private static final String UPDATED_FILE_PROFILE = "BBBBB";
    private static final String DEFAULT_SOURCE_SYSTEM = "AAAAA";
    private static final String UPDATED_SOURCE_SYSTEM = "BBBBB";

    @Inject
    private PaymentFileRepository paymentFileRepository;

    @Inject
    private PaymentFileService paymentFileService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPaymentFileMockMvc;

    private PaymentFile paymentFile;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PaymentFileResource paymentFileResource = new PaymentFileResource();
        ReflectionTestUtils.setField(paymentFileResource, "paymentFileService", paymentFileService);
        this.restPaymentFileMockMvc = MockMvcBuilders.standaloneSetup(paymentFileResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        paymentFile = new PaymentFile();
        paymentFile.setGfcId(DEFAULT_GFC_ID);
        paymentFile.setFileId(DEFAULT_FILE_ID);
        paymentFile.setProfileId(DEFAULT_PROFILE_ID);
        paymentFile.setFileName(DEFAULT_FILE_NAME);
        paymentFile.setFileStart(DEFAULT_FILE_START);
        paymentFile.setGksEnd(DEFAULT_GKS_END);
        paymentFile.setLastStep(DEFAULT_LAST_STEP);
        paymentFile.setNoTxns(DEFAULT_NO_TXN);
        paymentFile.setFailedTxns(DEFAULT_FAILED);
        paymentFile.setFileStatus(DEFAULT_FILE_STATUS);
        paymentFile.setFileProfile(DEFAULT_FILE_PROFILE);
        paymentFile.setSourceSystem(DEFAULT_SOURCE_SYSTEM);
    }

    @Test
    @Transactional
    public void createPaymentFile() throws Exception {
        int databaseSizeBeforeCreate = paymentFileRepository.findAll().size();

        // Create the PaymentFile

        restPaymentFileMockMvc.perform(post("/api/payment-files")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paymentFile)))
                .andExpect(status().isCreated());

        // Validate the PaymentFile in the database
        List<PaymentFile> paymentFiles = paymentFileRepository.findAll();
        assertThat(paymentFiles).hasSize(databaseSizeBeforeCreate + 1);
        PaymentFile testPaymentFile = paymentFiles.get(paymentFiles.size() - 1);
        assertThat(testPaymentFile.getGfcId()).isEqualTo(DEFAULT_GFC_ID);
        assertThat(testPaymentFile.getFileId()).isEqualTo(DEFAULT_FILE_ID);
        assertThat(testPaymentFile.getProfileId()).isEqualTo(DEFAULT_PROFILE_ID);
        assertThat(testPaymentFile.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testPaymentFile.getFileStart()).isEqualTo(DEFAULT_FILE_START);
        assertThat(testPaymentFile.getGksEnd()).isEqualTo(DEFAULT_GKS_END);
        assertThat(testPaymentFile.getLastStep()).isEqualTo(DEFAULT_LAST_STEP);
        assertThat(testPaymentFile.getNoTxns()).isEqualTo(DEFAULT_NO_TXN);
        assertThat(testPaymentFile.getFailedTxns()).isEqualTo(DEFAULT_FAILED);
        assertThat(testPaymentFile.getFileStatus()).isEqualTo(DEFAULT_FILE_STATUS);
        assertThat(testPaymentFile.getFileProfile()).isEqualTo(DEFAULT_FILE_PROFILE);
        assertThat(testPaymentFile.getSourceSystem()).isEqualTo(DEFAULT_SOURCE_SYSTEM);
    }

    @Test
    @Transactional
    public void getAllPaymentFiles() throws Exception {
        // Initialize the database
        paymentFileRepository.saveAndFlush(paymentFile);

        // Get all the paymentFiles
        restPaymentFileMockMvc.perform(get("/api/payment-files?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(paymentFile.getId().intValue())))
                .andExpect(jsonPath("$.[*].gfcId").value(hasItem(DEFAULT_GFC_ID.intValue())))
                .andExpect(jsonPath("$.[*].fileId").value(hasItem(DEFAULT_FILE_ID.intValue())))
                .andExpect(jsonPath("$.[*].profileId").value(hasItem(DEFAULT_PROFILE_ID.intValue())))
                .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
                .andExpect(jsonPath("$.[*].fileStart").value(hasItem(DEFAULT_FILE_START_STR)))
                .andExpect(jsonPath("$.[*].gksEnd").value(hasItem(DEFAULT_GKS_END.toString())))
                .andExpect(jsonPath("$.[*].lastStep").value(hasItem(DEFAULT_LAST_STEP.toString())))
                .andExpect(jsonPath("$.[*].noTxns").value(hasItem(DEFAULT_NO_TXN.intValue())))
                .andExpect(jsonPath("$.[*].failedTxns").value(hasItem(DEFAULT_FAILED.toString())))
                .andExpect(jsonPath("$.[*].fileStatus").value(hasItem(DEFAULT_FILE_STATUS.toString())))
                .andExpect(jsonPath("$.[*].fileProfile").value(hasItem(DEFAULT_FILE_PROFILE.toString())))
                .andExpect(jsonPath("$.[*].sourceSystem").value(hasItem(DEFAULT_SOURCE_SYSTEM.toString())));
    }

    @Test
    @Transactional
    public void getPaymentFile() throws Exception {
        // Initialize the database
        paymentFileRepository.saveAndFlush(paymentFile);

        // Get the paymentFile
        restPaymentFileMockMvc.perform(get("/api/payment-files/{id}", paymentFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(paymentFile.getId().intValue()))
            .andExpect(jsonPath("$.gfcId").value(DEFAULT_GFC_ID.intValue()))
            .andExpect(jsonPath("$.fileId").value(DEFAULT_FILE_ID.intValue()))
            .andExpect(jsonPath("$.profileId").value(DEFAULT_PROFILE_ID.intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.fileStart").value(DEFAULT_FILE_START_STR))
            .andExpect(jsonPath("$.gksEnd").value(DEFAULT_GKS_END.toString()))
            .andExpect(jsonPath("$.lastStep").value(DEFAULT_LAST_STEP.toString()))
            .andExpect(jsonPath("$.noTxns").value(DEFAULT_NO_TXN.intValue()))
            .andExpect(jsonPath("$.failedTxns").value(DEFAULT_FAILED.toString()))
            .andExpect(jsonPath("$.fileStatus").value(DEFAULT_FILE_STATUS.toString()))
            .andExpect(jsonPath("$.fileProfile").value(DEFAULT_FILE_PROFILE.toString()))
            .andExpect(jsonPath("$.sourceSystem").value(DEFAULT_SOURCE_SYSTEM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentFile() throws Exception {
        // Get the paymentFile
        restPaymentFileMockMvc.perform(get("/api/payment-files/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentFile() throws Exception {
        // Initialize the database
        paymentFileService.save(paymentFile);

        int databaseSizeBeforeUpdate = paymentFileRepository.findAll().size();

        // Update the paymentFile
        PaymentFile updatedPaymentFile = new PaymentFile();
        updatedPaymentFile.setId(paymentFile.getId());
        updatedPaymentFile.setGfcId(UPDATED_GFC_ID);
        updatedPaymentFile.setFileId(UPDATED_FILE_ID);
        updatedPaymentFile.setProfileId(UPDATED_PROFILE_ID);
        updatedPaymentFile.setFileName(UPDATED_FILE_NAME);
        updatedPaymentFile.setFileStart(UPDATED_FILE_START);
        updatedPaymentFile.setGksEnd(UPDATED_GKS_END);
        updatedPaymentFile.setLastStep(UPDATED_LAST_STEP);
        updatedPaymentFile.setNoTxns(UPDATED_NO_TXN);
        updatedPaymentFile.setFailedTxns(UPDATED_FAILED);
        updatedPaymentFile.setFileStatus(UPDATED_FILE_STATUS);
        updatedPaymentFile.setFileProfile(UPDATED_FILE_PROFILE);
        updatedPaymentFile.setSourceSystem(UPDATED_SOURCE_SYSTEM);

        restPaymentFileMockMvc.perform(put("/api/payment-files")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedPaymentFile)))
                .andExpect(status().isOk());

        // Validate the PaymentFile in the database
        List<PaymentFile> paymentFiles = paymentFileRepository.findAll();
        assertThat(paymentFiles).hasSize(databaseSizeBeforeUpdate);
        PaymentFile testPaymentFile = paymentFiles.get(paymentFiles.size() - 1);
        assertThat(testPaymentFile.getGfcId()).isEqualTo(UPDATED_GFC_ID);
        assertThat(testPaymentFile.getFileId()).isEqualTo(UPDATED_FILE_ID);
        assertThat(testPaymentFile.getProfileId()).isEqualTo(UPDATED_PROFILE_ID);
        assertThat(testPaymentFile.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testPaymentFile.getFileStart()).isEqualTo(UPDATED_FILE_START);
        assertThat(testPaymentFile.getGksEnd()).isEqualTo(UPDATED_GKS_END);
        assertThat(testPaymentFile.getLastStep()).isEqualTo(UPDATED_LAST_STEP);
        assertThat(testPaymentFile.getNoTxns()).isEqualTo(UPDATED_NO_TXN);
        assertThat(testPaymentFile.getFailedTxns()).isEqualTo(UPDATED_FAILED);
        assertThat(testPaymentFile.getFileStatus()).isEqualTo(UPDATED_FILE_STATUS);
        assertThat(testPaymentFile.getFileProfile()).isEqualTo(UPDATED_FILE_PROFILE);
        assertThat(testPaymentFile.getSourceSystem()).isEqualTo(UPDATED_SOURCE_SYSTEM);
    }

    @Test
    @Transactional
    public void deletePaymentFile() throws Exception {
        // Initialize the database
        paymentFileService.save(paymentFile);

        int databaseSizeBeforeDelete = paymentFileRepository.findAll().size();

        // Get the paymentFile
        restPaymentFileMockMvc.perform(delete("/api/payment-files/{id}", paymentFile.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PaymentFile> paymentFiles = paymentFileRepository.findAll();
        assertThat(paymentFiles).hasSize(databaseSizeBeforeDelete - 1);
    }
}
