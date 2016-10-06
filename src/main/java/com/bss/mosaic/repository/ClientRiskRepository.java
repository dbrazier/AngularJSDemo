package com.bss.mosaic.repository;

import com.bss.mosaic.domain.ClientRisk;

import org.springframework.data.jpa.repository.*;

import java.util.Optional;

/**
 * Spring Data JPA repository for the ClientRisk entity.
 */
@SuppressWarnings("unused")
public interface ClientRiskRepository extends JpaRepository<ClientRisk,Long> {

    Optional<ClientRisk> findByGfcId(final Long gfcId);

}
