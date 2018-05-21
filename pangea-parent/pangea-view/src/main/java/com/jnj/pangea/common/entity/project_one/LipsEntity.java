package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class LipsEntity extends CommonEntity {

    private String mandt;
    private String vbeln;
    private String lfimg;
    private String posnr;
    private String matnr;
    private String charg;
    private String lichn;
    private String werks;
    private String meins;
    private String lgmng;
    private String vgbel;
    private String vgpos;

    public LipsEntity(Map<String, Object> map) {
        super(map);

        setMandt((String) map.get("mandt"));
        setLfimg((String) map.get("lfimg"));
        setVbeln((String) map.get("vbeln"));
        setPosnr((String) map.get("posnr"));
        setMatnr((String) map.get("matnr"));
        setCharg((String) map.get("charg"));
        setLichn((String) map.get("lichn"));
        setWerks((String) map.get("werks"));
        setMeins((String) map.get("meins"));
        setLgmng((String) map.get("lgmng"));
        setVgbel((String) map.get("vgbel"));
        setVgpos((String) map.get("vgpos"));
    }

    public String getMandt() { return mandt; }

    public void setMandt(String mandt) { this.mandt = mandt; }

    public String getLfimg() { return lfimg; }

    public void setLfimg(String lfimg) { this.lfimg = lfimg; }

    public String getVbeln() { return vbeln; }

    public void setVbeln(String vbeln) { this.vbeln = vbeln; }

    public String getPosnr() {
        return this.posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getLichn() {
        return this.lichn;
    }

    public void setLichn(String lichn) {
        this.lichn = lichn;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getMeins() {
        return this.meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getLgmng() {
        return this.lgmng;
    }

    public void setLgmng(String lgmng) {
        this.lgmng = lgmng;
    }

    public String getVgbel() {
        return this.vgbel;
    }

    public void setVgbel(String vgbel) {
        this.vgbel = vgbel;
    }

    public String getVgpos() {
        return this.vgpos;
    }

    public void setVgpos(String vgpos) {
        this.vgpos = vgpos;
    }

}
