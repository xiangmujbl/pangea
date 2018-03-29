package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMSubBrandV1Entity extends CommonEntity {

    private String subBrand;
    private String subBrandDescription;

    public EDMSubBrandV1Entity(Map<String, Object> map) {
        super(map);

        setSubBrand((String) map.get("subBrand"));
        setSubBrandDescription((String) map.get("subBrandDescription"));
    }

    public String getSubBrand() {
        return this.subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }

    public String getSubBrandDescription() {
        return this.subBrandDescription;
    }

    public void setSubBrandDescription(String subBrandDescription) {
        this.subBrandDescription = subBrandDescription;
    }

}
