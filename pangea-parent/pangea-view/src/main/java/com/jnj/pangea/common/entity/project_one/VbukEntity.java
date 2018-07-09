package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class VbukEntity extends CommonEntity {

    private String gbstk;
    private String wbstk;
    private String kostk;
    private String lvstk;
    private String fkstk;
    private String mandt;
    private String vbeln;

    private String lfgsk;
    private String lfstk;
    private String abstk;

    public VbukEntity(Map<String, Object> map) {
        super(map);

        setGbstk((String) map.get("gbstk"));
        setWbstk((String) map.get("wbstk"));
        setKostk((String) map.get("kostk"));
        setLvstk((String) map.get("lvstk"));
        setFkstk((String) map.get("fkstk"));
        setMandt((String) map.get("mandt"));
        setVbeln((String) map.get("vbeln"));

        setLfgsk((String) map.get("lfgsk"));
        setLfstk((String) map.get("lfstk"));
        setAbstk((String) map.get("abstk"));
    }

    public void setGbstk(String gbstk) {
        this.gbstk = gbstk;
    }

    public void setWbstk(String wbstk) {
        this.wbstk = wbstk;
    }

    public void setKostk(String kostk) {
        this.kostk = kostk;
    }

    public void setLvstk(String lvstk) {
        this.lvstk = lvstk;
    }

    public void setFkstk(String fkstk) {
        this.fkstk = fkstk;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getGbstk() {
        return gbstk;
    }

    public String getWbstk() {
        return wbstk;
    }

    public String getKostk() {
        return kostk;
    }

    public String getLvstk() {
        return lvstk;
    }

    public String getFkstk() {
        return fkstk;
    }

    public String getMandt() {
        return mandt;
    }

    public String getVbeln() {
        return vbeln;
    }

    public void setLfgsk(String lfgsk) {
        this.lfgsk = lfgsk;
    }

    public String getLfstk() {
        return lfstk;
    }

    public void setLfstk(String lfstk) {
        this.lfstk = lfstk;
    }

    public String getAbstk() {
        return abstk;
    }

    public void setAbstk(String abstk) {
        this.abstk = abstk;
    }

    public String getLfgsk() {
        return lfgsk;
    }
}
