package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;

public class EDMPurchaseOrderOAV1DaoImpl extends CommonDaoImpl {

    private static EDMPurchaseOrderOAV1DaoImpl instance;

    public static EDMPurchaseOrderOAV1DaoImpl getInstance(){
        if(null == instance){
            instance = new EDMPurchaseOrderOAV1DaoImpl();
        }
        return instance;
    }

    public EDMPurchaseOrderOAV1Entity getCnfrmQtySumWithPoNumAndPoLineNbr(String poNum, String poLineNbr) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_NUM).is(poNum)
                .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_LINE_NBR).is(poLineNbr).toQueryString();
        return queryForObject(IConstant.REGION.EDM_PURCHASE_ORDER_OA_V1_AGGREGATION_CNFRM_QTY, queryString, EDMPurchaseOrderOAV1Entity.class);
    }

    public EDMPurchaseOrderOAV1Entity getRecvEaQtySumWithPoNumAndPoLineNbr(String poNum, String poLineNbr) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_NUM).is(poNum)
                .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_LINE_NBR).is(poLineNbr).toQueryString();
        return queryForObject(IConstant.REGION.EDM_PURCHASE_ORDER_OA_V1_AGGREGATION_RECVEAQTY, queryString, EDMPurchaseOrderOAV1Entity.class);
    }


}