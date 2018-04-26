package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsLotSizeKeyTransEntity extends CommonEntity {

    private String lotSizeKey;
    private String localLotSizeKey;
    private String sourceSystem;

    public PlanCnsLotSizeKeyTransEntity(Map<String, Object> map) {
        super(map);

        setLotSizeKey((String) map.get("lotSizeKey"));
        setLocalLotSizeKey((String) map.get("localLotSizeKey"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getLotSizeKey() {
        return this.lotSizeKey;
    }

    public void setLotSizeKey(String lotSizeKey) {
        this.lotSizeKey = lotSizeKey;
    }

    public String getLocalLotSizeKey() {
        return this.localLotSizeKey;
    }

    public void setLocalLotSizeKey(String localLotSizeKey) {
        this.localLotSizeKey = localLotSizeKey;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}
