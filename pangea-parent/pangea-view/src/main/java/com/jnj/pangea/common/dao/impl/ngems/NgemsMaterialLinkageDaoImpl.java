package com.jnj.pangea.common.dao.impl.ngems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.ngems.MaterialLinkageEntity;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class NgemsMaterialLinkageDaoImpl extends CommonDaoImpl {

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String MATERIAL_NUMBER = "materialNumber";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    
    private static NgemsMaterialLinkageDaoImpl instance;
    
    public static NgemsMaterialLinkageDaoImpl getInstance() {
        if (instance == null) {
            instance = new NgemsMaterialLinkageDaoImpl();
        }
        return instance;
    }
    public MaterialLinkageEntity getEntityWithLocalMaterialNumberAndSourceSystem(String matnr, String sourceSystem){
        String queryString = QueryHelper
                .buildCriteria(LOCAL_MATERIAL_NUMBER).is(matnr)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();

        MaterialLinkageEntity materialLinkageEntity = commonDao.queryForObject(RegionsConstant.NGEMS_MATERIAL_LINKAGE, queryString, MaterialLinkageEntity.class);
        return materialLinkageEntity;
    }
}
