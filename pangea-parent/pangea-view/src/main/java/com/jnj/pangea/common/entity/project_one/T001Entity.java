package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T001Entity extends CommonEntity {

    private String mandt;
    private String bukrs;
    private String waers;

    public T001Entity(Map<String, Object> map) {
        super(map);
        setMandt((String) map.get("mandt"));
        setBukrs((String) map.get("bukrs"));
        setWaers((String) map.get("waers"));
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getWaers() {
        return waers;
    }

    public void setWaers(String waers) {
        this.waers = waers;
    }
}
