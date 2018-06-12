package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneMastEntity extends CommonEntity {

    private String mandt;
    private String matnr;
    private String stlal;
    private String stlan;
    private String stlnr;
    private String werks;
    private String losvn;
    private String losbs;
    private String andat;
    private String aedat;

    public ProjectOneMastEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setMatnr((String) map.get("matnr"));
        setStlal((String) map.get("stlal"));
        setStlan((String) map.get("stlan"));
        setStlnr((String) map.get("stlnr"));
        setWerks((String) map.get("werks"));
        setLosvn((String) map.get("losvn"));
        setLosbs((String) map.get("losbs"));
        setAndat((String) map.get("andat"));
        setAedat((String) map.get("aedat"));
    }

    public String getMandt() {
        return this.mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getStlal() {
        return this.stlal;
    }

    public void setStlal(String stlal) {
        this.stlal = stlal;
    }

    public String getStlan() {
        return this.stlan;
    }

    public void setStlan(String stlan) {
        this.stlan = stlan;
    }

    public String getStlnr() {
        return this.stlnr;
    }

    public void setStlnr(String stlnr) {
        this.stlnr = stlnr;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getLosvn() {
        return this.losvn;
    }

    public void setLosvn(String losvn) {
        this.losvn = losvn;
    }

    public String getLosbs() {
        return this.losbs;
    }

    public void setLosbs(String losbs) {
        this.losbs = losbs;
    }

    public String getAndat() {
        return this.andat;
    }

    public void setAndat(String andat) {
        this.andat = andat;
    }

    public String getAedat() {
        return this.aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }

}
