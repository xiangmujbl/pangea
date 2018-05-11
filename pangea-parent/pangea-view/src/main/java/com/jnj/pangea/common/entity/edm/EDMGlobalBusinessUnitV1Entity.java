package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMGlobalBusinessUnitV1Entity extends CommonEntity {

    private String gbu;
    private String gbuName;

    public EDMGlobalBusinessUnitV1Entity(Map<String, Object> map) {
        super(map);

        setGbu((String) map.get("gbu"));
        setGbuName((String) map.get("gbuName"));
    }

    public String getGbu() {
        return this.gbu;
    }

    public void setGbu(String gbu) {
        this.gbu = gbu;
    }

    public String getGbuName() {
        return this.gbuName;
    }

    public void setGbuName(String gbuName) {
        this.gbuName = gbuName;
    }

}
