package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneQalsEntity extends CommonEntity {

    private String mandt;
    private String prueflos;
    private String werk;
    private String matnr;
    private String art;
    private String herkunft;
    private String objnr;
    private String stsma;
    private String stat35;
    private String enstehdat;
    private String entstezeit;
    private String ERSTELDATERSTELZEIT;
    private String AENDERDATAENDERZEIT;
    private String pastrterm;
    private String pastrzeit;
    private String paendterm;
    private String paendzeit;
    private String aufnr;
    private String kunnr;
    private String lifnr;
    private String charg;
    private String lagortchrg;
    private String ebeln;
    private String ebelp;
    private String mjahr;
    private String mblnr;
    private String werkvorg;
    private String lagortvorg;
    private String losmenge;
    private String mengeneinh;
    private String lmengezub;
    private String lmengeist;

    public ProjectOneQalsEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setPrueflos((String) map.get("prueflos"));
        setWerk((String) map.get("werk"));
        setMatnr((String) map.get("matnr"));
        setArt((String) map.get("art"));
        setHerkunft((String) map.get("herkunft"));
        setObjnr((String) map.get("objnr"));
        setStsma((String) map.get("stsma"));
        setStat35((String) map.get("stat35"));
        setEnstehdat((String) map.get("enstehdat"));
        setEntstezeit((String) map.get("entstezeit"));
        setERSTELDATERSTELZEIT((String) map.get("ERSTELDATERSTELZEIT"));
        setAENDERDATAENDERZEIT((String) map.get("AENDERDATAENDERZEIT"));
        setPastrterm((String) map.get("pastrterm"));
        setPastrzeit((String) map.get("pastrzeit"));
        setPaendterm((String) map.get("paendterm"));
        setPaendzeit((String) map.get("paendzeit"));
        setAufnr((String) map.get("aufnr"));
        setKunnr((String) map.get("kunnr"));
        setLifnr((String) map.get("lifnr"));
        setCharg((String) map.get("charg"));
        setLagortchrg((String) map.get("lagortchrg"));
        setEbeln((String) map.get("ebeln"));
        setEbelp((String) map.get("ebelp"));
        setMjahr((String) map.get("mjahr"));
        setMblnr((String) map.get("mblnr"));
        setWerkvorg((String) map.get("werkvorg"));
        setLagortvorg((String) map.get("lagortvorg"));
        setLosmenge((String) map.get("losmenge"));
        setMengeneinh((String) map.get("mengeneinh"));
        setLmengezub((String) map.get("lmengezub"));
        setLmengeist((String) map.get("lmengeist"));
    }

    public String getMandt() {
        return this.mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getPrueflos() {
        return this.prueflos;
    }

    public void setPrueflos(String prueflos) {
        this.prueflos = prueflos;
    }

    public String getWerk() {
        return this.werk;
    }

    public void setWerk(String werk) {
        this.werk = werk;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getArt() {
        return this.art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getHerkunft() {
        return this.herkunft;
    }

    public void setHerkunft(String herkunft) {
        this.herkunft = herkunft;
    }

    public String getObjnr() {
        return this.objnr;
    }

    public void setObjnr(String objnr) {
        this.objnr = objnr;
    }

    public String getStsma() {
        return this.stsma;
    }

    public void setStsma(String stsma) {
        this.stsma = stsma;
    }

    public String getStat35() {
        return this.stat35;
    }

    public void setStat35(String stat35) {
        this.stat35 = stat35;
    }

    public String getEnstehdat() {
        return this.enstehdat;
    }

    public void setEnstehdat(String enstehdat) {
        this.enstehdat = enstehdat;
    }

    public String getEntstezeit() {
        return this.entstezeit;
    }

    public void setEntstezeit(String entstezeit) {
        this.entstezeit = entstezeit;
    }

    public String getERSTELDATERSTELZEIT() {
        return this.ERSTELDATERSTELZEIT;
    }

    public void setERSTELDATERSTELZEIT(String ERSTELDATERSTELZEIT) {
        this.ERSTELDATERSTELZEIT = ERSTELDATERSTELZEIT;
    }

    public String getAENDERDATAENDERZEIT() {
        return this.AENDERDATAENDERZEIT;
    }

    public void setAENDERDATAENDERZEIT(String AENDERDATAENDERZEIT) {
        this.AENDERDATAENDERZEIT = AENDERDATAENDERZEIT;
    }

    public String getPastrterm() {
        return this.pastrterm;
    }

    public void setPastrterm(String pastrterm) {
        this.pastrterm = pastrterm;
    }

    public String getPastrzeit() {
        return this.pastrzeit;
    }

    public void setPastrzeit(String pastrzeit) {
        this.pastrzeit = pastrzeit;
    }

    public String getPaendterm() {
        return this.paendterm;
    }

    public void setPaendterm(String paendterm) {
        this.paendterm = paendterm;
    }

    public String getPaendzeit() {
        return this.paendzeit;
    }

    public void setPaendzeit(String paendzeit) {
        this.paendzeit = paendzeit;
    }

    public String getAufnr() {
        return this.aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getKunnr() {
        return this.kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getLifnr() {
        return this.lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getLagortchrg() {
        return this.lagortchrg;
    }

    public void setLagortchrg(String lagortchrg) {
        this.lagortchrg = lagortchrg;
    }

    public String getEbeln() {
        return this.ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getEbelp() {
        return this.ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public String getMjahr() {
        return this.mjahr;
    }

    public void setMjahr(String mjahr) {
        this.mjahr = mjahr;
    }

    public String getMblnr() {
        return this.mblnr;
    }

    public void setMblnr(String mblnr) {
        this.mblnr = mblnr;
    }

    public String getWerkvorg() {
        return this.werkvorg;
    }

    public void setWerkvorg(String werkvorg) {
        this.werkvorg = werkvorg;
    }

    public String getLagortvorg() {
        return this.lagortvorg;
    }

    public void setLagortvorg(String lagortvorg) {
        this.lagortvorg = lagortvorg;
    }

    public String getLosmenge() {
        return this.losmenge;
    }

    public void setLosmenge(String losmenge) {
        this.losmenge = losmenge;
    }

    public String getMengeneinh() {
        return this.mengeneinh;
    }

    public void setMengeneinh(String mengeneinh) {
        this.mengeneinh = mengeneinh;
    }

    public String getLmengezub() {
        return this.lmengezub;
    }

    public void setLmengezub(String lmengezub) {
        this.lmengezub = lmengezub;
    }

    public String getLmengeist() {
        return this.lmengeist;
    }

    public void setLmengeist(String lmengeist) {
        this.lmengeist = lmengeist;
    }

}
