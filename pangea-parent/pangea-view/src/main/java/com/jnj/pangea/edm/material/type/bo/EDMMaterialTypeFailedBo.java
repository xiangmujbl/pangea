package com.jnj.pangea.edm.material.type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMaterialTypeFailedBo extends BaseBo {

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("materialType", this.materialType)
                .add("materialTypeName",this.materialTypeName)
                .toJsonString();
    }

    private String materialType;
    private String materialTypeName;
    private String errorCode;
    private String errorValue;

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }
}
