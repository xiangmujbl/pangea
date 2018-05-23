package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMfgRtngItmDaoImpl extends CommonDaoImpl {

    private static EDMMfgRtngItmDaoImpl instance;

    public static EDMMfgRtngItmDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgRtngItmEntity> getEntityWithConditions(String srcSysCd,String rtngTypCd,String rtngItmNum,String rtngGrpCd) {
        if(StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(rtngTypCd) &&StringUtils.isNotBlank(rtngItmNum) && StringUtils.isNotBlank(rtngGrpCd)){
            String queryString = QueryHelper.buildCriteria(IConstant.MFG_RTNG_ITM.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MFG_RTNG_ITM.RTNGTYPCD).is(rtngTypCd)
                    .and(IConstant.MFG_RTNG_ITM.RTNGITMNUM).is(rtngItmNum)
                    .and(IConstant.MFG_RTNG_ITM.RTNGGRPCD).is(rtngGrpCd)
                    .toQueryString();
            return queryForList(IConstant.REGION.MFG_RTNG_ITM, queryString, EDMMfgRtngItmEntity.class);
        }

        return null;
    }
}
