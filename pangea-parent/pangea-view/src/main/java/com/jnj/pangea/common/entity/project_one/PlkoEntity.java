package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlkoEntity extends CommonEntity {

    private String plnty;
    private String plnnr;
    private String plnal;
    private String zaehl;
    private String datuv;
    private String aennr;
    private String loekz;
    private String andat;
    private String aedat;
    private String werks;
    private String verwe;
    private String statu;
    private String plnme;
    private String losvn;
    private String losbs;
    private String vagrp;
    private String ktext;
    private String profidnetz;

    public PlkoEntity(Map<String, Object> map) {
        super(map);

        setPlnty((String) map.get("plnty"));
        setPlnnr((String) map.get("plnnr"));
        setPlnal((String) map.get("plnal"));
        setZaehl((String) map.get("zaehl"));
        setDatuv((String) map.get("datuv"));
        setAennr((String) map.get("aennr"));
        setLoekz((String) map.get("loekz"));
        setAndat((String) map.get("andat"));
        setAedat((String) map.get("aedat"));
        setWerks((String) map.get("werks"));
        setVerwe((String) map.get("verwe"));
        setStatu((String) map.get("statu"));
        setPlnme((String) map.get("plnme"));
        setLosvn((String) map.get("losvn"));
        setLosbs((String) map.get("losbs"));
        setVagrp((String) map.get("vagrp"));
        setKtext((String) map.get("ktext"));
        setProfidnetz((String) map.get("profidnetz"));
    }

    public String getPlnty() {
        return this.plnty;
    }

    public void setPlnty(String plnty) {
        this.plnty = plnty;
    }

    public String getPlnnr() {
        return this.plnnr;
    }

    public void setPlnnr(String plnnr) {
        this.plnnr = plnnr;
    }

    public String getPlnal() {
        return this.plnal;
    }

    public void setPlnal(String plnal) {
        this.plnal = plnal;
    }

    public String getZaehl() {
        return this.zaehl;
    }

    public void setZaehl(String zaehl) {
        this.zaehl = zaehl;
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

    public String getLoekz() {
        return this.loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
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

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getVerwe() {
        return this.verwe;
    }

    public void setVerwe(String verwe) {
        this.verwe = verwe;
    }

    public String getStatu() {
        return this.statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getPlnme() {
        return this.plnme;
    }

    public void setPlnme(String plnme) {
        this.plnme = plnme;
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

    public String getVagrp() {
        return this.vagrp;
    }

    public void setVagrp(String vagrp) {
        this.vagrp = vagrp;
    }

    public String getKtext() {
        return this.ktext;
    }

    public void setKtext(String ktext) {
        this.ktext = ktext;
    }

    public String getProfidnetz() {
        return this.profidnetz;
    }

    public void setProfidnetz(String profidnetz) {
        this.profidnetz = profidnetz;
    }

}
