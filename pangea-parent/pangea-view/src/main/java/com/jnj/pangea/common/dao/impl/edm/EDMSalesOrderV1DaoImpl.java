package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import org.apache.commons.lang3.StringUtils;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;

import java.util.List;

public class EDMSalesOrderV1DaoImpl extends CommonDaoImpl {

    private static EDMSalesOrderV1DaoImpl instance;

    public static EDMSalesOrderV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSalesOrderV1DaoImpl();
        }
        return instance;
    }

    public EDMSalesOrderV1Entity getSalesOrderForHistoryDoc(String salesOrderNo, String salesOrderItem, String sourceSystem){
        if(StringUtils.isNotEmpty(salesOrderNo) && StringUtils.isNotEmpty(salesOrderItem)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_SALES_ORDER_V1.LOCAL_SALES_ORDER_NO).is(salesOrderNo)
                    .and(IConstant.EDM_SALES_ORDER_V1.LOCAL_SALES_ORDER_ITEM).is(salesOrderItem)
                    .and(IConstant.EDM_SALES_ORDER_V1.SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(IConstant.REGION.EDM_SALES_ORDER_V1, queryString, EDMSalesOrderV1Entity.class);
        }
        return null;
    }


}
