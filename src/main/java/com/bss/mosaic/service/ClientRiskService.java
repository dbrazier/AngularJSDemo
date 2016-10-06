package com.bss.mosaic.service;

import com.bss.mosaic.domain.ClientRisk;
import com.bss.mosaic.repository.ClientRiskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing ClientRisk.
 */
@Service
@Transactional
public class ClientRiskService {

    private final Logger log = LoggerFactory.getLogger(ClientRiskService.class);

    @Inject
    private ClientRiskRepository clientRiskRepository;

    /**
     * Save a clientRisk.
     *
     * @param clientRisk the entity to save
     * @return the persisted entity
     */
    public ClientRisk save(ClientRisk clientRisk) {
        log.debug("Request to save ClientRisk : {}", clientRisk);
        ClientRisk result = clientRiskRepository.save(clientRisk);
        return result;
    }

    /**
     *  Get all the clientRisks.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ClientRisk> findAll() {
        log.debug("Request to get all ClientRisks");
        List<ClientRisk> result = clientRiskRepository.findAll();
        return result;
    }

    /**
     *  Get one clientRisk by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public ClientRisk findOne(Long id) {
        log.debug("Request to get ClientRisk : {}", id);
        ClientRisk clientRisk = clientRiskRepository.findOne(id);
        return clientRisk;
    }

    /**
     *  Delete the  clientRisk by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ClientRisk : {}", id);
        clientRiskRepository.delete(id);
    }
}
