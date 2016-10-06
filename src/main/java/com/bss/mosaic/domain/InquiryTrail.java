package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A InquiryTrail.
 */
@Entity
@Table(name = "inquiry_trail")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InquiryTrail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "case_id")
    private String caseId;

    @Column(name = "cso_name")
    private String csoName;

    @Column(name = "cso_email")
    private String csoEmail;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_contact_no")
    private String clientContactNo;

    @Column(name = "client_contact_type")
    private String clientContactType;

    @Column(name = "inquiry_date")
    private ZonedDateTime inquiryDate;

    @Column(name = "response_date")
    private ZonedDateTime responseDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "cso_contact_type")
    private String csoContactType;

    @Column(name = "attachment")
    private String attachment;

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

    public String getCsoName() {
        return csoName;
    }

    public void setCsoName(String csoName) {
        this.csoName = csoName;
    }

    public String getCsoEmail() {
        return csoEmail;
    }

    public void setCsoEmail(String csoEmail) {
        this.csoEmail = csoEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientContactNo() {
        return clientContactNo;
    }

    public void setClientContactNo(String clientContactNo) {
        this.clientContactNo = clientContactNo;
    }

    public String getClientContactType() {
        return clientContactType;
    }

    public void setClientContactType(String clientContactType) {
        this.clientContactType = clientContactType;
    }

    public ZonedDateTime getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(ZonedDateTime inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public ZonedDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(ZonedDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCsoContactType() {
        return csoContactType;
    }

    public void setCsoContactType(String csoContactType) {
        this.csoContactType = csoContactType;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InquiryTrail inquiryTrail = (InquiryTrail) o;
        if(inquiryTrail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, inquiryTrail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InquiryTrail{" +
            "id=" + id +
            ", caseId='" + caseId + "'" +
            ", csoName='" + csoName + "'" +
            ", csoEmail='" + csoEmail + "'" +
            ", clientName='" + clientName + "'" +
            ", clientEmail='" + clientEmail + "'" +
            ", clientContactNo='" + clientContactNo + "'" +
            ", clientContactType='" + clientContactType + "'" +
            ", inquiryDate='" + inquiryDate + "'" +
            ", responseDate='" + responseDate + "'" +
            ", notes='" + notes + "'" +
            ", csoContactType='" + csoContactType + "'" +
            ", attachment='" + attachment + "'" +
            '}';
    }
}
