package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T461PEntity extends CommonEntity {
    public T461PEntity(Map<String, Object> map) {
        super(map);
        setStrgr((String)map.get("strgr"));
    }
    private String strgr;

    public String getStrgr() {
        return strgr;
    }

    public void setStrgr(String strgr) {
        this.strgr = strgr;
    }
}
