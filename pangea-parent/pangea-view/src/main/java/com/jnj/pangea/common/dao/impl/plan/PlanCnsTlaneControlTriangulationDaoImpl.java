package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlTriangulationEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class PlanCnsTlaneControlTriangulationDaoImpl extends CommonDaoImpl {

    private static PlanCnsTlaneControlTriangulationDaoImpl instance;

    public static PlanCnsTlaneControlTriangulationDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsTlaneControlTriangulationDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsTlaneControlTriangulationEntity> getEntityWithSourceSystemCriticalParameters(String sequenceNumber, String tlaneName) {
        if (sequenceNumber.isEmpty() || tlaneName.isEmpty()){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_TLANE_CONTROL_TRIANGULATION.SEQUENCE_NUMBER).is(sequenceNumber).and(IConstant.PLAN_CNS_TLANE_CONTROL_TRIANGULATION.TLANE_NAME).is(tlaneName).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_TLANE_CONTROL_TRIANGULATION,queryString,PlanCnsTlaneControlTriangulationEntity.class);
    }

    public List<PlanCnsTlaneControlTriangulationEntity> getMatchingEntities(String sequenceNumber, String tlaneName) {
        if (StringUtils.isNotBlank(sequenceNumber) && StringUtils.isNotBlank(tlaneName)) {
            String queryString = QueryHelper.buildCriteria(
                    IConstant.PLAN_CNS_TLANE_TRIANGULATION_CONTROL.SEQUENCE_NUMBER).is(sequenceNumber)
                    .and(IConstant.PLAN_CNS_TLANE_TRIANGULATION_CONTROL.TLANE_NAME).is(tlaneName)
                    .toQueryString();

            return queryForObject(IConstant.REGION.PLAN_CNS_TLANE_TRIANGULATION_CONTROL, queryString,
                    PlanCnsTlaneControlTriangulationEntity.class);
        }
        return null;
    }
}
