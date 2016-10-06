package com.bss.mosaic.service;

import com.bss.mosaic.domain.InquiryType;
import com.bss.mosaic.repository.InquiryTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing InquiryType.
 */
@Service
@Transactional
public class InquiryTypeService {

    private final Logger log = LoggerFactory.getLogger(InquiryTypeService.class);

    @Inject
    private InquiryTypeRepository inquiryTypeRepository;

    /**
     * Save a inquiryType.
     *
     * @param inquiryType the entity to save
     * @return the persisted entity
     */
    public InquiryType save(InquiryType inquiryType) {
        log.debug("Request to save InquiryType : {}", inquiryType);
        InquiryType result = inquiryTypeRepository.save(inquiryType);
        return result;
    }

    /**
     *  Get all the inquiryTypes.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<InquiryType> findAll() {
        log.debug("Request to get all InquiryTypes");
        List<InquiryType> result = inquiryTypeRepository.findAll();
        return result;
    }

    /**
     *  Get one inquiryType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public InquiryType findOne(Long id) {
        log.debug("Request to get InquiryType : {}", id);
        InquiryType inquiryType = inquiryTypeRepository.findOne(id);
        return inquiryType;
    }

    /**
     *  Delete the  inquiryType by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete InquiryType : {}", id);
        inquiryTypeRepository.delete(id);
    }
}
