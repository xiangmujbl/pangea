package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class TmabcEntity extends CommonEntity {

    private String maabc="";

    public TmabcEntity(Map<String, Object> map) {
        super(map);
        setMaabc((String) map.get("maabc"));
    }

    public String getMaabc() {
        return maabc;
    }

    public void setMaabc(String maabc) {
        this.maabc = maabc;
    }
}
