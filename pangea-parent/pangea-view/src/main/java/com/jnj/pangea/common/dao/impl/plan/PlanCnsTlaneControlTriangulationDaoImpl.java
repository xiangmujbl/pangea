package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlTriangulationEntity;

import java.util.List;

public class PlanCnsTlaneControlTriangulationDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_TLANE_CONTROL_TRIANGULATION = "/plan/cns_tlane_control_triangulation";

    public static final String SEQUENCE_NUMBER = "sequenceNumber";
    public static final String TLANE_NAME = "tlaneName";

    private static PlanCnsTlaneControlTriangulationDaoImpl instance;

    public static PlanCnsTlaneControlTriangulationDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsTlaneControlTriangulationDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsTlaneControlTriangulationEntity> getEntityWithSeqNumberTlaneName(String sequenceNumber, String tlaneName) {
        if (sequenceNumber.isEmpty() || tlaneName.isEmpty()){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(SEQUENCE_NUMBER).is(sequenceNumber).and(TLANE_NAME).is(tlaneName).toQueryString();
        return queryForList(PLAN_CNS_TLANE_CONTROL_TRIANGULATION,queryString,PlanCnsTlaneControlTriangulationEntity.class);
    }
}