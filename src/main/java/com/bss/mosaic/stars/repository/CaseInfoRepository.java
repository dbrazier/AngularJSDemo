package com.bss.mosaic.stars.repository;

import com.bss.mosaic.stars.domain.CaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by db44417 on 23/08/2016.
 */
@SuppressWarnings("unused")
public interface CaseInfoRepository extends JpaRepository<CaseInfo, String> {

    @Query(value = "SELECT PYID AS CASEID, PZINSKEY, BRANCH, ACCOUNTNO, ACCOUNTNAME, BASECURRENCY AS CURRENCY, BASENUMBER, " +
        "GFCID, GFCNAME, GFPID, GFCPARENTNAME, WORLDLINKCLIENTID, WORLDLINKGCNCODE, CLIENTID, SAFEWORDID, SERVICEMODEL, " +
        "TIER, CLIENTSECTOR, PXCREATEDATETIME, PXUPDATEDATETIME, PYLABEL, PYSLANAME, PYSLADEADLINE, PYSTATUSWORK, " +
        "CONTACTNAME, CASEOWNER, CASEOWNERNAME, INQUIRYTYPES, PXUPDATEOPERATOR " +
        "FROM PCA_WORK WHERE PYID = :caseId",
        nativeQuery=true
    )
    Optional<CaseInfo> findByCaseId(@Param("caseId") final String caseId);



    @Query(value = "SELECT * FROM (SELECT PYID AS CASEID, PZINSKEY, BRANCH, ACCOUNTNO, ACCOUNTNAME, BASECURRENCY AS CURRENCY, BASENUMBER, " +
        "GFCID, GFCNAME, GFPID, GFCPARENTNAME, WORLDLINKCLIENTID, WORLDLINKGCNCODE, CLIENTID, SAFEWORDID, SERVICEMODEL, " +
        "TIER, CLIENTSECTOR, PXCREATEDATETIME, PXUPDATEDATETIME, PYLABEL, PYSLANAME, PYSLADEADLINE, PYSTATUSWORK, " +
        "CONTACTNAME, CASEOWNER, CASEOWNERNAME, INQUIRYTYPES, PXUPDATEOPERATOR " +
        "FROM PCA_WORK WHERE ACCOUNTNO = :accountNo and GFCID IS NOT NULL) WHERE ROWNUM <= 1",
        nativeQuery=true
    )
    Optional<CaseInfo> findByAccountNo(@Param("accountNo") final String accountNo);


    @Query(value = "SELECT * FROM (SELECT PYID AS CASEID, PZINSKEY, BRANCH, ACCOUNTNO, ACCOUNTNAME, BASECURRENCY AS CURRENCY, BASENUMBER, " +
        "GFCID, GFCNAME, GFPID, GFCPARENTNAME, WORLDLINKCLIENTID, WORLDLINKGCNCODE, CLIENTID, SAFEWORDID, SERVICEMODEL, " +
        "TIER, CLIENTSECTOR, PXCREATEDATETIME, PXUPDATEDATETIME, PYLABEL, PYSLANAME, PYSLADEADLINE, PYSTATUSWORK, " +
        "CONTACTNAME, CASEOWNER, CASEOWNERNAME, INQUIRYTYPES, PXUPDATEOPERATOR " +
        "FROM PCA_WORK WHERE GFCID = :gfcId " +
        "ORDER BY PXUPDATEDATETIME DESC) WHERE ROWNUM <= 1 ",
        nativeQuery=true
    )
    Optional<CaseInfo> findLatestByGfcId(@Param("gfcId") final String gfcId);


    @Query(value = "SELECT * FROM (SELECT PYID AS CASEID, PZINSKEY, BRANCH, ACCOUNTNO, ACCOUNTNAME, BASECURRENCY AS CURRENCY, BASENUMBER, " +
        "GFCID, GFCNAME, GFPID, GFCPARENTNAME, WORLDLINKCLIENTID, WORLDLINKGCNCODE, CLIENTID, SAFEWORDID, SERVICEMODEL, " +
        "TIER, CLIENTSECTOR, PXCREATEDATETIME, PXUPDATEDATETIME, PYLABEL, PYSLANAME, PYSLADEADLINE, PYSTATUSWORK, " +
        "CONTACTNAME, CASEOWNER, CASEOWNERNAME, INQUIRYTYPES, PXUPDATEOPERATOR " +
        "FROM PCA_WORK WHERE GFCID = :gfcId " +
        "ORDER BY PXUPDATEDATETIME DESC) WHERE ROWNUM <= 5 ",
        nativeQuery=true
    )
    List<CaseInfo> findByGfcId(@Param("gfcId") final String gfcId);

}

