package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.domain.Search;
import com.bss.mosaic.repository.SearchRepository;
import com.bss.mosaic.service.SearchService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the SearchResource REST controller.
 *
 * @see SearchResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class SearchResourceIntTest {


    private static final Long DEFAULT_ACCOUNT_NO = 1L;
    private static final String DEFAULT_CASE_ID = "AAAAAAA";
    private static final String DEFAULT_GFC_ID = "BBBBBBB";

    @Inject
    private SearchRepository searchRepository;

    @Inject
    private SearchService searchService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSearchMockMvc;

    private Search search;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SearchResource searchResource = new SearchResource();
        ReflectionTestUtils.setField(searchResource, "searchService", searchService);
        this.restSearchMockMvc = MockMvcBuilders.standaloneSetup(searchResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        search = new Search();
        search.setAccountNo(DEFAULT_ACCOUNT_NO);
        search.setCaseId(DEFAULT_CASE_ID);
        search.setGfcId(DEFAULT_GFC_ID);
    }


    @Test
    @Transactional
    public void search() throws Exception {

        // Get all the searches
        restSearchMockMvc.perform(post("/api/searches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(search)))
            .andExpect(status().isNotFound());
    }



}
