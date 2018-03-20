package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T141Entity extends CommonEntity {

    private String mmsta;

    public T141Entity(Map<String, Object> map) {
        super(map);
        setMmsta((String) map.get("mmsta"));
    }

    public String getMmsta() {
        return mmsta;
    }

    public void setMmsta(String mmsta) {
        this.mmsta = mmsta;
    }
}
