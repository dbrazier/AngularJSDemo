package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A PaymentTransaction.
 */
@Entity
@Table(name = "payment_transaction")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaymentTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gfc_id")
    private Long gfcId;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "txn_ref_id")
    private Long txnRefId;

    @Column(name = "txn_id")
    private Long txnId;

    @Column(name = "txn_status")
    private String txnStatus;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;

    @Column(name = "debit_acc_no")
    private Long debitAccNo;

    @Column(name = "credit_acc_no")
    private Long creditAccNo;

    @Column(name = "beneficiary_name")
    private String beneficiaryName;

    @Column(name = "status_description")
    private String statusDescription;

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

    public Long getTxnRefId() {
        return txnRefId;
    }

    public void setTxnRefId(Long txnRefId) {
        this.txnRefId = txnRefId;
    }

    public Long getTxnId() {
        return txnId;
    }

    public void setTxnId(Long txnId) {
        this.txnId = txnId;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getDebitAccNo() {
        return debitAccNo;
    }

    public void setDebitAccNo(Long debitAccNo) {
        this.debitAccNo = debitAccNo;
    }

    public Long getCreditAccNo() {
        return creditAccNo;
    }

    public void setCreditAccNo(Long creditAccNo) {
        this.creditAccNo = creditAccNo;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentTransaction paymentTransaction = (PaymentTransaction) o;
        if(paymentTransaction.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, paymentTransaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaymentTransaction{" +
            "id=" + id +
            ", gfcId='" + gfcId + "'" +
            ", fileId='" + fileId + "'" +
            ", txnRefId='" + txnRefId + "'" +
            ", txnId='" + txnId + "'" +
            ", txnStatus='" + txnStatus + "'" +
            ", currency='" + currency + "'" +
            ", amount='" + amount + "'" +
            ", debitAccNo='" + debitAccNo + "'" +
            ", creditAccNo='" + creditAccNo + "'" +
            ", beneficiaryName='" + beneficiaryName + "'" +
            ", statusDescription='" + statusDescription + "'" +
            '}';
    }
}
