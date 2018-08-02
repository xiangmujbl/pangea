package com.jnj.pangea.common.dao.impl.p360;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.p360.MaterialEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class P360MaterialDaoImpl extends CommonDaoImpl {
    private static P360MaterialDaoImpl instance;
    private static final String ENTERPRISE_MATERIAL_NUMBER = "enterpriseMaterialNumber";
    private static final String P360_MATERIAL = "/p360/material";
    
    public static P360MaterialDaoImpl getInstance() {
        if (instance == null) {
            instance = new P360MaterialDaoImpl();
        }
        return instance;
    }
    public MaterialEntity getEntityWithMaterialNumber(String materialNumber){
       String queryString = QueryHelper.buildCriteria(ENTERPRISE_MATERIAL_NUMBER).is(materialNumber).toQueryString();
        return commonDao.queryForObject(P360_MATERIAL, queryString, MaterialEntity.class);
    }
}
