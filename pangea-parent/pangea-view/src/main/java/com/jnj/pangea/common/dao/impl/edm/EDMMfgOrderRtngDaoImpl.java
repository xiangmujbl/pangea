package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMMfgOrderRtngEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMfgOrderRtngDaoImpl extends CommonDaoImpl {

    public static final String EDM_MFG_ORDER_RTNG = "/edm/mfg_order_rtng";

    public static final String MFG_ORDER_RTNG_SOURCESYSCD = "srcSysCd";
    public static final String MFG_ORDER_RTNG_ORDRRTNGNUM = "ordrRtngNum";

    private static EDMMfgOrderRtngDaoImpl instance;

    public static EDMMfgOrderRtngDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderRtngDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgOrderRtngEntity> getEntityWithConditions(String srcSysCd,String ordrRtngNum) {

        ADFCriteria adfCriteria=QueryHelper.buildCriteria();
        if(StringUtils.isBlank(srcSysCd)){
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_RTNG_SOURCESYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_RTNG_SOURCESYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(ordrRtngNum)){
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_RTNG_ORDRRTNGNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_RTNG_ORDRRTNGNUM).is(ordrRtngNum.trim()));
        }
        String queryString=adfCriteria.toQueryString();
        return queryForList(EDM_MFG_ORDER_RTNG,queryString, EDMMfgOrderRtngEntity.class);
    }

    public List<EDMMfgOrderRtngEntity> joinMfgOrderRtngWithOrdrRtngNum(String ordrRtngNum){
        List<EDMMfgOrderRtngEntity> list = new ArrayList<EDMMfgOrderRtngEntity>();
        if(StringUtils.isNotBlank(ordrRtngNum)){
            String queryString  = QueryHelper.buildCriteria(MFG_ORDER_RTNG_ORDRRTNGNUM).is(ordrRtngNum)
                    .toQueryString();
            return queryForList(EDM_MFG_ORDER_RTNG, queryString, EDMMfgOrderRtngEntity.class);
        }
        return list;
    }

    public List<EDMMfgOrderRtngEntity> joinMfgOrderRtngWithOrdrRtngNumAndSrcSysCd(String ordrRtngNum,String srcSysCd){
        List<EDMMfgOrderRtngEntity> list = new ArrayList<EDMMfgOrderRtngEntity>();
        if(StringUtils.isNotBlank(ordrRtngNum) || StringUtils.isNotBlank(srcSysCd)){
            String queryString  = QueryHelper.buildCriteria(MFG_ORDER_RTNG_ORDRRTNGNUM).is(ordrRtngNum)
                    .and(MFG_ORDER_RTNG_SOURCESYSCD).is(srcSysCd)
                    .toQueryString();
            return queryForList(EDM_MFG_ORDER_RTNG, queryString, EDMMfgOrderRtngEntity.class);
        }
        return list;
    }
}
