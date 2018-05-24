package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMatlMfgRtngEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EDMMatlMfgRtngDaoImpl extends CommonDaoImpl {

    private static EDMMatlMfgRtngDaoImpl instance;

    public static EDMMatlMfgRtngDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatlMfgRtngDaoImpl();
        }
        return instance;
    }

    public List<EDMMatlMfgRtngEntity> getEntityWithFiveConditions(String srcSysCd, String matlNum, String plantCd, String rtngGrpCd, String rtngGrpCntrNum) {
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(plantCd)
                && StringUtils.isNotBlank(rtngGrpCd) && StringUtils.isNotBlank(matlNum) && StringUtils.isNotBlank(rtngGrpCntrNum)) {
            String queryString = QueryHelper.buildCriteria(IConstant.MATL_MFG_RTNG.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MATL_MFG_RTNG.MATLNUM).is(matlNum)
                    .and(IConstant.MATL_MFG_RTNG.RTNGGRPCD).is(rtngGrpCd)
                    .and(IConstant.MATL_MFG_RTNG.RTNGGRPCNTRNUM).is(rtngGrpCntrNum)
                    .and(IConstant.MATL_MFG_RTNG.PLNTCD).is(plantCd).
                            toQueryString();
            return queryForList(IConstant.REGION.MATL_MFG_RTNG, queryString, EDMMatlMfgRtngEntity.class);
        }
        return null;
    }

    public List<EDMMatlMfgRtngEntity> getEntityWithThreeConditions(String srcSysCd,String matlNum,String plntCd) {
        if (StringUtils.isNotBlank(srcSysCd) && StringUtils.isNotBlank(matlNum) && StringUtils.isNotBlank(plntCd)) {
            String queryString = QueryHelper.buildCriteria(IConstant.MATL_MFG_RTNG.SRCSYSCD).is(srcSysCd)
                    .and(IConstant.MATL_MFG_RTNG.MATLNUM).is(matlNum)
                    .and(IConstant.MATL_MFG_RTNG.PLNTCD).is(plntCd).toQueryString();
            return queryForList(IConstant.REGION.MATL_MFG_RTNG, queryString, EDMMatlMfgRtngEntity.class);
        }
        return null;
    }
}
