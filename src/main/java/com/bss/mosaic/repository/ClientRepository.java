package com.bss.mosaic.repository;

import com.bss.mosaic.domain.Client;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Client entity.
 */
@SuppressWarnings("unused")
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByGfcId(final  Long gfcId);
}
