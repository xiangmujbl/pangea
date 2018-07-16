package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlmzEntity extends CommonEntity {
    private String plnty;
    private String plnnr;
    private String zuonr;
    private String zaehl;
    private String datuv;
    private String aennr;
    private String loekz;
    private String plnal;
    private String plnfl;
    private String plnkn;
    private String stlty;
    private String stlnr;
    private String stlal;
    private String stlkn;
    private String werk_stl;
    public PlmzEntity(Map<String, Object> map) {
        super(map);
        setPlnty((String)map.get("plnty"));
        setPlnnr((String)map.get("plnnr"));
        setZuonr((String)map.get("zuonr"));
        setZaehl((String)map.get("zaehl"));
        setDatuv((String)map.get("datuv"));
        setAennr((String)map.get("aennr"));
        setLoekz((String)map.get("loekz"));
        setPlnal((String)map.get("plnal"));
        setPlnfl((String)map.get("plnfl"));
        setPlnkn((String)map.get("plnkn"));
        setStlty((String)map.get("stlty"));
        setStlnr((String)map.get("stlnr"));
        setStlal((String)map.get("stlal"));
        setStlkn((String)map.get("stlkn"));
        setWerk_stl((String)map.get("werk_stl"));
    }
    public String getPlnty() {
        return plnty;
    }

    public void setPlnty(String plnty) {
        this.plnty = plnty;
    }

    public String getPlnnr() {
        return plnnr;
    }

    public void setPlnnr(String plnnr) {
        this.plnnr = plnnr;
    }

    public String getZuonr() {
        return zuonr;
    }

    public void setZuonr(String zuonr) {
        this.zuonr = zuonr;
    }

    public String getZaehl() {
        return zaehl;
    }

    public void setZaehl(String zaehl) {
        this.zaehl = zaehl;
    }

    public String getDatuv() {
        return datuv;
    }

    public void setDatuv(String datuv) {
        this.datuv = datuv;
    }

    public String getAennr() {
        return aennr;
    }

    public void setAennr(String aennr) {
        this.aennr = aennr;
    }

    public String getLoekz() {
        return loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getPlnal() {
        return plnal;
    }

    public void setPlnal(String plnal) {
        this.plnal = plnal;
    }

    public String getPlnfl() {
        return plnfl;
    }

    public void setPlnfl(String plnfl) {
        this.plnfl = plnfl;
    }

    public String getPlnkn() {
        return plnkn;
    }

    public void setPlnkn(String plnkn) {
        this.plnkn = plnkn;
    }

    public String getStlty() {
        return stlty;
    }

    public void setStlty(String stlty) {
        this.stlty = stlty;
    }

    public String getStlnr() {
        return stlnr;
    }

    public void setStlnr(String stlnr) {
        this.stlnr = stlnr;
    }

    public String getStlal() {
        return stlal;
    }

    public void setStlal(String stlal) {
        this.stlal = stlal;
    }

    public String getStlkn() {
        return stlkn;
    }

    public void setStlkn(String stlkn) {
        this.stlkn = stlkn;
    }

    public String getWerk_stl() {
        return werk_stl;
    }

    public void setWerk_stl(String werk_stl) {
        this.werk_stl = werk_stl;
    }
}
