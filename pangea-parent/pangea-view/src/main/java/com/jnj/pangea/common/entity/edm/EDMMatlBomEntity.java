package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * @author: qzhan112
 * @date: 2018/5/14
 */
public class EDMMatlBomEntity extends CommonEntity {
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

    public EDMMatlBomEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setMatnr((String) map.get("matnr"));
        setStlal((String) map.get("stlal"));
        setStlan((String) map.get("stlan"));
        setStlnr((String) map.get("stlnr"));
        setWerks((String) map.get("werks"));
        setLosvn((String) map.get("losvn"));
        setLosbs((String) map.get("losbs"));
        setAedat((String) map.get("aedat"));
        setAndat((String) map.get("andat"));
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getStlal() {
        return stlal;
    }

    public void setStlal(String stlal) {
        this.stlal = stlal;
    }

    public String getStlan() {
        return stlan;
    }

    public void setStlan(String stlan) {
        this.stlan = stlan;
    }

    public String getStlnr() {
        return stlnr;
    }

    public void setStlnr(String stlnr) {
        this.stlnr = stlnr;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getLosvn() {
        return losvn;
    }

    public void setLosvn(String losvn) {
        this.losvn = losvn;
    }

    public String getLosbs() {
        return losbs;
    }

    public void setLosbs(String losbs) {
        this.losbs = losbs;
    }

    public String getAndat() {
        return andat;
    }

    public void setAndat(String andat) {
        this.andat = andat;
    }

    public String getAedat() {
        return aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }
}
