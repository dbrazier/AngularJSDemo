package com.bss.mosaic.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.ClientRisk;
import com.bss.mosaic.service.ClientRiskService;
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
 * REST controller for managing ClientRisk.
 */
@RestController
@RequestMapping("/api")
public class ClientRiskResource {

    private final Logger log = LoggerFactory.getLogger(ClientRiskResource.class);

    @Inject
    private ClientRiskService clientRiskService;

    /**
     * POST  /client-risks : Create a new clientRisk.
     *
     * @param clientRisk the clientRisk to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clientRisk, or with status 400 (Bad Request) if the clientRisk has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-risks",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientRisk> createClientRisk(@RequestBody ClientRisk clientRisk) throws URISyntaxException {
        log.debug("REST request to save ClientRisk : {}", clientRisk);
        if (clientRisk.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("clientRisk", "idexists", "A new clientRisk cannot already have an ID")).body(null);
        }
        ClientRisk result = clientRiskService.save(clientRisk);
        return ResponseEntity.created(new URI("/api/client-risks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("clientRisk", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /client-risks : Updates an existing clientRisk.
     *
     * @param clientRisk the clientRisk to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientRisk,
     * or with status 400 (Bad Request) if the clientRisk is not valid,
     * or with status 500 (Internal Server Error) if the clientRisk couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-risks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientRisk> updateClientRisk(@RequestBody ClientRisk clientRisk) throws URISyntaxException {
        log.debug("REST request to update ClientRisk : {}", clientRisk);
        if (clientRisk.getId() == null) {
            return createClientRisk(clientRisk);
        }
        ClientRisk result = clientRiskService.save(clientRisk);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("clientRisk", clientRisk.getId().toString()))
            .body(result);
    }

    /**
     * GET  /client-risks : get all the clientRisks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clientRisks in body
     */
    @RequestMapping(value = "/client-risks",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ClientRisk> getAllClientRisks() {
        log.debug("REST request to get all ClientRisks");
        return clientRiskService.findAll();
    }

    /**
     * GET  /client-risks/:id : get the "id" clientRisk.
     *
     * @param id the id of the clientRisk to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clientRisk, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/client-risks/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientRisk> getClientRisk(@PathVariable Long id) {
        log.debug("REST request to get ClientRisk : {}", id);
        ClientRisk clientRisk = clientRiskService.findOne(id);
        return Optional.ofNullable(clientRisk)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /client-risks/:id : delete the "id" clientRisk.
     *
     * @param id the id of the clientRisk to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/client-risks/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteClientRisk(@PathVariable Long id) {
        log.debug("REST request to delete ClientRisk : {}", id);
        clientRiskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("clientRisk", id.toString())).build();
    }

}
