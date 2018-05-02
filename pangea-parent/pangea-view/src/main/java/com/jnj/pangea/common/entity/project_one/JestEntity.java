package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class JestEntity extends CommonEntity {

    private String objnr;
    private String stat;
    private String inact;

    public JestEntity(Map<String, Object> map) {
        super(map);

        setObjnr((String) map.get("objnr"));
        setStat((String) map.get("stat"));
        setInact((String) map.get("inact"));
    }

    public String getObjnr() {
        return this.objnr;
    }

    public void setObjnr(String objnr) {
        this.objnr = objnr;
    }

    public String getStat() {
        return this.stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getInact() {
        return this.inact;
    }

    public void setInact(String inact) {
        this.inact = inact;
    }

}
