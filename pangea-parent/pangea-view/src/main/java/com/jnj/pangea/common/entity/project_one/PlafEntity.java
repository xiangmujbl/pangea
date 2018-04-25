package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlafEntity extends CommonEntity {

    private String plnum;
    private String plwrk;
    private String pwwrk;
    private String matnr;
    private String meins;
    private String beskz;
    private String sobes;
    private String numvr;
    private String paart;
    private String gsmng;
    private String avmng;
    private String bdmng;
    private String psttr;
    private String pstti;
    private String pedtr;
    private String pedti;
    private String webaz;
    private String auffx;
    private String lgort;
    private String verid;
    private String terst;
    private String tered;
    private String dispo;
    private String plscn;

    public PlafEntity(Map<String, Object> map) {
        super(map);

        setPlnum((String) map.get("plnum"));
        setPlwrk((String) map.get("plwrk"));
        setPwwrk((String) map.get("pwwrk"));
        setMatnr((String) map.get("matnr"));
        setMeins((String) map.get("meins"));
        setBeskz((String) map.get("beskz"));
        setSobes((String) map.get("sobes"));
        setNumvr((String) map.get("numvr"));
        setPaart((String) map.get("paart"));
        setGsmng((String) map.get("gsmng"));
        setAvmng((String) map.get("avmng"));
        setBdmng((String) map.get("bdmng"));
        setPsttr((String) map.get("psttr"));
        setPstti((String) map.get("pstti"));
        setPedtr((String) map.get("pedtr"));
        setPedti((String) map.get("pedti"));
        setWebaz((String) map.get("webaz"));
        setAuffx((String) map.get("auffx"));
        setLgort((String) map.get("lgort"));
        setVerid((String) map.get("verid"));
        setTerst((String) map.get("terst"));
        setTered((String) map.get("tered"));
        setDispo((String) map.get("dispo"));
        setPlscn((String) map.get("plscn"));
    }

    public String getPlnum() {
        return this.plnum;
    }

    public void setPlnum(String plnum) {
        this.plnum = plnum;
    }

    public String getPlwrk() {
        return this.plwrk;
    }

    public void setPlwrk(String plwrk) {
        this.plwrk = plwrk;
    }

    public String getPwwrk() {
        return this.pwwrk;
    }

    public void setPwwrk(String pwwrk) {
        this.pwwrk = pwwrk;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMeins() {
        return this.meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getBeskz() {
        return this.beskz;
    }

    public void setBeskz(String beskz) {
        this.beskz = beskz;
    }

    public String getSobes() {
        return this.sobes;
    }

    public void setSobes(String sobes) {
        this.sobes = sobes;
    }

    public String getNumvr() {
        return this.numvr;
    }

    public void setNumvr(String numvr) {
        this.numvr = numvr;
    }

    public String getPaart() {
        return this.paart;
    }

    public void setPaart(String paart) {
        this.paart = paart;
    }

    public String getGsmng() {
        return this.gsmng;
    }

    public void setGsmng(String gsmng) {
        this.gsmng = gsmng;
    }

    public String getAvmng() {
        return this.avmng;
    }

    public void setAvmng(String avmng) {
        this.avmng = avmng;
    }

    public String getBdmng() {
        return this.bdmng;
    }

    public void setBdmng(String bdmng) {
        this.bdmng = bdmng;
    }

    public String getPsttr() {
        return this.psttr;
    }

    public void setPsttr(String psttr) {
        this.psttr = psttr;
    }

    public String getPstti() {
        return this.pstti;
    }

    public void setPstti(String pstti) {
        this.pstti = pstti;
    }

    public String getPedtr() {
        return this.pedtr;
    }

    public void setPedtr(String pedtr) {
        this.pedtr = pedtr;
    }

    public String getPedti() {
        return this.pedti;
    }

    public void setPedti(String pedti) {
        this.pedti = pedti;
    }

    public String getWebaz() {
        return this.webaz;
    }

    public void setWebaz(String webaz) {
        this.webaz = webaz;
    }

    public String getAuffx() {
        return this.auffx;
    }

    public void setAuffx(String auffx) {
        this.auffx = auffx;
    }

    public String getLgort() {
        return this.lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getVerid() {
        return this.verid;
    }

    public void setVerid(String verid) {
        this.verid = verid;
    }

    public String getTerst() {
        return this.terst;
    }

    public void setTerst(String terst) {
        this.terst = terst;
    }

    public String getTered() {
        return this.tered;
    }

    public void setTered(String tered) {
        this.tered = tered;
    }

    public String getDispo() {
        return this.dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getPlscn() {
        return this.plscn;
    }

    public void setPlscn(String plscn) {
        this.plscn = plscn;
    }

}
