package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOnePlabEntity extends CommonEntity {

    private String plnty;
    private String plnnr;
    private String plnal;
    private String plnkn;
    private String plnrn;
    private String alnrn;
    private String knnrn;
    private String aobar;
    private String mimax;
    private String zaehl;
    private String datuv;
    private String aennr;
    private String zeinh;
    private String dauer;
    private String daukz;
    private String kalid;
    private String werks;
    private String andat;
    private String aedat;
    private String loekz;

    public ProjectOnePlabEntity(Map<String, Object> map) {
        super(map);

        setPlnty((String) map.get("plnty"));
        setPlnnr((String) map.get("plnnr"));
        setPlnal((String) map.get("plnal"));
        setPlnkn((String) map.get("plnkn"));
        setPlnrn((String) map.get("plnrn"));
        setAlnrn((String) map.get("alnrn"));
        setKnnrn((String) map.get("knnrn"));
        setAobar((String) map.get("aobar"));
        setMimax((String) map.get("mimax"));
        setZaehl((String) map.get("zaehl"));
        setDatuv((String) map.get("datuv"));
        setAennr((String) map.get("aennr"));
        setZeinh((String) map.get("zeinh"));
        setDauer((String) map.get("dauer"));
        setDaukz((String) map.get("daukz"));
        setKalid((String) map.get("kalid"));
        setWerks((String) map.get("werks"));
        setAndat((String) map.get("andat"));
        setAedat((String) map.get("aedat"));
        setLoekz((String) map.get("loekz"));

    }

    public String getLoekz() {
        return loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
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

    public String getPlnkn() {
        return this.plnkn;
    }

    public void setPlnkn(String plnkn) {
        this.plnkn = plnkn;
    }

    public String getPlnrn() {
        return this.plnrn;
    }

    public void setPlnrn(String plnrn) {
        this.plnrn = plnrn;
    }

    public String getAlnrn() {
        return this.alnrn;
    }

    public void setAlnrn(String alnrn) {
        this.alnrn = alnrn;
    }

    public String getKnnrn() {
        return this.knnrn;
    }

    public void setKnnrn(String knnrn) {
        this.knnrn = knnrn;
    }

    public String getAobar() {
        return this.aobar;
    }

    public void setAobar(String aobar) {
        this.aobar = aobar;
    }

    public String getMimax() {
        return this.mimax;
    }

    public void setMimax(String mimax) {
        this.mimax = mimax;
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

    public String getZeinh() {
        return this.zeinh;
    }

    public void setZeinh(String zeinh) {
        this.zeinh = zeinh;
    }

    public String getDauer() {
        return this.dauer;
    }

    public void setDauer(String dauer) {
        this.dauer = dauer;
    }

    public String getDaukz() {
        return this.daukz;
    }

    public void setDaukz(String daukz) {
        this.daukz = daukz;
    }

    public String getKalid() {
        return this.kalid;
    }

    public void setKalid(String kalid) {
        this.kalid = kalid;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
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

    @Override
    public String toString() {
        return "ProjectOnePlabEntity{" +
                "plnty='" + plnty + '\'' +
                ", plnnr='" + plnnr + '\'' +
                ", plnal='" + plnal + '\'' +
                ", plnkn='" + plnkn + '\'' +
                ", plnrn='" + plnrn + '\'' +
                ", alnrn='" + alnrn + '\'' +
                ", knnrn='" + knnrn + '\'' +
                ", aobar='" + aobar + '\'' +
                ", mimax='" + mimax + '\'' +
                ", zaehl='" + zaehl + '\'' +
                ", datuv='" + datuv + '\'' +
                ", aennr='" + aennr + '\'' +
                ", zeinh='" + zeinh + '\'' +
                ", dauer='" + dauer + '\'' +
                ", daukz='" + daukz + '\'' +
                ", kalid='" + kalid + '\'' +
                ", werks='" + werks + '\'' +
                ", andat='" + andat + '\'' +
                ", aedat='" + aedat + '\'' +
                ", loekz='" + loekz + '\'' +
                '}';
    }
}
