package com.jnj.omp.common.entity.project_one;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class T001KEntity extends CommonEntity {

    private String mandt;
    private String bwkey;
    private String bukrs;

    public T001KEntity(Map<String, Object> map) {
        super(map);
        setMandt((String) map.get("mandt"));
        setBwkey((String) map.get("bwkey"));
        setBukrs((String) map.get("bukrs"));
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getBwkey() {
        return bwkey;
    }

    public void setBwkey(String bwkey) {
        this.bwkey = bwkey;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }
}
