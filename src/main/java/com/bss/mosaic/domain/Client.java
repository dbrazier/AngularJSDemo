package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gfc_id")
    private Long gfcId;

    @Column(name = "gfc_name")
    private String gfcName;

    @Column(name = "gfp_id")
    private Long gfpId;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "service_model")
    private String serviceModel;

    @Column(name = "tier")
    private String tier;

    @Column(name = "client_sector")
    private String clientSector;

    @Column(name = "no_accounts")
    private Integer noAccounts;

    @Column(name = "last_interaction")
    private ZonedDateTime lastInteraction;

    @Column(name = "inquiry_volume")
    private String inquiryVolume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGfcId() {
        return gfcId;
    }

    public void setGfcId(Long gfcId) {
        this.gfcId = gfcId;
    }

    public String getGfcName() {
        return gfcName;
    }

    public void setGfcName(String gfcName) {
        this.gfcName = gfcName;
    }

    public Long getGfpId() {
        return gfpId;
    }

    public void setGfpId(Long gfpId) {
        this.gfpId = gfpId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getServiceModel() {
        return serviceModel;
    }

    public void setServiceModel(String serviceModel) {
        this.serviceModel = serviceModel;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getClientSector() {
        return clientSector;
    }

    public void setClientSector(String clientSector) {
        this.clientSector = clientSector;
    }

    public Integer getNoAccounts() {
        return noAccounts;
    }

    public void setNoAccounts(Integer noAccounts) {
        this.noAccounts = noAccounts;
    }

    public ZonedDateTime getLastInteraction() {
        return lastInteraction;
    }

    public void setLastInteraction(ZonedDateTime lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    public String getInquiryVolume() {
        return inquiryVolume;
    }

    public void setInquiryVolume(String inquiryVolume) {
        this.inquiryVolume = inquiryVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        if(client.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + id +
            ", gfcId='" + gfcId + "'" +
            ", gfcName='" + gfcName + "'" +
            ", gfpId='" + gfpId + "'" +
            ", contactName='" + contactName + "'" +
            ", contactNo='" + contactNo + "'" +
            ", contactEmail='" + contactEmail + "'" +
            ", serviceModel='" + serviceModel + "'" +
            ", tier='" + tier + "'" +
            ", clientSector='" + clientSector + "'" +
            ", noAccounts='" + noAccounts + "'" +
            ", lastInteraction='" + lastInteraction + "'" +
            ", inquiryVolume='" + inquiryVolume + "'" +
            '}';
    }
}
