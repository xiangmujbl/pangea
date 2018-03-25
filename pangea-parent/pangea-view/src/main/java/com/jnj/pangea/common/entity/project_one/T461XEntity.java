package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T461XEntity extends CommonEntity {

    public T461XEntity(Map<String, Object> map) {
        super(map);
        setStrgr((String)map.get("strgr"));
        setText40((String)map.get("text40"));
        setSpras((String)map.get("spras"));
    }

    private String strgr;
    private String text40;
    private String spras;

    public String getStrgr() {
        return strgr;
    }

    public void setStrgr(String strgr) {
        this.strgr = strgr;
    }

    public String getText40() {
        return text40;
    }

    public void setText40(String text40) {
        this.text40 = text40;
    }

    public String getSpras() {
        return spras;
    }

    public void setSpras(String spras) {
        this.spras = spras;
    }
}
