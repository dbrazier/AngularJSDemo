package com.bss.mosaic.service;

import com.bss.mosaic.domain.ClientAccount;
import com.bss.mosaic.repository.ClientAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing ClientAccount.
 */
@Service
@Transactional
public class ClientAccountService {

    private final Logger log = LoggerFactory.getLogger(ClientAccountService.class);

    @Inject
    private ClientAccountRepository clientAccountRepository;

    /**
     * Save a clientAccount.
     *
     * @param clientAccount the entity to save
     * @return the persisted entity
     */
    public ClientAccount save(ClientAccount clientAccount) {
        log.debug("Request to save ClientAccount : {}", clientAccount);
        ClientAccount result = clientAccountRepository.save(clientAccount);
        return result;
    }

    /**
     *  Get all the clientAccounts.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ClientAccount> findAll() {
        log.debug("Request to get all ClientAccounts");
        List<ClientAccount> result = clientAccountRepository.findAll();
        return result;
    }

    /**
     *  Get one clientAccount by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public ClientAccount findOne(Long id) {
        log.debug("Request to get ClientAccount : {}", id);
        ClientAccount clientAccount = clientAccountRepository.findOne(id);
        return clientAccount;
    }

    /**
     *  Delete the  clientAccount by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ClientAccount : {}", id);
        clientAccountRepository.delete(id);
    }
}
