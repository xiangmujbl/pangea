package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsRootDescriptionEntity;
import org.apache.commons.lang3.StringUtils;

public class PlanCnsRootDescriptionDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_ROOT_DESCRIPTION = "/plan/cns_root_description";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_DP_PARENT_CODE = "localDpParentCode";

    private static PlanCnsRootDescriptionDaoImpl instance;

    public static PlanCnsRootDescriptionDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsRootDescriptionDaoImpl();
        }
        return instance;
    }

    public PlanCnsRootDescriptionEntity getEntityWithSourceSystemAndLocalDpParentCode(String sourceSystem, String localDpParentCode) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localDpParentCode)){
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                    .and(LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
            return queryForObject(PLAN_CNS_ROOT_DESCRIPTION, queryString, PlanCnsRootDescriptionEntity.class);
        }
        return null;
    }
}
