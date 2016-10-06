package com.bss.mosaic.stars.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CASEID")
    private String caseId;

    @Column(name = "PZINSKEY")
    private String pzinsKey;

    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "ACCOUNTNO")
    private String accountNo;

    @Column(name = "ACCOUNTNAME")
    private String accountName;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "BASENUMBER")
    private String baseNumber;

    @Column(name = "GFCID")
    private String gfcId;

    @Column(name = "GFCNAME")
    private String gfcName;

    @Column(name = "GFPID")
    private String gfpId;

    @Column(name = "GFCPARENTNAME")
    private String gfcParentName;

    @Column(name = "WORLDLINKCLIENTID")
    private String worldlinkClientId;

    @Column(name = "WORLDLINKGCNCODE")
    private String worldlinkGcnCode;

    @Column(name = "CLIENTID")
    private String clientId;

    @Column(name = "SAFEWORDID")
    private String safewordId;

    @Column(name = "SERVICEMODEL")
    private String serviceModel;

    @Column(name = "TIER")
    private String tier;

    @Column(name = "CLIENTSECTOR")
    private String clientSector;

    @Column(name = "PXCREATEDATETIME")
    private Date createdate;

    @Column(name = "PXUPDATEDATETIME")
    private Date updateDate;

    @Column(name = "PYLABEL")
    private String pyLabel;

    @Column(name = "PYSTATUSWORK")
    private String status;

    @Column(name = "CONTACTNAME")
    private String contactName;

    @Column(name = "CASEOWNER")
    private String caseOwner;

    @Column(name = "CASEOWNERNAME")
    private String caseOwnerName;

    @Column(name = "INQUIRYTYPES")
    private String inquiryType;

    @Column(name = "PYSLANAME")
    private String slaName;

    @Column(name = "PYSLADEADLINE")
    private Date slaDeadline;

    @Column(name = "PXUPDATEOPERATOR")
    private String updatedById;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Boolean slaExpired;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getPzinsKey() {
        return pzinsKey;
    }

    public void setPzinsKey(String pzinsKey) {
        this.pzinsKey = pzinsKey;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(String baseNumber) {
        this.baseNumber = baseNumber;
    }

    public String getGfcId() {
        return gfcId;
    }

    public void setGfcId(String gfcId) {
        this.gfcId = gfcId;
    }

    public String getGfcName() {
        return gfcName;
    }

    public void setGfcName(String gfcName) {
        this.gfcName = gfcName;
    }

    public String getGfpId() {
        return gfpId;
    }

    public void setGfpId(String gfpId) {
        this.gfpId = gfpId;
    }

    public String getGfcParentName() {
        return gfcParentName;
    }

    public void setGfcParentName(String gfcParentName) {
        this.gfcParentName = gfcParentName;
    }

    public String getWorldlinkClientId() {
        return worldlinkClientId;
    }

    public void setWorldlinkClientId(String worldlinkClientId) {
        this.worldlinkClientId = worldlinkClientId;
    }

    public String getWorldlinkGcnCode() {
        return worldlinkGcnCode;
    }

    public void setWorldlinkGcnCode(String worldlinkGcnCode) {
        this.worldlinkGcnCode = worldlinkGcnCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSafewordId() {
        return safewordId;
    }

    public void setSafewordId(String safewordId) {
        this.safewordId = safewordId;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPyLabel() {
        return pyLabel;
    }

    public void setPyLabel(String pyLabel) {
        this.pyLabel = pyLabel;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaseOwner() {
        return caseOwner;
    }

    public void setCaseOwner(String caseOwner) {
        this.caseOwner = caseOwner;
    }

    public String getInquiryType() {
        return inquiryType;
    }

    public void setInquiryType(String inquiryType) {
        this.inquiryType = inquiryType;
    }

    public String getSlaName() {
        return slaName;
    }

    public void setSlaName(String slaName) {
        this.slaName = slaName;
    }

    public Date getSlaDeadline() {
        return slaDeadline;
    }

    public void setSlaDeadline(Date slaDeadline) {
        this.slaDeadline = slaDeadline;
    }

    public String getCaseOwnerName() {
        return caseOwnerName;
    }

    public void setCaseOwnerName(String caseOwnerName) {
        this.caseOwnerName = caseOwnerName;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public Boolean getSlaExpired() {
        return slaExpired;
    }

    public void setSlaExpired(Boolean slaExpired) {
        this.slaExpired = slaExpired;
    }
}
