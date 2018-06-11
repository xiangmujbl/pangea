package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class StasEntity extends CommonEntity {

    private String stlty;
    private String stlnr;
    private String stlal;
    private String stlkn;
    private String stasz;
    private String datuv;
    private String aennr;
    private String lkenz;
    private String andat;
    private String aedat;

    public StasEntity(Map<String, Object> map) {
        super(map);

        setStlty((String) map.get("stlty"));
        setStlnr((String) map.get("stlnr"));
        setStlal((String) map.get("stlal"));
        setStlkn((String) map.get("stlkn"));
        setStasz((String) map.get("stasz"));
        setDatuv((String) map.get("datuv"));
        setAennr((String) map.get("aennr"));
        setLkenz((String) map.get("lkenz"));
        setAndat((String) map.get("andat"));
        setAedat((String) map.get("aedat"));
    }

    public String getStlty() {
        return this.stlty;
    }

    public void setStlty(String stlty) {
        this.stlty = stlty;
    }

    public String getStlnr() {
        return this.stlnr;
    }

    public void setStlnr(String stlnr) {
        this.stlnr = stlnr;
    }

    public String getStlal() {
        return this.stlal;
    }

    public void setStlal(String stlal) {
        this.stlal = stlal;
    }

    public String getStlkn() {
        return this.stlkn;
    }

    public void setStlkn(String stlkn) {
        this.stlkn = stlkn;
    }

    public String getStasz() {
        return this.stasz;
    }

    public void setStasz(String stasz) {
        this.stasz = stasz;
    }

    public String getDatuv() {
        return this.datuv;
    }

    public void setDatuv(String datuv) {
        this.datuv = datuv;
    }

    public String getAennr() {
        return this.aennr;
    }

    public void setAennr(String aennr) {
        this.aennr = aennr;
    }

    public String getLkenz() {
        return this.lkenz;
    }

    public void setLkenz(String lkenz) {
        this.lkenz = lkenz;
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
