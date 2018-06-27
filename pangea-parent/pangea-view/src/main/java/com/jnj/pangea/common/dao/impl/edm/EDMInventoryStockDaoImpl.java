package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMInventoryStockEntity;



public class EDMInventoryStockDaoImpl extends CommonDaoImpl {

    private static EDMInventoryStockDaoImpl instance;

    public static EDMInventoryStockDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMInventoryStockDaoImpl();
        }
        return instance;
    }

    public EDMInventoryStockEntity getEntityWithWithSourceSystemAndLocalBatchIdAndLocalMaterial(String sourceSystem, String localBatchId, String localMaterial) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_INVENTORY_STOCK.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.EDM_INVENTORY_STOCK.LOCAL_BATCH_ID).is(localBatchId)
                .and(IConstant.EDM_INVENTORY_STOCK.LOCAL_MATERIAL).is(localMaterial).toQueryString();
        return queryForObject(IConstant.REGION.EDM_INVENTORY_STOCK_V1, queryString, EDMInventoryStockEntity.class);
    }
}
