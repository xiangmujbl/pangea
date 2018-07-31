package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMfgRtngItmDaoImpl extends CommonDaoImpl {

    public static final String MFG_RTNG_ITM = "/edm/mfg_rtng_itm";

    public static final String RTNGTYPCD = "rtngTypCd";
    public static final String RTNGITMNUM = "rtngItmNum";
    public static final String SRCSYSCD = "srcSysCd";
    public static final String RTNGGRPCD = "rtngGrpCd";
    public static final String OPERNUM = "operNum";

    private static EDMMfgRtngItmDaoImpl instance;

    public static EDMMfgRtngItmDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmDaoImpl();
        }
        return instance;
    }

    public EDMMfgRtngItmEntity getEntityWithConditions(String srcSysCd,String rtngTypCd,String rtngItmNum,String rtngGrpCd) {
        if(StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(rtngTypCd) &&StringUtils.isNotBlank(rtngItmNum) && StringUtils.isNotBlank(rtngGrpCd)){
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(RTNGTYPCD).is(rtngTypCd)
                    .and(RTNGITMNUM).is(rtngItmNum)
                    .and(RTNGGRPCD).is(rtngGrpCd)
                    .toQueryString();
            return queryForObject(MFG_RTNG_ITM, queryString, EDMMfgRtngItmEntity.class);
        }

        return null;
    }

    public List<EDMMfgRtngItmEntity> getEntityListWithConditions(String srcSysCd,String rtngTypCd,String rtngItmNum,String rtngGrpCd) {

        List<EDMMfgRtngItmEntity> mfgRtngItmEntityList = new ArrayList<>();
        if(StringUtils.isNotEmpty(srcSysCd) && StringUtils.isNotEmpty(rtngTypCd) &&StringUtils.isNotEmpty(rtngItmNum) && StringUtils.isNotEmpty(rtngGrpCd)){
            String queryString = QueryHelper.buildCriteria(SRCSYSCD).is(srcSysCd)
                    .and(RTNGTYPCD).is(rtngTypCd)
                    .and(RTNGITMNUM).is(rtngItmNum)
                    .and(RTNGGRPCD).is(rtngGrpCd)
                    .toQueryString();
            mfgRtngItmEntityList = queryForList(MFG_RTNG_ITM, queryString, EDMMfgRtngItmEntity.class);
        }
        return mfgRtngItmEntityList;
    }
}
