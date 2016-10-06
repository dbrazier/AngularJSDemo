package com.bss.mosaic.stars.repository;

import com.bss.mosaic.stars.domain.CaseNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@SuppressWarnings("unused")
public interface CaseNoteRepository extends JpaRepository<CaseNote, String> {

    @Query(value = "SELECT * FROM (SELECT PZINSKEY, PXINSNAME, NVL( PXUPDATEDATETIME, PXCREATEDATETIME ) AS PXUPDATEDATETIME, PXOBJCLASS, " +
        "PXREFOBJECTKEY, PXATTACHKEY, PYLABEL, NOTETEXT " +
        "FROM PC_DATA_WORKATTACH_NOTE WHERE PXREFOBJECTKEY = :caseKey  " +
        "ORDER BY NVL( PXUPDATEDATETIME, PXCREATEDATETIME ) DESC) WHERE ROWNUM <= 1",
        nativeQuery=true
    )
    Optional<CaseNote> findByCaseKey(@Param("caseKey") final String caseKey);

}
