package com.jnj.pangea.edm.category.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMCategoryBo extends BaseBo{
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

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("category", this.category)
                .toJsonString();
    }

    @Override
    public String toString() {
        return "EDMCategoryBo{" +
                "category='" + category + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
