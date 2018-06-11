package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMProjectOneSTASEntity extends CommonEntity {

    public EDMProjectOneSTASEntity(Map<String, Object> map) {
        super(map);
        setStlty((String) map.get("stlty"));
        setStlnr((String) map.get("stlnr"));
        setStlal((String) map.get("stlal"));
        setStlkn((String) map.get("stlkn"));
        setStasz((String) map.get("stasz"));
        setDatuv((String) map.get("datuv"));
        setAennr((String) map.get("aennr"));
        setLeknz((String) map.get("leknz"));
    }

    private String stlty ;
    private String stlnr ;
    private String stlal ;
    private String stlkn ;
    private String stasz ;
    private String datuv ;
    private String aennr ;
    private String leknz ;

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

    public String getStasz() {
        return stasz;
    }

    public void setStasz(String stasz) {
        this.stasz = stasz;
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

    public String getLeknz() {
        return leknz;
    }

    public void setLeknz(String leknz) {
        this.leknz = leknz;
    }

    @Override
    public String toString() {
        return "EDMProjectOneSTASEntity{" +
                "stlty='" + stlty + '\'' +
                ", stlnr='" + stlnr + '\'' +
                ", stlal='" + stlal + '\'' +
                ", stlkn='" + stlkn + '\'' +
                ", stasz='" + stasz + '\'' +
                ", datuv='" + datuv + '\'' +
                ", aennr='" + aennr + '\'' +
                ", leknz='" + leknz + '\'' +
                '}';
    }
}
