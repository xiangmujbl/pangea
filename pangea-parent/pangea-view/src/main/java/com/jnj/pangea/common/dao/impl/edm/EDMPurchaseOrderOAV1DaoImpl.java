package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
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
        if(poNum.isEmpty() || poLineNumber.isEmpty() || sourceSystem.isEmpty()){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_NUM).is(poNum)
                .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_LINE_NBR).is(poLineNumber)
                .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        //LogUtil.getCoreLog().info("----------------------getEntityByPoNumAndPoLineNumberAndSourceSystem--------------------"+queryString);
        return queryForObject(IConstant.REGION.EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
    }

    public List<EDMPurchaseOrderOAV1Entity> getPurchaseOrderListByPoNumPoLineNbr(String poNum, String poLineNbr) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_NUM).is(poNum)
                .and(IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_LINE_NBR).is(poLineNbr).toQueryString();
        return queryForList(IConstant.REGION.EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
    }

}