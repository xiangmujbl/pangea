package com.jnj.pangea.edm.product_family.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMProductFamilyBo extends BaseBo {
    private String productFamily;
    private String productFamilyName;

    public String getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getProductFamilyName() {
        return productFamilyName;
    }

    public void setProductFamilyName(String productFamilyName) {
        this.productFamilyName = productFamilyName;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("productFamily", this.productFamily)
                .toJsonString();
    }
}
