package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class T439TEntity extends CommonEntity {

    public T439TEntity(Map<String, Object> map) {
        super(map);
        setLoslt((String)map.get("loslt"));
        setDisls((String)map.get("disls"));
        setSpras((String)map.get("spras"));
    }

    private String loslt;
    private String disls;
    private String spras;

    public String getLoslt() {
        return loslt;
    }

    public void setLoslt(String loslt) {
        this.loslt = loslt;
    }

    public String getDisls() {
        return disls;
    }

    public void setDisls(String disls) {
        this.disls = disls;
    }

    public String getSpras() {
        return spras;
    }

    public void setSpras(String spras) {
        this.spras = spras;
    }
}
