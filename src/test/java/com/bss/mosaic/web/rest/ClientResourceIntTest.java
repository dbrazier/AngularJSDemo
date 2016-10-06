package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.Client;
import com.bss.mosaic.repository.ClientRepository;
import com.bss.mosaic.service.ClientService;

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
 * Test class for the ClientResource REST controller.
 *
 * @see ClientResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class ClientResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));


    private static final Long DEFAULT_GFC_ID = 1L;
    private static final Long UPDATED_GFC_ID = 2L;
    private static final String DEFAULT_GFC_NAME = "AAAAA";
    private static final String UPDATED_GFC_NAME = "BBBBB";

    private static final Long DEFAULT_GFP_ID = 1L;
    private static final Long UPDATED_GFP_ID = 2L;
    private static final String DEFAULT_CONTACT_NAME = "AAAAA";
    private static final String UPDATED_CONTACT_NAME = "BBBBB";
    private static final String DEFAULT_CONTACT_NO = "AAAAA";
    private static final String UPDATED_CONTACT_NO = "BBBBB";
    private static final String DEFAULT_CONTACT_EMAIL = "AAAAA";
    private static final String UPDATED_CONTACT_EMAIL = "BBBBB";
    private static final String DEFAULT_SERVICE_MODEL = "AAAAA";
    private static final String UPDATED_SERVICE_MODEL = "BBBBB";
    private static final String DEFAULT_TIER = "AAAAA";
    private static final String UPDATED_TIER = "BBBBB";
    private static final String DEFAULT_CLIENT_SECTOR = "AAAAA";
    private static final String UPDATED_CLIENT_SECTOR = "BBBBB";

    private static final Integer DEFAULT_NO_ACCOUNTS = 1;
    private static final Integer UPDATED_NO_ACCOUNTS = 2;

    private static final ZonedDateTime DEFAULT_LAST_INTERACTION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_INTERACTION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_INTERACTION_STR = dateTimeFormatter.format(DEFAULT_LAST_INTERACTION);
    private static final String DEFAULT_INQUIRY_VOLUME = "AAAAA";
    private static final String UPDATED_INQUIRY_VOLUME = "BBBBB";

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private ClientService clientService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restClientMockMvc;

    private Client client;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClientResource clientResource = new ClientResource();
        ReflectionTestUtils.setField(clientResource, "clientService", clientService);
        this.restClientMockMvc = MockMvcBuilders.standaloneSetup(clientResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        client = new Client();
        client.setGfcId(DEFAULT_GFC_ID);
        client.setGfcName(DEFAULT_GFC_NAME);
        client.setGfpId(DEFAULT_GFP_ID);
        client.setContactName(DEFAULT_CONTACT_NAME);
        client.setContactNo(DEFAULT_CONTACT_NO);
        client.setContactEmail(DEFAULT_CONTACT_EMAIL);
        client.setServiceModel(DEFAULT_SERVICE_MODEL);
        client.setTier(DEFAULT_TIER);
        client.setClientSector(DEFAULT_CLIENT_SECTOR);
        client.setNoAccounts(DEFAULT_NO_ACCOUNTS);
        client.setLastInteraction(DEFAULT_LAST_INTERACTION);
        client.setInquiryVolume(DEFAULT_INQUIRY_VOLUME);
    }

    @Test
    @Transactional
    public void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client

        restClientMockMvc.perform(post("/api/clients")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(client)))
                .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clients = clientRepository.findAll();
        assertThat(clients).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clients.get(clients.size() - 1);
        assertThat(testClient.getGfcId()).isEqualTo(DEFAULT_GFC_ID);
        assertThat(testClient.getGfcName()).isEqualTo(DEFAULT_GFC_NAME);
        assertThat(testClient.getGfpId()).isEqualTo(DEFAULT_GFP_ID);
        assertThat(testClient.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testClient.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testClient.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testClient.getServiceModel()).isEqualTo(DEFAULT_SERVICE_MODEL);
        assertThat(testClient.getTier()).isEqualTo(DEFAULT_TIER);
        assertThat(testClient.getClientSector()).isEqualTo(DEFAULT_CLIENT_SECTOR);
        assertThat(testClient.getNoAccounts()).isEqualTo(DEFAULT_NO_ACCOUNTS);
        assertThat(testClient.getLastInteraction()).isEqualTo(DEFAULT_LAST_INTERACTION);
        assertThat(testClient.getInquiryVolume()).isEqualTo(DEFAULT_INQUIRY_VOLUME);
    }

    @Test
    @Transactional
    public void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clients
        restClientMockMvc.perform(get("/api/clients?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
                .andExpect(jsonPath("$.[*].gfcId").value(hasItem(DEFAULT_GFC_ID.intValue())))
                .andExpect(jsonPath("$.[*].gfcName").value(hasItem(DEFAULT_GFC_NAME.toString())))
                .andExpect(jsonPath("$.[*].gfpId").value(hasItem(DEFAULT_GFP_ID.intValue())))
                .andExpect(jsonPath("$.[*].contactName").value(hasItem(DEFAULT_CONTACT_NAME.toString())))
                .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO.toString())))
                .andExpect(jsonPath("$.[*].contactEmail").value(hasItem(DEFAULT_CONTACT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].serviceModel").value(hasItem(DEFAULT_SERVICE_MODEL.toString())))
                .andExpect(jsonPath("$.[*].tier").value(hasItem(DEFAULT_TIER.toString())))
                .andExpect(jsonPath("$.[*].clientSector").value(hasItem(DEFAULT_CLIENT_SECTOR.toString())))
                .andExpect(jsonPath("$.[*].noAccounts").value(hasItem(DEFAULT_NO_ACCOUNTS)))
                .andExpect(jsonPath("$.[*].lastInteraction").value(hasItem(DEFAULT_LAST_INTERACTION_STR)))
                .andExpect(jsonPath("$.[*].inquiryVolume").value(hasItem(DEFAULT_INQUIRY_VOLUME.toString())));
    }

    @Test
    @Transactional
    public void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.gfcId").value(DEFAULT_GFC_ID.intValue()))
            .andExpect(jsonPath("$.gfcName").value(DEFAULT_GFC_NAME.toString()))
            .andExpect(jsonPath("$.gfpId").value(DEFAULT_GFP_ID.intValue()))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL.toString()))
            .andExpect(jsonPath("$.serviceModel").value(DEFAULT_SERVICE_MODEL.toString()))
            .andExpect(jsonPath("$.tier").value(DEFAULT_TIER.toString()))
            .andExpect(jsonPath("$.clientSector").value(DEFAULT_CLIENT_SECTOR.toString()))
            .andExpect(jsonPath("$.noAccounts").value(DEFAULT_NO_ACCOUNTS))
            .andExpect(jsonPath("$.lastInteraction").value(DEFAULT_LAST_INTERACTION_STR))
            .andExpect(jsonPath("$.inquiryVolume").value(DEFAULT_INQUIRY_VOLUME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClient() throws Exception {
        // Initialize the database
        clientService.save(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = new Client();
        updatedClient.setId(client.getId());
        updatedClient.setGfcId(UPDATED_GFC_ID);
        updatedClient.setGfcName(UPDATED_GFC_NAME);
        updatedClient.setGfpId(UPDATED_GFP_ID);
        updatedClient.setContactName(UPDATED_CONTACT_NAME);
        updatedClient.setContactNo(UPDATED_CONTACT_NO);
        updatedClient.setContactEmail(UPDATED_CONTACT_EMAIL);
        updatedClient.setServiceModel(UPDATED_SERVICE_MODEL);
        updatedClient.setTier(UPDATED_TIER);
        updatedClient.setClientSector(UPDATED_CLIENT_SECTOR);
        updatedClient.setNoAccounts(UPDATED_NO_ACCOUNTS);
        updatedClient.setLastInteraction(UPDATED_LAST_INTERACTION);
        updatedClient.setInquiryVolume(UPDATED_INQUIRY_VOLUME);

        restClientMockMvc.perform(put("/api/clients")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedClient)))
                .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clients = clientRepository.findAll();
        assertThat(clients).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clients.get(clients.size() - 1);
        assertThat(testClient.getGfcId()).isEqualTo(UPDATED_GFC_ID);
        assertThat(testClient.getGfcName()).isEqualTo(UPDATED_GFC_NAME);
        assertThat(testClient.getGfpId()).isEqualTo(UPDATED_GFP_ID);
        assertThat(testClient.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testClient.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testClient.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testClient.getServiceModel()).isEqualTo(UPDATED_SERVICE_MODEL);
        assertThat(testClient.getTier()).isEqualTo(UPDATED_TIER);
        assertThat(testClient.getClientSector()).isEqualTo(UPDATED_CLIENT_SECTOR);
        assertThat(testClient.getNoAccounts()).isEqualTo(UPDATED_NO_ACCOUNTS);
        assertThat(testClient.getLastInteraction()).isEqualTo(UPDATED_LAST_INTERACTION);
        assertThat(testClient.getInquiryVolume()).isEqualTo(UPDATED_INQUIRY_VOLUME);
    }

    @Test
    @Transactional
    public void deleteClient() throws Exception {
        // Initialize the database
        clientService.save(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Get the client
        restClientMockMvc.perform(delete("/api/clients/{id}", client.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Client> clients = clientRepository.findAll();
        assertThat(clients).hasSize(databaseSizeBeforeDelete - 1);
    }
}
