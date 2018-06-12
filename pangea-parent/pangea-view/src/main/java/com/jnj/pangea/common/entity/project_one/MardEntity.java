package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MardEntity extends CommonEntity {

    private String mandt;
    private String matnr;
    private String werks;
    private String lgort;
    private String labst;
    private String umlme;
    private String insme;
    private String einme;
    private String speme;
    private String retme;
    private String klabs;
    private String kinsm;
    private String keinm;
    private String kspem;
    private String umlmc;

    public MardEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
        setLgort((String) map.get("lgort"));
        setLabst((String) map.get("labst"));
        setUmlme((String) map.get("umlme"));
        setInsme((String) map.get("insme"));
        setEinme((String) map.get("einme"));
        setSpeme((String) map.get("speme"));
        setRetme((String) map.get("retme"));
        setKlabs((String) map.get("klabs"));
        setKinsm((String) map.get("kinsm"));
        setKeinm((String) map.get("keinm"));
        setKspem((String) map.get("kspem"));
        setUmlmc((String) map.get("umlmc"));
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

    public String getLgort() {
        return this.lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getLabst() {
        return this.labst;
    }

    public void setLabst(String labst) {
        this.labst = labst;
    }

    public String getUmlme() {
        return this.umlme;
    }

    public void setUmlme(String umlme) {
        this.umlme = umlme;
    }

    public String getInsme() {
        return this.insme;
    }

    public void setInsme(String insme) {
        this.insme = insme;
    }

    public String getEinme() {
        return this.einme;
    }

    public void setEinme(String einme) {
        this.einme = einme;
    }

    public String getSpeme() {
        return this.speme;
    }

    public void setSpeme(String speme) {
        this.speme = speme;
    }

    public String getRetme() {
        return this.retme;
    }

    public void setRetme(String retme) {
        this.retme = retme;
    }

    public String getKlabs() {
        return this.klabs;
    }

    public void setKlabs(String klabs) {
        this.klabs = klabs;
    }

    public String getKinsm() {
        return this.kinsm;
    }

    public void setKinsm(String kinsm) {
        this.kinsm = kinsm;
    }

    public String getKeinm() {
        return this.keinm;
    }

    public void setKeinm(String keinm) {
        this.keinm = keinm;
    }

    public String getKspem() {
        return this.kspem;
    }

    public void setKspem(String kspem) {
        this.kspem = kspem;
    }

    public String getUmlmc() {
        return this.umlmc;
    }

    public void setUmlmc(String umlmc) {
        this.umlmc = umlmc;
    }

}
