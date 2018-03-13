package com.jnj.pangea.common.entity.ngems;

import com.jnj.pangea.common.CommonEntity;

public class ProductFamilyEntity extends CommonEntity {
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
}
