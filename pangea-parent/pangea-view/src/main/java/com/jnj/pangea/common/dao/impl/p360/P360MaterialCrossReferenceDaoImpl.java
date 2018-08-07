package com.jnj.pangea.common.dao.impl.p360;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.p360.MaterialCrossReferenceEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class P360MaterialCrossReferenceDaoImpl extends CommonDaoImpl {
    private static P360MaterialCrossReferenceDaoImpl instance;
    private static final String SOURCE_SYSTEM_MATERIAL_NUMBER = "sourceSystemMaterialNumber";
    private static final String SOURCE_SYSTEM_NAME = "sourceSystemName";
    private static final String P360_MATERIAL_CROSS_REFERENCE = "/p360/material_cross_reference";
    
    public static P360MaterialCrossReferenceDaoImpl getInstance() {
        if (instance == null) {
            instance = new P360MaterialCrossReferenceDaoImpl();
        }
        return instance;
    }
    
    public MaterialCrossReferenceEntity getEntityWithLocalMaterialNumberAndLocalSourceSystem(String matnr, String localSourceSystem){
        String queryString = QueryHelper
                .buildCriteria(SOURCE_SYSTEM_MATERIAL_NUMBER).is(matnr)
                .and(SOURCE_SYSTEM_NAME).is(localSourceSystem)
                .toQueryString();

        MaterialCrossReferenceEntity materialCrossReferenceEntity = commonDao.queryForObject(P360_MATERIAL_CROSS_REFERENCE, queryString, MaterialCrossReferenceEntity.class);
        return materialCrossReferenceEntity;
    }
}
