package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InquiryType.
 */
@Entity
@Table(name = "inquiry_type")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InquiryType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "inquiry_type")
    private String inquiryType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInquiryType() {
        return inquiryType;
    }

    public void setInquiryType(String inquiryType) {
        this.inquiryType = inquiryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InquiryType inquiryType = (InquiryType) o;
        if(inquiryType.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, inquiryType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InquiryType{" +
            "id=" + id +
            ", inquiryType='" + inquiryType + "'" +
            '}';
    }
}
