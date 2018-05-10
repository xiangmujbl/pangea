package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EkkoEntity extends CommonEntity {

    private String bedat;
    private String bstyp;
    private String bukrs;
    private String reswk;
    private String kdatb;
    private String waers;
    private String frgrl;
    private String mandt;
    private String bsart;
    private String lphis;
    private String kdate;
    private String lifnr;
    private String llief;
    private String aedat;
    private String ebeln;
    private String ekgrp;
    private String ekorg;

    public EkkoEntity(Map<String, Object> map) {
        super(map);
        setBedat((String)map.get("bedat"));
        setBedat((String)map.get("bstyp"));
        setBedat((String)map.get("bukrs"));
        setBedat((String)map.get("reswk"));
        setBedat((String)map.get("kdatb"));
        setBedat((String)map.get("waers"));
        setBedat((String)map.get("frgrl"));
        setBedat((String)map.get("mandt"));
        setBedat((String)map.get("bsart"));
        setBedat((String)map.get("lphis"));
        setBedat((String)map.get("kdate"));
        setBedat((String)map.get("lifnr"));
        setBedat((String)map.get("llief"));
        setBedat((String)map.get("aedat"));
        setBedat((String)map.get("ebeln"));
        setBedat((String)map.get("ekgrp"));
        setBedat((String)map.get("ekorg"));
    }

    public String getBedat() {
        return bedat;
    }

    public void setBedat(String bedat) {
        this.bedat = bedat;
    }

    public String getBstyp() {
        return bstyp;
    }

    public void setBstyp(String bstyp) {
        this.bstyp = bstyp;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getReswk() {
        return reswk;
    }

    public void setReswk(String reswk) {
        this.reswk = reswk;
    }

    public String getKdatb() {
        return kdatb;
    }

    public void setKdatb(String kdatb) {
        this.kdatb = kdatb;
    }

    public String getWaers() {
        return waers;
    }

    public void setWaers(String waers) {
        this.waers = waers;
    }

    public String getFrgrl() {
        return frgrl;
    }

    public void setFrgrl(String frgrl) {
        this.frgrl = frgrl;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getBsart() {
        return bsart;
    }

    public void setBsart(String bsart) {
        this.bsart = bsart;
    }

    public String getLphis() {
        return lphis;
    }

    public void setLphis(String lphis) {
        this.lphis = lphis;
    }

    public String getKdate() {
        return kdate;
    }

    public void setKdate(String kdate) {
        this.kdate = kdate;
    }

    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getLlief() {
        return llief;
    }

    public void setLlief(String llief) {
        this.llief = llief;
    }

    public String getAedat() {
        return aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }

    public String getEbeln() {
        return ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getEkgrp() {
        return ekgrp;
    }

    public void setEkgrp(String ekgrp) {
        this.ekgrp = ekgrp;
    }

    public String getEkorg() {
        return ekorg;
    }

    public void setEkorg(String ekorg) {
        this.ekorg = ekorg;
    }
}
