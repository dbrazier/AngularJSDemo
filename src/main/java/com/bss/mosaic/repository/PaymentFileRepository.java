package com.bss.mosaic.repository;

import com.bss.mosaic.domain.PaymentFile;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PaymentFile entity.
 */
@SuppressWarnings("unused")
public interface PaymentFileRepository extends JpaRepository<PaymentFile,Long> {

    List<PaymentFile> findByGfcId(final Long gfcId);
}
