package com.bss.mosaic.service;

import com.bss.mosaic.domain.InquiryInfo;
import com.bss.mosaic.repository.InquiryInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing InquiryInfo.
 */
@Service
@Transactional
public class InquiryInfoService {

    private final Logger log = LoggerFactory.getLogger(InquiryInfoService.class);

    @Inject
    private InquiryInfoRepository inquiryInfoRepository;

    /**
     * Save a inquiryInfo.
     *
     * @param inquiryInfo the entity to save
     * @return the persisted entity
     */
    public InquiryInfo save(InquiryInfo inquiryInfo) {
        log.debug("Request to save InquiryInfo : {}", inquiryInfo);
        InquiryInfo result = inquiryInfoRepository.save(inquiryInfo);
        return result;
    }

    /**
     *  Get all the inquiryInfos.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<InquiryInfo> findAll() {
        log.debug("Request to get all InquiryInfos");
        List<InquiryInfo> result = inquiryInfoRepository.findAll();
        return result;
    }

    /**
     *  Get one inquiryInfo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public InquiryInfo findOne(Long id) {
        log.debug("Request to get InquiryInfo : {}", id);
        InquiryInfo inquiryInfo = inquiryInfoRepository.findOne(id);
        return inquiryInfo;
    }

    /**
     *  Delete the  inquiryInfo by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete InquiryInfo : {}", id);
        inquiryInfoRepository.delete(id);
    }
}
