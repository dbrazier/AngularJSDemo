package com.bss.mosaic.web.rest;

import com.bss.mosaic.MosaicApp;
import com.bss.mosaic.repository.RateRepository;
import com.bss.mosaic.domain.Rate;
import com.bss.mosaic.service.RateService;

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
 * Test class for the RateResource REST controller.
 *
 * @see RateResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MosaicApp.class)
@WebAppConfiguration
@IntegrationTest
public class RateResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));


    private static final Long DEFAULT_ACCOUNT_NO = 1L;
    private static final Long UPDATED_ACCOUNT_NO = 2L;

    private static final ZonedDateTime DEFAULT_RATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_RATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_RATE_DATE_STR = dateTimeFormatter.format(DEFAULT_RATE_DATE);

    private static final Double DEFAULT_DB_IR = 1D;
    private static final Double UPDATED_DB_IR = 2D;

    private static final Double DEFAULT_DB_AMOUNT = 1D;
    private static final Double UPDATED_DB_AMOUNT = 2D;

    private static final Double DEFAULT_CR_IR = 1D;
    private static final Double UPDATED_CR_IR = 2D;

    private static final Double DEFAULT_CR_AMOUNT = 1D;
    private static final Double UPDATED_CR_AMOUNT = 2D;

    @Inject
    private RateRepository rateRepository;

    @Inject
    private RateService rateService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRateMockMvc;

    private Rate rate;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RateResource rateResource = new RateResource();
        ReflectionTestUtils.setField(rateResource, "rateService", rateService);
        this.restRateMockMvc = MockMvcBuilders.standaloneSetup(rateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        rate = new Rate();
        rate.setAccountNo(DEFAULT_ACCOUNT_NO);
        rate.setRateDate(DEFAULT_RATE_DATE);
        rate.setDbIr(DEFAULT_DB_IR);
        rate.setDbAmount(DEFAULT_DB_AMOUNT);
        rate.setCrIr(DEFAULT_CR_IR);
        rate.setCrAmount(DEFAULT_CR_AMOUNT);
    }

    @Test
    @Transactional
    public void createRate() throws Exception {
        int databaseSizeBeforeCreate = rateRepository.findAll().size();

        // Create the Rate

        restRateMockMvc.perform(post("/api/rates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(rate)))
                .andExpect(status().isCreated());

        // Validate the Rate in the database
        List<Rate> rates = rateRepository.findAll();
        assertThat(rates).hasSize(databaseSizeBeforeCreate + 1);
        Rate testRate = rates.get(rates.size() - 1);
        assertThat(testRate.getAccountNo()).isEqualTo(DEFAULT_ACCOUNT_NO);
        assertThat(testRate.getRateDate()).isEqualTo(DEFAULT_RATE_DATE);
        assertThat(testRate.getDbIr()).isEqualTo(DEFAULT_DB_IR);
        assertThat(testRate.getDbAmount()).isEqualTo(DEFAULT_DB_AMOUNT);
        assertThat(testRate.getCrIr()).isEqualTo(DEFAULT_CR_IR);
        assertThat(testRate.getCrAmount()).isEqualTo(DEFAULT_CR_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllRates() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        // Get all the rates
        restRateMockMvc.perform(get("/api/rates?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(rate.getId().intValue())))
                .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO.intValue())))
                .andExpect(jsonPath("$.[*].rateDate").value(hasItem(DEFAULT_RATE_DATE_STR)))
                .andExpect(jsonPath("$.[*].dbIr").value(hasItem(DEFAULT_DB_IR.doubleValue())))
                .andExpect(jsonPath("$.[*].dbAmount").value(hasItem(DEFAULT_DB_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].crIr").value(hasItem(DEFAULT_CR_IR.doubleValue())))
                .andExpect(jsonPath("$.[*].crAmount").value(hasItem(DEFAULT_CR_AMOUNT.doubleValue())));
    }

    @Test
    @Transactional
    public void getRate() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        // Get the rate
        restRateMockMvc.perform(get("/api/rates/{id}", rate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(rate.getId().intValue()))
            .andExpect(jsonPath("$.accountNo").value(DEFAULT_ACCOUNT_NO.intValue()))
            .andExpect(jsonPath("$.rateDate").value(DEFAULT_RATE_DATE_STR))
            .andExpect(jsonPath("$.dbIr").value(DEFAULT_DB_IR.doubleValue()))
            .andExpect(jsonPath("$.dbAmount").value(DEFAULT_DB_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.crIr").value(DEFAULT_CR_IR.doubleValue()))
            .andExpect(jsonPath("$.crAmount").value(DEFAULT_CR_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRate() throws Exception {
        // Get the rate
        restRateMockMvc.perform(get("/api/rates/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRate() throws Exception {
        // Initialize the database
        rateService.save(rate);

        int databaseSizeBeforeUpdate = rateRepository.findAll().size();

        // Update the rate
        Rate updatedRate = new Rate();
        updatedRate.setId(rate.getId());
        updatedRate.setAccountNo(UPDATED_ACCOUNT_NO);
        updatedRate.setRateDate(UPDATED_RATE_DATE);
        updatedRate.setDbIr(UPDATED_DB_IR);
        updatedRate.setDbAmount(UPDATED_DB_AMOUNT);
        updatedRate.setCrIr(UPDATED_CR_IR);
        updatedRate.setCrAmount(UPDATED_CR_AMOUNT);

        restRateMockMvc.perform(put("/api/rates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedRate)))
                .andExpect(status().isOk());

        // Validate the Rate in the database
        List<Rate> rates = rateRepository.findAll();
        assertThat(rates).hasSize(databaseSizeBeforeUpdate);
        Rate testRate = rates.get(rates.size() - 1);
        assertThat(testRate.getAccountNo()).isEqualTo(UPDATED_ACCOUNT_NO);
        assertThat(testRate.getRateDate()).isEqualTo(UPDATED_RATE_DATE);
        assertThat(testRate.getDbIr()).isEqualTo(UPDATED_DB_IR);
        assertThat(testRate.getDbAmount()).isEqualTo(UPDATED_DB_AMOUNT);
        assertThat(testRate.getCrIr()).isEqualTo(UPDATED_CR_IR);
        assertThat(testRate.getCrAmount()).isEqualTo(UPDATED_CR_AMOUNT);
    }

    @Test
    @Transactional
    public void deleteRate() throws Exception {
        // Initialize the database
        rateService.save(rate);

        int databaseSizeBeforeDelete = rateRepository.findAll().size();

        // Get the rate
        restRateMockMvc.perform(delete("/api/rates/{id}", rate.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Rate> rates = rateRepository.findAll();
        assertThat(rates).hasSize(databaseSizeBeforeDelete - 1);
    }
}
