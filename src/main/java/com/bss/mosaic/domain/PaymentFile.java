package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PaymentFile.
 */
@Entity
@Table(name = "payment_file")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaymentFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gfc_id")
    private Long gfcId;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_start")
    private ZonedDateTime fileStart;

    @Column(name = "gks_end")
    private String gksEnd;

    @Column(name = "last_step")
    private String lastStep;

    @Column(name = "no_txns")
    private Long noTxns;

    @Column(name = "failed_txns")
    private String failedTxns;

    @Column(name = "file_status")
    private String fileStatus;

    @Column(name = "file_profile")
    private String fileProfile;

    @Column(name = "source_system")
    private String sourceSystem;

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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ZonedDateTime getFileStart() {
        return fileStart;
    }

    public void setFileStart(ZonedDateTime fileStart) {
        this.fileStart = fileStart;
    }

    public String getGksEnd() {
        return gksEnd;
    }

    public void setGksEnd(String gksEnd) {
        this.gksEnd = gksEnd;
    }

    public String getLastStep() {
        return lastStep;
    }

    public void setLastStep(String lastStep) {
        this.lastStep = lastStep;
    }

    public Long getNoTxns() { return noTxns; }

    public void setNoTxns(Long noTxns) {
        this.noTxns = noTxns;
    }

    public String getFailedTxns() {
        return failedTxns;
    }

    public void setFailedTxns(String failedTxns) {
        this.failedTxns = failedTxns;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFileProfile() {
        return fileProfile;
    }

    public void setFileProfile(String fileProfile) {
        this.fileProfile = fileProfile;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentFile paymentFile = (PaymentFile) o;
        if(paymentFile.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, paymentFile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaymentFile{" +
            "id=" + id +
            ", gfcId='" + gfcId + "'" +
            ", fileId='" + fileId + "'" +
            ", profileId='" + profileId + "'" +
            ", fileName='" + fileName + "'" +
            ", fileStart='" + fileStart + "'" +
            ", gksEnd='" + gksEnd + "'" +
            ", lastStep='" + lastStep + "'" +
            ", noTxns='" + noTxns + "'" +
            ", failedTxns='" + failedTxns + "'" +
            ", fileStatus='" + fileStatus + "'" +
            ", fileProfile='" + fileProfile + "'" +
            ", sourceSystem='" + sourceSystem + "'" +
            '}';
    }
}
