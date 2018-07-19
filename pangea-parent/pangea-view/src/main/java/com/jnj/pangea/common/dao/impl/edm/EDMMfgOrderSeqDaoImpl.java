package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderRtngEntity;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderSeqEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMfgOrderSeqDaoImpl extends CommonDaoImpl {

    public static final String MFG_ORDER_SEQ_SOURCESYSCD = "sourceSysCd";
    public static final String MFG_ORDER_SEQ_ORDRRTNGNUM = "ordrRtngNum";
    public static final String MFG_ORDER_SEQ_SCSYSCD = "srcSysCd";

    public static final String MFG_ORDER_RTNG_SOURCESYSCD = "srcSysCd";
    public static final String MFG_ORDER_RTNG_ORDRRTNGNUM = "ordrRtngNum";

    private static EDMMfgOrderSeqDaoImpl instance;

    public static EDMMfgOrderSeqDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgOrderSeqDaoImpl();
        }
        return instance;
    }

    public List<EDMMfgOrderSeqEntity> getEntityWithConditions(String srcSysCd, String ordrRtngNum) {
        ADFCriteria adfCriteria=QueryHelper.buildCriteria();
        if(StringUtils.isBlank(srcSysCd)){
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_SEQ_SCSYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_SEQ_SCSYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(ordrRtngNum)){
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_SEQ_ORDRRTNGNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(MFG_ORDER_SEQ_ORDRRTNGNUM).is(ordrRtngNum.trim()));
        }
        return queryForList(RegionsConstant.EDM_MFG_ORDER_SEQ, adfCriteria.toQueryString(), EDMMfgOrderSeqEntity.class);
    }

    public List<EDMMfgOrderSeqEntity> joinMfgOrderSeqWithOrdrRtngNum(String ordrRtngNum){
        List<EDMMfgOrderSeqEntity> list = new ArrayList<EDMMfgOrderSeqEntity>();
        if(org.apache.commons.lang.StringUtils.isNotBlank(ordrRtngNum)){
            String queryString  = QueryHelper.buildCriteria(MFG_ORDER_RTNG_ORDRRTNGNUM).is(ordrRtngNum)
                    .toQueryString();
            return queryForList(RegionsConstant.EDM_MFG_ORDER_SEQ, queryString, EDMMfgOrderSeqEntity.class);
        }
        return list;
    }

    public List<EDMMfgOrderSeqEntity> joinMfgOrderSeqWithOrdrRtngNumAndSrcSysCd(String ordrRtngNum,String srcSysCd){
        List<EDMMfgOrderSeqEntity> list = new ArrayList<EDMMfgOrderSeqEntity>();
        if(StringUtils.isNotBlank(ordrRtngNum) || StringUtils.isNotBlank(srcSysCd)){
            String queryString  = QueryHelper.buildCriteria(MFG_ORDER_RTNG_ORDRRTNGNUM).is(ordrRtngNum)
                    .and(MFG_ORDER_RTNG_SOURCESYSCD).is(srcSysCd)
                    .toQueryString();
            return queryForList(RegionsConstant.EDM_MFG_ORDER_SEQ, queryString, EDMMfgOrderSeqEntity.class);
        }
        return list;
    }
}
