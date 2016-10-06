package com.bss.mosaic.web.rest;

import com.bss.mosaic.domain.InquiryTrail;
import com.bss.mosaic.service.InquiryTrailService;
import com.bss.mosaic.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
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
 * REST controller for managing InquiryTrail.
 */
@RestController
@RequestMapping("/api")
public class InquiryTrailResource {

    private final Logger log = LoggerFactory.getLogger(InquiryTrailResource.class);

    @Inject
    private InquiryTrailService inquiryTrailService;

    /**
     * POST  /inquiry-trails : Create a new inquiryTrail.
     *
     * @param inquiryTrail the inquiryTrail to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inquiryTrail, or with status 400 (Bad Request) if the inquiryTrail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/inquiry-trails",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryTrail> createInquiryTrail(@RequestBody InquiryTrail inquiryTrail) throws URISyntaxException {
        log.debug("REST request to save InquiryTrail : {}", inquiryTrail);
        if (inquiryTrail.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("inquiryTrail", "idexists", "A new inquiryTrail cannot already have an ID")).body(null);
        }
        InquiryTrail result = inquiryTrailService.save(inquiryTrail);
        return ResponseEntity.created(new URI("/api/inquiry-trails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("inquiryTrail", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /inquiry-trails : Updates an existing inquiryTrail.
     *
     * @param inquiryTrail the inquiryTrail to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inquiryTrail,
     * or with status 400 (Bad Request) if the inquiryTrail is not valid,
     * or with status 500 (Internal Server Error) if the inquiryTrail couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/inquiry-trails",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryTrail> updateInquiryTrail(@RequestBody InquiryTrail inquiryTrail) throws URISyntaxException {
        log.debug("REST request to update InquiryTrail : {}", inquiryTrail);
        if (inquiryTrail.getId() == null) {
            return createInquiryTrail(inquiryTrail);
        }
        InquiryTrail result = inquiryTrailService.save(inquiryTrail);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("inquiryTrail", inquiryTrail.getId().toString()))
            .body(result);
    }

    /**
     * GET  /inquiry-trails : get all the inquiryTrails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inquiryTrails in body
     */
    @RequestMapping(value = "/inquiry-trails",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<InquiryTrail> getAllInquiryTrails() {
        log.debug("REST request to get all InquiryTrails");
        return inquiryTrailService.findAll();
    }

    /**
     * GET  /inquiry-trails/:id : get the "id" inquiryTrail.
     *
     * @param id the id of the inquiryTrail to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inquiryTrail, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/inquiry-trails/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryTrail> getInquiryTrail(@PathVariable Long id) {
        log.debug("REST request to get InquiryTrail : {}", id);
        InquiryTrail inquiryTrail = inquiryTrailService.findOne(id);
        return Optional.ofNullable(inquiryTrail)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /inquiry-trails/:id : delete the "id" inquiryTrail.
     *
     * @param id the id of the inquiryTrail to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/inquiry-trails/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteInquiryTrail(@PathVariable Long id) {
        log.debug("REST request to delete InquiryTrail : {}", id);
        inquiryTrailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("inquiryTrail", id.toString())).build();
    }

}
