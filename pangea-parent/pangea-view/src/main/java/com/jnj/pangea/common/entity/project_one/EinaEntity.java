package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EinaEntity extends CommonEntity {

    private String infnr;
    private String lifnr;
    private String matnr;
    private String lmein;
    private String matkl;
    private String loekz;
    private String erdat;
    private String ernam;
    private String txz01;
    private String idnlf;
    private String umrez;
    private String umren;
    private String meins;
    private String mfrnr;

    public EinaEntity(Map<String, Object> map) {
        super(map);

        setInfnr((String) map.get("infnr"));
        setLifnr((String) map.get("lifnr"));
        setMatnr((String) map.get("matnr"));
        setLmein((String) map.get("lmein"));
        setMatkl((String) map.get("matkl"));
        setLoekz((String) map.get("loekz"));
        setErdat((String) map.get("erdat"));
        setErnam((String) map.get("ernam"));
        setTxz01((String) map.get("txz01"));
        setIdnlf((String) map.get("idnlf"));
        setUmrez((String) map.get("umrez"));
        setUmren((String) map.get("umren"));
        setMeins((String) map.get("meins"));
        setMfrnr((String) map.get("mfrnr"));
    }

    public String getInfnr() {
        return this.infnr;
    }

    public void setInfnr(String infnr) {
        this.infnr = infnr;
    }

    public String getLifnr() {
        return this.lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getLmein() {
        return this.lmein;
    }

    public void setLmein(String lmein) {
        this.lmein = lmein;
    }

    public String getMatkl() {
        return this.matkl;
    }

    public void setMatkl(String matkl) {
        this.matkl = matkl;
    }

    public String getLoekz() {
        return this.loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
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

    public String getTxz01() {
        return this.txz01;
    }

    public void setTxz01(String txz01) {
        this.txz01 = txz01;
    }

    public String getIdnlf() {
        return this.idnlf;
    }

    public void setIdnlf(String idnlf) {
        this.idnlf = idnlf;
    }

    public String getUmrez() {
        return this.umrez;
    }

    public void setUmrez(String umrez) {
        this.umrez = umrez;
    }

    public String getUmren() {
        return this.umren;
    }

    public void setUmren(String umren) {
        this.umren = umren;
    }

    public String getMeins() {
        return this.meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getMfrnr() {
        return this.mfrnr;
    }

    public void setMfrnr(String mfrnr) {
        this.mfrnr = mfrnr;
    }

}
