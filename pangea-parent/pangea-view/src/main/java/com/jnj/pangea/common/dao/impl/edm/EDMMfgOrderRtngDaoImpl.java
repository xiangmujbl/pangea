package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderRtngEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMfgOrderRtngDaoImpl extends CommonDaoImpl {

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
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_SOURCESYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_SOURCESYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(ordrRtngNum)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_ORDRRTNGNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_ORDRRTNGNUM).is(ordrRtngNum.trim()));
        }
        String queryString=adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("queryString" +queryString);
        return queryForList(IConstant.REGION.EDM_MFG_ORDER_RTNG,queryString, EDMMfgOrderRtngEntity.class);
    }

    public List<EDMMfgOrderRtngEntity> joinMfgOrderRtngWithOrdrRtngNum(String ordrRtngNum){
        List<EDMMfgOrderRtngEntity> list = new ArrayList<EDMMfgOrderRtngEntity>();
        if(StringUtils.isNotBlank(ordrRtngNum)){
            String queryString  = QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_ORDRRTNGNUM).is(ordrRtngNum)
                    .toQueryString();
            return queryForList(IConstant.REGION.EDM_MFG_ORDER_RTNG, queryString, EDMMfgOrderRtngEntity.class);
        }
        return list;
    }
}
