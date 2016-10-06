package com.bss.mosaic.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.PaymentTransaction;
import com.bss.mosaic.service.PaymentTransactionService;
import com.bss.mosaic.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PaymentTransaction.
 */
@RestController
@RequestMapping("/api")
public class PaymentTransactionResource {

    private final Logger log = LoggerFactory.getLogger(PaymentTransactionResource.class);

    @Inject
    private PaymentTransactionService paymentTransactionService;

    /**
     * POST  /payment-transactions : Create a new paymentTransaction.
     *
     * @param paymentTransaction the paymentTransaction to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paymentTransaction, or with status 400 (Bad Request) if the paymentTransaction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/payment-transactions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentTransaction> createPaymentTransaction(@RequestBody PaymentTransaction paymentTransaction) throws URISyntaxException {
        log.debug("REST request to save PaymentTransaction : {}", paymentTransaction);
        if (paymentTransaction.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("paymentTransaction", "idexists", "A new paymentTransaction cannot already have an ID")).body(null);
        }
        PaymentTransaction result = paymentTransactionService.save(paymentTransaction);
        return ResponseEntity.created(new URI("/api/payment-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("paymentTransaction", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /payment-transactions : Updates an existing paymentTransaction.
     *
     * @param paymentTransaction the paymentTransaction to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paymentTransaction,
     * or with status 400 (Bad Request) if the paymentTransaction is not valid,
     * or with status 500 (Internal Server Error) if the paymentTransaction couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/payment-transactions",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentTransaction> updatePaymentTransaction(@RequestBody PaymentTransaction paymentTransaction) throws URISyntaxException {
        log.debug("REST request to update PaymentTransaction : {}", paymentTransaction);
        if (paymentTransaction.getId() == null) {
            return createPaymentTransaction(paymentTransaction);
        }
        PaymentTransaction result = paymentTransactionService.save(paymentTransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("paymentTransaction", paymentTransaction.getId().toString()))
            .body(result);
    }

    /**
     * GET  /payment-transactions : get all the paymentTransactions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of paymentTransactions in body
     */
    @RequestMapping(value = "/payment-transactions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PaymentTransaction> getAllPaymentTransactions() {
        log.debug("REST request to get all PaymentTransactions");
        return paymentTransactionService.findAll();
    }

    /**
     * GET  /payment-transactions/:id : get the "id" paymentTransaction.
     *
     * @param id the id of the paymentTransaction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the paymentTransaction, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/payment-transactions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentTransaction> getPaymentTransaction(@PathVariable Long id) {
        log.debug("REST request to get PaymentTransaction : {}", id);
        PaymentTransaction paymentTransaction = paymentTransactionService.findOne(id);
        return Optional.ofNullable(paymentTransaction)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /payment-transactions/:id : delete the "id" paymentTransaction.
     *
     * @param id the id of the paymentTransaction to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/payment-transactions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePaymentTransaction(@PathVariable Long id) {
        log.debug("REST request to delete PaymentTransaction : {}", id);
        paymentTransactionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("paymentTransaction", id.toString())).build();
    }

}
