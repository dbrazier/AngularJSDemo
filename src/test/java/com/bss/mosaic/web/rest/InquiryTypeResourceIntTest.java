package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.InquiryType;
import com.bss.mosaic.repository.InquiryTypeRepository;
import com.bss.mosaic.service.InquiryTypeService;

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
 * Test class for the InquiryTypeResource REST controller.
 *
 * @see InquiryTypeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class InquiryTypeResourceIntTest {

    private static final String DEFAULT_INQUIRY_TYPE = "AAAAA";
    private static final String UPDATED_INQUIRY_TYPE = "BBBBB";

    @Inject
    private InquiryTypeRepository inquiryTypeRepository;

    @Inject
    private InquiryTypeService inquiryTypeService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInquiryTypeMockMvc;

    private InquiryType inquiryType;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InquiryTypeResource inquiryTypeResource = new InquiryTypeResource();
        ReflectionTestUtils.setField(inquiryTypeResource, "inquiryTypeService", inquiryTypeService);
        this.restInquiryTypeMockMvc = MockMvcBuilders.standaloneSetup(inquiryTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        inquiryType = new InquiryType();
        inquiryType.setInquiryType(DEFAULT_INQUIRY_TYPE);
    }

    @Test
    @Transactional
    public void createInquiryType() throws Exception {
        int databaseSizeBeforeCreate = inquiryTypeRepository.findAll().size();

        // Create the InquiryType

        restInquiryTypeMockMvc.perform(post("/api/inquiry-types")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(inquiryType)))
                .andExpect(status().isCreated());

        // Validate the InquiryType in the database
        List<InquiryType> inquiryTypes = inquiryTypeRepository.findAll();
        assertThat(inquiryTypes).hasSize(databaseSizeBeforeCreate + 1);
        InquiryType testInquiryType = inquiryTypes.get(inquiryTypes.size() - 1);
        assertThat(testInquiryType.getInquiryType()).isEqualTo(DEFAULT_INQUIRY_TYPE);
    }

    @Test
    @Transactional
    public void getAllInquiryTypes() throws Exception {
        // Initialize the database
        inquiryTypeRepository.saveAndFlush(inquiryType);

        // Get all the inquiryTypes
        restInquiryTypeMockMvc.perform(get("/api/inquiry-types?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(inquiryType.getId().intValue())))
                .andExpect(jsonPath("$.[*].inquiryType").value(hasItem(DEFAULT_INQUIRY_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getInquiryType() throws Exception {
        // Initialize the database
        inquiryTypeRepository.saveAndFlush(inquiryType);

        // Get the inquiryType
        restInquiryTypeMockMvc.perform(get("/api/inquiry-types/{id}", inquiryType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(inquiryType.getId().intValue()))
            .andExpect(jsonPath("$.inquiryType").value(DEFAULT_INQUIRY_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInquiryType() throws Exception {
        // Get the inquiryType
        restInquiryTypeMockMvc.perform(get("/api/inquiry-types/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInquiryType() throws Exception {
        // Initialize the database
        inquiryTypeService.save(inquiryType);

        int databaseSizeBeforeUpdate = inquiryTypeRepository.findAll().size();

        // Update the inquiryType
        InquiryType updatedInquiryType = new InquiryType();
        updatedInquiryType.setId(inquiryType.getId());
        updatedInquiryType.setInquiryType(UPDATED_INQUIRY_TYPE);

        restInquiryTypeMockMvc.perform(put("/api/inquiry-types")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedInquiryType)))
                .andExpect(status().isOk());

        // Validate the InquiryType in the database
        List<InquiryType> inquiryTypes = inquiryTypeRepository.findAll();
        assertThat(inquiryTypes).hasSize(databaseSizeBeforeUpdate);
        InquiryType testInquiryType = inquiryTypes.get(inquiryTypes.size() - 1);
        assertThat(testInquiryType.getInquiryType()).isEqualTo(UPDATED_INQUIRY_TYPE);
    }

    @Test
    @Transactional
    public void deleteInquiryType() throws Exception {
        // Initialize the database
        inquiryTypeService.save(inquiryType);

        int databaseSizeBeforeDelete = inquiryTypeRepository.findAll().size();

        // Get the inquiryType
        restInquiryTypeMockMvc.perform(delete("/api/inquiry-types/{id}", inquiryType.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<InquiryType> inquiryTypes = inquiryTypeRepository.findAll();
        assertThat(inquiryTypes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
