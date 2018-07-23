package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMInventoryStockEntity;



public class EDMInventoryStockDaoImpl extends CommonDaoImpl {

    public static final String EDM_INVENTORY_STOCK_V1 = "/edm/inventory_stock_v1";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_BATCH_ID = "localBatchId";
    public static final String LOCAL_MATERIAL = "localMaterial";

    private static EDMInventoryStockDaoImpl instance;

    public static EDMInventoryStockDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMInventoryStockDaoImpl();
        }
        return instance;
    }

    public EDMInventoryStockEntity getEntityWithWithSourceSystemAndLocalBatchIdAndLocalMaterial(String sourceSystem, String localBatchId, String localMaterial) {

        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_BATCH_ID).is(localBatchId)
                .and(LOCAL_MATERIAL).is(localMaterial).toQueryString();
        return queryForObject(EDM_INVENTORY_STOCK_V1, queryString, EDMInventoryStockEntity.class);
    }
}
