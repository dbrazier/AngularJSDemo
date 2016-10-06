package com.bss.mosaic.web.rest;

import com.bss.mosaic.service.InquiryInfoService;
import com.bss.mosaic.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.InquiryInfo;
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
 * REST controller for managing InquiryInfo.
 */
@RestController
@RequestMapping("/api")
public class InquiryInfoResource {

    private final Logger log = LoggerFactory.getLogger(InquiryInfoResource.class);

    @Inject
    private InquiryInfoService inquiryInfoService;

    /**
     * POST  /inquiry-infos : Create a new inquiryInfo.
     *
     * @param inquiryInfo the inquiryInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inquiryInfo, or with status 400 (Bad Request) if the inquiryInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/inquiry-infos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryInfo> createInquiryInfo(@RequestBody InquiryInfo inquiryInfo) throws URISyntaxException {
        log.debug("REST request to save InquiryInfo : {}", inquiryInfo);
        if (inquiryInfo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("inquiryInfo", "idexists", "A new inquiryInfo cannot already have an ID")).body(null);
        }
        InquiryInfo result = inquiryInfoService.save(inquiryInfo);
        return ResponseEntity.created(new URI("/api/inquiry-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("inquiryInfo", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /inquiry-infos : Updates an existing inquiryInfo.
     *
     * @param inquiryInfo the inquiryInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inquiryInfo,
     * or with status 400 (Bad Request) if the inquiryInfo is not valid,
     * or with status 500 (Internal Server Error) if the inquiryInfo couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/inquiry-infos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryInfo> updateInquiryInfo(@RequestBody InquiryInfo inquiryInfo) throws URISyntaxException {
        log.debug("REST request to update InquiryInfo : {}", inquiryInfo);
        if (inquiryInfo.getId() == null) {
            return createInquiryInfo(inquiryInfo);
        }
        InquiryInfo result = inquiryInfoService.save(inquiryInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("inquiryInfo", inquiryInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /inquiry-infos : get all the inquiryInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inquiryInfos in body
     */
    @RequestMapping(value = "/inquiry-infos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<InquiryInfo> getAllInquiryInfos() {
        log.debug("REST request to get all InquiryInfos");
        return inquiryInfoService.findAll();
    }

    /**
     * GET  /inquiry-infos/:id : get the "id" inquiryInfo.
     *
     * @param id the id of the inquiryInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inquiryInfo, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/inquiry-infos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InquiryInfo> getInquiryInfo(@PathVariable Long id) {
        log.debug("REST request to get InquiryInfo : {}", id);
        InquiryInfo inquiryInfo = inquiryInfoService.findOne(id);
        return Optional.ofNullable(inquiryInfo)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /inquiry-infos/:id : delete the "id" inquiryInfo.
     *
     * @param id the id of the inquiryInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/inquiry-infos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteInquiryInfo(@PathVariable Long id) {
        log.debug("REST request to delete InquiryInfo : {}", id);
        inquiryInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("inquiryInfo", id.toString())).build();
    }

}
