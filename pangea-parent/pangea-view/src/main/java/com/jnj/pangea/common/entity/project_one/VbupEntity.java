package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class VbupEntity extends CommonEntity {

    private String vbeln;
    private String mandt;
    private String posnr;
    private String gbsta;
    private String absta;
    private String lfsta;
    private String lfgsa;
    private String fksta;
    private String wbsta;
    private String kosta;
    private String lvsta;

    public VbupEntity(Map<String, Object> map) {
        super(map);

        setVbeln((String) map.get("vbeln"));
        setMandt((String) map.get("mandt"));
        setMandt((String) map.get("posnr"));
        setGbsta((String) map.get("gbsta"));
        setAbsta((String) map.get("absta"));
        setLfsta((String) map.get("lfsta"));
        setLfgsa((String) map.get("lfgsa"));
        setFksta((String) map.get("fksta"));
        setWbsta((String) map.get("wbsta"));
        setKosta((String) map.get("kosta"));
        setLvsta((String) map.get("lvsta"));
    }

    public String getVbeln() {
        return vbeln;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getPosnr() {
        return posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

    public String getGbsta() {
        return gbsta;
    }

    public void setGbsta(String gbsta) {
        this.gbsta = gbsta;
    }

    public String getAbsta() {
        return absta;
    }

    public void setAbsta(String absta) {
        this.absta = absta;
    }

    public String getLfsta() {
        return lfsta;
    }

    public void setLfsta(String lfsta) {
        this.lfsta = lfsta;
    }

    public String getLfgsa() {
        return lfgsa;
    }

    public void setLfgsa(String lfgsa) {
        this.lfgsa = lfgsa;
    }

    public String getFksta() {
        return fksta;
    }

    public void setFksta(String fksta) {
        this.fksta = fksta;
    }

    public String getWbsta() {
        return wbsta;
    }

    public void setWbsta(String wbsta) {
        this.wbsta = wbsta;
    }

    public String getKosta() {
        return kosta;
    }

    public void setKosta(String kosta) {
        this.kosta = kosta;
    }

    public String getLvsta() {
        return lvsta;
    }

    public void setLvsta(String lvsta) {
        this.lvsta = lvsta;
    }
}