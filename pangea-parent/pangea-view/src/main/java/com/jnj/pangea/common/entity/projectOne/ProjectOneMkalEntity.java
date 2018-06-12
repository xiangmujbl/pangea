package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneMkalEntity extends CommonEntity {

    private String matnr;
    private String werks;
    private String verid;
    private String adatu;
    private String bdatu;
    private String stlal;
    private String stlan;
    private String plnty;
    private String plnnr;
    private String alnal;
    private String beskz;
    private String sobsl;
    private String losgr;
    private String mdv01;
    private String text1;
    private String verto;
    private String bstmi;
    private String bstma;
    private String alort;
    private String prfgF;
    private String prdat;
    private String mksp;
    private String ucmat;

    public ProjectOneMkalEntity(Map<String, Object> map) {
        super(map);

        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
        setVerid((String) map.get("verid"));
        setAdatu((String) map.get("adatu"));
        setBdatu((String) map.get("bdatu"));
        setStlal((String) map.get("stlal"));
        setStlan((String) map.get("stlan"));
        setPlnty((String) map.get("plnty"));
        setPlnnr((String) map.get("plnnr"));
        setAlnal((String) map.get("alnal"));
        setBeskz((String) map.get("beskz"));
        setSobsl((String) map.get("sobsl"));
        setLosgr((String) map.get("losgr"));
        setMdv01((String) map.get("mdv01"));
        setText1((String) map.get("text1"));
        setVerto((String) map.get("verto"));
        setBstmi((String) map.get("bstmi"));
        setBstma((String) map.get("bstma"));
        setAlort((String) map.get("alort"));
        setPrfgF((String) map.get("prfgF"));
        setPrdat((String) map.get("prdat"));
        setMksp((String) map.get("mksp"));
        setUcmat((String) map.get("ucmat"));
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

    public String getVerid() {
        return this.verid;
    }

    public void setVerid(String verid) {
        this.verid = verid;
    }

    public String getAdatu() {
        return this.adatu;
    }

    public void setAdatu(String adatu) {
        this.adatu = adatu;
    }

    public String getBdatu() {
        return this.bdatu;
    }

    public void setBdatu(String bdatu) {
        this.bdatu = bdatu;
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

    public String getAlnal() {
        return this.alnal;
    }

    public void setAlnal(String alnal) {
        this.alnal = alnal;
    }

    public String getBeskz() {
        return this.beskz;
    }

    public void setBeskz(String beskz) {
        this.beskz = beskz;
    }

    public String getSobsl() {
        return this.sobsl;
    }

    public void setSobsl(String sobsl) {
        this.sobsl = sobsl;
    }

    public String getLosgr() {
        return this.losgr;
    }

    public void setLosgr(String losgr) {
        this.losgr = losgr;
    }

    public String getMdv01() {
        return this.mdv01;
    }

    public void setMdv01(String mdv01) {
        this.mdv01 = mdv01;
    }

    public String getText1() {
        return this.text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getVerto() {
        return this.verto;
    }

    public void setVerto(String verto) {
        this.verto = verto;
    }

    public String getBstmi() {
        return this.bstmi;
    }

    public void setBstmi(String bstmi) {
        this.bstmi = bstmi;
    }

    public String getBstma() {
        return this.bstma;
    }

    public void setBstma(String bstma) {
        this.bstma = bstma;
    }

    public String getAlort() {
        return this.alort;
    }

    public void setAlort(String alort) {
        this.alort = alort;
    }

    public String getPrfgF() {
        return prfgF;
    }

    public void setPrfgF(String prfgF) {
        this.prfgF = prfgF;
    }

    public String getPrdat() {
        return this.prdat;
    }

    public void setPrdat(String prdat) {
        this.prdat = prdat;
    }

    public String getMksp() {
        return this.mksp;
    }

    public void setMksp(String mksp) {
        this.mksp = mksp;
    }

    public String getUcmat() {
        return this.ucmat;
    }

    public void setUcmat(String ucmat) {
        this.ucmat = ucmat;
    }

}
