package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.PaymentTransaction;
import com.bss.mosaic.repository.PaymentTransactionRepository;
import com.bss.mosaic.service.PaymentTransactionService;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PaymentTransactionResource REST controller.
 *
 * @see PaymentTransactionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class PaymentTransactionResourceIntTest {


    private static final Long DEFAULT_GFC_ID = 1L;
    private static final Long UPDATED_GFC_ID = 2L;

    private static final Long DEFAULT_FILE_ID = 1L;
    private static final Long UPDATED_FILE_ID = 2L;

    private static final Long DEFAULT_TXN_REF_ID = 1L;
    private static final Long UPDATED_TXN_REF_ID = 2L;

    private static final Long DEFAULT_TXN_ID = 1L;
    private static final Long UPDATED_TXN_ID = 2L;
    private static final String DEFAULT_TXN_STATUS = "AAAAA";
    private static final String UPDATED_TXN_STATUS = "BBBBB";
    private static final String DEFAULT_CURRENCY = "AAAAA";
    private static final String UPDATED_CURRENCY = "BBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final Long DEFAULT_DEBIT_ACC_NO = 1L;
    private static final Long UPDATED_DEBIT_ACC_NO = 2L;

    private static final Long DEFAULT_CREDIT_ACC_NO = 1L;
    private static final Long UPDATED_CREDIT_ACC_NO = 2L;
    private static final String DEFAULT_BENEFICIARY_NAME = "AAAAA";
    private static final String UPDATED_BENEFICIARY_NAME = "BBBBB";
    private static final String DEFAULT_STATUS_DESCRIPTION = "AAAAA";
    private static final String UPDATED_STATUS_DESCRIPTION = "BBBBB";

    @Inject
    private PaymentTransactionRepository paymentTransactionRepository;

    @Inject
    private PaymentTransactionService paymentTransactionService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPaymentTransactionMockMvc;

    private PaymentTransaction paymentTransaction;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PaymentTransactionResource paymentTransactionResource = new PaymentTransactionResource();
        ReflectionTestUtils.setField(paymentTransactionResource, "paymentTransactionService", paymentTransactionService);
        this.restPaymentTransactionMockMvc = MockMvcBuilders.standaloneSetup(paymentTransactionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        paymentTransaction = new PaymentTransaction();
        paymentTransaction.setGfcId(DEFAULT_GFC_ID);
        paymentTransaction.setFileId(DEFAULT_FILE_ID);
        paymentTransaction.setTxnRefId(DEFAULT_TXN_REF_ID);
        paymentTransaction.setTxnId(DEFAULT_TXN_ID);
        paymentTransaction.setTxnStatus(DEFAULT_TXN_STATUS);
        paymentTransaction.setCurrency(DEFAULT_CURRENCY);
        paymentTransaction.setAmount(DEFAULT_AMOUNT);
        paymentTransaction.setDebitAccNo(DEFAULT_DEBIT_ACC_NO);
        paymentTransaction.setCreditAccNo(DEFAULT_CREDIT_ACC_NO);
        paymentTransaction.setBeneficiaryName(DEFAULT_BENEFICIARY_NAME);
        paymentTransaction.setStatusDescription(DEFAULT_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createPaymentTransaction() throws Exception {
        int databaseSizeBeforeCreate = paymentTransactionRepository.findAll().size();

        // Create the PaymentTransaction

        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paymentTransaction)))
                .andExpect(status().isCreated());

        // Validate the PaymentTransaction in the database
        List<PaymentTransaction> paymentTransactions = paymentTransactionRepository.findAll();
        assertThat(paymentTransactions).hasSize(databaseSizeBeforeCreate + 1);
        PaymentTransaction testPaymentTransaction = paymentTransactions.get(paymentTransactions.size() - 1);
        assertThat(testPaymentTransaction.getGfcId()).isEqualTo(DEFAULT_GFC_ID);
        assertThat(testPaymentTransaction.getFileId()).isEqualTo(DEFAULT_FILE_ID);
        assertThat(testPaymentTransaction.getTxnRefId()).isEqualTo(DEFAULT_TXN_REF_ID);
        assertThat(testPaymentTransaction.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testPaymentTransaction.getTxnStatus()).isEqualTo(DEFAULT_TXN_STATUS);
        assertThat(testPaymentTransaction.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testPaymentTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPaymentTransaction.getDebitAccNo()).isEqualTo(DEFAULT_DEBIT_ACC_NO);
        assertThat(testPaymentTransaction.getCreditAccNo()).isEqualTo(DEFAULT_CREDIT_ACC_NO);
        assertThat(testPaymentTransaction.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testPaymentTransaction.getStatusDescription()).isEqualTo(DEFAULT_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPaymentTransactions() throws Exception {
        // Initialize the database
        paymentTransactionRepository.saveAndFlush(paymentTransaction);

        // Get all the paymentTransactions
        restPaymentTransactionMockMvc.perform(get("/api/payment-transactions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(paymentTransaction.getId().intValue())))
                .andExpect(jsonPath("$.[*].gfcId").value(hasItem(DEFAULT_GFC_ID.intValue())))
                .andExpect(jsonPath("$.[*].fileId").value(hasItem(DEFAULT_FILE_ID.intValue())))
                .andExpect(jsonPath("$.[*].txnRefId").value(hasItem(DEFAULT_TXN_REF_ID.intValue())))
                .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.intValue())))
                .andExpect(jsonPath("$.[*].txnStatus").value(hasItem(DEFAULT_TXN_STATUS.toString())))
                .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
                .andExpect(jsonPath("$.[*].debitAccNo").value(hasItem(DEFAULT_DEBIT_ACC_NO.intValue())))
                .andExpect(jsonPath("$.[*].creditAccNo").value(hasItem(DEFAULT_CREDIT_ACC_NO.intValue())))
                .andExpect(jsonPath("$.[*].beneficiaryName").value(hasItem(DEFAULT_BENEFICIARY_NAME.toString())))
                .andExpect(jsonPath("$.[*].statusDescription").value(hasItem(DEFAULT_STATUS_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getPaymentTransaction() throws Exception {
        // Initialize the database
        paymentTransactionRepository.saveAndFlush(paymentTransaction);

        // Get the paymentTransaction
        restPaymentTransactionMockMvc.perform(get("/api/payment-transactions/{id}", paymentTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(paymentTransaction.getId().intValue()))
            .andExpect(jsonPath("$.gfcId").value(DEFAULT_GFC_ID.intValue()))
            .andExpect(jsonPath("$.fileId").value(DEFAULT_FILE_ID.intValue()))
            .andExpect(jsonPath("$.txnRefId").value(DEFAULT_TXN_REF_ID.intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.intValue()))
            .andExpect(jsonPath("$.txnStatus").value(DEFAULT_TXN_STATUS.toString()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.debitAccNo").value(DEFAULT_DEBIT_ACC_NO.intValue()))
            .andExpect(jsonPath("$.creditAccNo").value(DEFAULT_CREDIT_ACC_NO.intValue()))
            .andExpect(jsonPath("$.beneficiaryName").value(DEFAULT_BENEFICIARY_NAME.toString()))
            .andExpect(jsonPath("$.statusDescription").value(DEFAULT_STATUS_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentTransaction() throws Exception {
        // Get the paymentTransaction
        restPaymentTransactionMockMvc.perform(get("/api/payment-transactions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentTransaction() throws Exception {
        // Initialize the database
        paymentTransactionService.save(paymentTransaction);

        int databaseSizeBeforeUpdate = paymentTransactionRepository.findAll().size();

        // Update the paymentTransaction
        PaymentTransaction updatedPaymentTransaction = new PaymentTransaction();
        updatedPaymentTransaction.setId(paymentTransaction.getId());
        updatedPaymentTransaction.setGfcId(UPDATED_GFC_ID);
        updatedPaymentTransaction.setFileId(UPDATED_FILE_ID);
        updatedPaymentTransaction.setTxnRefId(UPDATED_TXN_REF_ID);
        updatedPaymentTransaction.setTxnId(UPDATED_TXN_ID);
        updatedPaymentTransaction.setTxnStatus(UPDATED_TXN_STATUS);
        updatedPaymentTransaction.setCurrency(UPDATED_CURRENCY);
        updatedPaymentTransaction.setAmount(UPDATED_AMOUNT);
        updatedPaymentTransaction.setDebitAccNo(UPDATED_DEBIT_ACC_NO);
        updatedPaymentTransaction.setCreditAccNo(UPDATED_CREDIT_ACC_NO);
        updatedPaymentTransaction.setBeneficiaryName(UPDATED_BENEFICIARY_NAME);
        updatedPaymentTransaction.setStatusDescription(UPDATED_STATUS_DESCRIPTION);

        restPaymentTransactionMockMvc.perform(put("/api/payment-transactions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedPaymentTransaction)))
                .andExpect(status().isOk());

        // Validate the PaymentTransaction in the database
        List<PaymentTransaction> paymentTransactions = paymentTransactionRepository.findAll();
        assertThat(paymentTransactions).hasSize(databaseSizeBeforeUpdate);
        PaymentTransaction testPaymentTransaction = paymentTransactions.get(paymentTransactions.size() - 1);
        assertThat(testPaymentTransaction.getGfcId()).isEqualTo(UPDATED_GFC_ID);
        assertThat(testPaymentTransaction.getFileId()).isEqualTo(UPDATED_FILE_ID);
        assertThat(testPaymentTransaction.getTxnRefId()).isEqualTo(UPDATED_TXN_REF_ID);
        assertThat(testPaymentTransaction.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testPaymentTransaction.getTxnStatus()).isEqualTo(UPDATED_TXN_STATUS);
        assertThat(testPaymentTransaction.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testPaymentTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPaymentTransaction.getDebitAccNo()).isEqualTo(UPDATED_DEBIT_ACC_NO);
        assertThat(testPaymentTransaction.getCreditAccNo()).isEqualTo(UPDATED_CREDIT_ACC_NO);
        assertThat(testPaymentTransaction.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testPaymentTransaction.getStatusDescription()).isEqualTo(UPDATED_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deletePaymentTransaction() throws Exception {
        // Initialize the database
        paymentTransactionService.save(paymentTransaction);

        int databaseSizeBeforeDelete = paymentTransactionRepository.findAll().size();

        // Get the paymentTransaction
        restPaymentTransactionMockMvc.perform(delete("/api/payment-transactions/{id}", paymentTransaction.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PaymentTransaction> paymentTransactions = paymentTransactionRepository.findAll();
        assertThat(paymentTransactions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
