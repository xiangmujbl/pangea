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
    private String umrez;
    private String umren;
    private String webaz;
    private String pwerk;
    private String xloek;

    public String getMeins() {
        return meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    private String meins;

    public String getPwerk() {
        return pwerk;
    }

    public void setPwerk(String pwerk) {
        this.pwerk = pwerk;
    }

    public String getXloek() {
        return xloek;
    }

    public void setXloek(String xloek) {
        this.xloek = xloek;
    }

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
        setUmrez((String) map.get("umrez"));
        setUmren((String) map.get("umren"));
        setWebaz((String) map.get("webaz"));
        setPwerk((String) map.get("pwerk"));
        setXloek((String) map.get("xloek"));
        setMeins((String) map.get("meins"));

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

    public String getWebaz() {
        return this.webaz;
    }

    public void setWebaz(String webaz) {
        this.webaz = webaz;
    }

}
