package com.bss.mosaic.service;

import com.bss.mosaic.domain.PaymentFile;
import com.bss.mosaic.repository.PaymentFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing PaymentFile.
 */
@Service
@Transactional
public class PaymentFileService {

    private final Logger log = LoggerFactory.getLogger(PaymentFileService.class);

    @Inject
    private PaymentFileRepository paymentFileRepository;

    /**
     * Save a paymentFile.
     *
     * @param paymentFile the entity to save
     * @return the persisted entity
     */
    public PaymentFile save(PaymentFile paymentFile) {
        log.debug("Request to save PaymentFile : {}", paymentFile);
        PaymentFile result = paymentFileRepository.save(paymentFile);
        return result;
    }

    /**
     *  Get all the paymentFiles.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PaymentFile> findAll() {
        log.debug("Request to get all PaymentFiles");
        List<PaymentFile> result = paymentFileRepository.findAll();
        return result;
    }

    /**
     *  Get one paymentFile by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PaymentFile findOne(Long id) {
        log.debug("Request to get PaymentFile : {}", id);
        PaymentFile paymentFile = paymentFileRepository.findOne(id);
        return paymentFile;
    }

    /**
     *  Delete the  paymentFile by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PaymentFile : {}", id);
        paymentFileRepository.delete(id);
    }
}
