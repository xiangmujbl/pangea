package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MchbEntity extends CommonEntity {

    private String charg;
    private String lgort;

    public MchbEntity(Map<String, Object> map) {
        super(map);

        setCharg((String) map.get("charg"));
        setLgort((String) map.get("lgort"));
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getLgort() {
        return this.lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

}
