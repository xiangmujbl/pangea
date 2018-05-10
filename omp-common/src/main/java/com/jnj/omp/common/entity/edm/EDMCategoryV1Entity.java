package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EDMCategoryV1Entity extends CommonEntity {

    private String category;
    private String categoryName;

    public EDMCategoryV1Entity(Map<String, Object> map) {
        super(map);

        setCategory((String) map.get("category"));
        setCategoryName((String) map.get("categoryName"));
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
