package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMProductFamilyV1Entity extends CommonEntity {

    private String productFamily;
    private String productFamilyName;

    public EDMProductFamilyV1Entity(Map<String, Object> map) {
        super(map);

        setProductFamily((String) map.get("productFamily"));
        setProductFamilyName((String) map.get("productFamilyName"));
    }

    public String getProductFamily() {
        return this.productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getProductFamilyName() {
        return this.productFamilyName;
    }

    public void setProductFamilyName(String productFamilyName) {
        this.productFamilyName = productFamilyName;
    }

}
