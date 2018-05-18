package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EketEntity extends CommonEntity {

    private String bnfpo;
    private String dabmg;
    private String ebelp;
    private String wemng;
    private String banfn;
    private String ebeln;
    private String mandt;
    private String eindt;
    private String menge;
    private String etenr;
    private String glmng;

    public EketEntity(Map<String, Object> map) {
        super(map);
        setBnfpo((String)map.get("bnfpo"));
        setDabmg((String)map.get("dabmg"));
        setEbelp((String)map.get("ebelp"));
        setWemng((String)map.get("wemng"));
        setBanfn((String)map.get("banfn"));
        setEbeln((String)map.get("ebeln"));
        setMandt((String)map.get("mandt"));
        setEindt((String)map.get("eindt"));
        setMenge((String)map.get("menge"));
        setEtenr((String)map.get("etenr"));
        setGlmng((String)map.get("glmng"));
    }

    public String getBnfpo() {
        return bnfpo;
    }

    public void setBnfpo(String bnfpo) {
        this.bnfpo = bnfpo;
    }

    public String getDabmg() {
        return dabmg;
    }

    public void setDabmg(String dabmg) {
        this.dabmg = dabmg;
    }

    public String getEbelp() {
        return ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public String getWemng() {
        return wemng;
    }

    public void setWemng(String wemng) {
        this.wemng = wemng;
    }

    public String getBanfn() {
        return banfn;
    }

    public void setBanfn(String banfn) {
        this.banfn = banfn;
    }

    public String getEbeln() {
        return ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getEindt() {
        return eindt;
    }

    public void setEindt(String eindt) {
        this.eindt = eindt;
    }

    public String getMenge() {
        return menge;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    public String getEtenr() {
        return etenr;
    }

    public void setEtenr(String etenr) {
        this.etenr = etenr;
    }

    public String getGlmng() {
        return glmng;
    }

    public void setGlmng(String glmng) {
        this.glmng = glmng;
    }
}
