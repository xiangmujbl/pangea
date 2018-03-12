package com.jnj.pangea.common.entry.ngems;

import com.jnj.pangea.common.CommonEntry;

public class CategoryEntity extends CommonEntry{
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
