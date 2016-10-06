package com.bss.mosaic.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ClientParent.
 */
@Entity
@Table(name = "client_parent")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClientParent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gfp_id")
    private Long gfpId;

    @Column(name = "gfp_name")
    private String gfpName;

    @Column(name = "gfc_id")
    private Long gfcId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGfpId() {
        return gfpId;
    }

    public void setGfpId(Long gfpId) {
        this.gfpId = gfpId;
    }

    public String getGfpName() {
        return gfpName;
    }

    public void setGfpName(String gfpName) {
        this.gfpName = gfpName;
    }

    public Long getGfcId() {
        return gfcId;
    }

    public void setGfcId(Long gfcId) {
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
        ClientParent clientParent = (ClientParent) o;
        if(clientParent.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, clientParent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClientParent{" +
            "id=" + id +
            ", gfpId='" + gfpId + "'" +
            ", gfpName='" + gfpName + "'" +
            ", gfcId='" + gfcId + "'" +
            '}';
    }
}
