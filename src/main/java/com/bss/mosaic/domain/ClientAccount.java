package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A ClientAccount.
 */
@Entity
@Table(name = "client_account")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClientAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_no")
    private Long accountNo;

    @Column(name = "gfc_id")
    private Long gfcId;

    @Column(name = "branch")
    private Long branch;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance", precision=10, scale=2)
    private BigDecimal balance;

    @Column(name = "restriction")
    private String restriction;

    @Column(name = "status")
    private String status;

    @Column(name = "iban")
    private String iban;


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

    public Long getGfcId() {
        return gfcId;
    }

    public void setGfcId(Long gfcId) {
        this.gfcId = gfcId;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientAccount clientAccount = (ClientAccount) o;
        if(clientAccount.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, clientAccount.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClientAccount{" +
            "id=" + id +
            ", accountNo='" + accountNo + "'" +
            ", gfcId='" + gfcId + "'" +
            ", branch='" + branch + "'" +
            ", accountName='" + accountName + "'" +
            ", currency='" + currency + "'" +
            ", balance='" + balance + "'" +
            ", restriction='" + restriction + "'" +
            ", status='" + status + "'" +
            ", iban='" + iban + "'" +
            '}';
    }
}
