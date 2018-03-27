package com.jnj.pangea.edm.batch_master.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMBatchMasterBo extends BaseBo {

    private String sourceSystem;
    private String localMaterialNumber;
    private String materialNumber;
    private String localBatchNumber;
    private String localShelfLifeExpiration;
    private String localDateofManufacture;

    @Override
    public String toString() {
        return "EDMBatchMasterBo{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", localMaterialNumber='" + localMaterialNumber + '\'' +
                ", materialNumber='" + materialNumber + '\'' +
                ", localBatchNumber='" + localBatchNumber + '\'' +
                ", localShelfLifeExpiration='" + localShelfLifeExpiration + '\'' +
                ", localDateofManufacture='" + localDateofManufacture + '\'' +
                '}';
    }

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber",this.localMaterialNumber)
                .add("materialNumber",this.materialNumber)
                .add("localBatchNumber",this.localBatchNumber)
                .toJsonString();
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalBatchNumber() {
        return localBatchNumber;
    }

    public void setLocalBatchNumber(String localBatchNumber) {
        this.localBatchNumber = localBatchNumber;
    }

    public String getLocalShelfLifeExpiration() {
        return localShelfLifeExpiration;
    }

    public void setLocalShelfLifeExpiration(String localShelfLifeExpiration) {
        this.localShelfLifeExpiration = localShelfLifeExpiration;
    }

    public String getLocalDateofManufacture() {
        return localDateofManufacture;
    }

    public void setLocalDateofManufacture(String localDateofManufacture) {
        this.localDateofManufacture = localDateofManufacture;
    }
}
