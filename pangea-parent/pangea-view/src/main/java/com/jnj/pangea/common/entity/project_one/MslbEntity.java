package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MslbEntity extends CommonEntity {

    private String mandt;
    private String matnr;
    private String werks;
    private String charg;
    private String sobkz;
    private String lifnr;
    private String lblab;
    private String lbins;
    private String lbein;
    private String lbuml;

    public MslbEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
        setCharg((String) map.get("charg"));
        setSobkz((String) map.get("sobkz"));
        setLifnr((String) map.get("lifnr"));
        setLblab((String) map.get("lblab"));
        setLbins((String) map.get("lbins"));
        setLbein((String) map.get("lbein"));
        setLbuml((String) map.get("lbuml"));
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

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getSobkz() {
        return this.sobkz;
    }

    public void setSobkz(String sobkz) {
        this.sobkz = sobkz;
    }

    public String getLifnr() {
        return this.lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getLblab() {
        return this.lblab;
    }

    public void setLblab(String lblab) {
        this.lblab = lblab;
    }

    public String getLbins() {
        return this.lbins;
    }

    public void setLbins(String lbins) {
        this.lbins = lbins;
    }

    public String getLbein() {
        return this.lbein;
    }

    public void setLbein(String lbein) {
        this.lbein = lbein;
    }

    public String getLbuml() {
        return this.lbuml;
    }

    public void setLbuml(String lbuml) {
        this.lbuml = lbuml;
    }

}
