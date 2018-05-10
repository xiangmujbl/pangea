package com.jnj.omp.common.entity.ems;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EmsFMdmMaterialTypesEntity extends CommonEntity {
    private String mdmCode;
    private String mdmName;
    private String zSourceSystem;

    public EmsFMdmMaterialTypesEntity(Map<String, Object> map) {
        super(map);
        setMdmCode((String) map.get("mdmCode"));
        setMdmName((String) map.get("mdmName"));
        setzSourceSystem((String) map.get("zSourceSystem"));
    }

    public String getMdmCode() {
        return mdmCode;
    }

    public void setMdmCode(String mdmCode) {
        this.mdmCode = mdmCode;
    }

    public String getMdmName() {
        return mdmName;
    }

    public void setMdmName(String mdmName) {
        this.mdmName = mdmName;
    }

    public String getzSourceSystem() {
        return zSourceSystem;
    }

    public void setzSourceSystem(String zSourceSystem) {
        this.zSourceSystem = zSourceSystem;
    }
}
