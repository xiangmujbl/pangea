package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EDMMatPlantStatV1Entity extends CommonEntity {

    private String sourceSystem;
    private String localPlantStatus;
    private String plantStatus;

    public EDMMatPlantStatV1Entity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalPlantStatus((String) map.get("localPlantStatus"));
        setPlantStatus((String) map.get("plantStatus"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPlantStatus() {
        return localPlantStatus;
    }

    public void setLocalPlantStatus(String localPlantStatus) {
        this.localPlantStatus = localPlantStatus;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }
}
