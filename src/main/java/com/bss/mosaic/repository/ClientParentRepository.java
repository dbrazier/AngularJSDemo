package com.bss.mosaic.repository;

import com.bss.mosaic.domain.ClientParent;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the ClientParent entity.
 */
@SuppressWarnings("unused")
public interface ClientParentRepository extends JpaRepository<ClientParent,Long> {

    ClientParent findByGfpId(final  Long gfpId);
}
