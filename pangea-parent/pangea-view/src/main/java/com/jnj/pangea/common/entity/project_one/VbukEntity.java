package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class VbukEntity extends CommonEntity {

    private String vbeln;
    private String mandt;
    private String fkstk;
    private String lfgsk;
    private String lfstk;
    private String abstk;
    private String gbstk;

    public VbukEntity(Map<String, Object> map) {
        super(map);

        setVbeln((String) map.get("vbeln"));
        setMandt((String) map.get("mandt"));
        setFkstk((String) map.get("fkstk"));
        setLfgsk((String) map.get("lfgsk"));
        setLfstk((String) map.get("lfstk"));
        setAbstk((String) map.get("abstk"));
        setGbstk((String) map.get("gbstk"));
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

    public String getFkstk() {
        return fkstk;
    }

    public void setFkstk(String fkstk) {
        this.fkstk = fkstk;
    }

    public String getLfgsk() {
        return lfgsk;
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

    public String getGbstk() {
        return gbstk;
    }

    public void setGbstk(String gbstk) {
        this.gbstk = gbstk;
    }
}
