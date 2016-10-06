package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.ClientAccount;
import com.bss.mosaic.repository.ClientAccountRepository;
import com.bss.mosaic.service.ClientAccountService;

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
 * Test class for the ClientAccountResource REST controller.
 *
 * @see ClientAccountResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class ClientAccountResourceIntTest {


    private static final Long DEFAULT_ACCOUNT_NO = 1L;
    private static final Long UPDATED_ACCOUNT_NO = 2L;

    private static final Long DEFAULT_GFC_ID = 1L;
    private static final Long UPDATED_GFC_ID = 2L;

    private static final Long DEFAULT_BRANCH = 1L;
    private static final Long UPDATED_BRANCH = 2L;
    private static final String DEFAULT_ACCOUNT_NAME = "AAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBB";
    private static final String DEFAULT_CURRENCY = "AAAAA";
    private static final String UPDATED_CURRENCY = "BBBBB";

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);
    private static final String DEFAULT_RESTRICTION = "AAAAA";
    private static final String UPDATED_RESTRICTION = "BBBBB";
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    @Inject
    private ClientAccountRepository clientAccountRepository;

    @Inject
    private ClientAccountService clientAccountService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restClientAccountMockMvc;

    private ClientAccount clientAccount;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClientAccountResource clientAccountResource = new ClientAccountResource();
        ReflectionTestUtils.setField(clientAccountResource, "clientAccountService", clientAccountService);
        this.restClientAccountMockMvc = MockMvcBuilders.standaloneSetup(clientAccountResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        clientAccount = new ClientAccount();
        clientAccount.setAccountNo(DEFAULT_ACCOUNT_NO);
        clientAccount.setGfcId(DEFAULT_GFC_ID);
        clientAccount.setBranch(DEFAULT_BRANCH);
        clientAccount.setAccountName(DEFAULT_ACCOUNT_NAME);
        clientAccount.setCurrency(DEFAULT_CURRENCY);
        clientAccount.setBalance(DEFAULT_BALANCE);
        clientAccount.setRestriction(DEFAULT_RESTRICTION);
        clientAccount.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createClientAccount() throws Exception {
        int databaseSizeBeforeCreate = clientAccountRepository.findAll().size();

        // Create the ClientAccount

        restClientAccountMockMvc.perform(post("/api/client-accounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clientAccount)))
                .andExpect(status().isCreated());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccounts = clientAccountRepository.findAll();
        assertThat(clientAccounts).hasSize(databaseSizeBeforeCreate + 1);
        ClientAccount testClientAccount = clientAccounts.get(clientAccounts.size() - 1);
        assertThat(testClientAccount.getAccountNo()).isEqualTo(DEFAULT_ACCOUNT_NO);
        assertThat(testClientAccount.getGfcId()).isEqualTo(DEFAULT_GFC_ID);
        assertThat(testClientAccount.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testClientAccount.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testClientAccount.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testClientAccount.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testClientAccount.getRestriction()).isEqualTo(DEFAULT_RESTRICTION);
        assertThat(testClientAccount.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllClientAccounts() throws Exception {
        // Initialize the database
        clientAccountRepository.saveAndFlush(clientAccount);

        // Get all the clientAccounts
        restClientAccountMockMvc.perform(get("/api/client-accounts?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(clientAccount.getId().intValue())))
                .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO.intValue())))
                .andExpect(jsonPath("$.[*].gfcId").value(hasItem(DEFAULT_GFC_ID.intValue())))
                .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH.intValue())))
                .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME.toString())))
                .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
                .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
                .andExpect(jsonPath("$.[*].restriction").value(hasItem(DEFAULT_RESTRICTION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getClientAccount() throws Exception {
        // Initialize the database
        clientAccountRepository.saveAndFlush(clientAccount);

        // Get the clientAccount
        restClientAccountMockMvc.perform(get("/api/client-accounts/{id}", clientAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(clientAccount.getId().intValue()))
            .andExpect(jsonPath("$.accountNo").value(DEFAULT_ACCOUNT_NO.intValue()))
            .andExpect(jsonPath("$.gfcId").value(DEFAULT_GFC_ID.intValue()))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH.intValue()))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME.toString()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.restriction").value(DEFAULT_RESTRICTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingClientAccount() throws Exception {
        // Get the clientAccount
        restClientAccountMockMvc.perform(get("/api/client-accounts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClientAccount() throws Exception {
        // Initialize the database
        clientAccountService.save(clientAccount);

        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();

        // Update the clientAccount
        ClientAccount updatedClientAccount = new ClientAccount();
        updatedClientAccount.setId(clientAccount.getId());
        updatedClientAccount.setAccountNo(UPDATED_ACCOUNT_NO);
        updatedClientAccount.setGfcId(UPDATED_GFC_ID);
        updatedClientAccount.setBranch(UPDATED_BRANCH);
        updatedClientAccount.setAccountName(UPDATED_ACCOUNT_NAME);
        updatedClientAccount.setCurrency(UPDATED_CURRENCY);
        updatedClientAccount.setBalance(UPDATED_BALANCE);
        updatedClientAccount.setRestriction(UPDATED_RESTRICTION);
        updatedClientAccount.setStatus(UPDATED_STATUS);

        restClientAccountMockMvc.perform(put("/api/client-accounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedClientAccount)))
                .andExpect(status().isOk());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccounts = clientAccountRepository.findAll();
        assertThat(clientAccounts).hasSize(databaseSizeBeforeUpdate);
        ClientAccount testClientAccount = clientAccounts.get(clientAccounts.size() - 1);
        assertThat(testClientAccount.getAccountNo()).isEqualTo(UPDATED_ACCOUNT_NO);
        assertThat(testClientAccount.getGfcId()).isEqualTo(UPDATED_GFC_ID);
        assertThat(testClientAccount.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testClientAccount.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testClientAccount.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testClientAccount.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testClientAccount.getRestriction()).isEqualTo(UPDATED_RESTRICTION);
        assertThat(testClientAccount.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteClientAccount() throws Exception {
        // Initialize the database
        clientAccountService.save(clientAccount);

        int databaseSizeBeforeDelete = clientAccountRepository.findAll().size();

        // Get the clientAccount
        restClientAccountMockMvc.perform(delete("/api/client-accounts/{id}", clientAccount.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ClientAccount> clientAccounts = clientAccountRepository.findAll();
        assertThat(clientAccounts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
