package com.bss.mosaic.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ClientRisk.
 */
@Entity
@Table(name = "client_risk")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClientRisk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gfc_id")
    private Long gfcId;

    @Column(name = "list_type")
    private String listType;

    @Column(name = "temperature")
    private String temperature;

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

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientRisk clientRisk = (ClientRisk) o;
        if(clientRisk.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, clientRisk.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClientRisk{" +
            "id=" + id +
            ", gfcId='" + gfcId + "'" +
            ", listType='" + listType + "'" +
            ", temperature='" + temperature + "'" +
            '}';
    }
}
