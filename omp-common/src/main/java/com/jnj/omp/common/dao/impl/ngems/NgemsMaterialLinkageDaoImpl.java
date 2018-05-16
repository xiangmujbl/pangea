package com.jnj.omp.common.dao.impl.ngems;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.ngems.MaterialLinkageEntity;

import static com.jnj.omp.common.service.ICommonService.commonDao;

public class NgemsMaterialLinkageDaoImpl extends CommonDaoImpl {
    private static NgemsMaterialLinkageDaoImpl instance;
    public static NgemsMaterialLinkageDaoImpl getInstance() {
        if (instance == null) {
            instance = new NgemsMaterialLinkageDaoImpl();
        }
        return instance;
    }
    public MaterialLinkageEntity getEntityWithLocalMaterialNumberAndSourceSystem(String matnr, String sourceSystem){
        String queryString = QueryHelper
                .buildCriteria(IConstant.NGEMS_MATERIAL_LINKAGE.LOCAL_MATERIAL_NUMBER).is(matnr)
                .and(IConstant.NGEMS_MATERIAL_LINKAGE.SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();

        MaterialLinkageEntity materialLinkageEntity = commonDao.queryForObject(IConstant.REGION.NGEMS_MATERIAL_LINKAGE, queryString, MaterialLinkageEntity.class);
        return materialLinkageEntity;
    }
}
