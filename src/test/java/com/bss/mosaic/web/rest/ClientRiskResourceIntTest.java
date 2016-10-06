package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.ClientRisk;
import com.bss.mosaic.service.ClientRiskService;
import com.bss.mosaic.repository.ClientRiskRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ClientRiskResource REST controller.
 *
 * @see ClientRiskResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class ClientRiskResourceIntTest {


    private static final Long DEFAULT_GFC_ID = 1L;
    private static final Long UPDATED_GFC_ID = 2L;
    private static final String DEFAULT_LIST_TYPE = "AAAAA";
    private static final String UPDATED_LIST_TYPE = "BBBBB";
    private static final String DEFAULT_TEMPERATURE = "AAAAA";
    private static final String UPDATED_TEMPERATURE = "BBBBB";

    @Inject
    private ClientRiskRepository clientRiskRepository;

    @Inject
    private ClientRiskService clientRiskService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restClientRiskMockMvc;

    private ClientRisk clientRisk;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClientRiskResource clientRiskResource = new ClientRiskResource();
        ReflectionTestUtils.setField(clientRiskResource, "clientRiskService", clientRiskService);
        this.restClientRiskMockMvc = MockMvcBuilders.standaloneSetup(clientRiskResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        clientRisk = new ClientRisk();
        clientRisk.setGfcId(DEFAULT_GFC_ID);
        clientRisk.setListType(DEFAULT_LIST_TYPE);
        clientRisk.setTemperature(DEFAULT_TEMPERATURE);
    }

    @Test
    @Transactional
    public void createClientRisk() throws Exception {
        int databaseSizeBeforeCreate = clientRiskRepository.findAll().size();

        // Create the ClientRisk

        restClientRiskMockMvc.perform(post("/api/client-risks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clientRisk)))
                .andExpect(status().isCreated());

        // Validate the ClientRisk in the database
        List<ClientRisk> clientRisks = clientRiskRepository.findAll();
        assertThat(clientRisks).hasSize(databaseSizeBeforeCreate + 1);
        ClientRisk testClientRisk = clientRisks.get(clientRisks.size() - 1);
        assertThat(testClientRisk.getGfcId()).isEqualTo(DEFAULT_GFC_ID);
        assertThat(testClientRisk.getListType()).isEqualTo(DEFAULT_LIST_TYPE);
        assertThat(testClientRisk.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
    }

    @Test
    @Transactional
    public void getAllClientRisks() throws Exception {
        // Initialize the database
        clientRiskRepository.saveAndFlush(clientRisk);

        // Get all the clientRisks
        restClientRiskMockMvc.perform(get("/api/client-risks?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(clientRisk.getId().intValue())))
                .andExpect(jsonPath("$.[*].gfcId").value(hasItem(DEFAULT_GFC_ID.intValue())))
                .andExpect(jsonPath("$.[*].listType").value(hasItem(DEFAULT_LIST_TYPE.toString())))
                .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE.toString())));
    }

    @Test
    @Transactional
    public void getClientRisk() throws Exception {
        // Initialize the database
        clientRiskRepository.saveAndFlush(clientRisk);

        // Get the clientRisk
        restClientRiskMockMvc.perform(get("/api/client-risks/{id}", clientRisk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(clientRisk.getId().intValue()))
            .andExpect(jsonPath("$.gfcId").value(DEFAULT_GFC_ID.intValue()))
            .andExpect(jsonPath("$.listType").value(DEFAULT_LIST_TYPE.toString()))
            .andExpect(jsonPath("$.temperature").value(DEFAULT_TEMPERATURE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingClientRisk() throws Exception {
        // Get the clientRisk
        restClientRiskMockMvc.perform(get("/api/client-risks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClientRisk() throws Exception {
        // Initialize the database
        clientRiskService.save(clientRisk);

        int databaseSizeBeforeUpdate = clientRiskRepository.findAll().size();

        // Update the clientRisk
        ClientRisk updatedClientRisk = new ClientRisk();
        updatedClientRisk.setId(clientRisk.getId());
        updatedClientRisk.setGfcId(UPDATED_GFC_ID);
        updatedClientRisk.setListType(UPDATED_LIST_TYPE);
        updatedClientRisk.setTemperature(UPDATED_TEMPERATURE);

        restClientRiskMockMvc.perform(put("/api/client-risks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedClientRisk)))
                .andExpect(status().isOk());

        // Validate the ClientRisk in the database
        List<ClientRisk> clientRisks = clientRiskRepository.findAll();
        assertThat(clientRisks).hasSize(databaseSizeBeforeUpdate);
        ClientRisk testClientRisk = clientRisks.get(clientRisks.size() - 1);
        assertThat(testClientRisk.getGfcId()).isEqualTo(UPDATED_GFC_ID);
        assertThat(testClientRisk.getListType()).isEqualTo(UPDATED_LIST_TYPE);
        assertThat(testClientRisk.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);
    }

    @Test
    @Transactional
    public void deleteClientRisk() throws Exception {
        // Initialize the database
        clientRiskService.save(clientRisk);

        int databaseSizeBeforeDelete = clientRiskRepository.findAll().size();

        // Get the clientRisk
        restClientRiskMockMvc.perform(delete("/api/client-risks/{id}", clientRisk.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ClientRisk> clientRisks = clientRiskRepository.findAll();
        assertThat(clientRisks).hasSize(databaseSizeBeforeDelete - 1);
    }
}
