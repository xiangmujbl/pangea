package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import org.apache.commons.lang3.StringUtils;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;

import java.util.List;

public class EDMSalesOrderV1DaoImpl extends CommonDaoImpl {

    public static final String SALES_ORDER_NO = "salesOrderNo";
    public static final String SALES_ORDER_ITEM = "salesOrderItem";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static EDMSalesOrderV1DaoImpl instance;

    public static EDMSalesOrderV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSalesOrderV1DaoImpl();
        }
        return instance;
    }

    public EDMSalesOrderV1Entity getSalesOrderForHistoryDoc(String salesOrderNo, String salesOrderItem, String sourceSystem){
        if(StringUtils.isNotEmpty(salesOrderNo) && StringUtils.isNotEmpty(salesOrderItem)){
            String queryString = QueryHelper.buildCriteria(SALES_ORDER_NO).is(salesOrderNo)
                    .and(SALES_ORDER_ITEM).is(salesOrderItem)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(RegionsConstant.EDM_SALES_ORDER_V1, queryString, EDMSalesOrderV1Entity.class);
        }
        return null;
    }


}
