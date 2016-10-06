package com.bss.mosaic.service;

import com.bss.mosaic.domain.PaymentTransaction;
import com.bss.mosaic.repository.PaymentTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing PaymentTransaction.
 */
@Service
@Transactional
public class PaymentTransactionService {

    private final Logger log = LoggerFactory.getLogger(PaymentTransactionService.class);

    @Inject
    private PaymentTransactionRepository paymentTransactionRepository;

    /**
     * Save a paymentTransaction.
     *
     * @param paymentTransaction the entity to save
     * @return the persisted entity
     */
    public PaymentTransaction save(PaymentTransaction paymentTransaction) {
        log.debug("Request to save PaymentTransaction : {}", paymentTransaction);
        PaymentTransaction result = paymentTransactionRepository.save(paymentTransaction);
        return result;
    }

    /**
     *  Get all the paymentTransactions.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PaymentTransaction> findAll() {
        log.debug("Request to get all PaymentTransactions");
        List<PaymentTransaction> result = paymentTransactionRepository.findAll();
        return result;
    }

    /**
     *  Get one paymentTransaction by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PaymentTransaction findOne(Long id) {
        log.debug("Request to get PaymentTransaction : {}", id);
        PaymentTransaction paymentTransaction = paymentTransactionRepository.findOne(id);
        return paymentTransaction;
    }

    /**
     *  Delete the  paymentTransaction by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PaymentTransaction : {}", id);
        paymentTransactionRepository.delete(id);
    }
}
