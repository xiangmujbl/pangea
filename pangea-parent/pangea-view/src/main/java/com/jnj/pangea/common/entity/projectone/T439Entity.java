package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T439Entity extends CommonEntity {
    public T439Entity(Map<String, Object> map) {
        super(map);
        setDisls((String)map.get("disls"));
    }
    private String disls;

    public String getDisls() {
        return disls;
    }

    public void setDisls(String disls) {
        this.disls = disls;
    }
}
