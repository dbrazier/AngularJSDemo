package com.bss.mosaic.service;

import com.bss.mosaic.domain.Rate;
import com.bss.mosaic.repository.RateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Rate.
 */
@Service
@Transactional
public class RateService {

    private final Logger log = LoggerFactory.getLogger(RateService.class);

    @Inject
    private RateRepository rateRepository;

    /**
     * Save a rate.
     *
     * @param rate the entity to save
     * @return the persisted entity
     */
    public Rate save(Rate rate) {
        log.debug("Request to save Rate : {}", rate);
        Rate result = rateRepository.save(rate);
        return result;
    }

    /**
     *  Get all the rates.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Rate> findAll() {
        log.debug("Request to get all Rates");
        List<Rate> result = rateRepository.findAll();
        return result;
    }

    /**
     *  Get one rate by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Rate findOne(Long id) {
        log.debug("Request to get Rate : {}", id);
        Rate rate = rateRepository.findOne(id);
        return rate;
    }

    /**
     *  Delete the  rate by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Rate : {}", id);
        rateRepository.delete(id);
    }
}
