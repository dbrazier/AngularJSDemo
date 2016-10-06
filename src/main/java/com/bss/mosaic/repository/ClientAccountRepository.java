package com.bss.mosaic.repository;

import com.bss.mosaic.domain.ClientAccount;

import org.springframework.data.jpa.repository.*;

import java.util.Optional;

/**
 * Spring Data JPA repository for the ClientAccount entity.
 */
@SuppressWarnings("unused")
public interface ClientAccountRepository extends JpaRepository<ClientAccount,Long> {

    Optional<ClientAccount> findByAccountNo(final Long accountNo);
}
