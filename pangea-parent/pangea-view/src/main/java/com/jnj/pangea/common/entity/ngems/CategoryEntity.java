package com.jnj.pangea.common.entity.ngems;

import com.jnj.pangea.common.CommonEntity;

public class CategoryEntity extends CommonEntity {
    private String category;
    private String categoryName;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
