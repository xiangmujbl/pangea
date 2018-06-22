package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class VbakEntity extends CommonEntity {

    private String vbeln;
    private String erdat;
    private String audat;
    private String guebg;
    private String gueen;
    private String vbtyp;
    private String auart;
    private String augru;
    private String lifsk;
    private String faksk;
    private String vkorg;
    private String vtweg;
    private String spart;
    private String vkgrp;
    private String vkbur;
    private String vdatu;
    private String bstnk;
    private String kunnr;
    private String aedat;
    private String kalsm;
    private String mandt;

    public VbakEntity(Map<String, Object> map) {
        super(map);

        setVbeln((String) map.get("vbeln"));
        setErdat((String) map.get("erdat"));
        setAudat((String) map.get("audat"));
        setGuebg((String) map.get("guebg"));
        setGueen((String) map.get("gueen"));
        setVbtyp((String) map.get("vbtyp"));
        setAuart((String) map.get("auart"));
        setAugru((String) map.get("augru"));
        setLifsk((String) map.get("lifsk"));
        setFaksk((String) map.get("faksk"));
        setVkorg((String) map.get("vkorg"));
        setVtweg((String) map.get("vtweg"));
        setSpart((String) map.get("spart"));
        setVkgrp((String) map.get("vkgrp"));
        setVkbur((String) map.get("vkbur"));
        setVdatu((String) map.get("vdatu"));
        setBstnk((String) map.get("bstnk"));
        setKunnr((String) map.get("kunnr"));
        setAedat((String) map.get("aedat"));
        setKalsm((String) map.get("kalsm"));
        setMandt((String) map.get("mandt"));
    }

    public String getVbeln() {
        return vbeln;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getErdat() {
        return erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }

    public String getAudat() {
        return audat;
    }

    public void setAudat(String audat) {
        this.audat = audat;
    }

    public String getGuebg() {
        return guebg;
    }

    public void setGuebg(String guebg) {
        this.guebg = guebg;
    }

    public String getGueen() {
        return gueen;
    }

    public void setGueen(String gueen) {
        this.gueen = gueen;
    }

    public String getVbtyp() {
        return vbtyp;
    }

    public void setVbtyp(String vbtyp) {
        this.vbtyp = vbtyp;
    }

    public String getAuart() {
        return auart;
    }

    public void setAuart(String auart) {
        this.auart = auart;
    }

    public String getAugru() {
        return augru;
    }

    public void setAugru(String augru) {
        this.augru = augru;
    }

    public String getLifsk() {
        return lifsk;
    }

    public void setLifsk(String lifsk) {
        this.lifsk = lifsk;
    }

    public String getFaksk() {
        return faksk;
    }

    public void setFaksk(String faksk) {
        this.faksk = faksk;
    }

    public String getVkorg() {
        return vkorg;
    }

    public void setVkorg(String vkorg) {
        this.vkorg = vkorg;
    }

    public String getVtweg() {
        return vtweg;
    }

    public void setVtweg(String vtweg) {
        this.vtweg = vtweg;
    }

    public String getSpart() {
        return spart;
    }

    public void setSpart(String spart) {
        this.spart = spart;
    }

    public String getVkgrp() {
        return vkgrp;
    }

    public void setVkgrp(String vkgrp) {
        this.vkgrp = vkgrp;
    }

    public String getVkbur() {
        return vkbur;
    }

    public void setVkbur(String vkbur) {
        this.vkbur = vkbur;
    }

    public String getVdatu() {
        return vdatu;
    }

    public void setVdatu(String vdatu) {
        this.vdatu = vdatu;
    }

    public String getBstnk() {
        return bstnk;
    }

    public void setBstnk(String bstnk) {
        this.bstnk = bstnk;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getAedat() {
        return aedat;
    }

    public void setAedat(String aedat) {
        this.aedat = aedat;
    }

    public String getKalsm() {
        return kalsm;
    }

    public void setKalsm(String kalsm) {
        this.kalsm = kalsm;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }
}
