package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.ngems.GoldenMaterialEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class NgemsGoldenMaterialDaoImpl extends CommonDaoImpl {
    private static NgemsGoldenMaterialDaoImpl instance;
    public static NgemsGoldenMaterialDaoImpl getInstance() {
        if (instance == null) {
            instance = new NgemsGoldenMaterialDaoImpl();
        }
        return instance;
    }
    public GoldenMaterialEntity getEntityWithMaterialNumber(String materialNumber){
       String queryString = QueryHelper.buildCriteria(IConstant.NGEMS_GOLDEN_MATERIAL.MATERIAL_NUMBER).is(materialNumber).toQueryString();
        return commonDao.queryForObject(IConstant.REGION.NGEMS_GOLDEN_MATERIAL, queryString, GoldenMaterialEntity.class);
    }
}
