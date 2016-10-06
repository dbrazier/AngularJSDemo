package com.bss.mosaic.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Search.
 */
@Entity
@Table(name = "search")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_no")
    private Long accountNo;

    @Column(name = "case_id")
    private String caseId;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String gfcId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getGfcId() {
        return gfcId;
    }

    public void setGfcId(String gfcId) {
        this.gfcId = gfcId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Search search = (Search) o;
        if(search.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, search.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Search{" +
            "id=" + id +
            ", accountNo='" + accountNo + "'" +
            ", caseId='" + caseId + "'" +
            ", gfcId='" + gfcId + "'" +
            '}';
    }
}
