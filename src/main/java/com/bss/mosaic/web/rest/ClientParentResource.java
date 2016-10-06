package com.bss.mosaic.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.ClientParent;
import com.bss.mosaic.service.ClientParentService;
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
 * REST controller for managing ClientParent.
 */
@RestController
@RequestMapping("/api")
public class ClientParentResource {

    private final Logger log = LoggerFactory.getLogger(ClientParentResource.class);

    @Inject
    private ClientParentService clientParentService;

    /**
     * POST  /client-parents : Create a new clientParent.
     *
     * @param clientParent the clientParent to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clientParent, or with status 400 (Bad Request) if the clientParent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-parents",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientParent> createClientParent(@RequestBody ClientParent clientParent) throws URISyntaxException {
        log.debug("REST request to save ClientParent : {}", clientParent);
        if (clientParent.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("clientParent", "idexists", "A new clientParent cannot already have an ID")).body(null);
        }
        ClientParent result = clientParentService.save(clientParent);
        return ResponseEntity.created(new URI("/api/client-parents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("clientParent", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /client-parents : Updates an existing clientParent.
     *
     * @param clientParent the clientParent to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientParent,
     * or with status 400 (Bad Request) if the clientParent is not valid,
     * or with status 500 (Internal Server Error) if the clientParent couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/client-parents",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientParent> updateClientParent(@RequestBody ClientParent clientParent) throws URISyntaxException {
        log.debug("REST request to update ClientParent : {}", clientParent);
        if (clientParent.getId() == null) {
            return createClientParent(clientParent);
        }
        ClientParent result = clientParentService.save(clientParent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("clientParent", clientParent.getId().toString()))
            .body(result);
    }

    /**
     * GET  /client-parents : get all the clientParents.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clientParents in body
     */
    @RequestMapping(value = "/client-parents",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ClientParent> getAllClientParents() {
        log.debug("REST request to get all ClientParents");
        return clientParentService.findAll();
    }

    /**
     * GET  /client-parents/:id : get the "id" clientParent.
     *
     * @param id the id of the clientParent to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clientParent, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/client-parents/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClientParent> getClientParent(@PathVariable Long id) {
        log.debug("REST request to get ClientParent : {}", id);
        ClientParent clientParent = clientParentService.findOne(id);
        return Optional.ofNullable(clientParent)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /client-parents/:id : delete the "id" clientParent.
     *
     * @param id the id of the clientParent to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/client-parents/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteClientParent(@PathVariable Long id) {
        log.debug("REST request to delete ClientParent : {}", id);
        clientParentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("clientParent", id.toString())).build();
    }

}
