package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class PlanCnsPlanUnitDaoImpl extends CommonDaoImpl {

    public static final String CNS_PLAN_UNIT = "/plan/cns_plan_unit";

    public static final String LOCAL_UOM = "localUom";
    public static final String PLAN_FLAG = "planFlag";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static PlanCnsPlanUnitDaoImpl instance;

    public static PlanCnsPlanUnitDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanUnitDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlanUnitEntity getCnsPlanUnitEntityWithLocalUom(String localUom) {
        if (StringUtils.isNotBlank(localUom)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_UOM).is(localUom).toQueryString();
            return queryForObject(CNS_PLAN_UNIT, queryString, PlanCnsPlanUnitEntity.class);
        }
        return null;
    }

    public PlanCnsPlanUnitEntity getCnsPlanUnitEntityWithSourceSystemAndLocalUom(String sourceSystem, String localUom) {
        if (StringUtils.isNotBlank(localUom) && StringUtils.isNotBlank(sourceSystem)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_UOM).is(localUom)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(CNS_PLAN_UNIT, queryString, PlanCnsPlanUnitEntity.class);
        }
        return null;
    }

    public List<PlanCnsPlanUnitEntity> getCnsPlanUnitEntityListWithSourceSystemAndLocalUom(String sourceSystem, String localUom) {
        if (StringUtils.isNotBlank(localUom) && StringUtils.isNotBlank(sourceSystem)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_UOM).is(localUom)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(CNS_PLAN_UNIT, queryString, PlanCnsPlanUnitEntity.class);
        }
        return null;
    }

    public CnsPlanUnitEntity getEntityWithLocalUomAndPlanFlag(String localBaseUom, String planFlag) {
        String queryString = QueryHelper.buildCriteria(LOCAL_UOM).is(localBaseUom)
                .and(PLAN_FLAG).is(planFlag).toQueryString();
        return queryForObject(CNS_PLAN_UNIT, queryString, CnsPlanUnitEntity.class);
    }

}
