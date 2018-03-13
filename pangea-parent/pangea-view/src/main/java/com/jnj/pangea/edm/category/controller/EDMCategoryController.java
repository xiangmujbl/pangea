package com.jnj.pangea.edm.category.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.CategoryEntity;
import com.jnj.pangea.edm.category.bo.EDMCategoryBo;
import com.jnj.pangea.util.BeanUtil;

public class EDMCategoryController extends CommonController {
    @Override
    public ResultObject process(RawDataEvent raw) {
        LogUtil.getCoreLog().info("======================= EDMCategoryController start====");
        ResultObject resultObject = new ResultObject();

        CategoryEntity categoryEntity = BeanUtil.mapToBean(raw.getValue().toMap(), CategoryEntity.class);

        EDMCategoryBo categoryBo = new EDMCategoryBo();

        categoryBo.setCategory(categoryEntity.getCategory());
        categoryBo.setCategoryName(categoryEntity.getCategoryName());

        resultObject.setBaseBo(categoryBo);
        LogUtil.getCoreLog().info("======================= categoryBo: {}====", categoryBo);

        LogUtil.getCoreLog().info("======================= EDMCategoryController end====");

//        ViewResultItem viewRaw = ViewResultBuilder.newResultItem(categoryBo.getKey(), categoryBo.toMap());
////        result.add(viewRaw);

        return resultObject;
    }
}
