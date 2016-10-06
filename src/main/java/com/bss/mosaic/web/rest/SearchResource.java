package com.bss.mosaic.web.rest;

import com.bss.mosaic.domain.SearchResult;
import com.codahale.metrics.annotation.Timed;
import com.bss.mosaic.domain.Search;
import com.bss.mosaic.service.SearchService;
import com.bss.mosaic.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing Search.
 */
@RestController
@RequestMapping("/api")
public class SearchResource {

    private final Logger log = LoggerFactory.getLogger(SearchResource.class);

    @Inject
    private SearchService searchService;

    @RequestMapping(value = "/searches",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SearchResult> search(@RequestBody Search search) throws URISyntaxException {

        if (search.getAccountNo() == null && search.getCaseId() == null && search.getGfcId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("search", "empty", "Must enter either Account Number or Case Id")).body(null);
        }

        Optional<SearchResult> searchResult = null;
        if (search.getCaseId() != null && !search.getCaseId().isEmpty())
            searchResult = searchService.findByCaseId(search.getCaseId());
        else if (search.getAccountNo() != null)
            searchResult = searchService.findByAccountNo(search.getAccountNo());
        else if (search.getGfcId() != null && !search.getGfcId().isEmpty())
            searchResult = searchService.findByGfcId(search.getGfcId());

        return searchResult
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }


}
