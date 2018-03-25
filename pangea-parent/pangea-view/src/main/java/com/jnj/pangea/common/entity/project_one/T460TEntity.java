package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T460TEntity extends CommonEntity{
    private String ltext;
    private String sobsl;
    private String spras;

    public String getLtext() {
        return ltext;
    }

    public void setLtext(String ltext) {
        this.ltext = ltext;
    }

    public String getSobsl() {
        return sobsl;
    }

    public void setSobsl(String sobsl) {
        this.sobsl = sobsl;
    }

    public String getSpras() {
        return spras;
    }

    public void setSpras(String spras) {
        this.spras = spras;
    }

    public T460TEntity(Map<String, Object> map) {
        super(map);
        setLtext((String) map.get("ltext"));
        setSobsl((String)map.get("sobsl"));
        setSpras((String)map.get("spras"));
    }
}
