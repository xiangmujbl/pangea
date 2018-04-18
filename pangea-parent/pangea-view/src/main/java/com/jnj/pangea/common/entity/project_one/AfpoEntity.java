package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class AfpoEntity extends CommonEntity {

    private String aufnr;
    private String posnr;
    private String plnum;
    private String psamg;
    private String psmng;
    private String wemng;
    private String amein;
    private String matnr;
    private String elikz;
    private String verid;
    private String charg;

    public AfpoEntity(Map<String, Object> map) {
        super(map);

        setAufnr((String) map.get("aufnr"));
        setPosnr((String) map.get("posnr"));
        setPlnum((String) map.get("plnum"));
        setPsamg((String) map.get("psamg"));
        setPsmng((String) map.get("psmng"));
        setWemng((String) map.get("wemng"));
        setAmein((String) map.get("amein"));
        setMatnr((String) map.get("matnr"));
        setElikz((String) map.get("elikz"));
        setVerid((String) map.get("verid"));
        setCharg((String) map.get("charg"));
    }

    public String getAufnr() {
        return this.aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getPosnr() {
        return this.posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

    public String getPlnum() {
        return this.plnum;
    }

    public void setPlnum(String plnum) {
        this.plnum = plnum;
    }

    public String getPsamg() {
        return this.psamg;
    }

    public void setPsamg(String psamg) {
        this.psamg = psamg;
    }

    public String getPsmng() {
        return this.psmng;
    }

    public void setPsmng(String psmng) {
        this.psmng = psmng;
    }

    public String getWemng() {
        return this.wemng;
    }

    public void setWemng(String wemng) {
        this.wemng = wemng;
    }

    public String getAmein() {
        return this.amein;
    }

    public void setAmein(String amein) {
        this.amein = amein;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getElikz() {
        return this.elikz;
    }

    public void setElikz(String elikz) {
        this.elikz = elikz;
    }

    public String getVerid() {
        return this.verid;
    }

    public void setVerid(String verid) {
        this.verid = verid;
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

}
