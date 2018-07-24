package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsTlaneItemExceptionEntity;

import java.util.List;

public class CnsTlaneItemExceptionDaoImpl extends CommonDaoImpl {

    public static final String CNS_TLANE_ITEM_EXCEPTION = "/plan/cns_tlane_item_exception";

    public static final String REF_SEQ_NUM_TLANE_ITEM = "refSeqNumTlaneItem";
    public static final String SEQ_NUM_TLANE_ITEM = "sequenceNumber";
    public static final String MATERIAL_NUMBER = "materialNumber";
    public static final String TLANE_NAME = "tlaneName";

    private static CnsTlaneItemExceptionDaoImpl instance;

    public static CnsTlaneItemExceptionDaoImpl getInstance() {
        if (instance == null) {
            instance = new CnsTlaneItemExceptionDaoImpl();
        }
        return instance;
    }

    public List<CnsTlaneItemExceptionEntity> getEntitiesWithRefSeqNumMatNumTlaneName(String sequenceNumber,String materialNumber,String tlaneName) {
        String queryString = QueryHelper.buildCriteria(REF_SEQ_NUM_TLANE_ITEM).is(sequenceNumber)
                .and(MATERIAL_NUMBER).is(materialNumber)
                .and(TLANE_NAME).is(tlaneName)
                .toQueryString();
        return queryForList(CNS_TLANE_ITEM_EXCEPTION,queryString,CnsTlaneItemExceptionEntity.class);
    }
    public List<CnsTlaneItemExceptionEntity> getEntitiesWithSeqNumMatNumTlaneName(String sequenceNumber,String materialNumber,String tlaneName) {
        String queryString = QueryHelper.buildCriteria(SEQ_NUM_TLANE_ITEM).is(sequenceNumber)
                .and(MATERIAL_NUMBER).is(materialNumber)
                .and(TLANE_NAME).is(tlaneName)
                .toQueryString();
        return queryForList(CNS_TLANE_ITEM_EXCEPTION,queryString,CnsTlaneItemExceptionEntity.class);
    }
}

