package com.bss.mosaic.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.ClientAccount;
import com.bss.mosaic.service.ClientAccountService;
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
 * REST controller for managing ClientAccount.
 */
@RestController
@RequestMapping("/api")
public class ClientAccountResource {

    private final Logger log = LoggerFactory.getLogger(ClientAccountResource.class);

    @Inject
    private ClientAccountService clientAccountService;

    /**
     * POST  /client-accounts : Create a new clientAccount.
     *
     * @param clientAccount the clientAccount to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clientAccount, or with status 400 (Bad Request) if the clientAccount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-accounts",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientAccount> createClientAccount(@RequestBody ClientAccount clientAccount) throws URISyntaxException {
        log.debug("REST request to save ClientAccount : {}", clientAccount);
        if (clientAccount.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("clientAccount", "idexists", "A new clientAccount cannot already have an ID")).body(null);
        }
        ClientAccount result = clientAccountService.save(clientAccount);
        return ResponseEntity.created(new URI("/api/client-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("clientAccount", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /client-accounts : Updates an existing clientAccount.
     *
     * @param clientAccount the clientAccount to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientAccount,
     * or with status 400 (Bad Request) if the clientAccount is not valid,
     * or with status 500 (Internal Server Error) if the clientAccount couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-accounts",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientAccount> updateClientAccount(@RequestBody ClientAccount clientAccount) throws URISyntaxException {
        log.debug("REST request to update ClientAccount : {}", clientAccount);
        if (clientAccount.getId() == null) {
            return createClientAccount(clientAccount);
        }
        ClientAccount result = clientAccountService.save(clientAccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("clientAccount", clientAccount.getId().toString()))
            .body(result);
    }

    /**
     * GET  /client-accounts : get all the clientAccounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clientAccounts in body
     */
    @RequestMapping(value = "/client-accounts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ClientAccount> getAllClientAccounts() {
        log.debug("REST request to get all ClientAccounts");
        return clientAccountService.findAll();
    }

    /**
     * GET  /client-accounts/:id : get the "id" clientAccount.
     *
     * @param id the id of the clientAccount to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clientAccount, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/client-accounts/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientAccount> getClientAccount(@PathVariable Long id) {
        log.debug("REST request to get ClientAccount : {}", id);
        ClientAccount clientAccount = clientAccountService.findOne(id);
        return Optional.ofNullable(clientAccount)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /client-accounts/:id : delete the "id" clientAccount.
     *
     * @param id the id of the clientAccount to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/client-accounts/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteClientAccount(@PathVariable Long id) {
        log.debug("REST request to delete ClientAccount : {}", id);
        clientAccountService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("clientAccount", id.toString())).build();
    }

}
