package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderItmEntity;

import java.util.List;

public class EDMMfgOrderItmDaoImpl extends CommonDaoImpl {

    private static EDMMfgOrderItmDaoImpl instance;

    public static EDMMfgOrderItmDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderItmDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgOrderItmEntity> getEntityWithConditions(String srcSysCd, String mfgOrdrNum) {

        String queryString = QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.EDM_MFG_ORDER_ITM.FIELD_SRCSYSCD).is(srcSysCd)
                .and(IConstant.OMP_GDMBOMELEMENT.EDM_MFG_ORDER_ITM.FIELD_MFGORDRNUM).is(mfgOrdrNum)
                .toQueryString();
        return queryForList(IConstant.REGION.EDM_MFG_ORDER_ITM, queryString, EDMMfgOrderItmEntity.class);
    }
}
