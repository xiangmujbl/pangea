package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemExceptionEntity;

import java.util.List;

public class CnsTlaneItemExceptionDaoImpl extends CommonDaoImpl {

    private static CnsTlaneItemExceptionDaoImpl instance;

    public static CnsTlaneItemExceptionDaoImpl getInstance() {
        if (instance == null) {
            instance = new CnsTlaneItemExceptionDaoImpl();
        }
        return instance;
    }

    public List<CnsTlaneItemExceptionEntity> getEntitiesWithRefSeqNumMatNumTlaneName(String sequenceNumber,String materialNumber,String tlaneName) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_TLANE_ITEM_EXCEPTION.REF_SEQ_NUM_TLANE_ITEM).is(sequenceNumber)
                .and(IConstant.CNS_TLANE_ITEM_EXCEPTION.MATERIAL_NUMBER).is(materialNumber)
                .and(IConstant.CNS_TLANE_ITEM_EXCEPTION.TLANE_NAME).is(tlaneName)
                .toQueryString();
        return queryForList(IConstant.REGION.CNS_TLANE_ITEM_EXCEPTION,queryString,CnsTlaneItemExceptionEntity.class);
    }
    public List<CnsTlaneItemExceptionEntity> getEntitiesWithSeqNumMatNumTlaneName(String sequenceNumber,String materialNumber,String tlaneName) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_TLANE_ITEM_EXCEPTION.SEQ_NUM_TLANE_ITEM).is(sequenceNumber)
                .and(IConstant.CNS_TLANE_ITEM_EXCEPTION.MATERIAL_NUMBER).is(materialNumber)
                .and(IConstant.CNS_TLANE_ITEM_EXCEPTION.TLANE_NAME).is(tlaneName)
                .toQueryString();
        return queryForList(IConstant.REGION.CNS_TLANE_ITEM_EXCEPTION,queryString,CnsTlaneItemExceptionEntity.class);
    }
}

