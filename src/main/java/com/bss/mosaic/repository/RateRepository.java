package com.bss.mosaic.repository;

import com.bss.mosaic.domain.Rate;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Rate entity.
 */
@SuppressWarnings("unused")
public interface RateRepository extends JpaRepository<Rate,Long> {

    List<Rate> findByAccountNoOrderByRateDateDesc(Long accountNo);
    List<Rate> findAllByOrderByRateDateDesc();

}
