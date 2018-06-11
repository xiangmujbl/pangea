package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngHdrEntity;

import java.util.List;

public class EDMMfgRtngHdrDaoImpl extends CommonDaoImpl {

    private static EDMMfgRtngHdrDaoImpl instance;

    public static EDMMfgRtngHdrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngHdrDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtngHdrEntity> getEntityWithConditions(String srcSysCd, String rtngTypCd, String rtngGrpCd, String rtngGrpCntrNbr) {
        String queryString = QueryHelper.buildCriteria(IConstant.MFG_RTNG_HDR.SRCSYSCD).is(srcSysCd)
                .and(IConstant.MFG_RTNG_HDR.RTNGTYPCD).is(rtngTypCd)
                .and(IConstant.MFG_RTNG_HDR.RTNGGRPCD).is(rtngGrpCd)
                .and(IConstant.MFG_RTNG_HDR.RTNGGRPCNTRNBR).is(rtngGrpCntrNbr)
                .toQueryString();
        return queryForList(IConstant.REGION.MFG_RTNG_HDR, queryString, EDMMfgRtngHdrEntity.class);
    }
}
