package com.jnj.pangea.common.entry.ngems;

import com.jnj.pangea.common.CommonEntry;

public class BrandEntity extends CommonEntry{
    private String brand;
    private String brandDescription;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }
}
