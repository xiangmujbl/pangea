package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EineEntity extends CommonEntity {

    private String infnr;
    private String ekorg;
    private String esokz;
    private String loekz;
    private String werks;
    private String erdat;
    private String ernam;
    private String ekgrp;
    private String waers;
    private String minbm;
    private String norbm;
    private String aplfz;
    private String mwskz;
    private String bstae;
    private String meprf;
    private String inco1;
    private String inco2;
    private String verid;
    private String bstma;
    private String rdprf;
    private String j1bnbm;

    public EineEntity(Map<String, Object> map) {
        super(map);

        setInfnr((String) map.get("infnr"));
        setEkorg((String) map.get("ekorg"));
        setEsokz((String) map.get("esokz"));
        setLoekz((String) map.get("loekz"));
        setWerks((String) map.get("werks"));
        setErdat((String) map.get("erdat"));
        setErnam((String) map.get("ernam"));
        setEkgrp((String) map.get("ekgrp"));
        setWaers((String) map.get("waers"));
        setMinbm((String) map.get("minbm"));
        setNorbm((String) map.get("norbm"));
        setAplfz((String) map.get("aplfz"));
        setMwskz((String) map.get("mwskz"));
        setBstae((String) map.get("bstae"));
        setMeprf((String) map.get("meprf"));
        setInco1((String) map.get("inco1"));
        setInco2((String) map.get("inco2"));
        setVerid((String) map.get("verid"));
        setBstma((String) map.get("bstma"));
        setRdprf((String) map.get("rdprf"));
        setJ1bnbm((String) map.get("j1bnbm"));
    }

    public String getInfnr() {
        return this.infnr;
    }

    public void setInfnr(String infnr) {
        this.infnr = infnr;
    }

    public String getEkorg() {
        return this.ekorg;
    }

    public void setEkorg(String ekorg) {
        this.ekorg = ekorg;
    }

    public String getEsokz() {
        return this.esokz;
    }

    public void setEsokz(String esokz) {
        this.esokz = esokz;
    }

    public String getLoekz() {
        return this.loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getErdat() {
        return this.erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }

    public String getErnam() {
        return this.ernam;
    }

    public void setErnam(String ernam) {
        this.ernam = ernam;
    }

    public String getEkgrp() {
        return this.ekgrp;
    }

    public void setEkgrp(String ekgrp) {
        this.ekgrp = ekgrp;
    }

    public String getWaers() {
        return this.waers;
    }

    public void setWaers(String waers) {
        this.waers = waers;
    }

    public String getMinbm() {
        return this.minbm;
    }

    public void setMinbm(String minbm) {
        this.minbm = minbm;
    }

    public String getNorbm() {
        return this.norbm;
    }

    public void setNorbm(String norbm) {
        this.norbm = norbm;
    }

    public String getAplfz() {
        return this.aplfz;
    }

    public void setAplfz(String aplfz) {
        this.aplfz = aplfz;
    }

    public String getMwskz() {
        return this.mwskz;
    }

    public void setMwskz(String mwskz) {
        this.mwskz = mwskz;
    }

    public String getBstae() {
        return this.bstae;
    }

    public void setBstae(String bstae) {
        this.bstae = bstae;
    }

    public String getMeprf() {
        return this.meprf;
    }

    public void setMeprf(String meprf) {
        this.meprf = meprf;
    }

    public String getInco1() {
        return this.inco1;
    }

    public void setInco1(String inco1) {
        this.inco1 = inco1;
    }

    public String getInco2() {
        return this.inco2;
    }

    public void setInco2(String inco2) {
        this.inco2 = inco2;
    }

    public String getVerid() {
        return this.verid;
    }

    public void setVerid(String verid) {
        this.verid = verid;
    }

    public String getBstma() {
        return this.bstma;
    }

    public void setBstma(String bstma) {
        this.bstma = bstma;
    }

    public String getRdprf() {
        return this.rdprf;
    }

    public void setRdprf(String rdprf) {
        this.rdprf = rdprf;
    }

    public String getJ1bnbm() {
        return this.j1bnbm;
    }

    public void setJ1bnbm(String j1bnbm) {
        this.j1bnbm = j1bnbm;
    }

}
