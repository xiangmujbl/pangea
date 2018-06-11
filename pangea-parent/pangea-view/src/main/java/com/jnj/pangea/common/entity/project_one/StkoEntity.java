package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class StkoEntity extends CommonEntity {
    private String mandt;
    private String stlty;
    private String stlnr;
    private String stlal;
    private String stkoz;
    private String datuv;
    private String aennr;
    private String loekz;
    private String vgkzl;
    private String andat;
    private String aedat;
    private String bmein;
    private String bmeng;
    private String stktx;
    private String stlst;

    public StkoEntity(Map<String, Object> map) {
        super(map);
        setMandt((String)map.get("mandt"));
        setStlty((String)map.get("stlty"));
        setStlnr((String)map.get("stlnr"));
        setStlal((String)map.get("stlal"));
        setStkoz((String)map.get("stkoz"));
        setDatuv((String)map.get("datuv"));
        setAennr((String)map.get("aennr"));
        setLoekz((String)map.get("loekz"));
        setVgkzl((String)map.get("vgkzl"));
        setAndat((String)map.get("andat"));
        setAedat((String)map.get("aedat"));
        setBmein((String)map.get("bmein"));
        setBmeng((String)map.get("bmeng"));
        setStktx((String)map.get("stktx"));
        setStlst((String)map.get("stlst"));
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public void setStlty(String stlty) {
        this.stlty = stlty;
    }

    public void setStlnr(String stlnr) {
        this.stlnr = stlnr;
    }

    public void setStlal(String stlal) {
        this.stlal = stlal;
    }

    public void setStkoz(String stkoz) {
        this.stkoz = stkoz;
    }

    public void setDatuv(String datuv) {
        this.datuv = datuv;
    }

    public void setAennr(String aennr) {
        this.aennr = aennr;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public void setVgkzl(String vgkzl) {
        this.vgkzl = vgkzl;
    }

    public void setAndat(String andat) {
        this.andat = andat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }

    public void setBmein(String bmein) {
        this.bmein = bmein;
    }

    public void setBmeng(String bmeng) {
        this.bmeng = bmeng;
    }

    public void setStktx(String stktx) {
        this.stktx = stktx;
    }

    public void setStlst(String stlst) {
        this.stlst = stlst;
    }

    public String getMandt() {
        return mandt;
    }

    public String getStlty() {
        return stlty;
    }

    public String getStlnr() {
        return stlnr;
    }

    public String getStlal() {
        return stlal;
    }

    public String getStkoz() {
        return stkoz;
    }

    public String getDatuv() {
        return datuv;
    }

    public String getAennr() {
        return aennr;
    }

    public String getLoekz() {
        return loekz;
    }

    public String getVgkzl() {
        return vgkzl;
    }

    public String getAndat() {
        return andat;
    }

    public String getAedat() {
        return aedat;
    }

    public String getBmein() {
        return bmein;
    }

    public String getBmeng() {
        return bmeng;
    }

    public String getStktx() {
        return stktx;
    }

    public String getStlst() {
        return stlst;
    }
}
