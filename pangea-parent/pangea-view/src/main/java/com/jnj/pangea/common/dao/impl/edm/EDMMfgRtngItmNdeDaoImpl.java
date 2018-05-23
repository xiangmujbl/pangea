package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmNdeEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMfgRtngItmNdeDaoImpl extends CommonDaoImpl {

    private static EDMMfgRtngItmNdeDaoImpl instance;

    public static EDMMfgRtngItmNdeDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmNdeDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtngItmNdeEntity> getEntityWithConditions(String srcSysCd, String rtngTypCd, String rntgGrpCntrNbr, String rtngGrpCd) {

        if (StringUtils.isNotBlank(srcSysCd)&&StringUtils.isNotBlank(rtngTypCd)&& StringUtils.isNotBlank(rntgGrpCntrNbr)&& StringUtils.isNotBlank(rtngGrpCd)) {
            String queryString = QueryHelper.buildCriteria(IConstant.MFG_RTNG_ITM_NDE.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGGRPCD).is(rtngGrpCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGTYPCD).is(rtngTypCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGGRPCNTRNBR).is(rntgGrpCntrNbr)
                    .toQueryString();
            return queryForList(IConstant.REGION.MFG_RTNG_ITM_NDE, queryString, EDMMfgRtngItmNdeEntity.class);
        }
        return null;
    }
}
