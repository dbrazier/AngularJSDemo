package com.bss.mosaic.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.PaymentFile;
import com.bss.mosaic.service.PaymentFileService;
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
 * REST controller for managing PaymentFile.
 */
@RestController
@RequestMapping("/api")
public class PaymentFileResource {

    private final Logger log = LoggerFactory.getLogger(PaymentFileResource.class);

    @Inject
    private PaymentFileService paymentFileService;

    /**
     * POST  /payment-files : Create a new paymentFile.
     *
     * @param paymentFile the paymentFile to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paymentFile, or with status 400 (Bad Request) if the paymentFile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/payment-files",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentFile> createPaymentFile(@RequestBody PaymentFile paymentFile) throws URISyntaxException {
        log.debug("REST request to save PaymentFile : {}", paymentFile);
        if (paymentFile.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("paymentFile", "idexists", "A new paymentFile cannot already have an ID")).body(null);
        }
        PaymentFile result = paymentFileService.save(paymentFile);
        return ResponseEntity.created(new URI("/api/payment-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("paymentFile", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /payment-files : Updates an existing paymentFile.
     *
     * @param paymentFile the paymentFile to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paymentFile,
     * or with status 400 (Bad Request) if the paymentFile is not valid,
     * or with status 500 (Internal Server Error) if the paymentFile couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/payment-files",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentFile> updatePaymentFile(@RequestBody PaymentFile paymentFile) throws URISyntaxException {
        log.debug("REST request to update PaymentFile : {}", paymentFile);
        if (paymentFile.getId() == null) {
            return createPaymentFile(paymentFile);
        }
        PaymentFile result = paymentFileService.save(paymentFile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("paymentFile", paymentFile.getId().toString()))
            .body(result);
    }

    /**
     * GET  /payment-files : get all the paymentFiles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of paymentFiles in body
     */
    @RequestMapping(value = "/payment-files",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PaymentFile> getAllPaymentFiles() {
        log.debug("REST request to get all PaymentFiles");
        return paymentFileService.findAll();
    }

    /**
     * GET  /payment-files/:id : get the "id" paymentFile.
     *
     * @param id the id of the paymentFile to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the paymentFile, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/payment-files/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentFile> getPaymentFile(@PathVariable Long id) {
        log.debug("REST request to get PaymentFile : {}", id);
        PaymentFile paymentFile = paymentFileService.findOne(id);
        return Optional.ofNullable(paymentFile)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /payment-files/:id : delete the "id" paymentFile.
     *
     * @param id the id of the paymentFile to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/payment-files/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePaymentFile(@PathVariable Long id) {
        log.debug("REST request to delete PaymentFile : {}", id);
        paymentFileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("paymentFile", id.toString())).build();
    }

}
