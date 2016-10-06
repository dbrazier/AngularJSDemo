package com.bss.mosaic.repository;

import com.bss.mosaic.domain.InquiryInfo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InquiryInfo entity.
 */
@SuppressWarnings("unused")
public interface InquiryInfoRepository extends JpaRepository<InquiryInfo,Long> {

    InquiryInfo findByCaseId(final String caseId);

    List<InquiryInfo> findByGfcId(final  Long gfcId);

    List<InquiryInfo> findByGfcIdOrderByLastInteractionDesc(final  Long gfcId);
}
