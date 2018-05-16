package com.jnj.omp.common.dao.impl.ems;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;

public class EMSFMdmMaterialTypesDaoImpl extends CommonDaoImpl {
    private static EMSFMdmMaterialTypesDaoImpl instance;

    public static EMSFMdmMaterialTypesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EMSFMdmMaterialTypesDaoImpl();
        }
        return instance;
    }

}
