package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMPurchaseOrderOAV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_PURCHASE_ORDER_OA_V1 = "/edm/purchase_order_oa_v1";

    public static final String PO_NUM = "poNum";
    public static final String PO_LINE_NBR = "poLineNbr";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static EDMPurchaseOrderOAV1DaoImpl instance;

    public static EDMPurchaseOrderOAV1DaoImpl getInstance(){
        if(null == instance){
            instance = new EDMPurchaseOrderOAV1DaoImpl();
        }
        return instance;
    }

    public EDMPurchaseOrderOAV1Entity getEntityByPoNumAndPoLineNumberAndSourceSystem(String poNum, String poLineNumber, String sourceSystem){
        if(poNum.isEmpty() || poLineNumber.isEmpty() || sourceSystem.isEmpty()){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(PO_NUM).is(poNum)
                .and(PO_LINE_NBR).is(poLineNumber)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        //LogUtil.getCoreLog().info("----------------------getEntityByPoNumAndPoLineNumberAndSourceSystem--------------------"+queryString);
        return queryForObject(EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
    }

    public List<EDMPurchaseOrderOAV1Entity> getPurchaseOrderListByPoNumPoLineNbr(String poNum, String poLineNbr) {
        String queryString = QueryHelper.buildCriteria(PO_NUM).is(poNum)
                .and(PO_LINE_NBR).is(poLineNbr).toQueryString();
        return queryForList(EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
    }

}