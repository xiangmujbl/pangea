package com.jnj.omp.common.dao.impl.edm;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;

public class EDMSalesOrderV1DaoImpl extends CommonDaoImpl {

    private static EDMSalesOrderV1DaoImpl instance;

    public static EDMSalesOrderV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSalesOrderV1DaoImpl();
        }
        return instance;
    }

}
