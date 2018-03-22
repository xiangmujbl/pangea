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

}
