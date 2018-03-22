package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T460AEntity extends CommonEntity{
    private String sobsl;
    public String getSobsl() {
        return sobsl;
    }

    public void setSobsl(String sobsl) {
        this.sobsl = sobsl;
    }

    public T460AEntity(Map<String, Object> map) {
        super(map);
        setSobsl((String) map.get("sobsl"));
    }
}
