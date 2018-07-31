package com.jnj.pangea.edm.sub_brand_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * EDMSub-Brand- rework - Curation
 * AEAZ-8297
 */
public class EDMSubBrandV1Bo extends BaseBo {

    private String  subBrand ;
    private String subBrandDescription ;

    
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("subBrand", this.subBrand)
                .toJsonString();
    }

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
    public String toString() {
        return "EDMSubBrandV1Bo{" +
                "subBrand='" + subBrand + '\'' +
                ", subBrandDescription='" + subBrandDescription + '\'' +
                '}';
    }
}
