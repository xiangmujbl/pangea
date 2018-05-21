package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class LikpEntity extends CommonEntity {

    private String vbeln;
    private String vstel;
    private String gstrp;
    private String lfart;
    private String vbtyp;
    private String lfdat;
    private String erdat;
    private String bolnr;
    private String lifex;
    private String wadatist;
    private String lifnr;
    private String werks;

    public LikpEntity(Map<String, Object> map) {
        super(map);

        setVbeln((String) map.get("vbeln"));
        setVstel((String) map.get("vstel"));
        setGstrp((String) map.get("gstrp"));
        setLfart((String) map.get("lfart"));
        setVbtyp((String) map.get("vbtyp"));
        setLfdat((String) map.get("lfdat"));
        setErdat((String) map.get("erdat"));
        setBolnr((String) map.get("bolnr"));
        setLifex((String) map.get("lifex"));
        setWadatist((String) map.get("wadatist"));
        setLifnr((String) map.get("lifnr"));
        setWerks((String) map.get("werks"));
    }

    public String getVbeln() {
        return this.vbeln;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getVstel() {
        return this.vstel;
    }

    public void setVstel(String vstel) {
        this.vstel = vstel;
    }

    public String getGstrp() {
        return this.gstrp;
    }

    public void setGstrp(String gstrp) {
        this.gstrp = gstrp;
    }

    public String getLfart() {
        return this.lfart;
    }

    public void setLfart(String lfart) {
        this.lfart = lfart;
    }

    public String getVbtyp() {
        return this.vbtyp;
    }

    public void setVbtyp(String vbtyp) {
        this.vbtyp = vbtyp;
    }

    public String getLfdat() {
        return this.lfdat;
    }

    public void setLfdat(String lfdat) {
        this.lfdat = lfdat;
    }

    public String getErdat() {
        return this.erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }

    public String getBolnr() {
        return this.bolnr;
    }

    public void setBolnr(String bolnr) {
        this.bolnr = bolnr;
    }

    public String getLifex() {
        return this.lifex;
    }

    public void setLifex(String lifex) {this.lifex = lifex; }

    public String getWadatist() {
        return this.wadatist;
    }

    public void setWadatist(String wadatist) {
        this.wadatist = wadatist;
    }

    public String getLifnr() {
        return this.lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }


}
