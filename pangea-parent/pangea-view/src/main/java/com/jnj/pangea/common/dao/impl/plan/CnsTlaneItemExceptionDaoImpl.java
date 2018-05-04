package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemExceptionEntity;

public class CnsTlaneItemExceptionDaoImpl extends CommonDaoImpl {

    private static CnsTlaneItemExceptionDaoImpl instance;

    public static CnsTlaneItemExceptionDaoImpl getInstance() {
        if (instance == null) {
            instance = new CnsTlaneItemExceptionDaoImpl();
        }
        return instance;
    }

    public CnsTlaneItemExceptionEntity getEntityWithRefSeqNumTlaneItem(String sequenceNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_TLANE_ITEM_EXCEPTION.REF_SEQ_NUM_TLANE_ITEM).is(sequenceNumber).toQueryString();
        return queryForObject(IConstant.REGION.CNS_TLANE_ITEM_EXCEPTION,queryString,CnsTlaneItemExceptionEntity.class);
    }
}

