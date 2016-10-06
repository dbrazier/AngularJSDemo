package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Rate.
 */
@Entity
@Table(name = "rate")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_no")
    private Long accountNo;

    @Column(name = "rate_date")
    private ZonedDateTime rateDate;

    @Column(name = "db_ir")
    private Double dbIr;

    @Column(name = "db_amount")
    private Double dbAmount;

    @Column(name = "cr_ir")
    private Double crIr;

    @Column(name = "cr_amount")
    private Double crAmount;

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

    public ZonedDateTime getRateDate() {
        return rateDate;
    }

    public void setRateDate(ZonedDateTime rateDate) {
        this.rateDate = rateDate;
    }

    public Double getDbIr() {
        return dbIr;
    }

    public void setDbIr(Double dbIr) {
        this.dbIr = dbIr;
    }

    public Double getDbAmount() {
        return dbAmount;
    }

    public void setDbAmount(Double dbAmount) {
        this.dbAmount = dbAmount;
    }

    public Double getCrIr() {
        return crIr;
    }

    public void setCrIr(Double crIr) {
        this.crIr = crIr;
    }

    public Double getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(Double crAmount) {
        this.crAmount = crAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rate rate = (Rate) o;
        if(rate.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Rate{" +
            "id=" + id +
            ", accountNo='" + accountNo + "'" +
            ", rateDate='" + rateDate + "'" +
            ", dbIr='" + dbIr + "'" +
            ", dbAmount='" + dbAmount + "'" +
            ", crIr='" + crIr + "'" +
            ", crAmount='" + crAmount + "'" +
            '}';
    }
}
