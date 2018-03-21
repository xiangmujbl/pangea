package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.ems.EmsFMdmMaterialTypesEntity;

public class EMSFMdmMaterialTypesDaoImpl extends CommonDaoImpl{
    private static EMSFMdmMaterialTypesDaoImpl instance;

    public static EMSFMdmMaterialTypesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmMaterialTypesDaoImpl();
        }
        return instance;
    }

    public EmsFMdmMaterialTypesEntity getMaterialTypeWithEMS(String zSourceSystem) {

        String queryString = QueryHelper.buildCriteria(IConstant.VALUE.EMS).is(zSourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_PLANT_V1,queryString,EmsFMdmMaterialTypesEntity.class);
    }
}
