package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMBrandV1Entity extends CommonEntity {

    private String brand;
    private String brandDescription;

    public EDMBrandV1Entity(Map<String, Object> map) {
        super(map);

        setBrand((String) map.get("brand"));
        setBrandDescription((String) map.get("brandDescription"));
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandDescription() {
        return this.brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

}
