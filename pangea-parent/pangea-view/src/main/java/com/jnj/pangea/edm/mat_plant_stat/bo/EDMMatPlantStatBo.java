package com.jnj.pangea.edm.mat_plant_stat.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMatPlantStatBo extends BaseBo{
    private String sourceSystem;
    private String localPlantStatus;
    private String plantStatus;

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

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localPlantStatus",this.localPlantStatus)
                .toJsonString();
    }
}
