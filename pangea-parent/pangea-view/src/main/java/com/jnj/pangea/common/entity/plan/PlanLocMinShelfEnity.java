package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * @author: qzhan112
 * @date: 2018/5/17
 */
public class PlanLocMinShelfEnity extends CommonEntity {
    private String localMaterialNumber;
    private String localPlant;
    private String sourceSystem;
    private String minMinShelfLife;
    private String minShelfLife;

    public PlanLocMinShelfEnity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalPlant((String) map.get("localPlant"));
        setSourceSystem((String) map.get("sourceSystem"));
        setMinMinShelfLife((String) map.get("minMinShelfLife"));
        setMinShelfLife((String) map.get("minShelfLife"));
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getMinMinShelfLife() {
        return minMinShelfLife;
    }

    public void setMinMinShelfLife(String minMinShelfLife) {
        this.minMinShelfLife = minMinShelfLife;
    }

    public String getMinShelfLife() {
        return minShelfLife;
    }

    public void setMinShelfLife(String minShelfLife) {
        this.minShelfLife = minShelfLife;
    }
}
