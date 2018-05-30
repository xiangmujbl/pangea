package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmNdeEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMfgRtngItmNdeDaoImpl extends CommonDaoImpl {

    private static EDMMfgRtngItmNdeDaoImpl instance;

    public static EDMMfgRtngItmNdeDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmNdeDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtngItmNdeEntity> getEntityWithConditions(String srcSysCd, String rtngTypCd, String rtngGrpCd,String rntgGrpCntrNbr) {
            String queryString = QueryHelper.buildCriteria(IConstant.MFG_RTNG_ITM_NDE.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGGRPCD).is(rtngGrpCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGTYPCD).is(rtngTypCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGGRPCNTRNBR).is(rntgGrpCntrNbr)
                    .toQueryString();
            return queryForList(IConstant.REGION.MFG_RTNG_ITM_NDE, queryString, EDMMfgRtngItmNdeEntity.class);
    }

    public List<EDMMfgRtngItmNdeEntity> getEntityListWithConditions(String srcSysCd, String rtngTypCd, String rtngGrpCd,String rtngGrpCntrNbr) {
        List<EDMMfgRtngItmNdeEntity> mfgRtngItmNdeEntityList = new ArrayList<>();
        if (StringUtils.isNotEmpty(srcSysCd) && StringUtils.isNotEmpty(rtngTypCd) && StringUtils.isNotEmpty(rtngGrpCd) && StringUtils.isNotEmpty(rtngGrpCntrNbr) ){
            String queryString = QueryHelper.buildCriteria(IConstant.MFG_RTNG_ITM_NDE.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGGRPCD).is(rtngGrpCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGTYPCD).is(rtngTypCd)
                    .and(IConstant.MFG_RTNG_ITM_NDE.RTNGGRPCNTRNBR).is(rtngGrpCntrNbr)
                    .toQueryString();
            mfgRtngItmNdeEntityList = queryForList(IConstant.REGION.MFG_RTNG_ITM_NDE, queryString, EDMMfgRtngItmNdeEntity.class);
        }

        return mfgRtngItmNdeEntityList;
    }
}
