package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMPurchaseOrderOAV1DaoImpl extends CommonDaoImpl {

    public static final String PO_NUM = "poNum";
    public static final String PO_LINE_NBR = "poLineNbr";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String CNFRM_QTY = "cnfrmQty";
    public static final String EV_TYPE_CD = "evTypeCd";
    public static final String PLNTCD = "plntCd";
    public static final String PRCHSNG_ORG_NUM = "prchsngOrgNum";
    public static final String PO_TYPE_CD = "poTypeCd";
    public static final String PURCHASE_ORDER_OA = "purchase_order_oa";
    public static final String RECVEAQTY = "recvEaQty";

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
        return queryForObject(RegionsConstant.EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
    }

    public List<EDMPurchaseOrderOAV1Entity> getPurchaseOrderListByPoNumPoLineNbr(String poNum, String poLineNbr) {
        String queryString = QueryHelper.buildCriteria(PO_NUM).is(poNum)
                .and(PO_LINE_NBR).is(poLineNbr).toQueryString();
        return queryForList(RegionsConstant.EDM_PURCHASE_ORDER_OA_V1, queryString, EDMPurchaseOrderOAV1Entity.class);
    }

}