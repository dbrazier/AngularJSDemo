package com.bss.mosaic.service;

import com.bss.mosaic.domain.ClientParent;
import com.bss.mosaic.repository.ClientParentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing ClientParent.
 */
@Service
@Transactional
public class ClientParentService {

    private final Logger log = LoggerFactory.getLogger(ClientParentService.class);

    @Inject
    private ClientParentRepository clientParentRepository;

    /**
     * Save a clientParent.
     *
     * @param clientParent the entity to save
     * @return the persisted entity
     */
    public ClientParent save(ClientParent clientParent) {
        log.debug("Request to save ClientParent : {}", clientParent);
        ClientParent result = clientParentRepository.save(clientParent);
        return result;
    }

    /**
     *  Get all the clientParents.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ClientParent> findAll() {
        log.debug("Request to get all ClientParents");
        List<ClientParent> result = clientParentRepository.findAll();
        return result;
    }

    /**
     *  Get one clientParent by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public ClientParent findOne(Long id) {
        log.debug("Request to get ClientParent : {}", id);
        ClientParent clientParent = clientParentRepository.findOne(id);
        return clientParent;
    }

    /**
     *  Delete the  clientParent by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ClientParent : {}", id);
        clientParentRepository.delete(id);
    }
}
