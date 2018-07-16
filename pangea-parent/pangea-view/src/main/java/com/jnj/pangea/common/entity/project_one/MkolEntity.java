package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MkolEntity extends CommonEntity {

    private String mandt;
    private String matnr;
    private String werks;
    private String charg;
    private String sobkz;
    private String lifnr;
    private String slabs;
    private String sinsm;
    private String seinm;
    private String sspem;
    private String Lgort;

    public MkolEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
        setCharg((String) map.get("charg"));
        setSobkz((String) map.get("sobkz"));
        setLifnr((String) map.get("lifnr"));
        setSlabs((String) map.get("slabs"));
        setSinsm((String) map.get("sinsm"));
        setSeinm((String) map.get("seinm"));
        setSspem((String) map.get("sspem"));
        setLgort((String) map.get("lgort"));

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

    public String getSlabs() {
        return this.slabs;
    }

    public void setSlabs(String slabs) {
        this.slabs = slabs;
    }

    public String getSinsm() {
        return this.sinsm;
    }

    public void setSinsm(String sinsm) {
        this.sinsm = sinsm;
    }

    public String getSeinm() {
        return this.seinm;
    }

    public void setSeinm(String seinm) {
        this.seinm = seinm;
    }

    public String getSspem() {
        return this.sspem;
    }

    public void setSspem(String sspem) {
        this.sspem = sspem;
    }

    public String getLgort() {
        return Lgort;
    }

    public void setLgort(String lgort) {
        Lgort = lgort;
    }

}
