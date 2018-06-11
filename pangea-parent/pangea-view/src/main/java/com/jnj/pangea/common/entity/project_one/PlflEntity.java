package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlflEntity extends CommonEntity {

    private String plnty;
    private String plnnr;
    private String plnal;
    private String plnfl;
    private String zaehl;
    private String datuv;
    private String aennr;
    private String loekz;
    private String andat;
    private String aedat;
    private String flgat;
    private String ltxa1;
    private String losvn;
    private String losbs;
    private String bschl1;
    private String bschl2;
    private String bknt1;
    private String bknt2;

    public PlflEntity(Map<String, Object> map) {
        super(map);

        setPlnty((String) map.get("plnty"));
        setPlnnr((String) map.get("plnnr"));
        setPlnal((String) map.get("plnal"));
        setPlnfl((String) map.get("plnfl"));
        setZaehl((String) map.get("zaehl"));
        setDatuv((String) map.get("datuv"));
        setAennr((String) map.get("aennr"));
        setLoekz((String) map.get("loekz"));
        setAndat((String) map.get("andat"));
        setAedat((String) map.get("aedat"));
        setFlgat((String) map.get("flgat"));
        setLtxa1((String) map.get("ltxa1"));
        setLosvn((String) map.get("losvn"));
        setLosbs((String) map.get("losbs"));
        setBschl1((String) map.get("bschl1"));
        setBschl2((String) map.get("bschl2"));
        setBknt1((String) map.get("bknt1"));
        setBknt2((String) map.get("bknt2"));
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

    public String getPlnfl() {
        return this.plnfl;
    }

    public void setPlnfl(String plnfl) {
        this.plnfl = plnfl;
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

    public String getFlgat() {
        return this.flgat;
    }

    public void setFlgat(String flgat) {
        this.flgat = flgat;
    }

    public String getLtxa1() {
        return this.ltxa1;
    }

    public void setLtxa1(String ltxa1) {
        this.ltxa1 = ltxa1;
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

    public String getBschl1() {
        return this.bschl1;
    }

    public void setBschl1(String bschl1) {
        this.bschl1 = bschl1;
    }

    public String getBschl2() {
        return this.bschl2;
    }

    public void setBschl2(String bschl2) {
        this.bschl2 = bschl2;
    }

    public String getBknt1() {
        return this.bknt1;
    }

    public void setBknt1(String bknt1) {
        this.bknt1 = bknt1;
    }

    @Override
    public String toString() {
        return "PlflEntity{" +
                "plnty='" + plnty + '\'' +
                ", plnnr='" + plnnr + '\'' +
                ", plnal='" + plnal + '\'' +
                ", plnfl='" + plnfl + '\'' +
                ", zaehl='" + zaehl + '\'' +
                ", datuv='" + datuv + '\'' +
                ", aennr='" + aennr + '\'' +
                ", loekz='" + loekz + '\'' +
                ", andat='" + andat + '\'' +
                ", aedat='" + aedat + '\'' +
                ", flgat='" + flgat + '\'' +
                ", ltxa1='" + ltxa1 + '\'' +
                ", losvn='" + losvn + '\'' +
                ", losbs='" + losbs + '\'' +
                ", bschl1='" + bschl1 + '\'' +
                ", bschl2='" + bschl2 + '\'' +
                ", bknt1='" + bknt1 + '\'' +
                ", bknt2='" + bknt2 + '\'' +
                '}';
    }

    public String getBknt2() {
        return this.bknt2;
    }

    public void setBknt2(String bknt2) {
        this.bknt2 = bknt2;
    }

}
