package com.jnj.pangea.edm.material.type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMaterialTypeBo  extends BaseBo {

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("materialType", this.materialType)
                .toJsonString();
    }

    private String materialType;
    private String materialTypeName;

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
}