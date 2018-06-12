package com.jnj.pangea.common.entity.projectOne;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class ProjectOneKakoEntity extends CommonEntity {

    private String kapid;
    private String aznor;
    private String begzt;
    private String endzt;
    private String kalid;
    private String kapar;
    private String meins;
    private String name;
    private String ngrad;
    private String planr;
    private String poolk;
    private String werks;
    private String kapter;
    private String kapavo;
    private String ueberlast;
    private String kaplpl;
    private String kapeh;
    private String mehr;
    private String ang_unit;
    private String is_bottleneck;

    public ProjectOneKakoEntity(Map<String, Object> map) {
        super(map);

        setKapid((String) map.get("kapid"));
        setAznor((String) map.get("aznor"));
        setBegzt((String) map.get("begzt"));
        setEndzt((String) map.get("endzt"));
        setKalid((String) map.get("kalid"));
        setKapar((String) map.get("kapar"));
        setMeins((String) map.get("meins"));
        setName((String) map.get("name"));
        setNgrad((String) map.get("ngrad"));
        setPlanr((String) map.get("planr"));
        setPoolk((String) map.get("poolk"));
        setWerks((String) map.get("werks"));
        setKapter((String) map.get("kapter"));
        setKapavo((String) map.get("kapavo"));
        setUeberlast((String) map.get("ueberlast"));
        setKaplpl((String) map.get("kaplpl"));
        setKapeh((String) map.get("kapeh"));
        setMehr((String) map.get("mehr"));
        setAng_unit((String) map.get("ang_unit"));
        setIs_bottleneck((String) map.get("is_bottleneck"));
    }

    public String getKapid() {
        return this.kapid;
    }

    public void setKapid(String kapid) {
        this.kapid = kapid;
    }

    public String getAznor() {
        return this.aznor;
    }

    public void setAznor(String aznor) {
        this.aznor = aznor;
    }

    public String getBegzt() {
        return this.begzt;
    }

    public void setBegzt(String begzt) {
        this.begzt = begzt;
    }

    public String getEndzt() {
        return this.endzt;
    }

    public void setEndzt(String endzt) {
        this.endzt = endzt;
    }

    public String getKalid() {
        return this.kalid;
    }

    public void setKalid(String kalid) {
        this.kalid = kalid;
    }

    public String getKapar() {
        return this.kapar;
    }

    public void setKapar(String kapar) {
        this.kapar = kapar;
    }

    public String getMeins() {
        return this.meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNgrad() {
        return this.ngrad;
    }

    public void setNgrad(String ngrad) {
        this.ngrad = ngrad;
    }

    public String getPlanr() {
        return this.planr;
    }

    public void setPlanr(String planr) {
        this.planr = planr;
    }

    public String getPoolk() {
        return this.poolk;
    }

    public void setPoolk(String poolk) {
        this.poolk = poolk;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getKapter() {
        return this.kapter;
    }

    public void setKapter(String kapter) {
        this.kapter = kapter;
    }

    public String getKapavo() {
        return this.kapavo;
    }

    public void setKapavo(String kapavo) {
        this.kapavo = kapavo;
    }

    public String getUeberlast() {
        return this.ueberlast;
    }

    public void setUeberlast(String ueberlast) {
        this.ueberlast = ueberlast;
    }

    public String getKaplpl() {
        return this.kaplpl;
    }

    public void setKaplpl(String kaplpl) {
        this.kaplpl = kaplpl;
    }

    public String getKapeh() {
        return this.kapeh;
    }

    public void setKapeh(String kapeh) {
        this.kapeh = kapeh;
    }

    public String getMehr() {
        return this.mehr;
    }

    public void setMehr(String mehr) {
        this.mehr = mehr;
    }

    public String getAng_unit() {
        return this.ang_unit;
    }

    public void setAng_unit(String ang_unit) {
        this.ang_unit = ang_unit;
    }

    public String getIs_bottleneck() {
        return this.is_bottleneck;
    }

    public void setIs_bottleneck(String is_bottleneck) {
        this.is_bottleneck = is_bottleneck;
    }

}
