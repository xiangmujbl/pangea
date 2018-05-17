package com.jnj.omp.common.entity.project_one;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class VbapEntity extends CommonEntity {

    private String vbeln;
    private String posnr;
    private String matnr;
    private String werks;
    private String pstyv;
    private String lfrel;
    private String fkrel;
    private String abgru;
    private String kwmeng;
    private String vrkme;
    private String umvkz;
    private String umvkn;
    private String faksp;
    private String netwr;
    private String waerk;
    private String lgort;
    private String vstel;
    private String route;

    public VbapEntity(Map<String, Object> map) {
        super(map);

        setVbeln((String) map.get("vbeln"));
        setPosnr((String) map.get("posnr"));
        setMatnr((String) map.get("matnr"));
        setWerks((String) map.get("werks"));
        setPstyv((String) map.get("pstyv"));
        setLfrel((String) map.get("lfrel"));
        setFkrel((String) map.get("fkrel"));
        setAbgru((String) map.get("abgru"));
        setKwmeng((String) map.get("kwmeng"));
        setVrkme((String) map.get("vrkme"));
        setUmvkz((String) map.get("umvkz"));
        setUmvkn((String) map.get("umvkn"));
        setFaksp((String) map.get("faksp"));
        setNetwr((String) map.get("netwr"));
        setWaerk((String) map.get("waerk"));
        setLgort((String) map.get("lgort"));
        setVstel((String) map.get("vstel"));
        setRoute((String) map.get("route"));
    }

    public String getVbeln() {
        return vbeln;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getPosnr() {
        return posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

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

    public String getPstyv() {
        return pstyv;
    }

    public void setPstyv(String pstyv) {
        this.pstyv = pstyv;
    }

    public String getLfrel() {
        return lfrel;
    }

    public void setLfrel(String lfrel) {
        this.lfrel = lfrel;
    }

    public String getFkrel() {
        return fkrel;
    }

    public void setFkrel(String fkrel) {
        this.fkrel = fkrel;
    }

    public String getAbgru() {
        return abgru;
    }

    public void setAbgru(String abgru) {
        this.abgru = abgru;
    }

    public String getKwmeng() {
        return kwmeng;
    }

    public void setKwmeng(String kwmeng) {
        this.kwmeng = kwmeng;
    }

    public String getVrkme() {
        return vrkme;
    }

    public void setVrkme(String vrkme) {
        this.vrkme = vrkme;
    }

    public String getUmvkz() {
        return umvkz;
    }

    public void setUmvkz(String umvkz) {
        this.umvkz = umvkz;
    }

    public String getUmvkn() {
        return umvkn;
    }

    public void setUmvkn(String umvkn) {
        this.umvkn = umvkn;
    }

    public String getFaksp() {
        return faksp;
    }

    public void setFaksp(String faksp) {
        this.faksp = faksp;
    }

    public String getNetwr() {
        return netwr;
    }

    public void setNetwr(String netwr) {
        this.netwr = netwr;
    }

    public String getWaerk() {
        return waerk;
    }

    public void setWaerk(String waerk) {
        this.waerk = waerk;
    }

    public String getLgort() {
        return lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getVstel() {
        return vstel;
    }

    public void setVstel(String vstel) {
        this.vstel = vstel;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
