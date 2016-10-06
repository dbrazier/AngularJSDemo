package com.bss.mosaic.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A InquiryInfo.
 */
@Entity
@Table(name = "inquiry_info")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InquiryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "case_id")
    private String caseId;

    @Column(name = "gfc_id")
    private Long gfcId;

    @Column(name = "inquiry_type_id")
    private String inquiryTypeId;

    @Column(name = "case_owner")
    private String caseOwner;

    @Column(name = "status")
    private String status;

    @Column(name = "last_interaction")
    private ZonedDateTime lastInteraction;

    @Column(name = "sla")
    private Integer sla;

    @Column(name = "case_start_date")
    private ZonedDateTime caseStartDate;

    @Column(name = "urgency")
    private String urgency;

    @Column(name = "notes")
    private String notes;

    @Column(name = "account_no")
    private Long accountNo;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private ZonedDateTime slaEndDate;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String slaName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Long getGfcId() {
        return gfcId;
    }

    public void setGfcId(Long gfcId) {
        this.gfcId = gfcId;
    }

    public String getInquiryTypeId() {
        return inquiryTypeId;
    }

    public void setInquiryTypeId(String inquiryTypeId) {
        this.inquiryTypeId = inquiryTypeId;
    }

    public String getCaseOwner() {
        return caseOwner;
    }

    public void setCaseOwner(String caseOwner) {
        this.caseOwner = caseOwner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getLastInteraction() {
        return lastInteraction;
    }

    public void setLastInteraction(ZonedDateTime lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    public Integer getSla() {
        return sla;
    }

    public void setSla(Integer sla) {
        this.sla = sla;
    }

    public ZonedDateTime getCaseStartDate() {
        return caseStartDate;
    }

    public void setCaseStartDate(ZonedDateTime caseStartDate) {
        this.caseStartDate = caseStartDate;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public ZonedDateTime getSlaEndDate() {
        return slaEndDate;
    }

    public void setSlaEndDate(ZonedDateTime slaEndDate) {
        this.slaEndDate = slaEndDate;
    }

    public String getSlaName() {
        return slaName;
    }

    public void setSlaName(String slaName) {
        this.slaName = slaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InquiryInfo inquiryInfo = (InquiryInfo) o;
        if(inquiryInfo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, inquiryInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InquiryInfo{" +
            "id=" + id +
            ", caseId='" + caseId + "'" +
            ", gfcId='" + gfcId + "'" +
            ", inquiryTypeId='" + inquiryTypeId + "'" +
            ", caseOwner='" + caseOwner + "'" +
            ", status='" + status + "'" +
            ", lastInteraction='" + lastInteraction + "'" +
            ", sla='" + sla + "'" +
            ", caseStartDate='" + caseStartDate + "'" +
            ", urgency='" + urgency + "'" +
            ", notes='" + notes + "'" +
            ", accountNo='" + accountNo + "'" +
            '}';
    }
}
