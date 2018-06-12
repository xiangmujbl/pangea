package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMProjectOneMAPLEntity extends CommonEntity {
    public EDMProjectOneMAPLEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
        setPlnty((String) map.get("plnty"));
        setPlnnr((String) map.get("plnnr"));
        setPlnal((String) map.get("plnal"));
        setZkriz((String) map.get("zkriz"));
        setZaehl((String) map.get("zaehl"));
        setDatuv((String) map.get("datuv"));
        setAennr((String) map.get("aennr"));
        setLoekz((String) map.get("loekz"));
        setAndat((String) map.get("andat"));
        setAedat((String) map.get("aedat"));
    }

    private String matnr;
    private String werks;
    private String plnty;
    private String plnnr;
    private String plnal;
    private String zkriz;
    private String zaehl;
    private String datuv;
    private String aennr;
    private String loekz;
    private String andat;
    private String aedat;

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
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

    public String getPlnal() {
        return plnal;
    }

    public void setPlnal(String plnal) {
        this.plnal = plnal;
    }

    public String getZkriz() {
        return zkriz;
    }

    public void setZkriz(String zkriz) {
        this.zkriz = zkriz;
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

    public String getAndat() {
        return andat;
    }

    public void setAndat(String andat) {
        this.andat = andat;
    }

    public String getAedat() {
        return aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }
}
