package com.bss.mosaic.repository;

import com.bss.mosaic.domain.InquiryType;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the InquiryType entity.
 */
@SuppressWarnings("unused")
public interface InquiryTypeRepository extends JpaRepository<InquiryType,Long> {

}
