package com.bss.mosaic.service;

import com.bss.mosaic.domain.InquiryTrail;
import com.bss.mosaic.repository.InquiryTrailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing InquiryTrail.
 */
@Service
@Transactional
public class InquiryTrailService {

    private final Logger log = LoggerFactory.getLogger(InquiryTrailService.class);

    @Inject
    private InquiryTrailRepository inquiryTrailRepository;

    /**
     * Save a inquiryTrail.
     *
     * @param inquiryTrail the entity to save
     * @return the persisted entity
     */
    public InquiryTrail save(InquiryTrail inquiryTrail) {
        log.debug("Request to save InquiryTrail : {}", inquiryTrail);
        InquiryTrail result = inquiryTrailRepository.save(inquiryTrail);
        return result;
    }

    /**
     *  Get all the inquiryTrails.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<InquiryTrail> findAll() {
        log.debug("Request to get all InquiryTrails");
        List<InquiryTrail> result = inquiryTrailRepository.findAll();
        return result;
    }

    /**
     *  Get one inquiryTrail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public InquiryTrail findOne(Long id) {
        log.debug("Request to get InquiryTrail : {}", id);
        InquiryTrail inquiryTrail = inquiryTrailRepository.findOne(id);
        return inquiryTrail;
    }

    /**
     *  Delete the  inquiryTrail by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete InquiryTrail : {}", id);
        inquiryTrailRepository.delete(id);
    }
}
