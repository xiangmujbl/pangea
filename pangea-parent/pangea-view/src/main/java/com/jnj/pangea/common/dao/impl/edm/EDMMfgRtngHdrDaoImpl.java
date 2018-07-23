package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMMfgRtngHdrEntity;

import java.util.List;

public class EDMMfgRtngHdrDaoImpl extends CommonDaoImpl {

    public static final String MFG_RTNG_HDR = "/edm/mfg_rtng_hdr";

    public static final String SRCSYSCD = "srcSysCd";
    public static final String RTNGTYPCD = "rtngTypCd";
    public static final String RTNGGRPCD = "rtngGrpCd";
    public static final String RTNGGRPCNTRNBR = "rtngGrpCntrNbr";

    private static EDMMfgRtngHdrDaoImpl instance;

    public static EDMMfgRtngHdrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngHdrDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtngHdrEntity> getEntityWithConditions(String srcSysCd, String rtngTypCd, String rtngGrpCd, String rtngGrpCntrNbr) {
        String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                .and(RTNGTYPCD).is(rtngTypCd)
                .and(RTNGGRPCD).is(rtngGrpCd)
                .and(RTNGGRPCNTRNBR).is(rtngGrpCntrNbr)
                .toQueryString();
        return queryForList(MFG_RTNG_HDR, queryString, EDMMfgRtngHdrEntity.class);
    }
}
