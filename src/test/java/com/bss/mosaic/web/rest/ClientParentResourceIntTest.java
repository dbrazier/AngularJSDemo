package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.ClientParent;
import com.bss.mosaic.repository.ClientParentRepository;
import com.bss.mosaic.service.ClientParentService;

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
 * Test class for the ClientParentResource REST controller.
 *
 * @see ClientParentResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class ClientParentResourceIntTest {


    private static final Long DEFAULT_GFP_ID = 1L;
    private static final Long UPDATED_GFP_ID = 2L;
    private static final String DEFAULT_GFP_NAME = "AAAAA";
    private static final String UPDATED_GFP_NAME = "BBBBB";

    private static final Long DEFAULT_GFC_ID = 1L;
    private static final Long UPDATED_GFC_ID = 2L;

    @Inject
    private ClientParentRepository clientParentRepository;

    @Inject
    private ClientParentService clientParentService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restClientParentMockMvc;

    private ClientParent clientParent;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClientParentResource clientParentResource = new ClientParentResource();
        ReflectionTestUtils.setField(clientParentResource, "clientParentService", clientParentService);
        this.restClientParentMockMvc = MockMvcBuilders.standaloneSetup(clientParentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        clientParent = new ClientParent();
        clientParent.setGfpId(DEFAULT_GFP_ID);
        clientParent.setGfpName(DEFAULT_GFP_NAME);
        clientParent.setGfcId(DEFAULT_GFC_ID);
    }

    @Test
    @Transactional
    public void createClientParent() throws Exception {
        int databaseSizeBeforeCreate = clientParentRepository.findAll().size();

        // Create the ClientParent

        restClientParentMockMvc.perform(post("/api/client-parents")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clientParent)))
                .andExpect(status().isCreated());

        // Validate the ClientParent in the database
        List<ClientParent> clientParents = clientParentRepository.findAll();
        assertThat(clientParents).hasSize(databaseSizeBeforeCreate + 1);
        ClientParent testClientParent = clientParents.get(clientParents.size() - 1);
        assertThat(testClientParent.getGfpId()).isEqualTo(DEFAULT_GFP_ID);
        assertThat(testClientParent.getGfpName()).isEqualTo(DEFAULT_GFP_NAME);
        assertThat(testClientParent.getGfcId()).isEqualTo(DEFAULT_GFC_ID);
    }

    @Test
    @Transactional
    public void getAllClientParents() throws Exception {
        // Initialize the database
        clientParentRepository.saveAndFlush(clientParent);

        // Get all the clientParents
        restClientParentMockMvc.perform(get("/api/client-parents?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(clientParent.getId().intValue())))
                .andExpect(jsonPath("$.[*].gfpId").value(hasItem(DEFAULT_GFP_ID.intValue())))
                .andExpect(jsonPath("$.[*].gfpName").value(hasItem(DEFAULT_GFP_NAME.toString())))
                .andExpect(jsonPath("$.[*].gfcId").value(hasItem(DEFAULT_GFC_ID.intValue())));
    }

    @Test
    @Transactional
    public void getClientParent() throws Exception {
        // Initialize the database
        clientParentRepository.saveAndFlush(clientParent);

        // Get the clientParent
        restClientParentMockMvc.perform(get("/api/client-parents/{id}", clientParent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(clientParent.getId().intValue()))
            .andExpect(jsonPath("$.gfpId").value(DEFAULT_GFP_ID.intValue()))
            .andExpect(jsonPath("$.gfpName").value(DEFAULT_GFP_NAME.toString()))
            .andExpect(jsonPath("$.gfcId").value(DEFAULT_GFC_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingClientParent() throws Exception {
        // Get the clientParent
        restClientParentMockMvc.perform(get("/api/client-parents/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClientParent() throws Exception {
        // Initialize the database
        clientParentService.save(clientParent);

        int databaseSizeBeforeUpdate = clientParentRepository.findAll().size();

        // Update the clientParent
        ClientParent updatedClientParent = new ClientParent();
        updatedClientParent.setId(clientParent.getId());
        updatedClientParent.setGfpId(UPDATED_GFP_ID);
        updatedClientParent.setGfpName(UPDATED_GFP_NAME);
        updatedClientParent.setGfcId(UPDATED_GFC_ID);

        restClientParentMockMvc.perform(put("/api/client-parents")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedClientParent)))
                .andExpect(status().isOk());

        // Validate the ClientParent in the database
        List<ClientParent> clientParents = clientParentRepository.findAll();
        assertThat(clientParents).hasSize(databaseSizeBeforeUpdate);
        ClientParent testClientParent = clientParents.get(clientParents.size() - 1);
        assertThat(testClientParent.getGfpId()).isEqualTo(UPDATED_GFP_ID);
        assertThat(testClientParent.getGfpName()).isEqualTo(UPDATED_GFP_NAME);
        assertThat(testClientParent.getGfcId()).isEqualTo(UPDATED_GFC_ID);
    }

    @Test
    @Transactional
    public void deleteClientParent() throws Exception {
        // Initialize the database
        clientParentService.save(clientParent);

        int databaseSizeBeforeDelete = clientParentRepository.findAll().size();

        // Get the clientParent
        restClientParentMockMvc.perform(delete("/api/client-parents/{id}", clientParent.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ClientParent> clientParents = clientParentRepository.findAll();
        assertThat(clientParents).hasSize(databaseSizeBeforeDelete - 1);
    }
}
