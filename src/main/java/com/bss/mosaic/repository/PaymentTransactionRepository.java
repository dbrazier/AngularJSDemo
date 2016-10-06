package com.bss.mosaic.repository;

import com.bss.mosaic.domain.PaymentTransaction;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PaymentTransaction entity.
 */
@SuppressWarnings("unused")
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction,Long> {

    List<PaymentTransaction> findByGfcId(final Long gfcId);
    List<PaymentTransaction> findByFileId(final Long fileId);
}
