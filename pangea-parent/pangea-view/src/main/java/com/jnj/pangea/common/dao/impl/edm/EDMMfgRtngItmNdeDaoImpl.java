package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmNdeEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMfgRtngItmNdeDaoImpl extends CommonDaoImpl {

    public static final String MFG_RTNG_ITM_NDE = "/edm/mfg_rtng_itm_nde";

    public static final String SRCSYSCD = "srcSysCd";
    public static final String RTNGTYPCD = "rtngTypCd";
    public static final String RTNGNDENUM = "rtngNdeNum";
    public static final String RTNGGRPCNTRNBR = "rtngGrpCntrNbr";
    public static final String RTNGGRPCD = "rtngGrpCd";

    private static EDMMfgRtngItmNdeDaoImpl instance;

    public static EDMMfgRtngItmNdeDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmNdeDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtngItmNdeEntity> getEntityWithConditions(String srcSysCd, String rtngTypCd, String rtngGrpCd,String rntgGrpCntrNbr) {
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(RTNGGRPCD).is(rtngGrpCd)
                    .and(RTNGTYPCD).is(rtngTypCd)
                    .and(RTNGGRPCNTRNBR).is(rntgGrpCntrNbr)
                    .toQueryString();
            return queryForList(MFG_RTNG_ITM_NDE, queryString, EDMMfgRtngItmNdeEntity.class);
    }

    public List<EDMMfgRtngItmNdeEntity> getEntityListWithConditions(String srcSysCd, String rtngTypCd, String rtngGrpCd,String rtngGrpCntrNbr) {
        List<EDMMfgRtngItmNdeEntity> mfgRtngItmNdeEntityList = new ArrayList<>();
        if (StringUtils.isNotEmpty(srcSysCd) && StringUtils.isNotEmpty(rtngTypCd) && StringUtils.isNotEmpty(rtngGrpCd) && StringUtils.isNotEmpty(rtngGrpCntrNbr) ){
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(RTNGGRPCD).is(rtngGrpCd)
                    .and(RTNGTYPCD).is(rtngTypCd)
                    .and(RTNGGRPCNTRNBR).is(rtngGrpCntrNbr)
                    .toQueryString();
            mfgRtngItmNdeEntityList = queryForList(MFG_RTNG_ITM_NDE, queryString, EDMMfgRtngItmNdeEntity.class);
        }

        return mfgRtngItmNdeEntityList;
    }
}
