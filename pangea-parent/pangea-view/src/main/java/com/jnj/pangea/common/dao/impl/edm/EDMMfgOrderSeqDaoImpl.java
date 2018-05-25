package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderRtngEntity;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderSeqEntity;
import org.apache.commons.lang3.StringUtils;

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
        if(StringUtils.isNotBlank(srcSysCd)){
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_SOURCESYSCD).is(srcSysCd);
        }else{
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_SOURCESYSCD).isNull();
        }
        if(StringUtils.isNotBlank(ordrRtngNum)){
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_ORDRRTNGNUM).is(ordrRtngNum);
        }else{
            adfCriteria.and(IConstant.OMP_GDMBOMELEMENT.MFG_ORDER_RTNG.FIELD_ORDRRTNGNUM).isNull();
        }
        return queryForList(IConstant.REGION.EDM_MFG_ORDER_SEQ, adfCriteria.toQueryString(), EDMMfgOrderSeqEntity.class);
    }
}
