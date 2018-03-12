package com.jnj.pangea.edm.sub_brand.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMSubBrandBo extends BaseBo{
    private String subBrand;
    private String subBrandDescription;

    public String getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }

    public String getSubBrandDescription() {
        return subBrandDescription;
    }

    public void setSubBrandDescription(String subBrandDescription) {
        this.subBrandDescription = subBrandDescription;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("subBrand", this.subBrand)
                .toJsonString();
    }
}
