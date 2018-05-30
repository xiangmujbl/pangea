package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderRtngEntity;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderSeqEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EDMMfgOrderSeqDaoImpl extends CommonDaoImpl {

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
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_SEQ.FIELD_SCSYSCD).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_SEQ.FIELD_SCSYSCD).is(srcSysCd.trim()));
        }

        if(StringUtils.isBlank(ordrRtngNum)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_SEQ.FIELD_ORDRRTNGNUM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_SEQ.FIELD_ORDRRTNGNUM).is(ordrRtngNum.trim()));
        }
        LogUtil.getCoreLog().info("EDMMfgOrderSeqDaoImpl "+adfCriteria.toQueryString());
        return queryForList(IConstant.REGION.EDM_MFG_ORDER_SEQ, adfCriteria.toQueryString(), EDMMfgOrderSeqEntity.class);
    }

    public List<EDMMfgOrderSeqEntity> joinMfgOrderSeqWithOrdrRtngNum(String ordrRtngNum){
        List<EDMMfgOrderSeqEntity> list = new ArrayList<EDMMfgOrderSeqEntity>();
        if(org.apache.commons.lang.StringUtils.isNotBlank(ordrRtngNum)){
            String queryString  = QueryHelper.buildCriteria(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_ORDRRTNGNUM).is(ordrRtngNum)
                    .toQueryString();
            return queryForList(IConstant.REGION.EDM_MFG_ORDER_SEQ, queryString, EDMMfgOrderSeqEntity.class);
        }
        return list;
    }
}
