package com.bss.mosaic.repository;

import com.bss.mosaic.domain.Search;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Search entity.
 */
@SuppressWarnings("unused")
public interface SearchRepository extends JpaRepository<Search,Long> {

}
