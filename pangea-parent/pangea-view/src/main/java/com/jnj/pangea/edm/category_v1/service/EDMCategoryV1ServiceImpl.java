package com.jnj.pangea.edm.category_v1.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.infa_mdm.ClKupCatgV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.category_v1.bo.EDMCategoryV1Bo;

/**
 * EDMCategory - rework - Curation
 * AEAZ-8294
 */
public class EDMCategoryV1ServiceImpl implements ICommonService {

    private static EDMCategoryV1ServiceImpl instance;

    public static EDMCategoryV1ServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMCategoryV1ServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMCategoryV1Bo categoryBo = new EDMCategoryV1Bo();
        ClKupCatgV1Entity clKupCatgEntity=(ClKupCatgV1Entity)o;
        if(clKupCatgEntity!=null){
            categoryBo.setCategory(clKupCatgEntity.getCatgCd());
            categoryBo.setCategoryName(clKupCatgEntity.getCatDescnTxt());
        }
        resultObject.setBaseBo(categoryBo);
        return resultObject;
    }

}
