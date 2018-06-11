package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMPurchaseOrderOAV1DaoImpl extends CommonDaoImpl {

    private static EDMPurchaseOrderOAV1DaoImpl instance;

    public static EDMPurchaseOrderOAV1DaoImpl getInstance(){
        if(null == instance){
            instance = new EDMPurchaseOrderOAV1DaoImpl();
        }
        return instance;
    }

    public EDMPurchaseOrderOAV1Entity getEntityByPoNumAndPoLineNumberAndSourceSystem(String poNum, String poLineNumber, String sourceSystem){
        if(StringUtils.isNotEmpty(poNum) && StringUtils.isNotEmpty(poLineNumber)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_NUM).is(poNum)
                    .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_LINE_NBR).is(poLineNumber)
                    .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(IConstant.REGION.EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
        }

        return null;
    }

    public List<EDMPurchaseOrderOAV1Entity> getEntityByPoNumAndPoLineNbrCnfrmQtyOverZero(String poNum, String poLineNbr){
        if(StringUtils.isNotEmpty(poNum) && StringUtils.isNotEmpty(poLineNbr)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_NUM).is(poNum)
                    .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_LINE_NBR).is(poLineNbr)
                    .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.CNFRM_QTY).greaterThan("0").toQueryString();
            return queryForList(IConstant.REGION.EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
        }

        return null;
    }

    public List<EDMPurchaseOrderOAV1Entity> getEntityByPoNumAndPoLineNbrEvTypeCdIsOne(String poNum, String poLineNbr){
        if(StringUtils.isNotEmpty(poNum) && StringUtils.isNotEmpty(poLineNbr)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_NUM).is(poNum)
                    .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_LINE_NBR).is(poLineNbr)
                    .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.EV_TYPE_CD).is("1").toQueryString();
            return queryForList(IConstant.REGION.EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
        }

        return null;
    }


}