package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsRootDescriptionUserOverrideEntity extends CommonEntity {

    private String sourceSystem;
    private String localDpParentCode;
    private String rootDesc;
    private String ovrRootDesc;

    public PlanCnsRootDescriptionUserOverrideEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalDpParentCode((String) map.get("localDpParentCode"));
        setRootDesc((String) map.get("rootDesc"));
        setOvrRootDesc((String) map.get("ovrRootDesc"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalDpParentCode() {
        return this.localDpParentCode;
    }

    public void setLocalDpParentCode(String localDpParentCode) {
        this.localDpParentCode = localDpParentCode;
    }

    public String getRootDesc() {
        return this.rootDesc;
    }

    public void setRootDesc(String rootDesc) {
        this.rootDesc = rootDesc;
    }

    public String getOvrRootDesc() {
        return this.ovrRootDesc;
    }

    public void setOvrRootDesc(String ovrRootDesc) {
        this.ovrRootDesc = ovrRootDesc;
    }

}
