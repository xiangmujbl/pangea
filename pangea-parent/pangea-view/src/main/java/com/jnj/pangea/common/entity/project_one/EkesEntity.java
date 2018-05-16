package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EkesEntity extends CommonEntity {

    private String ebeln;
    private String ebelp;
    private String etens;
    private String mandt;
    private String ebtyp;
    private String eindt;
    private String erdat;
    private String menge;
    private String dabmg;
    private String estkz;
    private String vbeln;
    private String vbelp;
    private String charg;
    private String ematn;
    private String ezeit;
    private String imwrk;
    private String kzdis;
    private String loekz;
    private String lpein;
    private String mahnz;
    private String mprof;
    private String refEtens;
    private String uecha;
    private String uzeit;
    private String xblnr;


    public EkesEntity(Map<String, Object> map) {
        super(map);
        setEbeln((String)map.get("ebeln"));
        setEbelp((String)map.get("ebelp"));
        setEtens((String)map.get("etens"));
        setMandt((String)map.get("mandt"));
        setEbtyp((String)map.get("ebtyp"));
        setEindt((String)map.get("eindt"));
        setErdat((String)map.get("erdat"));
        setMenge((String)map.get("menge"));
        setDabmg((String)map.get("dabmg"));
        setEstkz((String)map.get("estkz"));
        setVbeln((String)map.get("vbeln"));
        setVbelp((String)map.get("vbelp"));
        setCharg((String)map.get("charg"));
        setEmatn((String)map.get("ematn"));
        setEzeit((String)map.get("ezeit"));
        setImwrk((String)map.get("imwrk"));
        setKzdis((String)map.get("kzdis"));
        setLoekz((String)map.get("loekz"));
        setLpein((String)map.get("lpein"));
        setMahnz((String)map.get("mahnz"));
        setMprof((String)map.get("mprof"));
        setRefEtens((String)map.get("refEtens"));
        setUecha((String)map.get("uecha"));
        setUzeit((String)map.get("uzeit"));
        setXblnr((String)map.get("xblnr"));
    }

    public String getEbeln() {
        return ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getEbelp() {
        return ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public String getEtens() {
        return etens;
    }

    public void setEtens(String etens) {
        this.etens = etens;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getEbtyp() {
        return ebtyp;
    }

    public void setEbtyp(String ebtyp) {
        this.ebtyp = ebtyp;
    }

    public String getEindt() {
        return eindt;
    }

    public void setEindt(String eindt) {
        this.eindt = eindt;
    }

    public String getErdat() {
        return erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }

    public String getMenge() {
        return menge;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    public String getDabmg() {
        return dabmg;
    }

    public void setDabmg(String dabmg) {
        this.dabmg = dabmg;
    }

    public String getEstkz() {
        return estkz;
    }

    public void setEstkz(String estkz) {
        this.estkz = estkz;
    }

    public String getVbeln() {
        return vbeln;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getVbelp() {
        return vbelp;
    }

    public void setVbelp(String vbelp) {
        this.vbelp = vbelp;
    }

    public String getCharg() {
        return charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getEmatn() {
        return ematn;
    }

    public void setEmatn(String ematn) {
        this.ematn = ematn;
    }

    public String getEzeit() {
        return ezeit;
    }

    public void setEzeit(String ezeit) {
        this.ezeit = ezeit;
    }

    public String getImwrk() {
        return imwrk;
    }

    public void setImwrk(String imwrk) {
        this.imwrk = imwrk;
    }

    public String getKzdis() {
        return kzdis;
    }

    public void setKzdis(String kzdis) {
        this.kzdis = kzdis;
    }

    public String getLoekz() {
        return loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getLpein() {
        return lpein;
    }

    public void setLpein(String lpein) {
        this.lpein = lpein;
    }

    public String getMahnz() {
        return mahnz;
    }

    public void setMahnz(String mahnz) {
        this.mahnz = mahnz;
    }

    public String getMprof() {
        return mprof;
    }

    public void setMprof(String mprof) {
        this.mprof = mprof;
    }

    public String getRefEtens() {
        return refEtens;
    }

    public void setRefEtens(String refEtens) {
        this.refEtens = refEtens;
    }

    public String getUecha() {
        return uecha;
    }

    public void setUecha(String uecha) {
        this.uecha = uecha;
    }

    public String getUzeit() {
        return uzeit;
    }

    public void setUzeit(String uzeit) {
        this.uzeit = uzeit;
    }

    public String getXblnr() {
        return xblnr;
    }

    public void setXblnr(String xblnr) {
        this.xblnr = xblnr;
    }
}
