package com.bss.mosaic.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.InquiryType;
import com.bss.mosaic.service.InquiryTypeService;
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
 * REST controller for managing InquiryType.
 */
@RestController
@RequestMapping("/api")
public class InquiryTypeResource {

    private final Logger log = LoggerFactory.getLogger(InquiryTypeResource.class);

    @Inject
    private InquiryTypeService inquiryTypeService;

    /**
     * POST  /inquiry-types : Create a new inquiryType.
     *
     * @param inquiryType the inquiryType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inquiryType, or with status 400 (Bad Request) if the inquiryType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/inquiry-types",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryType> createInquiryType(@RequestBody InquiryType inquiryType) throws URISyntaxException {
        log.debug("REST request to save InquiryType : {}", inquiryType);
        if (inquiryType.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("inquiryType", "idexists", "A new inquiryType cannot already have an ID")).body(null);
        }
        InquiryType result = inquiryTypeService.save(inquiryType);
        return ResponseEntity.created(new URI("/api/inquiry-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("inquiryType", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /inquiry-types : Updates an existing inquiryType.
     *
     * @param inquiryType the inquiryType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inquiryType,
     * or with status 400 (Bad Request) if the inquiryType is not valid,
     * or with status 500 (Internal Server Error) if the inquiryType couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/inquiry-types",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryType> updateInquiryType(@RequestBody InquiryType inquiryType) throws URISyntaxException {
        log.debug("REST request to update InquiryType : {}", inquiryType);
        if (inquiryType.getId() == null) {
            return createInquiryType(inquiryType);
        }
        InquiryType result = inquiryTypeService.save(inquiryType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("inquiryType", inquiryType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /inquiry-types : get all the inquiryTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inquiryTypes in body
     */
    @RequestMapping(value = "/inquiry-types",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<InquiryType> getAllInquiryTypes() {
        log.debug("REST request to get all InquiryTypes");
        return inquiryTypeService.findAll();
    }

    /**
     * GET  /inquiry-types/:id : get the "id" inquiryType.
     *
     * @param id the id of the inquiryType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inquiryType, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/inquiry-types/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryType> getInquiryType(@PathVariable Long id) {
        log.debug("REST request to get InquiryType : {}", id);
        InquiryType inquiryType = inquiryTypeService.findOne(id);
        return Optional.ofNullable(inquiryType)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /inquiry-types/:id : delete the "id" inquiryType.
     *
     * @param id the id of the inquiryType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/inquiry-types/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteInquiryType(@PathVariable Long id) {
        log.debug("REST request to delete InquiryType : {}", id);
        inquiryTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("inquiryType", id.toString())).build();
    }

}
