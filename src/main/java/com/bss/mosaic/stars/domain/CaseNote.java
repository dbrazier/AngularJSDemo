package com.bss.mosaic.stars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

/**
 * Created by db44417 on 25/08/2016.
 */
@Entity
public class CaseNote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PZINSKEY")
    private String pzinsKey;

    @Column(name = "PXINSNAME")
    private String name;

    @Column(name = "PXUPDATEDATETIME")
    private Date updateDate;

    @Column(name = "PXOBJCLASS")
    private String objClass;

    @Column(name = "PXREFOBJECTKEY")
    private String objKey;

    @Column(name = "PXATTACHKEY")
    private String attachKey;

    @Column(name = "PYLABEL")
    private String label;

    @Column(name = "NOTETEXT")
    private String noteText;


    public String getPzinsKey() {
        return pzinsKey;
    }

    public void setPzinsKey(String pzinsKey) {
        this.pzinsKey = pzinsKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getObjClass() {
        return objClass;
    }

    public void setObjClass(String objClass) {
        this.objClass = objClass;
    }

    public String getObjKey() {
        return objKey;
    }

    public void setObjKey(String objKey) {
        this.objKey = objKey;
    }

    public String getAttachKey() {
        return attachKey;
    }

    public void setAttachKey(String attachKey) {
        this.attachKey = attachKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

}
