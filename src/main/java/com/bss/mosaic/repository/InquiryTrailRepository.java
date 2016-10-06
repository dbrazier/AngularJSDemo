package com.bss.mosaic.repository;

import com.bss.mosaic.domain.InquiryTrail;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InquiryTrail entity.
 */
@SuppressWarnings("unused")
public interface InquiryTrailRepository extends JpaRepository<InquiryTrail,Long> {

    List<InquiryTrail> findByCaseIdOrderByInquiryDateDesc(final String caseId);
}
