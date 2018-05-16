package com.jnj.omp.common.dao.impl.ngems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.ngems.GoldenMaterialEntity;

import static com.jnj.omp.common.service.ICommonService.commonDao;

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
