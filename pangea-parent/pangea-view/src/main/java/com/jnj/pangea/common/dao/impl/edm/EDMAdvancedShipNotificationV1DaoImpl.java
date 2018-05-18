package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

public class EDMAdvancedShipNotificationV1DaoImpl extends CommonDaoImpl {

    private static EDMAdvancedShipNotificationV1DaoImpl instance;

    public static EDMAdvancedShipNotificationV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMAdvancedShipNotificationV1DaoImpl();
        }
        return instance;
    }

}
